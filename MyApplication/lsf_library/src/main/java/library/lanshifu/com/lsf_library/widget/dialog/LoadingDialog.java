package library.lanshifu.com.lsf_library.widget.dialog;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.widget.TextView;

import library.lanshifu.com.lsf_library.R;
import library.lanshifu.com.lsf_library.utils.StringUtil;
import library.lanshifu.com.lsf_library.utils.T;

/**
 * 加载框
 * Created by 蓝师傅 on 2016/12/9.
 */

public class LoadingDialog extends AlertDialog {

    private boolean mCanCancle = true;
    private Context mContext;
    private TextView textView;


    protected LoadingDialog(@NonNull Context context) {
        super(context);
        this.mContext = context;
        setCanceledOnTouchOutside(false);
    }

    protected LoadingDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
        this.mContext = context;
        setCanceledOnTouchOutside(false);
    }

    protected LoadingDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        this.mCanCancle = cancelable;
        this.mContext = context;

        setCanceledOnTouchOutside(false);
    }


    public void setDismissListener(OnDismissListener listener) {
        setOnDismissListener(listener);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_progress_dialog);
        textView = (TextView) findViewById(R.id.product_msg);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (!mCanCancle) {
            T.showShort( "办理中，不能取消！");
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void setMessage(CharSequence message) {
        if(StringUtil.isNotEmpty(message)){
            textView.setText(message);
        }
    }
}
