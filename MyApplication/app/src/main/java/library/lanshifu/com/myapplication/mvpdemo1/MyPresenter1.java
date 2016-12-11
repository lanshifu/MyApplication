package library.lanshifu.com.myapplication.mvpdemo1;

import android.app.Activity;

import library.lanshifu.com.myapplication.base.BasePresenter;

/**
 * Created by 蓝师傅 on 2016/12/10.
 */

public class MyPresenter1 extends BasePresenter<MVPActivity> {

    public MyPresenter1(Activity activity, MVPActivity mView) {
        super(activity, mView);
    }
}
