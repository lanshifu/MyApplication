
package library.lanshifu.com.lsf_library.widget.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import library.lanshifu.com.lsf_library.R;
import library.lanshifu.com.lsf_library.utils.StringUtil;


public class DialogViewDefaultV20 extends DialogViewDefaultV10 {

    private ViewGroup bottomView;

    public DialogViewDefaultV20(Dialog dialog, DialogCustom.Builder builder) {
        super(dialog, builder);
    }

    @Override
    public int getContentView() {
        return R.layout.layout_alert_dialog_v2;
    }

    @Override
    public void processTitle() {

        if (StringUtil.isNotEmpty(builder.getTitle()) || builder.getIcon() != null) {
            // title
            ViewStub vs = (ViewStub) dialog.findViewById(R.id.vs_dialog_title);
            View titleView = vs.inflate();
            TextView title = (TextView) titleView.findViewById(R.id.tv_dialog_title);
            title.setText(builder.getTitle());
            title.setCompoundDrawablesWithIntrinsicBounds(builder.getIcon(), null, null, null);
        }

    }

    @Override
    public void processContent() {
        boolean viewExist = builder.getView() != null;
        boolean listExist = builder.getAdapter() != null;

        if (viewExist && listExist) {
            throw new IllegalArgumentException("自定义view和列表不能共存");
        }

        TextView msgView = (TextView) dialog.findViewById(R.id.dialog_content_messages);
        ImageView msgIcon = (ImageView) dialog.findViewById(R.id.dialog_content_messages_icon);
        
        msgView.setVisibility(View.GONE);
        msgIcon.setVisibility(View.GONE);
        
        if (StringUtil.isNotEmpty(builder.getMessage())) {
        	
        	if(StringUtil.isNotEmpty(builder.getAidMessage())){
             	 // double msg
                 LinearLayout parent = (LinearLayout) dialog.findViewById(R.id.dialog_content_msgll);
                 parent.setVisibility(View.VISIBLE);
                 TextView tvMainMsg = (TextView) parent.findViewById(R.id.dialog_content_main_msg);
                 TextView tvAidMsg = (TextView) parent.findViewById(R.id.dialog_content_aid_msg);
                 tvMainMsg.setText(builder.getMessage());
                 tvAidMsg.setText(builder.getAidMessage());
                 
             } else {
            	 // msg
                 msgView.setText(builder.getMessage());
                 msgView.setVisibility(View.VISIBLE);
                 if (null != builder.getMsgIcon()) {
                	 msgIcon.setImageDrawable(builder.getMsgIcon());
                     msgIcon.setVisibility(View.VISIBLE);
                 }
             }

        } 

        if (builder.getView() != null) {
            // view
            LinearLayout parent = (LinearLayout) dialog.findViewById(R.id.dialog_content_ll);
            parent.setVisibility(View.VISIBLE);
            parent.addView(builder.getView());
        }

        if (builder.getAdapter() != null) {
            // listview
            ListView listView = (ListView) dialog.findViewById(R.id.dialog_content_list);
            listView.setVisibility(View.VISIBLE);
            listView.setAdapter(builder.getAdapter());
            listView.setOnItemClickListener(builder.getOnItemClickListener());
        }

    }

    @Override
    public void processBottomButton(View.OnClickListener onClick) {

        if (StringUtil.isNotEmpty(builder.getPositiveButtonText())
                || builder.getPositiveButtonListener() != null) {
            // PositiveButton
            setBottomBtnClick(DialogInterface.BUTTON_POSITIVE, onClick);

        }

        if (StringUtil.isNotEmpty(builder.getNegativeButtonText())
                || builder.getNegativeButtonListener() != null) {
            // NegativeButton
            setBottomBtnClick(DialogInterface.BUTTON_NEGATIVE, onClick);

        }

        if (StringUtil.isNotEmpty(builder.getNeutralButtonText())
                || builder.getNeutralButtonListener() != null) {
            // NeutralButton
            setBottomBtnClick(DialogInterface.BUTTON_NEUTRAL, onClick);

        }

    }

    @Override
    public void adjustMsgLines() {

        TextView msgView = (TextView) dialog.findViewById(R.id.dialog_content_messages);
        if (msgView != null && StringUtil.isNotEmpty(builder.getMessage())) {
            int lines = msgView.getLineCount();
            if (lines > 1) {
                Resources res = builder.getContext().getResources();
                msgView.setPadding(res.getDimensionPixelSize(R.dimen.spacing_8x), 0,
                        res.getDimensionPixelSize(R.dimen.spacing_8x), 0);
                msgView.setGravity(Gravity.LEFT | Gravity.CENTER_VERTICAL);
            }
        }

    }

    /**
     * 列表重新计算高度
     * 
     * @param listView [参数说明]
     * @return void [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    private void setListViewHeightBasedOnChildren(ListView listView) {
        // 获取ListView对应的Adapter
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null || listAdapter.getCount() == 0) {
            return;
        }

        float maxCount = 5.5f;
        maxCount = Math.min(listAdapter.getCount(), maxCount);

        int totalHeight = 0;
        // listAdapter.getCount()返回数据项的数目

        View listItem = listAdapter.getView(0, null, listView);
        listItem.measure(0, 0); // 计算子项View 的宽高
        totalHeight = (int) (listItem.getMeasuredHeight() * maxCount); // 统计所有子项的总高度

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);

    }

    /**
     * 重新计算高度
     * 
     * @return void [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    protected void resetHeight() {
        if (getListView().getVisibility() == View.VISIBLE) {
            setListViewHeightBasedOnChildren(getListView());
        }
    }

}
