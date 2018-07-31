package com.easy.cloud.core.cache.redis.handler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.context.annotation.Lazy;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import com.easy.cloud.core.basic.utils.EcBaseUtils;
import com.easy.cloud.core.cache.redis.constant.EcRedisConstant.EcRedisTemplateName;
import com.easy.cloud.core.common.collections.utils.EcCollectionsUtils;
import com.easy.cloud.core.common.json.utils.EcJSONUtils;
import com.easy.cloud.core.common.map.utils.EcMapUtils;
import com.easy.cloud.core.common.string.utils.EcStringUtils;

import redis.clients.jedis.Jedis;

/**
 * @author daiqi
 * @ClassName : DqRedisTemplateHandler
 * @Description : redis模板助手类
 * @date 2017年12月7日 上午10:33:04
 */
@Component
public class EcRedisTemplateHandler {
    /**
     * 保存到redis中为普通的字符串
     */
    private static StringRedisTemplate stringRedisTemplate;
    /**
     * 保存到redis中的值为序列化对象字符串
     */
    private static RedisTemplate<String, Object> redisTemlateValueSerializer;

    @Resource(name = EcRedisTemplateName.REDIS_TEMPLATE_VALUE_SERIALIZER_NAME)
    public void setRedisTemlateValueSerializer(RedisTemplate<String, Object> redisTemlateValueSerializer) {
        EcRedisTemplateHandler.redisTemlateValueSerializer = redisTemlateValueSerializer;
    }

    @Resource(name = EcRedisTemplateName.REDIS_TEMPLATE_VALUE_STR_NAME)
    public void setStringRedisTemplate(StringRedisTemplate stringRedisTemplate) {
        EcRedisTemplateHandler.stringRedisTemplate = stringRedisTemplate;
    }

    /**
     * <p>
     * 删除key列表
     * </p>
     *
     * @param keys
     * @author daiqi
     * @date 2017年12月13日 下午12:56:01
     */
    public static void delete(Collection<String> keys) {
        if (EcCollectionsUtils.isEmpty(keys)) {
            return;
        }
        stringRedisTemplate.delete(keys);
    }

    /**
     * <p>
     * 删除key
     * </p>
     * <p>
     * <pre>
     * </pre>
     *
     * @param key
     * @author daiqi
     * @date 2017年12月13日 下午12:54:56
     */
    public static void delete(String key) {
        if (EcStringUtils.isEmpty(key)) {
            return;
        }
        stringRedisTemplate.delete(key);
    }

    /**
     * 获取redis中的字符串对象
     * <p>
     * 根据key获取值为普通字符串的值，并转换为clazz对应类型的对象
     * </p>
     * <p>
     * 如果获取的对象为json数组类型的字符串，则应先获取clazz为String.class对象，再使用DqJSONUtils.parseArray(
     * )方法转化为对应的list
     * </p>
     * <p>
     * <pre>
     * DqRedisTemplateHandler.get("userKey",UserEntity.class) = UserEntityObj
     * DqRedisTemplateHandler.get("userKey",String.class) = StringObj
     * DqRedisTemplateHandler.get("userKey",HashMap.class) = HashMapObj
     * 若为json数组字符串
     * DqRedisTemplateHandler.get("userKey",String.class) = JsonArrayStr
     * DqJSONUtils.parseArray(jsonArrayStr,UserEntity.class) = UserEntityList
     * </pre>
     *
     * @param key   : 主键
     * @param clazz : Class : 泛型class,这个class不能为抽象类和接口的class
     * @return
     * @author daiqi
     * @date 2017年12月7日 下午5:57:53
     */
    public static <T> T get(String key, Class<T> clazz) {
        return EcJSONUtils.parseObject(stringRedisTemplate.opsForValue().get(key), clazz);
    }

    /**
     * <p>
     * 将obj保存到redis中的字符串对象
     * </p>
     *
     * @param key : 主键
     * @param obj : 保存的obj对象
     * @return
     * @author daiqi
     * @date 2017年12月7日 下午5:57:53
     */
    public static void set(String key, Object obj) {
        stringRedisTemplate.opsForValue().set(key, EcJSONUtils.toJSONString(obj));
    }

