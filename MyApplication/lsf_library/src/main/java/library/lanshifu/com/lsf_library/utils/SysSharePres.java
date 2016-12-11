/*
 * 文 件 名:  SysSharePres.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  mKF67523
 * 修改时间:  2012-12-4
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */

package library.lanshifu.com.lsf_library.utils;

import android.content.Context;
import android.content.SharedPreferences;


import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import library.lanshifu.com.lsf_library.Config;
import library.lanshifu.com.lsf_library.MyAppLication;
import library.lanshifu.com.lsf_library.encrypt.DESedeEncrypt;

/**
 * 数据保存
 * 
 * @author mKF67523
 * @version [版本号, 2012-12-4]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class SysSharePres {

    /** 键值 */
    public static final String KEY_IS_FULL = "isfull";

    public static final String KEY_IS_AUTO_UPDATE = "isautoupdate";

    public static final String KEY_IS_GUIDE = "isguide";

    public static final String KEY_IS_USERNAME_REMEMBER = "rememberusername";

    public static final String KEY_IS_PASSWORD_REMEMBER = "rememberpassword";

    public static final String KEY_USERNAME = "username";

    public static final String KEY_ISLEADER = "isleader";

    public static final String KEY_SERVER_NUMBER = "servernumber";

    /** 保存登录信息 **/
    public static final String KEY_ISLUNCHED = "islunched";

    public static final String KEY_ISSTILLDOWNLOAD = "isStillDownLoad";

    public static final String KEY_OPERID = "operid";

    public static final String KEY_OPERNAME = "opername";

    public static final String KEY_ACCOUNTS4A = "accounts4a";

    public static final String KEY_PASSWORD = "password";

    public static final String KEY_TOKEN = "token";

    public static final String KEY_REGION = "region";

    public static final String KEY_ORGID = "orgid";

    public static final String KEY_CONTACTPHONE = "contactphone";

    public static final String KEY_CUSTMGRTYPE = "custmgrtype";

    public static final String KEY_LOGINDATE = "logindate";

    public static final String KEY_LOGINTYPE = "loginType";

    public static final String KYE_PICTURENUMBER = "pictureNUmber";

    private static final String PRES_NAME = "system_share";

    // 是否开启屏保
    public static final String KEY_ISGESTURE = "isgesture";

    // 是否登录重新设置手势
    public final static String KEY_LOGIN_RESET = "loginreset";

    // 登录后重新设置手势标示
    public final static String KEY_LOGIN_RESET_FLAG = "loginresetflag";

    // 是否设置了自动解锁
    public final static String KEY_HAS_AUTO_LOCK = "autolock";

    // 是否设置了手势解锁
    public final static String KEY_HAS_GESTURE_LOCK = "gesturelock";
    
    // 最后的操作时间
    public final static String LAST_OPERATION_TIME = "lastoperationtime";

    // 能否记录触摸时间
    public static final String CAN_RECORD_TOUCHTIME = "CAN_RECORD_TOUCHTIME";
    
    private SharedPreferences mSharePres;

    private DESedeEncrypt dESedeEncrypt;

    /**
     * <默认构造函数>
     */
    private SysSharePres() {
        mSharePres = MyAppLication.getContext().getSharedPreferences(PRES_NAME,
                Context.MODE_PRIVATE);

        dESedeEncrypt = new DESedeEncrypt();
    }

    private static class SysSharePresHolder {

        static final SysSharePres INSTANCE = new SysSharePres();
    }

    public static SysSharePres getInstance() {
        return SysSharePresHolder.INSTANCE;
    }

    /**
     * 全屏设置
     */
    public void setIsFullScreen(boolean isfull) {
        putBoolean(KEY_IS_FULL, isfull);
    }

    public boolean getIsFullScreen() {
        return getBoolean(KEY_IS_FULL);
    }

    /**
     * 自动更新
     */
    public void setIsAutoUpdate(boolean isautoupdate) {
        putBoolean(KEY_IS_AUTO_UPDATE, isautoupdate);
    }

    public boolean getIsAutoUpdate() {
        return getBoolean(KEY_IS_AUTO_UPDATE);
    }

    /**
     * 欢迎页推送设置
     */
    public void setIsGuide(boolean isGuide) {
        putBoolean(KEY_IS_GUIDE, isGuide);
    }

    public boolean getIsGuide() {
        return getBoolean(KEY_IS_GUIDE, true);
    }

    /**
     * 记住用户名
     */
    public void setIsRememberUsername(boolean isremember) {
        putBoolean(KEY_IS_USERNAME_REMEMBER, isremember);
    }

    public boolean getIsRememberUsername() {
        return getBoolean(KEY_IS_USERNAME_REMEMBER);
    }

    /**
     * 记住密码
     */
    public void setIsRememberPassWord(boolean isremember) {
        putBoolean(KEY_IS_PASSWORD_REMEMBER, isremember);
    }

    public boolean getIsRememberPassWord() {
        return getBoolean(KEY_IS_PASSWORD_REMEMBER);
    }

    /**
     * 记住临时用户名
     */
    public void setUserName(String username) {
        putString(KEY_USERNAME, username);
    }

    public String getUserName() {
        return getString(KEY_USERNAME);
    }

    /**
     * 记住是否为领导
     */
    public void setIsLeader(String isleader) {
        putString(KEY_ISLEADER, isleader);
    }

    public String getIsLeader() {
        return getString(KEY_ISLEADER, "0");
    }

    /**
     * 记住临时手机号码
     */
    public void setServerNumber(String serverNumber) {
        putString(KEY_SERVER_NUMBER, serverNumber);
    }

    public String getServerNumber() {
        return getString(KEY_SERVER_NUMBER);
    }

    public void setIslunched(boolean islunched) {
        putBoolean(KEY_ISLUNCHED, islunched);
    }

    public boolean getIslunched() {
        return getBoolean(KEY_ISLUNCHED);
    }

    public void setIsStillDownLoad(boolean isStillDownLoad) {
        putBoolean(KEY_ISSTILLDOWNLOAD, isStillDownLoad);
    }

    public boolean getIsStillDownLoad() {
        return getBoolean(KEY_ISSTILLDOWNLOAD);
    }

    public void setOperid(String operid) {
        putString(KEY_OPERID, operid);
    }

    public String getOperid() {
        return getString(KEY_OPERID);
    }

    public void setOpername(String opername) {
        putString(KEY_OPERNAME, opername);
    }

    public String getOpername() {
        return getString(KEY_OPERNAME);
    }

    public void setAccounts4a(String accounts4a) {
        putString(KEY_ACCOUNTS4A, accounts4a);
    }

    public String getAccounts4a() {
        return getString(KEY_ACCOUNTS4A);
    }

    public void setPassword(String password) {
        putString(KEY_PASSWORD, password);
    }

    public String getPassword() {
        return getString(KEY_PASSWORD);
    }

    public void setToken(String token) {
        putString(KEY_TOKEN, token);
    }

    public String getToken() {
        return getString(KEY_TOKEN);
    }

    public void setRegion(String region) {
        putString(KEY_REGION, region);
    }

    public String getRegion() {
        return getString(KEY_REGION);
    }

    public void setOrgid(String orgid) {
        putString(KEY_ORGID, orgid);
    }

    public String getOrgid() {
        return getString(KEY_ORGID);
    }

    public void setContactphone(String contactphone) {
        putString(KEY_CONTACTPHONE, contactphone);
    }

    public String getContactphone() {
        return getString(KEY_CONTACTPHONE);
    }

    public void setCustmgrtype(String custmgrtype) {
        putString(KEY_CUSTMGRTYPE, custmgrtype);
    }

    public String getCustmgrtype() {
        return getString(KEY_CUSTMGRTYPE);
    }

    public void setLogindate(String logindate) {
        putString(KEY_LOGINDATE, logindate);
    }

    public String getLogindate() {
        return getString(KEY_LOGINDATE);
    }

    public void setLoginType(String loginType) {
        putString(KEY_LOGINTYPE, loginType);
    }

    public String getLoginType() {
        return getString(KEY_LOGINTYPE);
    }

    public void setPictureNUmber(int pictureNUmber) {
        putInt(KYE_PICTURENUMBER, pictureNUmber);
    }

    public int getPictureNUmber() {
        return getInt(KYE_PICTURENUMBER);
    }
    
    public void setLastOperationTime(Long time){
        mSharePres.edit().putLong(LAST_OPERATION_TIME, time).commit();
    }
    
    public Long getLastOperationTime(){
        return mSharePres.getLong(LAST_OPERATION_TIME, 0);
    }

    /************** set ***********************************************/
    public void putString(String key, String value) {
        if (Config.isEncryptUserData && StringUtil.isNotEmpty(value)) {
            value = dESedeEncrypt.encrypt(value);
        }
        mSharePres.edit().putString(key, value).commit();
    }

    public void putBoolean(String key, Boolean value) {
        mSharePres.edit().putBoolean(key, value).commit();
    }

    public void putFloat(String key, float value) {
        mSharePres.edit().putFloat(key, value).commit();
    }

    public void putInt(String key, int value) {
        mSharePres.edit().putInt(key, value).commit();
    }

    public void putLong(String key, long value) {
        mSharePres.edit().putLong(key, value).commit();
    }

    /************** get ***********************************************/

    public String getString(String key) {
        return getString(key, "");
    }

    public String getString(String key, String def) {
        String value = mSharePres.getString(key, def);
        if (StringUtil.isEmpty(value)) {
            return value;
        }

        if (value.equals(def)) {
            return value;
        }

        if (Config.isEncryptUserData) {
            value = dESedeEncrypt.decrypt(value);
        }
        return value;
    }

    public Boolean getBoolean(String key) {
        return getBoolean(key, false);
    }

    public Boolean getBoolean(String key, Boolean def) {
        return mSharePres.getBoolean(key, def);
    }

    public float getFloat(String key) {
        return getFloat(key, 0.0f);
    }

    public float getFloat(String key, float def) {
        return mSharePres.getFloat(key, def);
    }

    public int getInt(String key) {
        return getInt(key, 0);
    }

    public int getInt(String key, int def) {
        return mSharePres.getInt(key, def);
    }

    public long getLong(String key) {
        return getLong(key, 0l);
    }

    public long getLong(String key, long def) {
        return mSharePres.getLong(key, def);
    }

    public void remove(String key) {
        mSharePres.edit().remove(key).commit();
    }

    /**
     * 兼容旧的数据，全部加密
     */
    public void upgradeEncrypt() {
        Map<String, ?> map = mSharePres.getAll();

        if (null==map) {
            // 为空
            return;
        }
        boolean encrypted = SysSharePres.getInstance().getBoolean("encrypted");

        if (encrypted) {
            return;
        }

        Iterator<?> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Entry<String, ?> next = (Entry<String, ?>) it.next();
            if (next.getValue() instanceof String) {
                putString(next.getKey(), (String) next.getValue());
            }
        }
        SysSharePres.getInstance().putBoolean("encrypted", true);
    }

}
