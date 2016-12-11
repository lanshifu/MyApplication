
package library.lanshifu.com.lsf_library.widget.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import library.lanshifu.com.lsf_library.R;
import library.lanshifu.com.lsf_library.utils.StringUtil;


public class DialogViewDefaultV10 extends DialogDefaultImpl {

    protected DialogCustom.Builder builder;

    protected Dialog dialog;

    private ViewGroup bottomView;

    public DialogViewDefaultV10(Dialog dialog, DialogCustom.Builder builder) {
        this.dialog = dialog;
        this.builder = builder;
    }

    @Override
    public int getContentView() {
        return -1;
    }

    @Override
    public void processTitle() {


    }

    @Override
    public void processContent() {


    }

    @Override
    public void processBottomButton(View.OnClickListener onClick) {



    }

    private void checkIfSingleBtn() {
        if (bottomView != null) {

            int visableCount = 0;
            int visable = 0;
            for (int i = 0; i < bottomView.getChildCount(); i++) {
                if (View.VISIBLE == bottomView.getChildAt(i).getVisibility()) {
                    visableCount++;
                    visable = i;
                }
                if (visableCount > 1) {
                    break;
                }
            }
            if (visableCount == 1) {
                // 只有个buttone的，更换背景
                bottomView.getChildAt(visable).setBackgroundResource(
                        R.drawable.dialog_singlebtn_seslector);
            }
        }

    }

    protected void setBottomBtnClick(final int which, final View.OnClickListener bottomClickListener) {
        if (bottomView == null) {
            ViewStub vsBottom = (ViewStub) dialog.findViewById(R.id.vs_dialog_bottom);
            bottomView = (ViewGroup) vsBottom.inflate();
        }

        switch (which) {
            case DialogInterface.BUTTON_POSITIVE:

                Button positiveButton = (Button) bottomView
                        .findViewById(R.id.dialog_bottom_positive_btn);
                positiveButton.setVisibility(View.VISIBLE);
                positiveButton.setText(builder.getPositiveButtonText());
                positiveButton.setTag(which);
                positiveButton.setOnClickListener(bottomClickListener);

                break;

            case DialogInterface.BUTTON_NEGATIVE:

                Button negativeButton = (Button) bottomView
                        .findViewById(R.id.dialog_bottom_negative_btn);
                negativeButton.setVisibility(View.VISIBLE);
                negativeButton.setText(builder.getNegativeButtonText());
                negativeButton.setTag(which);
                negativeButton.setOnClickListener(bottomClickListener);

                bottomView.findViewById(R.id.dialog_bottom_positive_line).setVisibility(
                        View.VISIBLE);

                break;

            case DialogInterface.BUTTON_NEUTRAL:

                Button neutralButton = (Button) bottomView
                        .findViewById(R.id.dialog_bottom_neutral_btn);
                neutralButton.setVisibility(View.VISIBLE);
                neutralButton.setText(builder.getNeutralButtonText());
                neutralButton.setTag(which);
                neutralButton.setOnClickListener(bottomClickListener);

                break;

            default:
                break;
        }

    }

    @Override
    public Button getButton(int which) {
        if (bottomView == null) {
            return null;
        }

        switch (which) {
            case DialogInterface.BUTTON_POSITIVE:

                return (Button) bottomView.findViewById(R.id.dialog_bottom_positive_btn);

            case DialogInterface.BUTTON_NEGATIVE:

                return (Button) bottomView.findViewById(R.id.dialog_bottom_negative_btn);

            case DialogInterface.BUTTON_NEUTRAL:

                return (Button) bottomView.findViewById(R.id.dialog_bottom_neutral_btn);

            default:
                break;
        }
        return null;

    }

    @Override
    public ListView getListView() {

        return (ListView) dialog.findViewById(R.id.dialog_content_list);

    }

    @Override
    public void show() {

        if (builder.getType() == DialogCustom.TYPE_MSG) {
            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    dialog.dismiss();

                }
            }, builder.getDuration());
        }

        resetHeight();

    }

    @Override
    public void show(int width, int height) {

        View view = dialog.findViewById(R.id.dialog_content_parent);
        LayoutParams lp = view.getLayoutParams();
        lp.height = height;
        lp.width = width;
        view.setLayoutParams(lp);
    }




    /**
     * 重新计算高度
     * 
     * @return void [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    protected void resetHeight() {

    }

}