    /**
     * <p>
     * 将obj保存到redis中的字符串对象，并设置一个有效时间
     * </p>
     * <p>
     * <pre>
     * </pre>
     *
     * @param key     : String : 主键
     * @param obj     : Object : 保存的obj对象
     * @param timeOut : Long 有效时间 单位毫秒
     * @author daiqi
     * @date 2017年12月7日 下午6:26:10
     */
    public static void set(String key, Object obj, long timeOut) {
        stringRedisTemplate.opsForValue().set(key, EcJSONUtils.toJSONString(obj), timeOut, TimeUnit.MILLISECONDS);
    }

    /**
     * <p>
     * 将obj保存到redis中的字符串对象
     * </p>
     * <p>
     * <pre>
     * </pre>
     *
     * @param key : String : 主键
     * @param obj : Object : 保存的obj对象
     * @author daiqi
     * @date 2017年12月7日 下午6:26:10
     */
    public static boolean setIfAbsent(String key, Object obj) {
        return stringRedisTemplate.opsForValue().setIfAbsent(key, EcJSONUtils.toJSONString(obj));
    }

    /**
     * <p>
     * 同时设置多个对象到reidis中
     * </p>
     * <p>
     * <pre>
     * DqRedisTemplateHandler.multiSet(multiMap)
     * </pre>
     *
     * @param multiMap : Map<String,Object> : 设置对象的集合
     * @author daiqi
     * @date 2017年12月11日 下午1:44:22
     */
    public static void multiSet(Map<String, Object> multiMap) {
        if (EcMapUtils.isEmpty(multiMap)) {
            return;
        }
        Map<String, String> insertMultiMap = EcMapUtils.newHashMap();

        for (String key : multiMap.keySet()) {
            insertMultiMap.put(key, EcJSONUtils.toJSONString(multiMap.get(key)));
        }
        stringRedisTemplate.opsForValue().multiSet(insertMultiMap);
    }

    /**
     * <p>
     * 根据keys列表批量获取泛型数据列表
     * </p>
     * <p>
     * <pre>
     * DqRedisTemplateHandler.multiGet(keys, UserEntity.class)
     * </pre>
     *
     * @param keys  : List<String> : key列表
     * @param clazz : Class<T> : 泛型class
     * @return List<T> : key对应的值
     * @author daiqi
     * @date 2017年12月11日 下午1:52:11
     */
    public static <T> List<T> multiGet(List<String> keys, Class<T> clazz) {
        if (EcCollectionsUtils.isEmpty(keys) || EcBaseUtils.isNull(clazz)) {
            return null;
        }
        return EcJSONUtils.fromListToTList(stringRedisTemplate.opsForValue().multiGet(keys), clazz);
    }

    /**
     * <p>
     * 自增操作,如果不存在key则创建key并且其值初始化为value，否则在原来的value上加上value
     * </p>
     * <p>
     * <pre>
     * DqRedisTemplateHandler.increment("incrementKey", 2) = 2
     * </pre>
     *
     * @param key   : String : 主key
     * @param value : long : 设置的值
     * @return 已经自增value的值
     * @author daiqi
     * @date 2017年12月11日 下午4:13:29
     */
    public static long increment(String key, long value) {
        return stringRedisTemplate.opsForValue().increment(key, value);
    }

    /**
     * <p>
     * 自增操作,如果不存在key则创建key并且其值初始化为value，否则在原来的value上加上value
     * </p>
     * <p>
     * <pre>
     * DqRedisTemplateHandler.increment("incrementKey", 2.0) = 2.0
     * </pre>
     *
     * @param key   : String : 主key
     * @param value : double : 设置的值
     * @return 已经自增value的值
     * @author daiqi
     * @date 2017年12月11日 下午4:13:29
     */
    public static double increment(String key, double value) {
        return stringRedisTemplate.opsForValue().increment(key, value);
    }

