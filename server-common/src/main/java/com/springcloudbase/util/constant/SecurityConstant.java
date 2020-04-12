package com.springcloudbase.util.constant;

/**
 * 
 * @author Administrator
 *
 */
public interface SecurityConstant {

	public static final String VALID_CODE_PREFIX = "redis_valid_code:";

	public static final String SESSION_KEY_PREFIX = "redis_session_key:";

	public static final String WEB_TOKEN = "_web_token";

	public static String IS_JSON_KEY = "isJson";
	
	public static String PJP_KEY = "pjpKey"; // 环绕通知key

	public static String BMS_RESULT_KEY = "BMS_RESULT_KEY";

	public static String REPEATED_SUBMISSION_SESSION = "session"; // session级别数据校验

	public static String REPEATED_SUBMISSION_GLOBAL = "global"; // 全局级别session校验

}
