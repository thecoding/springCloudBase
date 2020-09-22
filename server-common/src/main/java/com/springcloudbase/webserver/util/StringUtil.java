package com.springcloudbase.webserver.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.util.*;
import java.util.Map.Entry;
import java.util.zip.CRC32;

/**
 * 字符工具
 * 
 * @author Administrator
 *
 */
public class StringUtil extends StringUtils {

	private static Logger logger = LoggerFactory.getLogger(StringUtil.class);

	/** */
	public static final String PATTERN_434 = "434";

	/**
	 * 
	 * @param obj
	 * @return
	 */
	public static boolean isNotBlank(Object obj) {
		String str = obj2String(obj);
		return isNotBlank(str);
	}

	/**
	 * urlEncode 编码模板数据
	 * 
	 * @param content
	 * @return
	 */
	public static String encode2String(Map<String, String> content) {
		String charset = "UTF-8";
		if (content == null)
			return null;
		Set<Entry<String, String>> entries = content.entrySet();
		StringBuffer sb = new StringBuffer();
		for (Entry<String, String> entry : entries) {
			String key = entry.getKey();
			String value = entry.getValue();
			if (StringUtil.hasValue(key) && StringUtil.hasValue(value)) {
				try {
					key = URLEncoder.encode("#" + key + "#", charset);
					value = URLEncoder.encode(value, charset);

					sb.append(key).append("=").append(value);
				} catch (UnsupportedEncodingException e) {
					logger.error("URL编码失败", e);
				}
				sb.append("&");
			}
		}

		if (sb.length() > 0) {
			return sb.substring(0, sb.length() - 1);
		}

		return null;
	}

	/**
	 * 判断字符串是否为空
	 * 
	 * @param s
	 * @return boolean
	 */
	public static boolean hasValue(String s) {
		if (s != null && s.trim().length() > 0)
			return true;
		return false;
	}

	public static boolean isEmpty(String s) {
		return !hasValue(s);
	}

	public static boolean hasValueBeforeTrim(String s) {
		if (s != null && s.trim().length() > 0)
			return true;
		return false;
	}

	/**
	 * 根式化字符串为 如：pattern=434 src=1234567890 则格式化为 1234***7890
	 * 
	 * @param src
	 *            被格式化字符串长度不能小等于格式化后的总长度
	 * @param pattern
	 *            必须为3位数
	 * @return
	 */
	public static String format(String src, String pattern) {
		if (isEmpty(src)) {
			return "";
		}
		char[] chars = pattern.toCharArray();

		int s = Integer.parseInt(chars[0] + "");

		int m = Integer.parseInt(chars[1] + "");

		int e = Integer.parseInt(chars[2] + "");

		StringBuffer sb = new StringBuffer(src.substring(0, s));

		String es = src.substring(src.length() - e);

		while (m-- > 0) {
			sb.append("*");
		}

		sb.append(es);

		return sb.toString();
	}