    /**
     * <p>
     * 根据key获取字符串redis中字符串长度
     * </p>
     * <p>
     * <pre>
     * DqRedisTemplateHandler.size("stringKey") = 2
     * </pre>
     *
     * @param key : String : 主key
     * @return 指定key的字符串长度
     * @author daiqi
     * @date 2017年12月11日 下午4:27:13
     */
    public static long size(String key) {
        return stringRedisTemplate.opsForValue().size(key);
    }

    /**
     * <p>
     * 获取指定key的redis列表所有数据
     * </p>
     * <p>
     * <pre>
     * DqRedisTemplateHandler.rangeAll("listKey", 0, 10, UserEntity.class)
     * </pre>
     *
     * @param key   : String : 主key
     * @param clazz : 泛型列表的class
     * @return 返回泛型列表
     * @author daiqi
     * @date 2017年12月13日 上午11:03:49
     */
    public static <T> List<T> rangeAll(String key, Class<T> clazz) {
        return range(key, 0, -1, clazz);
    }

    /**
     * <p>
     * 根据key获取redis列表中指定范围内的泛型列表数据
     * </p>
     * <p>
     * <pre>
     * DqRedisTemplateHandler.range("listKey", 0, 10, UserEntity.class)
     * </pre>
     *
     * @param key   : String : 主key
     * @param start : int : 开始坐标
     * @param end   : int : 结束坐标
     * @param clazz : 泛型列表的class
     * @return 返回泛型列表
     * @author daiqi
     * @date 2017年12月11日 下午4:40:38
     */
    public static <T> List<T> range(String key, int start, int end, Class<T> clazz) {
        if (EcStringUtils.isEmpty(key) || EcBaseUtils.isNull(clazz)) {
            return null;
        }
        return EcJSONUtils.fromListToTList(stringRedisTemplate.opsForList().range(key, start, end), clazz);
    }

    /**
     * <p>
     * 将value从左边放入redis列表中,若obj为Collection子类则将循环放入到list中
     * </p>
     * <p>
     * <pre>
     * DqRedisTemplateHandler.leftPush("listKey", value)
     * </pre>
     *
     * @param key   : String : 主key
     * @param value : Object : 任意对象
     * @author daiqi
     * @date 2017年12月11日 下午5:49:20
     */
    public static void leftPush(String key, Object value) {
        if (EcStringUtils.isEmpty(key) || EcBaseUtils.isNull(value)) {
            return;
        }
        if (value instanceof Collection || value instanceof Object[]) {
            stringRedisTemplate.opsForList().leftPushAll(key, getCollectionByObj(value));
        } else {
            stringRedisTemplate.opsForList().leftPush(key, EcJSONUtils.toJSONString(value));
        }
    }

    /**
     * <p>
     * 将value从右边放入redis列表中,若obj为Collection子类则将循环放入到list中
     * </p>
     * <p>
     * <pre>
     * DqRedisTemplateHandler.rightPush("listKey", value)
     * </pre>
     *
     * @param key   : String : 主key
     * @param value : Object : 任意对象
     * @author daiqi
     * @date 2017年12月11日 下午5:49:20
     */
    public static void rightPush(String key, Object value) {
        if (EcStringUtils.isEmpty(key) || EcBaseUtils.isNull(value)) {
            return;
        }
        if (value instanceof Collection || value instanceof Object[]) {
            stringRedisTemplate.opsForList().rightPushAll(key, getCollectionByObj(value));
        } else {
            stringRedisTemplate.opsForList().rightPush(key, EcJSONUtils.toJSONString(value));
        }
    }

    /**
     * <p>
     * 将value设置到redis指定list的index位置
     * </p>
     * <p>
     * <pre>
     * DqRedisTemplateHandler.setToList("listKey", 1, value)
     * </pre>
     *
     * @param key   : String : 主key
     * @param index : long : 下标
     * @param value : Object : 任意对象
     * @author daiqi
     * @date 2017年12月13日 上午11:42:04
     */
    public static void setToList(String key, long index, Object value) {
        if (EcStringUtils.isEmpty(key) || EcBaseUtils.isNull(value)) {
            return;
        }
        stringRedisTemplate.opsForList().set(key, index, EcJSONUtils.toJSONString(value));
    }

