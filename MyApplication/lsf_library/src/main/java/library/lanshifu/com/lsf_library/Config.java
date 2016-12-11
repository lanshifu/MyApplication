/*
 * 文 件 名:  Config.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  wKF45592
 * 修改时间:  2011-7-4
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */

package library.lanshifu.com.lsf_library;


import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import library.lanshifu.com.lsf_library.utils.FileUtil;
import library.lanshifu.com.lsf_library.utils.StringUtil;

/**
 * <一句话功能简述> <功能详细描述>
 * 
 * @author wKF45592
 * @version [版本号, 2011-7-4]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class Config {

    public static int HTTP_TIMEOUT_DEFAULT = 120000;
    public static int OPER_TIMEOUT_DEFAULT = 120000;

    public static boolean DEBUG_LOG = false;

    /** HTTP请求超时时间 */
    public static int HTTP_TIMEOUT;

    /** 后台超时时间 */
    public static int OPER_TIMEOUT;

    /** 默认分页请求，每页的记录数 */
    public static String DEF_PAGE_SIZE;

    /** 打印日志，并且打印到sd卡上 */
    public static boolean IS_LOG_OUT;

    /** 打印异常日志，并且打印到sd卡上 */
    public static boolean IS_EXCEPTION_LOG_OUT;

    /** 子项目可指定异常处理类， 需继承基线ExceptionUtil类 */
    public static String EXCEPTION_CLASS;

    /** 演示版开关 */
    public static boolean isDemo;

    /** 升级地址 */
    public static String UPGRADE_URL;

    /** 接口地址 */
    public static String URL;

    /** 融合版html网页入口地址 */
    public static String WEBSITE_URL;

    /** 附件下载URL */
    public static String ATTACH_URL;

    /** 附件上传URL */
    public static String ATTACH_UPLOAD_URL;

    /** 缓存类名 */
    public static String CACHE_CLASS;

    /** crm请求参数设置类 */
    public static String CRM_HEAD_CLASS;

    /** cvs请求参数设置类 */
    public static String CVS_HEAD_CLASS;

    /** ESOP请求参数设置类 */
    public static String ESOP_HEAD_CLASS;
    /** IMOP请求参数设置类 */
    public static String IMOP_HEAD_CLASS;

    /** ESOP请求参数设置类 */
    public static String TITLE_HELPER_CLASS;

    public static Properties PROPERTIES;

    /** AsyncTask 在4.0变成同步，声明一个线程池 */
    public static ExecutorService UNLIMIT_EXECUTOR;

    /** Imageloader 是否采用第三方库com.nostra13.universalimageloader */
    public static boolean isFromNostra13 = true;

    /** 百度sdk开关 */
    public static boolean isBaiduEnable = false;

    /** http报文加解密 */
    public static boolean isEncryptHttp = false;

    /** 用户敏感数据加密 */
    public static boolean isEncryptUserData = false;

    /** 非法token */
    public static String TOKENINVALID = "T9999";

    /** 用户不一致 */
    public static String TOKENINVALIDUSER = "T9997";

    /** 超时token */
    public static String TOKENTIMEOUT = "T9998";

    /** 水印开关, 开启后还需设置水印内容Session.setWatermark() */
    public static boolean isShowWaterMark = false;

    /** https验证开关，开启后需配置HTTPS_PUB_KEY **/
    public static boolean httpsVerify;

    /** https的公钥 **/
    public static String HTTPS_PUB_KEY;

    public static boolean isKeystoreVerify = false;

    public static boolean isHijackVerify = false;

    static {
        UNLIMIT_EXECUTOR = (ExecutorService) Executors.newCachedThreadPool();
    }

    public static void init(InputStream is) {
        PROPERTIES = new Properties();
        try {

            PROPERTIES.load(is);

            isDemo = getPropertyBoolean("isDemo");

            HTTP_TIMEOUT = getPropertyInt("HTTP_TIMEOUT");

            OPER_TIMEOUT = getPropertyInt("OPER_TIMEOUT");

            IS_LOG_OUT = getPropertyBoolean("IS_LOG_OUT");

            IS_EXCEPTION_LOG_OUT = getPropertyBoolean("IS_EXCEPTION_LOG_OUT", true, false);

            EXCEPTION_CLASS = getProperty("exception_class", false);

            isShowWaterMark = getPropertyBoolean("isShowWaterMark", false, false);

            DEF_PAGE_SIZE = getProperty("DEF_PAGE_SIZE");

            UPGRADE_URL = getProperty("UPGRADE_URL", !isDemo);

            URL = getProperty("URL");

            WEBSITE_URL = getProperty("WEBSITE_URL");

            ATTACH_URL = getProperty("ATTACH_URL");

            CACHE_CLASS = getProperty("CACHE_CLASS");

            CRM_HEAD_CLASS = getProperty("CRM_HEAD_CLASS");

            CVS_HEAD_CLASS = getProperty("CVS_HEAD_CLASS");

            ESOP_HEAD_CLASS = getProperty("ESOP_HEAD_CLASS");

            IMOP_HEAD_CLASS = getProperty("IMOP_HEAD_CLASS");

            TITLE_HELPER_CLASS = getProperty("TITLE_HELPER_CLASS");

            isFromNostra13 = getPropertyBoolean("isFromNostra13", true, false);

            isBaiduEnable = getPropertyBoolean("isBaiduEnable", false);

            isEncryptHttp = getPropertyBoolean("isEncryptHttp", false);

            isEncryptUserData = getPropertyBoolean("isEncryptUserData", false);

            TOKENINVALID = getProperty("TOKENINVALID", "T9999", true);

            TOKENINVALIDUSER = getProperty("TOKENINVALIDUSER", "T9997", true);

            TOKENTIMEOUT = getProperty("TOKENTIMEOUT", "T9998", true);

            ATTACH_UPLOAD_URL = getProperty("ATTACH_UPLOAD_URL", false);

            httpsVerify = getPropertyBoolean("httpsVerify", false);

            HTTPS_PUB_KEY = getProperty("HTTPS_PUB_KEY", false);

            isKeystoreVerify = getPropertyBoolean("isKeystoreVerify", false);

            isHijackVerify = getPropertyBoolean("isHijackVerify", false);

        } catch (IOException e) {
            Log.e("Config", e.toString());
            throw new RuntimeException(e);
        } catch (Exception e) {
            Log.e("Config", e.toString());
            throw new RuntimeException(e);
        } finally {
            FileUtil.closeQuietly(is);
        }
    }

    /**
     * 默认返回值false，没有配置则会抛异常
     */
    public static boolean getPropertyBoolean(String name) {
        return getPropertyBoolean(name, true);
    }

    /**
     * 默认返回值false
     * 
     * @param name
     * @param throwException [config.properties没配置时，true:抛异常，反之fales:不抛异常]
     */
    public static boolean getPropertyBoolean(String name, boolean throwException) {
        return getPropertyBoolean(name, false, throwException);
    }

    /**
     * @param name
     * @param def [返回默认值]
     * @param throwException [config.properties没配置时，true:抛异常，反之fales:不抛异常]
     */
    public static boolean getPropertyBoolean(String name, boolean def, boolean throwException) {
        String value = getProperty(name, throwException);

        if (StringUtil.isEmpty(value)) {
            return def;
        }
        return Boolean.parseBoolean(value);
    }

    /**
     * 默认返回值0，没有配置则会抛异常
     */
    public static int getPropertyInt(String name) {
        return getPropertyInt(name, true);
    }

    /**
     * 默认返回值0
     * 
     * @param throwException [config.properties没配置时，true:抛异常，反之fales:不抛异常]
     */
    public static int getPropertyInt(String name, boolean throwException) {

        return getPropertyInt(name, 0, throwException);
    }

    /**
     * @param name
     * @param def [返回默认值]
     * @param throwException [config.properties没配置时，true:抛异常，反之fales:不抛异常]
     */
    public static int getPropertyInt(String name, int def, boolean throwException) {
        String value = getProperty(name, throwException);

        if (StringUtil.isEmpty(value)) {
            return def;
        }
        return Integer.parseInt(value);
    }

    /**
     * 默认返回值空，没有配置则会抛异常
     */
    public static String getProperty(String name) {
        return getProperty(name, true);
    }

    /**
     * 默认返回值空
     * 
     * @param name
     * @param throwException [config.properties没配置时，true:抛异常，反之fales:不抛异常]
     */
    public static String getProperty(String name, boolean throwException) {
        return getProperty(name, "", throwException);
    }

    /**
     * @param name
     * @param def [默认返回值]
     * @param throwException [config.properties没配置时，true:抛异常，反之fales:不抛异常]
     */
    public static String getProperty(String name, String def, boolean throwException) {
        String key = name.toLowerCase(Locale.getDefault());

        checkException(key, throwException);

        return PROPERTIES.getProperty(key, def).trim();
    }

    public static boolean containsKey(String key) {
        return PROPERTIES.containsKey(key);
    }

    private static void checkException(String name, boolean throwException) {
        if (throwException) {
//            if (!PROPERTIES.containsKey(name)) {
//                throw new IllegalArgumentException("请在config.properties配置文件中设置属性：" + name);
//            }
        }
    }
}
