package library.lanshifu.com.myapplication.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import butterknife.ButterKnife;
import library.lanshifu.com.myapplication.comm.BaseAppCompatActivity;
import okhttp3.Response;

/**
 * Created by 蓝师傅 on 2016/12/10.
 */

public  abstract class BaseActivity<T extends Ipresenter> extends BaseAppCompatActivity implements Iview{

    protected T mPresenter;
    protected Activity mContext;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ButterKnife.bind(this);
        mContext = this;
        mPresenter = getPresenter();
        initEventAndData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }



    protected abstract T getPresenter();

    protected  abstract void initEventAndData();


    @Override
    public void updateSuccess(Response response, int taskId) {

    }

    @Override
    public void onError(int erorCode, String errorString) {

    }




}