    /**
     * <p>
     * 根据下标获取指定列表中的泛型对象
     * </p>
     * <p>
     * <pre>
     * DqRedisTemplateHandler.indexFromList("listKey", 1, UserEntity.class)
     * </pre>
     *
     * @param key   : String : 主key
     * @param index : long : 下标
     * @param clazz : 泛型的class
     * @return 泛型对象
     * @author daiqi
     * @date 2017年12月13日 上午11:47:11
     */
    public static <T> T indexFromList(String key, long index, Class<T> clazz) {
        if (EcStringUtils.isEmpty(key) || index < 0 || EcBaseUtils.isNull(clazz)) {
            return null;
        }
        return EcJSONUtils.parseObject(stringRedisTemplate.opsForList().index(key, index), clazz);
    }

    /**
     * <p>
     * 返回并移除redis列表左边的第一个元素
     * </p>
     * <p>
     * <pre>
     * </pre>
     *
     * @param key
     * @param clazz
     * @return
     * @author daiqi
     * @date 2017年12月13日 下午12:57:55
     */
    public static <T> T leftPop(String key, Class<T> clazz) {
        return EcJSONUtils.parseObject(stringRedisTemplate.opsForList().leftPop(key), clazz);
    }

    /**
     * <p>
     * 返回并移除redis列表右边的第一个元素
     * </p>
     * <p>
     * <pre>
     * </pre>
     *
     * @param key
     * @param clazz
     * @return
     * @author daiqi
     * @date 2017年12月13日 下午12:57:55
     */
    public static <T> T rightPop(String key, Class<T> clazz) {
        return EcJSONUtils.parseObject(stringRedisTemplate.opsForList().rightPop(key), clazz);
    }

    /**
     * <p>
     * 移除sourceKey对应列表的最后一个元素，并将其插入到destinationKey对应的列表的第一个位置中，同时返回该元素
     * </p>
     * <p>
     * <pre>
     * DqRedisTemplateHandler.rightPopAndLeftPush("sourceKey", "destinationKey")
     * </pre>
     *
     * @param sourceKey      : 源列表key
     * @param destinationKey : 目标列表key
     * @param clazz          : 泛型class
     * @return 移动的元素
     * @author daiqi
     * @date 2017年12月13日 下午1:44:45
     */
    public static <T> T rightPopAndLeftPush(String sourceKey, String destinationKey, Class<T> clazz) {
        if (EcStringUtils.isEmpty(sourceKey) || EcStringUtils.isEmpty(destinationKey)) {
            return null;
        }
        return EcJSONUtils.parseObject(stringRedisTemplate.opsForList().rightPopAndLeftPush(sourceKey, destinationKey),
                clazz);
    }

    /**
     * -------------------------------------map---------------------------------
     * --
     */
    /**
     * <p>
     * 删除给定的哈希hashKeys 返回删除的条数
     * </p>
     * <p>
     * <pre>
     * DqRedisTemplateHandler.deleteHashKey("redisKey", "hashKey1", "hashKey2") = 2
     * </pre>
     *
     * @param key      : String : redis的主key
     * @param hashKeys : Object[] : 删除的hashKey列表
     * @return 删除的条数
     * @author daiqi
     * @date 2017年12月13日 下午1:53:45
     */
    public static long deleteHashKey(String key, Object... hashKeys) {
        if (EcStringUtils.isEmpty(key) || EcBaseUtils.isNull(hashKeys) || hashKeys.length == 0) {
            return 0;
        }
        return stringRedisTemplate.opsForHash().delete(key, hashKeys);
    }