	/**
	 * 将形如key=value&key=value的字符串转换为相应的Map对象
	 * 
	 * @param result
	 * @return
	 */
	public static Map<String, String> convertResultStringToMap(String result) {
		Map<String, String> map = null;
		try {

			if (hasValue(result)) {
				if (result.startsWith("{") && result.endsWith("}")) {
					result = result.substring(1, result.length() - 1);
				}
				map = parseQString(result);
			}

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return map;
	}

	/**
	 * 解析应答字符串，生成应答要素
	 * 
	 * @param str
	 *            需要解析的字符串
	 * @return 解析的结果map
	 * @throws UnsupportedEncodingException
	 */
	public static Map<String, String> parseQString(String str) throws UnsupportedEncodingException {

		Map<String, String> map = new HashMap<String, String>();
		int len = str.length();
		StringBuilder temp = new StringBuilder();
		char curChar;
		String key = null;
		boolean isKey = true;
		boolean isOpen = false;// 值里有嵌套
		char openName = 0;
		if (len > 0) {
			for (int i = 0; i < len; i++) {// 遍历整个带解析的字符串
				curChar = str.charAt(i);// 取当前字符
				if (isKey) {// 如果当前生成的是key

					if (curChar == '=') {// 如果读取到=分隔符
						key = temp.toString();
						temp.setLength(0);
						isKey = false;
					} else {
						temp.append(curChar);
					}
				} else {// 如果当前生成的是value
					if (isOpen) {
						if (curChar == openName) {
							isOpen = false;
						}

					} else {// 如果没开启嵌套
						if (curChar == '{') {// 如果碰到，就开启嵌套
							isOpen = true;
							openName = '}';
						}
						if (curChar == '[') {
							isOpen = true;
							openName = ']';
						}
					}
					if (curChar == '&' && !isOpen) {// 如果读取到&分割符,同时这个分割符不是值域，这时将map里添加
						putKeyValueToMap(temp, isKey, key, map);
						temp.setLength(0);
						isKey = true;
					} else {
						temp.append(curChar);
					}
				}

			}
			putKeyValueToMap(temp, isKey, key, map);
		}
		return map;
	}

	private static void putKeyValueToMap(StringBuilder temp, boolean isKey, String key, Map<String, String> map)
			throws UnsupportedEncodingException {
		if (isKey) {
			key = temp.toString();
			if (key.length() == 0) {
				throw new RuntimeException("QString format illegal");
			}
			map.put(key, "");
		} else {
			if (key.length() == 0) {
				throw new RuntimeException("QString format illegal");
			}
			map.put(key, temp.toString());
		}
	}

	/**
	 * 字符串转换unicode
	 */
	public static String string2Unicode(String string) {

		StringBuffer unicode = new StringBuffer();

		for (int i = 0; i < string.length(); i++) {

			// 取出每一个字符
			char c = string.charAt(i);

			// 转换为unicode
			unicode.append("\\u" + Integer.toHexString(c));
		}

		return unicode.toString();
	}

	/**
	 * unicode 转字符串
	 */
	public static String unicode2String(String unicode) {

		StringBuffer string = new StringBuffer();

		String[] hex = unicode.split("\\\\u");

		for (int i = 1; i < hex.length; i++) {

			// 转换出每一个代码点
			int data = Integer.parseInt(hex[i], 16);

			// 追加成string
			string.append((char) data);
		}

		return string.toString();
	}

	/**
	 * 是否为中文字符
	 * 
	 * @param c
	 * @return
	 */
	private static final boolean isChinese(char c) {
		Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
		if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
				|| ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
				|| ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
				|| ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
			return true;
		}
		return false;
	}

	/**
	 * 判断字符串是否包含中文
	 * 
	 * @param strName
	 * @return
	 */
	public static final boolean isChinese(String strName) {
		char[] ch = strName.toCharArray();
		for (int i = 0; i < ch.length; i++) {
			char c = ch[i];
			if (isChinese(c)) {
				return true;
			}
		}
		return false;
	}

	private static final char[] NUMBERS = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

	/**
	 * 生成随机数
	 * 
	 * @param length
	 *            必须大于0 随机数长度
	 * @return
	 */
	public static String randomNumber(int length) {
		StringBuffer sb = new StringBuffer();
		Random r = new Random();
		while (length-- > 0) {
			int index = r.nextInt(NUMBERS.length - 1);
			sb.append(NUMBERS[index]);
		}

		return sb.toString();
	}

	private static final char[] WORDS = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O',
			'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
			'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };

	/**
	 * 生成随机字符串
	 */

	public static String randomString(int length) {
		StringBuffer sb = new StringBuffer();
		Random r = new Random();
		while (length-- > 0) {

			if (length % 2 == 0) {
				int index = r.nextInt(WORDS.length - 1);
				sb.append(WORDS[index]);
			} else {
				int index = r.nextInt(NUMBERS.length - 1);
				sb.append(NUMBERS[index]);
			}
		}

		return sb.toString();
	}

