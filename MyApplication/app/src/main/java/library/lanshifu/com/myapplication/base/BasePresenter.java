package library.lanshifu.com.myapplication.base;

import android.app.Activity;
import android.content.Context;

/**
 * Created by 蓝师傅 on 2016/12/10.
 */

public abstract class BasePresenter<T extends Iview> extends Ipresenter {

    protected Activity mActivity;
    protected T mView;
//    protected CompositeSubscription mCompositeSubscription;

    public BasePresenter(Activity activity,T mView) {
        this.mActivity = activity;
        this.mView = mView;

    }


    
}