    /**
     * <p>
     * 确认hash中是有有hashKey
     * </p>
     * <p>
     * <pre>
     * DqRedisTemplateHandler.hasKeyFromHash("key", "hashKey") = true
     * </pre>
     *
     * @param key     : String : redis的主key
     * @param hashKey : Object : hashKey
     * @return 存在返回true 不存在返回false
     * @author daiqi
     * @date 2017年12月13日 下午1:59:43
     */
    public static Boolean hasKeyFromHash(String key, Object hashKey) {
        if (EcStringUtils.isEmpty(key) || EcBaseUtils.isNull(hashKey)) {
            return false;
        }
        return stringRedisTemplate.opsForHash().hasKey(key, hashKey);
    }

    /**
     * <p>
     * 从hash中获取泛型数据
     * </p>
     * <p>
     * <pre>
     * DqRedisTemplateHandler.getFromHash("key", "hashKey", UserEntity.class)
     * </pre>
     *
     * @param key     : String : 主key
     * @param hashKey : String : hashKey
     * @param clazz   : 泛型class
     * @return 获取到的泛型对象
     * @author daiqi
     * @date 2017年12月13日 下午2:03:03
     */
    public static <T> T getFromHash(String key, Object hashKey, Class<T> clazz) {
        if (EcStringUtils.isEmpty(key) || EcBaseUtils.isNull(hashKey) || EcBaseUtils.isNull(clazz)) {
            return null;
        }
        return EcJSONUtils.parseObject(stringRedisTemplate.opsForHash().get(key, String.valueOf(hashKey)), clazz);
    }

    /**
     * <p>
     * 根据hashKey的列表从hash中获取相应泛型对象
     * </p>
     * <p>
     * <pre>
     * DqRedisTemplateHandler.multiGetFromHash("key", hashKeys, UserEntity.class)
     * </pre>
     *
     * @param key      : String : 主key
     * @param hashKeys : List<String> : hashKey列表
     * @param clazz    : 泛型class
     * @return 泛型对象列表
     * @author daiqi
     * @date 2017年12月13日 下午2:05:53
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public static <T> List<T> multiGetFromHash(String key, Collection<Object> hashKeys, Class<T> clazz) {
        if (EcStringUtils.isEmpty(key) || EcCollectionsUtils.isEmpty(hashKeys) || EcBaseUtils.isNull(clazz)) {
            return null;
        }
        Collection hashKeysStr = EcJSONUtils.fromListToTList(hashKeys, String.class);
        return EcJSONUtils.fromListToTList(stringRedisTemplate.opsForHash().multiGet(key, hashKeysStr), clazz);
    }

    /**
     * <p>
     * 通过给定的delta增加散列hashKey的值（整型）
     * </p>
     * <p>
     * <pre>
     * DqRedisTemplateHandler.incrementOfHash("key", "hashKey", 2) = 2
     * </pre>
     *
     * @param key     : String : 主key
     * @param hashKey : String : hashKey
     * @param delta   : Long : 递增的值
     * @return 递增后的值
     * @author daiqi
     * @date 2017年12月13日 下午2:10:23
     */
    public Long incrementOfHash(String key, Object hashKey, long delta) {
        if (EcStringUtils.isEmpty(key) || EcBaseUtils.isNull(hashKey)) {
            return null;
        }
        return stringRedisTemplate.opsForHash().increment(key, String.valueOf(hashKey), delta);
    }

    /**
     * <p>
     * 通过给定的delta增加散列hashKey的值（浮点型）
     * </p>
     * <p>
     * <pre>
     * DqRedisTemplateHandler.incrementOfHash("key", "hashKey", 2.0) = 2.0
     * </pre>
     *
     * @param key     : String : 主key
     * @param hashKey : String : hashKey
     * @param delta   : double : 递增的值
     * @return 递增后的值
     * @author daiqi
     * @date 2017年12月13日 下午2:10:23
     */
    public Double incrementOfHash(String key, Object hashKey, double delta) {
        if (EcStringUtils.isEmpty(key) || EcBaseUtils.isNull(hashKey)) {
            return null;
        }
        return stringRedisTemplate.opsForHash().increment(key, String.valueOf(hashKey), delta);
    }

