/*
 * 文 件 名:  AlertDialogUtil.java
 * 版    权:  Huawei Technologies Co., Led. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  cKF46827
 * 修改时间:  2011-5-25
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */

package library.lanshifu.com.lsf_library.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import library.lanshifu.com.lsf_library.R;


/**
 * 对话框的封装 {@link Builder}
 * 
 * @author wWX173427
 * @version [版本号, 2014-4-30]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class DialogCustom extends Dialog {
    public static final int TYPE_MSG = 100;
    public static final int TYPE_BUSIZ = 200;
    public static final int TYPE_DEFALUT = 300;
    public static final int TYPE_TOAST = 400;

    private static final float VER10 = 1.0f;
    private static final float VER20 = 2.0f;

    protected Builder builder;

    private IDialogViewInterface dialogView;

    public DialogCustom(Builder builder) {
        this(builder, R.style.pickerDialog);
    }

    private DialogCustom(Builder builder, int theme) {
        super(builder.getContext(), theme);
        this.builder = builder;
        init();
    }

    /**
     * 根据builder的数据来控制布局的显示
     * 
     * @return void [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    private void init() {
        if (builder.getView() != null && builder.getAdapter() != null) {
            throw new IllegalArgumentException("自定义view和列表不能共存");
        }


        dialogView = new DialogViewDefaultV20(this, builder);


        processView();
    }

    private void processView() {

        setContentView(dialogView.getContentView());

        dialogView.processTitle();
        dialogView.processContent();
        dialogView.processBottomButton(new BottomClickListener());

        setOnKeyListener(builder.getOnKeyListener());
        setCancelable(builder.isCancelable());
        setOnCancelListener(builder.getOnCancelListener());
        setOnDismissListener(builder.getOnDismissListener());

    }

    /**
     * 底部按钮的点击事件
     * 
     * @author wWX173427
     * @version [版本号, 2014-4-30]
     * @see [相关类/方法]
     * @since [产品/模块版本]
     */
    public class BottomClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {

            int which = (Integer) v.getTag();

            if (builder.getNegativeButtonListener() != null
                    && which == DialogInterface.BUTTON_NEGATIVE) {
                builder.getNegativeButtonListener().onClick(DialogCustom.this, which);
            }

            if (builder.getPositiveButtonListener() != null
                    && which == DialogInterface.BUTTON_POSITIVE) {
                builder.getPositiveButtonListener().onClick(DialogCustom.this, which);
            }

            if (builder.getNeutralButtonListener() != null
                    && which == DialogInterface.BUTTON_NEUTRAL) {
                builder.getNeutralButtonListener().onClick(DialogCustom.this, which);
            }

            if (builder.getOnClickListener() != null) {
                builder.getOnClickListener().onClick(DialogCustom.this, which);
            }

        }
    }

    /******************************************************************************/
    /******************************************************************************/

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            dialogView.adjustMsgLines();
        }
    }

    /**
     * dialog显示后，重新计算高度--内容区
     */
    @Override
    public void show() {
        dialogView.show();
        super.show();
    }

    /**
     * dialog显示后，指定宽高--内容区
     */
    public void show(int width, int height) {
        dialogView.show(width, height);
        super.show();
    }

    /**
     * dialog显示后，指定宽高--整个dialog窗口
     */
    public void showWindow(int width, int height) {
        super.show();
        Window dialogWindow = this.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.height = width;
        lp.width = height;
        dialogWindow.setAttributes(lp);
    }

    /**
     * 获取底部某个按钮
     * 
     * @param which
     * @return Button [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [DialogInterface.BUTTON_POSITIVE、DialogInterface.BUTTON_NEGATIVE、
     *      DialogInterface.BUTTON_NEUTRAL]
     */
    public Button getButton(int which) {
        return dialogView.getButton(which);

    }

    public ListView getListView() {

        return dialogView.getListView();

    }

    /******************************************************************************/
    /******************************************************************************/

    /**
     * <pre>
     * 1.宿主一定要activity
     * 2.实例化Builder的时候，默认设置标题“操作提示”
     * 3.一些默认设置参考setDefault****
     * 4.只有一个按钮的时候，设置哪个都可以，但是统一设置setPositiveButton
     * 5.getView()表示dialog自定义的view，builder.getAdapter()默认的列表，两个互斥。
     * 6.没有icon的，山东又添加了，后续以规范为主
     * 7.工具类使用{@link }
     * </pre>
     */
    public static class Builder {
        private Context mContext;

        private Drawable mIcon;

        private Drawable mMsgIcon;

        private CharSequence mTitle;

        private CharSequence mMessage;

        private CharSequence mAidMessage;

        private CharSequence mPositiveButtonText;
        private OnClickListener mPositiveButtonListener;

        private CharSequence mNegativeButtonText;
        private OnClickListener mNegativeButtonListener;

        private CharSequence mNeutralButtonText;
        private OnClickListener mNeutralButtonListener;

        public OnClickListener mOnClickListener;

        private View mView;

        private boolean mCancelable = true;

        @Deprecated
        private boolean isShowDel = true;

        private OnKeyListener mOnKeyListener;
        private OnCancelListener mOnCancelListener;

        private ListAdapter mAdapter;
        public AdapterView.OnItemClickListener mOnItemClickListener;

        public OnDismissListener mOnDismissListener;

        private int mDuration = 3000;

        private int mType = TYPE_DEFALUT;

        private Builder() {

        }

        public Builder(Context context) {
            this.mContext = context;
            setDefaultTitle();
        }

        public void setTitle(int titleId) {
            mTitle = mContext.getText(titleId);
        }

        public void setTitle(CharSequence title) {
            mTitle = title;

        }

        public void setMessage(int messageId) {
            mMessage = mContext.getText(messageId);

        }

        public void setMessage(CharSequence message) {
            mMessage = message;

        }

        public void setAidMessage(int messageId) {
            mAidMessage = mContext.getText(messageId);

        }

        public void setAidMessage(CharSequence aidMessage) {
            mAidMessage = aidMessage;
        }

        public void setIcon(int iconId) {
            mIcon = mContext.getResources().getDrawable(iconId);

        }

        public void setIcon(Drawable icon) {
            mIcon = icon;
        }

        public Drawable getMsgIcon() {
            return mMsgIcon;
        }

        public void setMsgIcon(Drawable msgIcon) {
            this.mMsgIcon = msgIcon;
        }

        public void setPositiveButton(int textId) {
            mPositiveButtonText = mContext.getText(textId);

        }

        public void setPositiveButton(CharSequence text) {
            mPositiveButtonText = text;

        }

        public void setPositiveButton(int textId, OnClickListener listener) {
            mPositiveButtonText = mContext.getText(textId);
            mPositiveButtonListener = listener;

        }

        public void setPositiveButton(CharSequence text, OnClickListener listener) {
            mPositiveButtonText = text;
            mPositiveButtonListener = listener;

        }

        public void setNegativeButton(int textId) {
            mNegativeButtonText = mContext.getText(textId);

        }

        public void setNegativeButton(CharSequence text) {
            mNegativeButtonText = text;

        }

        public void setNegativeButton(int textId, OnClickListener listener) {
            mNegativeButtonText = mContext.getText(textId);
            mNegativeButtonListener = listener;

        }

        public void setNegativeButton(CharSequence text, OnClickListener listener) {
            mNegativeButtonText = text;
            mNegativeButtonListener = listener;

        }

        public void setNeutralButton(int textId) {
            mNeutralButtonText = mContext.getText(textId);

        }

        public void setNeutralButton(CharSequence text) {
            mNeutralButtonText = text;

        }

        public void setNeutralButton(int textId, OnClickListener listener) {
            mNeutralButtonText = mContext.getText(textId);
            mNeutralButtonListener = listener;

        }

        public void setNeutralButton(CharSequence text, OnClickListener listener) {
            mNeutralButtonText = text;
            mNeutralButtonListener = listener;

        }

        public void setOnClickListener(OnClickListener listener) {
            mOnClickListener = listener;
        }

        public void setCancelable(boolean cancelable) {
            mCancelable = cancelable;

        }

        public void setOnCancelListener(OnCancelListener onCancelListener) {
            mOnCancelListener = onCancelListener;

        }

        public void setOnKeyListener(OnKeyListener onKeyListener) {
            mOnKeyListener = onKeyListener;

        }

        public void setAdapter(ListAdapter adapter) {
            mAdapter = adapter;
        }

        public void setOnItemClickListener(AdapterView.OnItemClickListener listener) {
            mOnItemClickListener = listener;
        }

        public void setOnDismissListener(OnDismissListener onDismissListener) {
            this.mOnDismissListener = onDismissListener;
        }

        public void setView(int viewid) {
            LayoutInflater layoutInflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            mView = layoutInflater.inflate(viewid, null, false);
        }

        public void setView(View view) {
            mView = view;
        }

        public Context getContext() {
            return mContext;
        }

        public Drawable getIcon() {
            return mIcon;
        }

        public CharSequence getTitle() {
            return mTitle;
        }

        public CharSequence getMessage() {
            return mMessage;
        }

        public CharSequence getAidMessage() {
            return mAidMessage;
        }

        public CharSequence getPositiveButtonText() {
            return mPositiveButtonText;
        }

        public OnClickListener getPositiveButtonListener() {
            return mPositiveButtonListener;
        }

        public CharSequence getNegativeButtonText() {
            return mNegativeButtonText;
        }

        public OnClickListener getNegativeButtonListener() {
            return mNegativeButtonListener;
        }

        public CharSequence getNeutralButtonText() {
            return mNeutralButtonText;
        }

        public OnClickListener getNeutralButtonListener() {
            return mNeutralButtonListener;
        }

        public boolean isCancelable() {
            return mCancelable;
        }

        public OnCancelListener getOnCancelListener() {
            return mOnCancelListener;
        }

        public View getView() {
            return mView;
        }

        public OnKeyListener getOnKeyListener() {
            return mOnKeyListener;
        }

        public ListAdapter getAdapter() {
            return mAdapter;
        }

        public AdapterView.OnItemClickListener getOnItemClickListener() {
            return mOnItemClickListener;
        }

        public OnClickListener getOnClickListener() {
            return mOnClickListener;
        }

        public OnDismissListener getOnDismissListener() {
            return mOnDismissListener;
        }

        public int getDuration() {
            return mDuration;
        }

        public void setDuration(int mDuration) {
            this.mDuration = mDuration;
        }

        @Deprecated
        public boolean isShowDel() {
            return isShowDel;
        }

        @Deprecated
        public void setShowDel(boolean isShowDel) {
            this.isShowDel = isShowDel;
        }

        public int getType() {
            return mType;
        }

        public void setType(int mType) {
            this.mType = mType;
        }

        public void setDefaultTwoButton() {
            setPositiveButton("确定");
            setNegativeButton("取消");
        }

        public void setDefaultOneButton() {
            setPositiveButton("确定");
        }

        public void setDefaultTitle() {
            setTitle("操作提示");
        }

        /**
         * 展示双按钮对话框
         *
         * @param title
         * @param message
         */
        public void showDefaultTwoButton(String title, String message,
                                         OnClickListener onClickListener) {
            this.setDefaultTwoButton();
            this.setTitle(title);
            this.setMessage(message);
            this.setNegativeButton("取消", new OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            this.setPositiveButton("确定", onClickListener);
            this.show();
        }

        /**
         * 展示双按钮对话框
         *
         * @param title
         * @param content
         */
        public void showTwoButtonDialog(String title, String content) {
            this.setDefaultTwoButton();
            this.setTitle(title);
            this.setMessage(content);
            this.show();
        }

        /**
         * 展示双按钮对话框
         *
         * @param title
         * @param content
         */
        public void showTwoButtonDialog(String title, String content, String aidMessage) {
            this.setDefaultTwoButton();
            this.setTitle(title);
            this.setMessage(content);
            this.setAidMessage(aidMessage);
            this.show();
        }

        /**
         * 展示成功对话框
         *
         * @param title
         * @param content
         */
        public void showSuccessDialog(String title, String content,
                                      OnDismissListener onDismissListener) {
            setMsgIcon(mContext.getResources().getDrawable(R.mipmap.dialog_msg_success));
            show2sDismissDialog(title, content, onDismissListener);
        }

        /**
         * 展示警告对话框
         *
         * @param title
         * @param content
         */
        public void showAlertDialog(String title, String content,
                                    OnClickListener onClickListener) {
            this.setTitle(title);
            this.setMessage(content);
            setMsgIcon(mContext.getResources().getDrawable(R.mipmap.dialog_msg_alert));
            this.setDefaultOneButton();
            this.setPositiveButton("知道了", onClickListener);
            this.show();
        }



        /**
         * 展示2s消失对话框
         *
         * @param title
         * @param content
         */
        public void show2sDismissDialog(String title, String content,
                                        OnDismissListener onDismissListener) {
            this.setTitle(title);
            this.setMessage(content);
            this.setDefaultOneButton();
            this.setPositiveButton("知道了", new OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            this.setOnDismissListener(onDismissListener);
            Builder.create2sDismissDialog(this).show();
        }

        /**
         * 创建一个DialogCustom展示
         *
         * @return void [返回类型说明]
         * @exception throws [违例类型] [违例说明]
         * @see [类、类#方法、类#成员]
         */
        public void show() {
            create().show();
        }

        /**
         * 创建一个默认的Dialog
         *
         * @return DialogCustom [返回类型说明]
         * @exception throws [违例类型] [违例说明]
         * @see [类、类#方法、类#成员]
         */
        public DialogCustom create() {
            DialogCustom dialog = new DialogCustom(this);
            return dialog;
        }

        /**
         * 只展示提示信息的Dialog
         *
         * @param context [参数说明]
         * @return void [返回类型说明]
         * @exception throws [违例类型] [违例说明]
         * @see [类、类#方法、类#成员]
         */
        public static DialogCustom createMsg(Context context, String msg) {
            DialogCustom.Builder builder = new DialogCustom.Builder(context);
            builder.setMessage(msg);
            builder.setType(DialogCustom.TYPE_MSG);
            DialogCustom dialog = new DialogCustom(builder);
            return dialog;
        }

        /**
         * 只展示提示信息的Dialog
         *
         * @param context [参数说明]
         * @return void [返回类型说明]
         * @exception throws [违例类型] [违例说明]
         * @see [类、类#方法、类#成员]
         */
        public static DialogCustom createMsg(Context context, String title, String msg) {
            DialogCustom.Builder builder = new DialogCustom.Builder(context);
            builder.setMessage(msg);
            builder.setTitle(title);
            builder.setType(DialogCustom.TYPE_MSG);
            DialogCustom dialog = new DialogCustom(builder);
            return dialog;
        }

        /***
         * 自定义的view的Dialog
         *
         * @param view [参数说明]
         * @param view [是否展示右上角删除按钮]
         * @return void [返回类型说明]
         * @exception throws [违例类型] [违例说明]
         * @see [类、类#方法、类#成员]
         */
        public static DialogCustom createBuisz(View view, boolean isShowDel) {
            DialogCustom.Builder builder = new DialogCustom.Builder(view.getContext());
            builder.setView(view);
            builder.setType(DialogCustom.TYPE_BUSIZ);
            builder.setShowDel(isShowDel);
            DialogCustom dialog = new DialogCustom(builder);
            return dialog;

        }

        /**
         * TOAST，替换掉系统的toast
         *
         * @param context [参数说明]
         * @return void [返回类型说明]
         * @exception throws [违例类型] [违例说明]
         * @see [类、类#方法、类#成员]
         */
        public static DialogCustom createToast(Context context, String msg) {
            DialogCustom.Builder builder = new DialogCustom.Builder(context);
            builder.setMessage(msg);
            builder.setType(DialogCustom.TYPE_TOAST);
            DialogCustom dialog = new DialogCustom(builder);
            return dialog;
        }

        /**
         * 创建2S消失对话框
         *
         * @param builder
         */
        public static DialogCustom create2sDismissDialog(DialogCustom.Builder builder) {

            final DialogCustom dialog = new DialogCustom(builder);

            new Handler(new Handler.Callback() {

                @Override
                public boolean handleMessage(Message msg) {
                    if (null != dialog && dialog.isShowing()) {
                        dialog.dismiss();
                    }
                    return false;
                }
            }).sendEmptyMessageDelayed(0, 2000);
            return dialog;

        }

    }

}
