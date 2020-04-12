package com.springcloudbase.util;

import com.springcloudbase.vo.result.ResponseBean;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Mirko on 2020/4/11.
 */
@Slf4j
public class RequestUtil {


    public static HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    /***
     * @desc 获取当前的response对象
     * @return
     */
    public static HttpServletResponse getResponse() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
    }


    /**
     * 设置线程返回结果
     * @param resultInfo
     */
    public static void setResult(ResponseBean resultInfo){
        HttpServletRequest request = getRequest();
        if (null != request && null != resultInfo) {
            request.setAttribute(ResponseBean.class.getSimpleName(),resultInfo);
        }
    }

    /**
     * 获取返回结果
     * @return
     */
    public static ResponseBean getResult(){
        HttpServletRequest request = getRequest();
        if (null != request) {
            return (ResponseBean)request.getAttribute(ResponseBean.class.getSimpleName());
        }
        return null;
    }

    /**
     * 是否有结果
     * @return
     */
    public static boolean hasResult(){
        return getResult() != null ? true : false;
    }


    /**
     *
     * @desc 获取当前Response的OutPutStream
     * @param templateName
     * @return OutputStream
     * @throws IOException
     */
    public static OutputStream getOutputStream(String templateName) throws IOException {
        HttpServletResponse response = getResponse();
        if (response != null) {
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-disposition", "attachment;filename=" + templateName);
            return response.getOutputStream();
        }
        log.error("response is empty,please check config again!!");
        return null;
    }

//    public static String toRequestString() {
//        HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//        try {
//            if (req != null && !req.getInputStream().isFinished()) {
//                String reqStr = IOUtils.toString(req.getInputStream(), "utf-8");
//                return reqStr;
//            }
//        } catch (IOException e) {
//            log.error("toRequestString.error:{}", e.getStackTrace());
//        }
//        return StringUtils.EMPTY;
//    }

    /**
     *
     * @return
     */
    public static boolean isAjaxRequest() {
        HttpServletRequest request = getRequest();
        if (request != null) {
            String requestedWith = request.getHeader("x-requested-with");
            return StringUtils.equalsIgnoreCase(requestedWith, "XMLHttpRequest");
        }
        return false;
    }

    /**
     * 获取请求参数
     *
     * @param clz
     *            返回类型
     * @return
     */
    public static <T> T getAttribute(Class<T> clz) {
        return getAttribute(clz.getSimpleName(), clz);
    }

    /**
     * 获取请求参数
     *
     * @param attrName
     *            参数名称
     * @param clz
     *            返回类型
     * @return
     */
    public static <T> T getAttribute(String attrName, Class<T> clz) {
        HttpServletRequest req = getRequest();
        if (req == null)
            return null;
        Object o = getAttribute(attrName);
        return o == null ? null : (T) o;
    }

    /**
     *
     * @param obj
     */
    public static void setAttribute(Object obj) {
        HttpServletRequest req = getRequest();
        if (req != null && obj != null)
            setAttribute(obj.getClass().getSimpleName(), obj);
    }

    /**
     * Integer类型参数
     *
     * @param paramName
     * @return
     */
    public static Double getParameterDouble(String paramName) {
        HttpServletRequest req = getRequest();
        if (req == null)
            return null;
        String o = getParameter(paramName);
        return o == null ? null : Double.valueOf(o);
    }

    /**
     * Integer类型参数
     *
     * @param paramName
     * @return
     */
    public static Integer getParameterInt(String paramName) {
        HttpServletRequest req = getRequest();
        if (req == null)
            return null;
        String o = getParameter(paramName);
        return o == null ? null : Integer.valueOf(o);
    }

    /**
     * 获取form请求参数
     *
     * @param paramName
     * @return
     */
    public static String getParameter(String paramName) {
        HttpServletRequest req = getRequest();
        if (req == null)
            return null;
        String value = req.getParameter(paramName);
        return value == null ? null : value.trim();
    }

    /**
     * 获取请求参数
     *
     * @param attrName
     * @return
     */
    public static Object getAttribute(String attrName) {
        HttpServletRequest req = getRequest();
        if (req == null)
            return null;
        return req.getAttribute(attrName);
    }

    /**
     * 获取字符串
     *
     * @param paramName
     *            参数名称
     * @return
     */
    public static String getString(String paramName) {
        HttpServletRequest req = getRequest();
        if (req == null)
            return null;
        String str = req.getParameter(paramName);
        return StringUtils.isNotBlank(str) ? str.trim() : str;
    }

    /**
     * 获取list
     *
     * @param paramName
     *            参数名称
     * @return
     */
    public static List<?> getList(String paramName) {
        return getAttribute(paramName, List.class);
    }

    public static String getIp() {
        HttpServletRequest request = getRequest();
        if (request == null)
            return null;

        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        if (StringUtils.isNotBlank(ip) && ip.indexOf("0:0:0:0:0:0:0:1") > -1)
            ip = "127.0.0.1";

        return ip;
    }