    /**
     * <p>
     * 获取hash中保存的所有hashKey
     * </p>
     * <p>
     * <pre>
     * DqRedisTemplateHandler.keysFromHash("key") = ["hashKey1", "hashKey2"]
     * </pre>
     *
     * @param key : String : 主key
     * @return hashKey的set集合
     * @author daiqi
     * @date 2017年12月13日 下午2:14:45
     */
    public static Set<Object> keysFromHash(String key) {
        if (EcStringUtils.isEmpty(key)) {
            return null;
        }
        return stringRedisTemplate.opsForHash().keys(key);
    }

    /**
     * <p>
     * 获取hash的保存的元素个数
     * </p>
     * <p>
     * <pre>
     * DqRedisTemplateHandler.sizeFromHash("key") = 3
     * </pre>
     *
     * @param key : String : 主key
     * @return 该hash的元素个数
     * @author daiqi
     * @date 2017年12月13日 下午2:14:45
     */
    public static long sizeFromHash(String key) {
        if (EcStringUtils.isEmpty(key)) {
            return 0;
        }
        return stringRedisTemplate.opsForHash().size(key);
    }

    /**
     * <p>
     * 将hashMap中所有数据放入hash中
     * </p>
     * <p>
     * <pre>
     * DqRedisTemplateHandler.putAllToHash("key", map)
     * </pre>
     *
     * @param key     : String : 主key
     * @param hashMap : Map<String,Object> : hashMap
     * @author daiqi
     * @date 2017年12月13日 下午2:40:47
     */
    public static void putAllToHash(String key, Map<Object, Object> hashMap) {
        if (EcStringUtils.isEmpty(key) || EcMapUtils.isEmpty(hashMap)) {
            return;
        }
        Map<String, Object> putHashMap = EcMapUtils.newHashMap();
        for (Object hashKey : hashMap.keySet()) {
            putHashMap.put(String.valueOf(hashKey), EcJSONUtils.toJSONString(hashMap.get(hashKey)));
        }
        stringRedisTemplate.opsForHash().putAll(key, putHashMap);
    }

    /**
     * <p>
     * 将hashKey和value放入hash中
     * </p>
     * <p>
     * <pre>
     * DqRedisTemplateHandler.putOfHash("key", "hashKey", value)
     * </pre>
     *
     * @param key   : String : 主key
     * @param value : Object : 任意对象
     * @author daiqi
     * @date 2017年12月13日 下午2:40:47
     */
    public static void putToHash(String key, String hashKey, Object value) {
        if (EcStringUtils.isEmpty(key) || EcStringUtils.isEmpty(hashKey) || EcBaseUtils.isNull(value)) {
            return;
        }
        stringRedisTemplate.opsForHash().put(key, hashKey, EcJSONUtils.toJSONString(value));
    }

    /**
     * <p>
     * 获取key对应的redis中该map的所有值
     * </p>
     * <p>
     * <pre>
     * DqRedisTemplateHandler.valuesFromHash("key", UserEntity.class) = ["userObj1","userObj2","userObj3"]
     * </pre>
     *
     * @param key   : 主key
     * @param clazz : 泛型后的class
     * @return List<T>
     * @author daiqi
     * @date 2017年12月19日 上午9:08:27
     */
    public static <T> List<T> valuesFromHash(String key, Class<T> clazz) {
        if (EcStringUtils.isEmpty(key) || EcBaseUtils.isNull(clazz)) {
            return null;
        }
        return EcJSONUtils.fromListToTList(stringRedisTemplate.opsForHash().values(key), clazz);
    }

    /**
     * <p>
     * 获取key对应的整个哈希存储
     * </p>
     * <p>
     * <pre>
     * DqRedisTemplateHandler.valuesFromHash("key", UserEntity.class) = {"hashKey1", userObj1},{"hashKey2", userObj2},{"hashKey3", userObj3}
     * </pre>
     *
     * @param key   : redis主key
     * @param clazz : Class<T> : 泛型class
     * @return Map<String,T>
     * @author daiqi
     * @date 2017年12月19日 上午9:18:43
     */
    @SuppressWarnings("unchecked")
    public static <T> Map<String, T> entriesFromHash(String key, Class<T> clazz) {
        if (EcStringUtils.isEmpty(key) || EcBaseUtils.isNull(clazz)) {
            return null;
        }
        return EcJSONUtils.parseObject(stringRedisTemplate.opsForHash().entries(key), HashMap.class);
    }

