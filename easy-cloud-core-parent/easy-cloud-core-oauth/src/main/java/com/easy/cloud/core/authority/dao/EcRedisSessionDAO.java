package com.easy.cloud.core.authority.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.crazycake.shiro.RedisSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.easy.cloud.core.authority.manager.EcRedisManager;
import com.easy.cloud.core.common.json.utils.EcJSONUtils;
import com.easy.cloud.core.common.string.utils.EcStringUtils;

/**
 * 
 * <p>
 * 重写RedisSessionDAO的相关接口
 * </p>
 *
 * <pre>
 *  说明：保存到redis中的key和value为String类型
 *  约定：
 *  命名规范：
 *  使用示例：
 * </pre>
 *
 * @author daiqi
 * @创建时间 2018年5月25日 下午6:25:19
 */
public class EcRedisSessionDAO extends RedisSessionDAO {

	private static Logger logger = LoggerFactory.getLogger(EcRedisSessionDAO.class);
	/**
	 * 自定义的redisManager
	 */
	private EcRedisManager redisManager;

	/**
	 * The Redis key prefix for the sessions
	 */
	private String keyPrefix = "shiro_redis_session:";

	@Override
	public void update(Session session) throws UnknownSessionException {
		this.saveSession(session);
	}

	/**
	 * save session
	 * 
	 * @param session
	 * @throws UnknownSessionException
	 */
	private void saveSession(Session session) throws UnknownSessionException {
		if (session == null || session.getId() == null) {
			logger.error("session or session id is null");
			return;
		}

		String key = getKey(session.getId());
		String value = EcJSONUtils.toJSONString(session);
		session.setTimeout(redisManager.getExpire() * 1000);
		this.redisManager.set(key, value, redisManager.getExpire());
	}

	@Override
	public void delete(Session session) {
		if (session == null || session.getId() == null) {
			logger.error("session or session id is null");
			return;
		}
		redisManager.del(this.getKey(session.getId()));

	}

	@Override
	public Collection<Session> getActiveSessions() {
		Set<Session> sessions = new HashSet<Session>();

		Set<String> keys = redisManager.getKeys(this.keyPrefix + "*");
		if (keys != null && keys.size() > 0) {
			for (String key : keys) {
				Session s = EcJSONUtils.parseObject(redisManager.get(key), Session.class);
				sessions.add(s);
			}
		}

		return sessions;
	}

	@Override
	protected Serializable doCreate(Session session) {
		Serializable sessionId = this.generateSessionId(session);
		this.assignSessionId(session, sessionId);
		this.saveSession(session);
		return sessionId;
	}

	@Override
	protected Session doReadSession(Serializable sessionId) {
		if (sessionId == null) {
			logger.error("session id is null");
			return null;
		}

		Session s = EcJSONUtils.parseObject(redisManager.get(this.getKey(sessionId)), Session.class);
		return s;
	}

	/**
	 * String
	 * 
	 * @param key
	 * @return
	 */
	private String getKey(Serializable sessionId) {
		return EcStringUtils.generateKeyUseColonSeparator(this.keyPrefix, sessionId.toString());
	}

	public EcRedisManager getRedisManager() {
		return redisManager;
	}

	public void setRedisManager(EcRedisManager redisManager) {
		this.redisManager = redisManager;
	}

	/**
	 * Returns the Redis session keys prefix.
	 * 
	 * @return The prefix
	 */
	public String getKeyPrefix() {
		return keyPrefix;
	}

	/**
	 * Sets the Redis sessions key prefix.
	 * 
	 * @param keyPrefix
	 *            The prefix
	 */
	public void setKeyPrefix(String keyPrefix) {
		this.keyPrefix = keyPrefix;
	}
}
