package com.sika.code.batch.standard.item.writer;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.core.text.StrPool;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.sika.code.core.util.BeanUtil;
import com.sika.code.batch.standard.bean.writer.RedisWriterBean;
import com.sika.code.batch.standard.builder.writerdata.BaseWriterDataBuilder;
import com.sika.code.batch.standard.constant.RedisStoreTypeEnum;
import lombok.Data;
import lombok.experimental.Accessors;
import org.assertj.core.util.Lists;
import org.springframework.batch.item.ItemWriter;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

/**
 * <pre>
 *  redis批量item写入器
 * </pre>
 *
 * @author sikadai
 * @version 1.0
 * @since 2022/6/25 13:37
 */
@Data
@Accessors(chain = true)
public class RedisBatchItemWriterSupport implements ItemWriter<Map<String, Object>> {
    private BaseWriterDataBuilder<Map<String, Object>> dataBuilder;
    private StringRedisTemplate stringRedisTemplate;
    private RedisWriterBean redisWriterBean;
    private Map<Integer, RedisAddData> redisAddDataMap = Maps.newConcurrentMap();

    @Override
    public void write(List<? extends Map<String, Object>> list) throws Exception {
        List<? extends Map<String, Object>> buildMap = dataBuilder.build(list);
        RedisAddData redisAddData = getRedisAddDataBean(redisWriterBean.getRedisStoreTypeEnum());
        redisAddData.setRedisWriterBean(redisWriterBean);
        redisAddData.setStringRedisTemplate(stringRedisTemplate);

        redisAddData.add(buildMap);
    }


    protected RedisAddData getRedisAddDataBean(RedisStoreTypeEnum redisStoreTypeEnum) {
        RedisAddData redisAddData = redisAddDataMap.get(redisStoreTypeEnum.getType());
        if (redisAddData != null) {
            return redisAddData;
        }
        Class<? extends RedisAddData> redisAddDataClass;
        if (RedisStoreTypeEnum.STRING.equals(redisStoreTypeEnum)) {
            redisAddDataClass = StringRedisAddData.class;
        } else if (RedisStoreTypeEnum.LIST.equals(redisStoreTypeEnum)) {
            redisAddDataClass = ListRedisAddData.class;
        } else if (RedisStoreTypeEnum.HASH.equals(redisStoreTypeEnum)) {
            redisAddDataClass = HashRedisAddData.class;
        } else {
            redisAddDataClass = SetRedisAddData.class;
        }
        redisAddData = BeanUtil.newInstance(redisAddDataClass);
        redisAddDataMap.put(redisStoreTypeEnum.getType(), redisAddData);
        return redisAddData;
    }

    @Data
    public static abstract class RedisAddData {
        protected StringRedisTemplate stringRedisTemplate;
        protected RedisWriterBean redisWriterBean;

        public abstract void add(List<? extends Map<String, Object>> maps);

        protected void expire(String redisKey) {
            if (redisWriterBean.getExpireSecond() != null && redisWriterBean.getExpireSecond() > 0) {
                stringRedisTemplate.expire(redisKey, redisWriterBean.getExpireSecond(), TimeUnit.SECONDS);
            }
        }

        protected String fullRedisKey(Object keySuffix) {
            if (ObjectUtil.isNotEmpty(keySuffix)) {
                return CharSequenceUtil.join(StrPool.COLON, redisWriterBean.getRedisKey(), keySuffix);
            } else {
                return redisWriterBean.getRedisKey();
            }
        }

        protected boolean needGroup() {
            return redisWriterBean.getGroupNum() != null && redisWriterBean.getGroupNum() > 0;
        }

        /**
         * <p>
         * 为了保证索引的随机性，长度建议使用2的整数幂
         * </p>
         *
         * @param key    : 编号
         * @param length : 长度
         * @return int
         * @author sikadai
         * @since 2022/6/26 11:36
         */
        private int computeIndex(Object key, int length) {
            int h = key.hashCode();
            return (h ^ (h >>> 16)) & (length - 1);
        }