	/**
	 * 生成UUID 无分隔符
	 * 
	 * @return
	 */
	public static String UUID() {
		String uuid = UUID.randomUUID().toString();

		return uuid.replace("-", "").toUpperCase();
	}

	public static char[] chars = new char[] { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o',
			'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
			'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U',
			'V', 'W', 'X', 'Y', 'Z' };

	/**
	 * 8位UUID
	 * 
	 * @return
	 */
	public static String shortUUID() {
		StringBuffer shortBuffer = new StringBuffer();
		String uuid = UUID.randomUUID().toString().replace("-", "");
		for (int i = 0; i < 8; i++) {
			String str = uuid.substring(i * 4, i * 4 + 4);
			int x = Integer.parseInt(str, 16);
			shortBuffer.append(chars[x % 0x3E]);
		}
		return shortBuffer.toString();
	}

	/**
	 * 获取主机名称
	 * 
	 * @return
	 * @auther ssjie
	 */
	public static String getHostName() {
		InetAddress netAddress;
		try {
			netAddress = InetAddress.getLocalHost();
			return netAddress.getHostName();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return "";
	}

	public static String radomString(int size) {
		String charList = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
		String rev = "";
		Random f = new Random();
		for (int i = 0; i < size; i++) {
			rev += charList.charAt(Math.abs(f.nextInt()) % charList.length());
		}
		return rev;
	}

	/**
	 * 拼接数组
	 * 
	 * @param array
	 * @param separator
	 *            分隔符
	 * @param end
	 *            结束符
	 * @return
	 */
	public static String concatEntries(String[] array, String separator, String end) {
		if (array == null)
			return null;

		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < array.length; i++) {
			if (i > 0)
				sb.append(separator);
			String c = array[i];
			sb.append(c == null ? "" : c);
		}

		if (end != null) {
			sb.append(end);
		}

		return sb.toString();
	}

    static String strs="zxcvbnmlkjhgfdsaqwertyuiopQWERTYUIOPASDFGHJKLZXCVBNM1234567890";

    public static String getRandomString(int length) {
        StringBuffer sb = new StringBuffer();
        Random random = new Random();

        for (int i = 0; i < length; ++i) {
            int number = random.nextInt(62);
            sb.append(strs.charAt(number));
        }
        return sb.toString();
    }

	/**
	 * 拼接集合
	 * 
	 * @param array
	 * @param separator
	 *            分隔符
	 * @param end
	 *            结束符
	 * @return
	 */
	public static String concatEntries(Collection<String> array, String separator, String end) {
		String[] arr = new String[array.size()];
		array.toArray(arr);
		return concatEntries(arr, separator, end);
	}

	public static boolean hasValue(String[] array, String param) {
		if (null == array || !hasValue(param)) {
			return false;
		}
		for (int i = 0; i < array.length; i++) {
			if (array[i].equals(param)) {
				return true;
			}
		}

		return false;
	}

	public static String addSpace(String s, int size) {
		if (!hasValue(s)) {
			return null;
		}
		StringBuilder str = new StringBuilder(s.replace(" ", ""));

		int i = str.length() / size;
		int j = str.length() % size;

		for (int x = (j == 0 ? i - 1 : i); x > 0; x--) {
			str = str.insert(x * size, " ");
		}
		return str.toString();
	}

	/**
	 * 去掉四个字节的字符串
	 * 
	 * @param content
	 * @return
	 */
	public static String removeFourChar(String content) {
		byte[] conbyte = content.getBytes();
		for (int i = 0; i < conbyte.length; i++) {
			if ((conbyte[i] & 0xF8) == 0xF0) {
				for (int j = 0; j < 4; j++) {
					conbyte[i + j] = 0x30;
				}
				i += 3;
			}
		}
		content = new String(conbyte);
		return content.replaceAll("0000", "");
	}

	/**
	 * 
	 * @desc 将对象转化为String
	 * @param val
	 * @return
	 */
	public static String obj2String(Object val) {
		if (val != null) {
			return String.valueOf(val);
		}
		return EMPTY;
	}