    /** -----------------------------set----------------------------------------- */

    /**
     * <p>
     * 将value放到redis的set中
     * </p>
     * <p>
     * <pre>
     * </pre>
     *
     * @param key
     * @param value
     * @return
     * @author daiqi
     * @date 2017年12月19日 上午10:02:14
     */
    public static long addOfSet(String key, Object value) {
        if (EcStringUtils.isEmpty(key) || EcBaseUtils.isNull(value)) {
            return 0;
        }
        return stringRedisTemplate.opsForSet().add(key, EcJSONUtils.toJSONString(value));
    }

    /**
     * <p>
     * 批量将数据放入set中
     * </p>
     * <p>
     * <pre>
     * </pre>
     *
     * @param key
     * @param values
     * @return
     * @author daiqi
     * @date 2017年12月19日 上午10:15:22
     */
    public static long addOfSet(String key, List<Object> values) {
        String[] putValues = (String[]) EcJSONUtils.fromListToTList(values, String.class).toArray();
        return stringRedisTemplate.opsForSet().add(key, putValues);
    }

    /**
     * <p>
     * 从set中移除needRemoveObj的对象
     * </p>
     * <p>
     * <pre>
     * </pre>
     *
     * @param key            : String : 主key
     * @param needRemoveObjs : List<Object> : 需要移除的对象列表
     * @return 移除元素的数量
     * @author daiqi
     * @date 2017年12月19日 上午9:32:16
     */
    public static long removeOfSet(String key, List<Object> needRemoveObjs) {
        if (EcStringUtils.isEmpty(key) || EcCollectionsUtils.isEmpty(needRemoveObjs)) {
            return 0;
        }
        List<String> removeListStr = EcJSONUtils.fromListToTList(needRemoveObjs, String.class);
        return stringRedisTemplate.opsForSet().remove(key, removeListStr.toArray());
    }

