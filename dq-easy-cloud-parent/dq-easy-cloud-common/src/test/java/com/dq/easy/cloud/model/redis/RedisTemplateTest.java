package com.dq.easy.cloud.model.redis;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dq.easy.cloud.DqEasyCloudModelApplication;
import com.dq.easy.cloud.model.user.entity.UserEntity;
import com.dq.easy.cloud.module.basic.utils.DqBaseUtils;
import com.dq.easy.cloud.module.cache.redis.handler.DqRedisTemplateHandler;
import com.dq.easy.cloud.module.common.generator.primarykey.pojo.bo.DqSnowflakeIdWorkerBO;
import com.dq.easy.cloud.module.common.json.utils.DqJSONUtils;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DqEasyCloudModelApplication.class) // 指定spring-boot的启动类
public class RedisTemplateTest {
	private DqSnowflakeIdWorkerBO snowflakeIdWorker= DqSnowflakeIdWorkerBO.singleInstance(0, 0);
	
	@Test
	public void testSet(){
		List<UserEntity> lists = new ArrayList<>();
		for(int i = 0 ; i < 3;++i){
			UserEntity userEntity = new UserEntity();
			userEntity.setCreateBy(11111L);
			userEntity.setCreateDate(new Date());
			userEntity.setPassword("passwoearew");
			userEntity.setId(snowflakeIdWorker.nextId());
			lists.add(userEntity);
		}
		DqRedisTemplateHandler.set("user1List", lists);
		String value = DqRedisTemplateHandler.get("user1List",String.class);
		System.out.println("------------------" + value);
		List<UserEntity> list = DqJSONUtils.parseArray(value, UserEntity.class);
		System.out.println("===============" + list.size());
	}
	@Test
	public void testSetTimeout(){
		List<UserEntity> lists = new ArrayList<>();
		for(int i = 0 ; i < 3;++i){
			UserEntity userEntity = new UserEntity();
			userEntity.setCreateBy(11111L);
			userEntity.setCreateDate(new Date());
			userEntity.setPassword("passwoearew");
			userEntity.setId(snowflakeIdWorker.nextId());
			lists.add(userEntity);
		}
		DqRedisTemplateHandler.set("user1ListOffSet", lists);
		String value = DqRedisTemplateHandler.get("user1ListOffSet",String.class);
		System.out.println("------------------" + value);
		List<UserEntity> list = DqJSONUtils.parseArray(value, UserEntity.class);
		System.out.println("===============" + list.size());
	}
	@Test
	public void testMuileSet(){
		Map<String,Object> multiMap = new LinkedHashMap<>();
		List<String> keys = new ArrayList<>();
		for(int i = 0 ; i < 3;++i){
			UserEntity userEntity = new UserEntity();
			userEntity.setId(snowflakeIdWorker.nextId());
			userEntity.setCreateBy(userEntity.getId());
			userEntity.setCreateDate(new Date());
			userEntity.setPassword("password" + i);
			multiMap.put(Long.toString(userEntity.getId()), userEntity);
			keys.add(Long.toString(userEntity.getId()));
		}
		DqRedisTemplateHandler.multiSet(multiMap);
		
		List<UserEntity> retList = DqRedisTemplateHandler.multiGet(keys, UserEntity.class);
		for(UserEntity userEntity : retList){
			System.out.println("------------" + userEntity.getId());
		}
	}
	@Test
	public void testleftPush(){
		List<UserEntity> list = new ArrayList<>();
		List<String> keys = new ArrayList<>();
		for(int i = 0 ; i < 3;++i){
			UserEntity userEntity = new UserEntity();
			userEntity.setId(snowflakeIdWorker.nextId());
			userEntity.setCreateBy(userEntity.getId());
			userEntity.setCreateDate(new Date());
			userEntity.setPassword("password" + i);
			keys.add("leftPush:"+userEntity.getId());
			DqRedisTemplateHandler.leftPush("leftPush", userEntity);;
		}
		List<UserEntity> retList = DqRedisTemplateHandler.rangeAll("leftPush", UserEntity.class);
		for(UserEntity userEntity : retList){
			System.out.println("------------" + userEntity.getId());
		}
	}
	@Test
	public void testleftPushAll(){
		List<UserEntity> list = new ArrayList<>();
		List<String> keys = new ArrayList<>();
		UserEntity [] userEntities = new UserEntity[3];
		for(int i = 0 ; i < 3;++i){
			UserEntity userEntity = new UserEntity();
			userEntity.setId(snowflakeIdWorker.nextId());
			userEntity.setCreateBy(userEntity.getId());
			userEntity.setCreateDate(new Date());
			userEntity.setPassword("password" + i);
			keys.add("leftPush:"+userEntity.getId());
			list.add(userEntity);
			userEntities[i] = userEntity;
		}
		DqRedisTemplateHandler.leftPush("leftPushAll", list);;
		List<UserEntity> retList = DqRedisTemplateHandler.rangeAll("leftPushAll", UserEntity.class);
		for(UserEntity userEntity : retList){
			System.out.println("------------" + userEntity.getId());
		}
	}
	@Test
	public void testRightPushAll(){
		List<UserEntity> list = new ArrayList<>();
		List<String> keys = new ArrayList<>();
		UserEntity [] userEntities = new UserEntity[3];
		for(int i = 0 ; i < 3;++i){
			UserEntity userEntity = new UserEntity();
			userEntity.setId(snowflakeIdWorker.nextId());
			userEntity.setCreateBy(userEntity.getId());
			userEntity.setCreateDate(new Date());
			userEntity.setPassword("password" + i);
			keys.add("rightPushAll:"+userEntity.getId());
			list.add(userEntity);
			userEntities[i] = userEntity;
		}
		DqRedisTemplateHandler.leftPush("rightPushAll", list);;
		List<UserEntity> retList = DqRedisTemplateHandler.rangeAll("rightPushAll", UserEntity.class);
		for(UserEntity userEntity : retList){
			System.out.println("------------" + userEntity.getId());
		}
	}
	@Test
	public void testIndexFromList(){
		UserEntity userEntity = DqRedisTemplateHandler.indexFromList("rightPushAll", 1, UserEntity.class);
		if(DqBaseUtils.isNotNull(userEntity)){
			System.out.println(userEntity.getId());
		}
	}
	@Test
	public void testIncrement(){
		DqRedisTemplateHandler.increment("increment1", 15);
	}
	@Test
	public void testSetSerializer(){
		UserEntity userEntity = new UserEntity();
		userEntity.setCreateBy(11111L);
		userEntity.setCreateDate(new Date());
		userEntity.setPassword("passwoearew");
		userEntity.setId(snowflakeIdWorker.nextId());
		DqRedisTemplateHandler.setSerializer("user1Serializer", userEntity);
		System.out.println("------------------" + DqRedisTemplateHandler.getSerializer("user", UserEntity.class));
	}
	
