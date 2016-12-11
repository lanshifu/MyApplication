
package library.lanshifu.com.lsf_library.widget.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RadioButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import library.lanshifu.com.lsf_library.R;
import library.lanshifu.com.lsf_library.utils.Dictitem;
import library.lanshifu.com.lsf_library.utils.T;

/**
 * 单选对话框,可自定义adapter
 * 
 * @author cWX146282 说 明: <说明>
 */
public class AbsSingleChoicePopup {

    public interface onSingleChoice {
        public void onCancelClick();

        public void onEnsureClick(Dictitem bean, int selectPos);
    }
    
    /**
     * 单选列表选中 接口
     */
    public interface onItemSelect {
        public void setSelectedPos(int selectPos);
    }

    private DialogCustom mDialog;

    private onSingleChoice onSingleChoice;

    private int selectPos = -1;

    private boolean hasBottom = false;

    private DialogBuilder builder;

    private library.lanshifu.com.lsf_library.base.BaseAdapter adapter;

    private static Map<String, Integer> selectMap = new HashMap<String, Integer>();

    private String popupkey;



    public AbsSingleChoicePopup(Context context, String title, List<Dictitem> beans) {
        this.adapter = new GroupPopAdapter(context, beans);
        initData(context, title, true);
    }

    public AbsSingleChoicePopup(Context context, String title, String[] items) {
        List<Dictitem> beans = new ArrayList<Dictitem>();
        for (int i = 0; i < items.length; i++) {
            Dictitem dict = new Dictitem(items[i], items[i]);
            beans.add(dict);
        }
        this.adapter = new GroupPopAdapter(context, beans);
        initData(context, title, true);

    }

    /**
     * 此处初始化数据，若 title或者adapter为空，则无title和默认单选适配器
     * 
     * @param context
     * @param title
     * @param
     * @param
     */
    protected void initData(final Context context, final String title, final boolean canceledTouch) {

        selectPos = selectMap.get(title) == null ? -1 : selectMap.get(title);

        popupkey = title;

        builder = new DialogBuilder(context);

        builder.setOnKeyListener(new OnKeyListener() {

            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (canceledTouch) {
                    if (onSingleChoice != null) {
                        onSingleChoice.onCancelClick();
                    }
                    return false;
                }
                return true;
            }
        });
        builder.setOnCancelListener(new DialogInterface.OnCancelListener() {

            @Override
            public void onCancel(DialogInterface dialog) {
                if (onSingleChoice != null) {
                    onSingleChoice.onCancelClick();
                }
            }
        });

        builder.setTitle(title);

        builder.setAdapter(adapter);

        builder.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectPos = position;
                performEnsureClick(!hasBottom);

            }
        });

    }

    public void show() {

        // 处理异常
        if (adapter.getList() == null) {
            T.showShort("选项为空，请联系开发人员");
            return;

        }

        if (hasBottom) {
            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    performEnsureClick(true);
                }
            });
            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {

                    if (onSingleChoice != null) {
                        onSingleChoice.onCancelClick();
                    }

                    dialog.dismiss();
                }
            });
        }

        mDialog = new DialogCustom(builder);
        mDialog.show();

        if (selectPos >= 0) {
        	ListView listView= mDialog.getListView();
        	if(null!=listView){
        		listView.setSelection(selectPos);
        	}
        }

    }

    /**
     * 改为必有
     * 
     * @param hasBottom [参数说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     * @deprecated
     */
    public void setHasBottom(boolean hasBottom) {
        this.hasBottom = hasBottom;
    }

    public void setSelectPos(int selectPos) {
        this.selectPos = selectPos;
    }

    public void setOnSingleChoice(onSingleChoice onSingleChoice) {
        this.onSingleChoice = onSingleChoice;
    }

    /**
     * 选中情况下操作：
     */
    private void performEnsureClick(boolean dismiss) {

        if (selectPos < 0) {
            return;
        }

        Adapter adapter = builder.getAdapter();

        if (!dismiss) {
            if (adapter instanceof onItemSelect) {
                ((onItemSelect) adapter).setSelectedPos(selectPos);
            }

        } else {
            Dictitem dictitem = (Dictitem) adapter.getItem(selectPos);

            if (onSingleChoice != null) {
                onSingleChoice.onEnsureClick(dictitem, selectPos);
            }

            if (selectPos >= 0) {
                selectMap.put(popupkey, selectPos);
            }

            mDialog.dismiss();
        }

    }

    private class GroupPopAdapter extends library.lanshifu.com.lsf_library.base.BaseAdapter<Dictitem> implements onItemSelect {

        public GroupPopAdapter(Context context, List<Dictitem> list) {
            super(context, list);
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            if (convertView == null) {
                convertView = inflate(getConvertViewId(), parent);
            }

            Dictitem info = getItem(position);


            RadioButton itemRb = (RadioButton) convertView
                    .findViewById(R.id.singlechoice_dialog_item_select);

            itemRb.setChecked(selectPos == position);
            itemRb.setText(info.getDictname());

            itemRb.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    selectPos = position;
                    performEnsureClick(!hasBottom);
                }
            });


            return convertView;

        }

        @Override
        public void setSelectedPos(int selectPos) {
            notifyDataSetChanged();
        }

    }

    private int getConvertViewId() {
        return R.layout.singlechoice_dialog_item_v2;
    }

    public void dismiss() {
        if (null != mDialog) {
            mDialog.dismiss();
        }
    }
    
    public void setCanceledOnTouchOutside(boolean cancel){
    	if (null != mDialog) {
    		mDialog.setCanceledOnTouchOutside(cancel);
    	}
    }

}
