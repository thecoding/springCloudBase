package com.springcloudbase.util;

import com.springcloudbase.util.constant.SecurityConstant;
import com.springcloudbase.vo.BaseUser;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpSession;

public class SessionUtil {

	public static final int SESSION_TIMEOUT = 14400;

	private static final String SESSION_USER_KEY = "SESSION-USER-KEY";

	private static final String SESSION_FUN_KEY = "SESSION-USER-FUN";

	private static Logger logger = LoggerFactory.getLogger(SessionUtil.class);

	/**
	 * 获取session用户
	 * 
	 * @return
	 */
	public static BaseUser getUser() {
		HttpSession session = RequestUtil.getSession();
		logger.info("session.getId()：{}",session.getId());
		if (session != null) {
			logger.info("获取user最后操作时间：{}", session.getLastAccessedTime());
			return (BaseUser) session.getAttribute(SESSION_USER_KEY);
		}
		logger.error("getUser.error;;session is null");
		return null;
	}

	/**
	 * 删除用户session
	 */
	public static void delUser() {
		HttpSession session = RequestUtil.getSession();
		if (session != null) {
			session.removeAttribute(SESSION_USER_KEY);
		}
	}

	/**
	 * 获取验证码
	 * 
	 * @return
	 */
	public static String getValidCode() {
		HttpSession session = RequestUtil.getSession();
		if (session != null) {
			return String.valueOf(session.getAttribute(SecurityConstant.VALID_CODE_PREFIX));
		}
		logger.error("getValidCode;;session is null");
		return StringUtils.EMPTY;
	}

	/**
	 * 设置用户
	 * 
	 * @param user
	 */
	public static void setUser(BaseUser user) {
		HttpSession session = RequestUtil.getSession();
		if (session != null) {
			session.setAttribute(SESSION_USER_KEY, user);
		}
	}

//	/**
//	 * @desc 设置当前函数信息
//	 * @param function
//	 */
//	public static void setFunctionInfo(BmsFunc function) {
//		HttpServletRequest req = RequestUtil.getRequest();
//		if (req != null) {
//			req.setAttribute(SESSION_FUN_KEY, function);
//		}
//	}
//
//	/**
//	 * @desc 获取当前操作的操作函数
//	 * @return
//	 */
//	public static BmsFunc getFunctionInfo() {
//		HttpServletRequest req = RequestUtil.getRequest();
//		if (req != null) {
//			return (BmsFunc) req.getAttribute(SESSION_FUN_KEY);
//		}
//		return null;
//	}

	/**
	 * 存入验证码
	 * 
	 * @param validCode
	 */
	public static void setValidCode(String validCode) {
		HttpSession session = RequestUtil.getSession();
		if (session != null) {
			session.setAttribute(SecurityConstant.VALID_CODE_PREFIX, validCode);
		}
	}

	/**
	 * 存入Token
	 * 
	 * @param groupId
	 * @param token
	 */
	public static void pushToken(String groupId, String token) {
		HttpSession session = RequestUtil.getSession();
		if (session != null) {
			session.setAttribute(groupId, token);
		}
		RequestUtil.setAttribute(SecurityConstant.WEB_TOKEN, token);
	}

	/**
	 * 提取Token并移除原有token
	 * 
	 * @param groupId
	 * @return
	 */
	public static String pullToken(String groupId) {
		HttpSession session = RequestUtil.getSession();
		if (session != null) {
			Object obj = session.getAttribute(groupId);
			if (obj != null) {
				String token = String.valueOf(session.getAttribute(groupId));
				session.removeAttribute(groupId);
				return token;
			}
		}
		return StringUtils.EMPTY;
	}

//	/**
//	 * 设置用户登录的唯一标识
//	 *
//	 * @param userId
//	 * @param timeout
//	 */
//	public static void setKey(String userId) {
//		RedisService.getRedisService(AppConstants.REDIS_NAME_HOST_70)
//				.setex(SecurityConstant.SESSION_KEY_PREFIX + userId, RequestUtil.getSessionId(), SESSION_TIMEOUT);
//	}
//
//	/**
//	 * 获取用户登录唯一标识
//	 *
//	 * @param userId
//	 * @return
//	 */
//	public static String getKey(String userId) {
//		return RedisService.getRedisService(AppConstants.REDIS_NAME_HOST_70)
//				.get(SecurityConstant.SESSION_KEY_PREFIX + userId);
//	}

	/**
	 * 删除用户登录唯一标识
	 * 
	 * @param userId
	 */
//	public static void delKey(String userId) {
//		RedisService.getRedisService(AppConstants.REDIS_NAME_HOST_70).del(SecurityConstant.SESSION_KEY_PREFIX + userId);
//	}

//	/**
//	 * 清除用户缓存
//	 *
//	 * @param userId
//	 */
//	public static void clearUser(String userId) {
//		delKey(userId);
//		logger.info("clearUserId:{}", userId);
//	}

	/**
	 * 
	 * @desc 用户注销登录
	 *
	 */
	public static void loginOut() {
		HttpSession session = RequestUtil.getSession();
		if (session != null) {
			session.removeAttribute(SESSION_USER_KEY);
			session.invalidate();
		}
	}

}
