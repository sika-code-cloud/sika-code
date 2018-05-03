package com.easy.cloud.core.redis;

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

import com.easy.cloud.EcCoreApplication;
import com.easy.cloud.core.basic.utils.EcBaseUtils;
import com.easy.cloud.core.cache.redis.handler.EcRedisTemplateHandler;
import com.easy.cloud.core.common.json.utils.EcJSONUtils;
import com.easy.cloud.core.user.entity.UserEntity;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = EcCoreApplication.class) // 指定spring-boot的启动类
public class RedisTemplateTest {
	
	@Test
	public void testSet(){
		List<UserEntity> lists = new ArrayList<>();
		for(int i = 0 ; i < 3;++i){
			UserEntity userEntity = new UserEntity();
			userEntity.setCreateBy(11111L);
			userEntity.setCreateDate(new Date());
			userEntity.setPassword("passwoearew");
			
			lists.add(userEntity);
		}
		EcRedisTemplateHandler.set("user1List", lists);
		String value = EcRedisTemplateHandler.get("user1List",String.class);
		System.out.println("------------------" + value);
		List<UserEntity> list = EcJSONUtils.parseArray(value, UserEntity.class);
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
			
			lists.add(userEntity);
		}
		EcRedisTemplateHandler.set("user1ListOffSet", lists);
		String value = EcRedisTemplateHandler.get("user1ListOffSet",String.class);
		System.out.println("------------------" + value);
		List<UserEntity> list = EcJSONUtils.parseArray(value, UserEntity.class);
		System.out.println("===============" + list.size());
	}
	@Test
	public void testMuileSet(){
		Map<String,Object> multiMap = new LinkedHashMap<>();
		List<String> keys = new ArrayList<>();
		for(int i = 0 ; i < 3;++i){
			UserEntity userEntity = new UserEntity();
			
			userEntity.setCreateBy(userEntity.getId());
			userEntity.setCreateDate(new Date());
			userEntity.setPassword("password" + i);
			multiMap.put(Long.toString(userEntity.getId()), userEntity);
			keys.add(Long.toString(userEntity.getId()));
		}
		EcRedisTemplateHandler.multiSet(multiMap);
		
		List<UserEntity> retList = EcRedisTemplateHandler.multiGet(keys, UserEntity.class);
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
			
			userEntity.setCreateBy(userEntity.getId());
			userEntity.setCreateDate(new Date());
			userEntity.setPassword("password" + i);
			keys.add("leftPush:"+userEntity.getId());
			EcRedisTemplateHandler.leftPush("leftPush", userEntity);;
		}
		List<UserEntity> retList = EcRedisTemplateHandler.rangeAll("leftPush", UserEntity.class);
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
			
			userEntity.setCreateBy(userEntity.getId());
			userEntity.setCreateDate(new Date());
			userEntity.setPassword("password" + i);
			keys.add("leftPush:"+userEntity.getId());
			list.add(userEntity);
			userEntities[i] = userEntity;
		}
		EcRedisTemplateHandler.leftPush("leftPushAll", list);;
		List<UserEntity> retList = EcRedisTemplateHandler.rangeAll("leftPushAll", UserEntity.class);
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
			
			userEntity.setCreateBy(userEntity.getId());
			userEntity.setCreateDate(new Date());
			userEntity.setPassword("password" + i);
			keys.add("rightPushAll:"+userEntity.getId());
			list.add(userEntity);
			userEntities[i] = userEntity;
		}
		EcRedisTemplateHandler.leftPush("rightPushAll", list);;
		List<UserEntity> retList = EcRedisTemplateHandler.rangeAll("rightPushAll", UserEntity.class);
		for(UserEntity userEntity : retList){
			System.out.println("------------" + userEntity.getId());
		}
	}
	@Test
	public void testIndexFromList(){
		UserEntity userEntity = EcRedisTemplateHandler.indexFromList("rightPushAll", 1, UserEntity.class);
		if(EcBaseUtils.isNotNull(userEntity)){
			System.out.println(userEntity.getId());
		}
	}
	@Test
	public void testIncrement(){
		EcRedisTemplateHandler.increment("increment1", 15);
	}
	@Test
	public void testSetSerializer(){
		UserEntity userEntity = new UserEntity();
		userEntity.setCreateBy(11111L);
		userEntity.setCreateDate(new Date());
		userEntity.setPassword("passwoearew");
		
		EcRedisTemplateHandler.setSerializer("user1Serializer", userEntity);
		System.out.println("------------------" + EcRedisTemplateHandler.getSerializer("user", UserEntity.class));
	}
	
	@Test
	public void testDeleteHashKey(){
		String key = "";
		String hashKeys = "311";
		EcRedisTemplateHandler.deleteHashKey(key, hashKeys);
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
			
			userEntity.setCreateBy(userEntity.getId());
			userEntity.setCreateDate(new Date());
			userEntity.setPassword("password" + i);
			hashMap.put(userEntity.getId(), userEntity);
		}
		EcRedisTemplateHandler.putAllOfHash(key, hashMap);
	}
	
	@Test
	public void testValuesOfMap(){
		String key = "putAllOfHash";
		List<HashMap> list = EcRedisTemplateHandler.valuesOfMap(key, HashMap.class);
		System.out.println(list.size());
	}
	
	@Test
	public void testEntriesOfMap(){
		String key = "putAllOfHash";
		Map<String, UserEntity> map = EcRedisTemplateHandler.entriesOfMap(key, UserEntity.class);
		System.out.println(map.keySet());
	}
	
}