	public static byte[] getString2Utf8(Object val) {
		String str = obj2String(val);
		if (isNotBlank(str)) {
			try {
				return str.getBytes("utf-8");
			} catch (UnsupportedEncodingException e) {
				logger.error(e.getMessage());
			}
		}
		return null;
	}

	/**
	 * @desc 根据传入字符串核算crc32编码
	 * @param src
	 * @return
	 */
	public static Long toCRC32(Object src) {
		if (isNotBlank(src)) {
			CRC32 crc32 = new CRC32();
			crc32.update(getString2Utf8(src));
			return crc32.getValue();
		}
		return 0l;
	}

	/**
	 * @desc 根据传入字符串核算crc32编码
	 * @param src
	 * @return
	 */
	public static String toCRC32(Object src, int length) {
		long crc32 = toCRC32(src);
		if (crc32 > 0) {
			return leftPad(String.valueOf(crc32 % (int) Math.pow(10, length)), length, "0");
		}
		return null;
	}

	/**
	 * 解析出url参数中的键值对 如 "index.jsp?Action=del&id=123"，解析出Action:del,id:123存入map中
	 *
	 * @param URL
	 *            url地址
	 * @return url请求参数部分
	 */
	public static Map<String, String> URLRequest(String URL) {
		Map<String, String> mapRequest = new HashMap<String, String>();

		String[] arrSplit = null;

		String strUrlParam = TruncateUrlPage(URL);
		if (strUrlParam == null) {
			return mapRequest;
		}
		// 每个键值为一组 www.2cto.com
		arrSplit = strUrlParam.split("[&]");
		for (String strSplit : arrSplit) {
			String[] arrSplitEqual = null;
			arrSplitEqual = strSplit.split("[=]");

			// 解析出键值
			if (arrSplitEqual.length > 1) {
				// 正确解析
				mapRequest.put(arrSplitEqual[0], arrSplitEqual[1]);

			} else {
				if (arrSplitEqual[0] != "") {
					// 只有参数没有值，不加入
					mapRequest.put(arrSplitEqual[0], "");
				}
			}
		}
		return mapRequest;
	}

	/**
	 * 去掉url中的路径，留下请求参数部分
	 *
	 * @param strURL
	 *            url地址
	 * @return url请求参数部分
	 */
	private static String TruncateUrlPage(String strURL) {
		String strAllParam = null;
		String[] arrSplit = null;
		strURL = strURL.trim();
		arrSplit = strURL.split("[?]");
		if (strURL.length() > 1) {
			if (arrSplit.length > 1) {
				if (arrSplit[1] != null) {
					strAllParam = arrSplit[1];
				}
			}
		}
		return strAllParam;
	}

	/*
	 * @desc 将驼峰命名字段将大写转为_小写
	 */
	public static String conversionHump(String src) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < src.length(); i++) {
			if (isUpperCase(src.charAt(i))) {
				sb.append("_" + toLower(src.charAt(i)));
			} else {
				sb.append(src.charAt(i));
			}
		}
		return sb.toString();
	}

	/*
	 * 小写转大写
	 */
	public static char toUpper(char c) {
		return c >= 65 && c <= 90 ? c : (char) (c - 32);
	}

	/*
	 * 大写转小写
	 */
	public static char toLower(char c) {
		return c >= 97 && c <= 122 ? c : (char) (c + 32);
	}

	/*
	 * 是否是大写
	 */
	public static boolean isUpperCase(char c) {
		// Character.isUpperCase(c);
		return c >= 65 && c <= 90;
	}

	/*
	 * 是否是小写
	 */
	public static boolean isLowerCase(char c) {
		// Character.isLowerCase(c);
		return c >= 97 && c <= 122;
	}

	/**
	 * 去除字符串中的双引号
	 * @return
	 */
	public static String removeQuotes(String str){
		if (str.isEmpty())return null;

		String chara="\"";
		if (str.contains(chara)){
			str = str.replace(chara, "");
		}
		return str;
	}
}