        protected void addToListOrSet(List<? extends Map<String, Object>> maps) {
            if (needGroup()) {
                Map<Integer, List<String>> groupMapList = groupForIndex(maps);
                for (Map.Entry<Integer, List<String>> entry : groupMapList.entrySet()) {
                    doAddListOrSet(entry.getKey(), entry.getValue());
                }
            } else {
                doAddListOrSet(null, convertToJsonStr(maps));
            }
        }

        protected void doAddListOrSet(Object key, List<String> objects) {
            String fullKey = fullRedisKey(key);
            String[] objectArr = ArrayUtil.toArray(objects, String.class);
            if (this instanceof SetRedisAddData) {
                stringRedisTemplate.opsForSet().add(fullKey, objectArr);
            } else if (this instanceof ListRedisAddData) {
                stringRedisTemplate.opsForList().rightPushAll(fullKey, objectArr);
            }
            expire(fullKey);
        }

        /**
         * <p>
         * 根据索引进行分组
         * </p>
         *
         * @param mapLists
         * @return java.util.Map<java.lang.Integer, java.util.List < java.lang.String>>
         * @author sikadai
         * @since 2022/6/26 13:23
         */
        protected Map<Integer, List<String>> groupForIndex(List<? extends Map<String, Object>> mapLists) {
            Map<Integer, List<String>> groupMapList = new TreeMap<>();
            for (Map<String, Object> objectMap : mapLists) {
                Object key = objectMap.get(redisWriterBean.getKey());
                if (ObjectUtil.isEmpty(key)) {
                    throw new RuntimeException(StrUtil.format("分组KEY【{}】对应的值不能为空", redisWriterBean.getKey()));
                }
                int keyIndex = computeIndex(key, redisWriterBean.getGroupNum());
                List<String> listTempFromCache = groupMapList.get(keyIndex);
                if (listTempFromCache == null) {
                    listTempFromCache = Lists.newArrayList();
                }
                listTempFromCache.add(JSON.toJSONString(objectMap));
                groupMapList.put(keyIndex, listTempFromCache);
            }
            return groupMapList;
        }

        protected List<String> convertToJsonStr(List<? extends Map<String, Object>> mapLists) {
            List<String> list = Lists.newArrayList();
            mapLists.forEach(item -> list.add(JSON.toJSONString(item)));
            return list;
        }
    }

    public static class StringRedisAddData extends RedisAddData {

        @Override
        public void add(List<? extends Map<String, Object>> maps) {
            Map<String, String> hashMap = Maps.newLinkedHashMap();
            maps.forEach(item -> {
                String fullKey = fullRedisKey(MapUtil.getStr(item, redisWriterBean.getKey()));
                Assert.notBlank("KEY【{}】对应的值不能为空", redisWriterBean.getKey());
                hashMap.put(fullKey, JSON.toJSONString(item));
            });
            stringRedisTemplate.opsForValue().multiSet(hashMap);
            stringRedisTemplate.executePipelined((RedisCallback<Object>) connection -> {
                maps.forEach(item -> {
                    String fullKey = fullRedisKey(MapUtil.getStr(item, redisWriterBean.getKey()));
                    expire(fullKey);
                });
                return null;
            });
        }
    }

    public static class HashRedisAddData extends RedisAddData {

        @Override
        public void add(List<? extends Map<String, Object>> maps) {
            String redisKey = redisWriterBean.getRedisKey();
            Map<String, String> hashMap = Maps.newLinkedHashMap();
            maps.forEach(item -> {
                String hashKey = MapUtil.getStr(item, redisWriterBean.getKey());
                Assert.notBlank("KEY【{}】对应的值不能为空", redisWriterBean.getKey());
                hashMap.put(hashKey, JSON.toJSONString(item));
            });
            stringRedisTemplate.opsForHash().putAll(redisKey, hashMap);
            expire(redisKey);
        }
    }

    public static class ListRedisAddData extends RedisAddData {
        @Override
        public void add(List<? extends Map<String, Object>> maps) {
            addToListOrSet(maps);
        }
    }

    public static class SetRedisAddData extends RedisAddData {
        @Override
        public void add(List<? extends Map<String, Object>> maps) {
            addToListOrSet(maps);
        }
    }

}