    /**
     * 使用jedis管道测试用例
     */
    public void pipelineSample() {
        // pipeline
        RedisCallback<Object> pipelineCallback = new RedisCallback<Object>() {
            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                Jedis jedis = (Jedis) connection.getNativeConnection();
                jedis.pipelined().lpush("", "");
                return null;
            }
        };
        stringRedisTemplate.execute(pipelineCallback);
    }

    public static <T> T execute(RedisCallback<T> redisCallback) {
        return stringRedisTemplate.execute(redisCallback);
    }

    /**
     * 使用jedis管道测试用例
     */
    public static Jedis getJedis() {
        return (Jedis) stringRedisTemplate.getConnectionFactory().getConnection().getNativeConnection();
    }

    /**
     * <p>
     * 移除set中的一个随机元素，并返回该元素
     * </p>
     * <p>
     * <pre>
     * </pre>
     *
     * @param key
     * @param clazz
     * @return
     * @author daiqi
     * @date 2017年12月19日 上午9:37:09
     */
    public static <T> T popOfSet(String key, Class<T> clazz) {
        if (EcStringUtils.isEmpty(key) || EcBaseUtils.isNull(clazz)) {
            return null;
        }
        return EcJSONUtils.parseObject(stringRedisTemplate.opsForSet().pop(key), clazz);
    }

    /**
     * <p>
     * 获取set的集合数量
     * </p>
     * <p>
     * <pre>
     * </pre>
     *
     * @param key
     * @return
     * @author daiqi
     * @date 2017年12月19日 上午9:42:22
     */
    public static long sizeOfSet(String key) {
        if (EcStringUtils.isEmpty(key)) {
            return 0;
        }
        return stringRedisTemplate.opsForSet().size(key);
    }

    /**
     * <p>
     * 判断obj是否是该set的成员
     * </p>
     * <p>
     * <pre>
     * </pre>
     *
     * @param key
     * @param obj
     * @return boolean : 是返回true否则返回false
     * @author daiqi
     * @date 2017年12月19日 上午9:43:20
     */
    public static Boolean isMemberOfSet(String key, Object obj) {
        if (EcStringUtils.isEmpty(key) || EcBaseUtils.isNull(obj)) {
            return false;
        }
        return stringRedisTemplate.opsForSet().isMember(key, EcJSONUtils.toJSONString(obj));
    }

    /**
     * <p>
     * key对应的无序集合和otherKey对应的无序集合的交集
     * </p>
     * <p>
     * <pre>
     * DqRedisTemplateHandler.intersectOfSet() = [];
     * </pre>
     *
     * @param key      : redisKey
     * @param otherKey : 另一个set的rediskey
     * @param clazz    : 泛型class
     * @return List<T>
     * @author daiqi
     * @date 2017年12月19日 上午9:46:42
     */
    public static <T> List<T> intersectOfSet(String key, String otherKey, Class<T> clazz) {
        if (EcStringUtils.isEmpty(key) || EcStringUtils.isEmpty(otherKey) || EcBaseUtils.isNull(clazz)) {
            return null;
        }
        return EcJSONUtils.fromListToTList(stringRedisTemplate.opsForSet().intersect(key, otherKey), clazz);
    }

    /**
     * <p>
     * 获取set的所有成员
     * </p>
     * <p>
     * <pre>
     * DqRedisTemplateHandler.membersOfSet() = []
     * </pre>
     *
     * @param key   : redis主key
     * @param clazz : 泛型class
     * @return List<T>
     * @author daiqi
     * @date 2017年12月19日 上午9:53:39
     */
    public static <T> List<T> membersOfSet(String key, Class<T> clazz) {
        if (EcStringUtils.isEmpty(key) || EcBaseUtils.isNull(clazz)) {
            return null;
        }
        return EcJSONUtils.fromListToTList(stringRedisTemplate.opsForSet().members(key), clazz);
    }

    /**
     * <p>
     * 从set中随机获取一个元素
     * </p>
     * <p>
     * <pre>
     * </pre>
     *
     * @param key
     * @param clazz
     * @return
     * @author daiqi
     * @date 2017年12月19日 上午9:58:32
     */
    public static <T> T randomMemberOfSet(String key, Class<T> clazz) {
        if (EcStringUtils.isEmpty(key) || EcBaseUtils.isNull(clazz)) {
            return null;
        }
        return EcJSONUtils.parseObject(stringRedisTemplate.opsForSet().randomMember(key), clazz);
    }

    /**
     * ----------------------------zset-----------------------------
     */

    public static void setSerializer(String key, Object obj) {
        redisTemlateValueSerializer.opsForValue().set(key, EcJSONUtils.toJSONString(obj));
    }

    public static <T> T getSerializer(String key, Class<T> clazz) {
        return EcJSONUtils.parseObject(redisTemlateValueSerializer.opsForValue().get(key), clazz);
    }

    /**
     * <p>
     * 根据obj获取Collection<String>对象
     * </p>
     * <p>
     * <pre>
     * getCollectionByObj(obj)
     * </pre>
     *
     * @param obj
     * @return 如果obj为Collection的子类或者Object[]的子类则返回Collection<String>类型,否则返回null
     * @author daiqi
     * @date 2017年12月13日 上午11:32:13
     */
    private static Collection<String> getCollectionByObj(Object obj) {
        try {
            Collection<?> listValue = null;
            if (obj instanceof Collection) {
                listValue = (Collection<?>) obj;
            } else {
                List<Object> tempList = new ArrayList<>();
                Object[] valueArray = (Object[]) obj;
                for (Object temObj : valueArray) {
                    tempList.add(temObj);
                }
                listValue = tempList;
            }
            return EcJSONUtils.fromListToTList(listValue, String.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
