package library.lanshifu.com.myapplication.base;

import okhttp3.Response;

/**
 * Created by 蓝师傅 on 2016/12/10.
 */

public interface  Iview {

    void updateSuccess(Response response,int taskId);

    void onError(int erorCode,String errorString);

}