	@Test
	public void testDeleteHashKey(){
		String key = "";
		String hashKeys = "311";
		DqRedisTemplateHandler.deleteHashKey(key, hashKeys);
	}
	@Test
	public void testPutAllOfHash(){
		String key = "putAllOfHash";
		String hashKeys = "311";
		List<UserEntity> list = new ArrayList<>();
		List<String> keys = new ArrayList<>();
		Map<Object,Object> hashMap = new HashMap<>();
		UserEntity [] userEntities = new UserEntity[3];
		for(int i = 0 ; i < 3;++i){
			UserEntity userEntity = new UserEntity();
			userEntity.setId(snowflakeIdWorker.nextId());
			userEntity.setCreateBy(userEntity.getId());
			userEntity.setCreateDate(new Date());
			userEntity.setPassword("password" + i);
			hashMap.put(userEntity.getId(), userEntity);
		}
		DqRedisTemplateHandler.putAllOfHash(key, hashMap);
	}
	
	@Test
	public void testValuesOfMap(){
		String key = "putAllOfHash";
		List<HashMap> list = DqRedisTemplateHandler.valuesOfMap(key, HashMap.class);
		System.out.println(list.size());
	}
	
	@Test
	public void testEntriesOfMap(){
		String key = "putAllOfHash";
		Map<String, UserEntity> map = DqRedisTemplateHandler.entriesOfMap(key, UserEntity.class);
		System.out.println(map.keySet());
	}
	
}