//    public static String getMac(String ip) {
//
//        if (StringUtils.isNotBlank(ip) && (ip.indexOf("127.0.0.1") > -1 || ip.indexOf("0.0.0.0") > -1)) {
//            return localMAC();
//        }
//
//        String commandLine = "";
//        switch (OSUtil.getOSname()) {
//            case Linux:
//                commandLine = "arp -n " + ip;
//                break;
//            case Windows:
//                commandLine = "arp -a " + ip;
//                break;
//        }
//
//        String str = "";
//        String macAddress = "";
//
//        String macPattern = "([0-9A-Fa-f]{2})(-[0-9A-Fa-f]{2}){5}";
//
//        try {
//            Process p = Runtime.getRuntime().exec(commandLine);
//            InputStreamReader ir = new InputStreamReader(p.getInputStream());
//            LineNumberReader input = new LineNumberReader(ir);
//            do {
//                str = input.readLine();
//                if (str != null && str.indexOf(ip) > -1) {
//                    Pattern pt = Pattern.compile(macPattern);
//                    Matcher matcher = pt.matcher(str);
//                    if (matcher.find()) {
//                        int index = str.indexOf(matcher.group());
//                        macAddress = str.substring(index, index + 17);
//                        return macAddress.toUpperCase();
//                    }
//                }
//            } while (null != str);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return macAddress;
//    }

    public static String localMAC() {
        try {
            byte[] mac = NetworkInterface.getByInetAddress(InetAddress.getLocalHost()).getHardwareAddress();
            StringBuffer sb = new StringBuffer("");
            for (int i = 0; mac != null && i < mac.length; i++) {
                if (i != 0) {
                    sb.append("-");
                }
                // 字节转换为整数
                int temp = mac[i] & 0xff;
                String str = Integer.toHexString(temp);
                if (str.length() == 1) {
                    sb.append("0" + str);
                } else {
                    sb.append(str);
                }
            }
            return sb.toString().toUpperCase();
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        return "FF:FF:FF:FF:FF:FF";
    }

    /**
     * 获取Map
     *
     * @param paramName
     *            参数名称
     * @return
     */
    public static Map<?, ?> getMap(String paramName) {
        return getAttribute(paramName, Map.class);
    }

    /**
     * 获取paramMap
     *
     * @return
     */
    public static Map<?, ?> getParamMap() {
        HttpServletRequest req = getRequest();
        if (req == null)
            return null;
        Object o = req.getParameterMap();
        return o == null ? null : (Map) o;
    }

    /**
     * 从request中获得参数Map，并返回可读的Map
     *
     * @return
     */
    @SuppressWarnings("unchecked")
    public static Map getMapParameter() {
        HttpServletRequest request = getRequest();
        // 参数Map
        Map properties = request.getParameterMap();
        // 返回值Map
        Map returnMap = new HashMap();
        Iterator entries = properties.entrySet().iterator();
        Map.Entry entry;
        String name = "";
        String value = "";
        while (entries.hasNext()) {
            entry = (Map.Entry) entries.next();
            name = (String) entry.getKey();
            Object valueObj = entry.getValue();
            if (null == valueObj) {
                value = "";
            } else if (valueObj instanceof String[]) {
                String[] values = (String[]) valueObj;
                for (int i = 0; i < values.length; i++) {
                    value = values[i] + ",";
                }
                value = value.substring(0, value.length() - 1);
            } else {
                value = valueObj.toString();
            }
            returnMap.put(name, value);
        }
        return returnMap;
    }

    /**
     * 获取请求url
     *
     * @return
     */
    public static String getRequestUrl() {
        HttpServletRequest req = getRequest();
        if (req == null)
            return null;
        return req.getRequestURI() == null ? null : req.getRequestURI();
    }

    /**
     * request setAttribute
     *
     * @param name
     * @param o
     */
    public static void setAttribute(String name, Object o) {
        HttpServletRequest req = getRequest();
        if (req != null)
            req.setAttribute(name, o);

    }

    /**
     * @desc 获取ip地址,防止集群、代理
     * @return ip
     */
    public static String getAddr() {
        HttpServletRequest request = getRequest();
        if (request == null) {
            return StringUtils.EMPTY;
        }
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return StringUtils.substringBefore(ip, ",");
    }

    public static HttpSession getSession() {
        HttpServletRequest req = getRequest();
        if (req != null)
            return req.getSession(true);
        return null;
    }

    public static String getHeader(String headerName) {
        return getRequest().getHeader(headerName);
    }

    public static int getInt(String paramName) {
        String paramValue = getString(paramName);
        if (StringUtils.isNotBlank(paramValue)) {
            return new Integer(paramValue);
        }
        return 0;
    }

    public static int getInt(String paramName, int defaultValue) {
        int result = getInt(paramName);
        if (result == 0)
            return defaultValue;
        return defaultValue;
    }

    public static String getSessionId() {
        return getSession().getId();
    }
}
