
package library.lanshifu.com.lsf_library.base;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;

import java.util.ArrayList;
import java.util.List;

import library.lanshifu.com.lsf_library.R;

public abstract class BaseAdapter<T> extends android.widget.BaseAdapter {

    protected Context context;
    private ViewGroup viewGroup;

    private ViewGroup viewParent;

    private View noContentView;

    AdapterAction<T> adapterAction;

    public List<T> getList() {
        return adapterAction.getList();
    }

    public void setList(List<T> list) {
        adapterAction.setList(list);
    }

    /**
     * <默认构造函数> 因为涉及到theme，布局需要当前activity的content，所以强制传Context
     */
    @SuppressWarnings("unused")
    private BaseAdapter() {
        init(null, new ArrayList<T>());
    }

    /**
     * 因为涉及到theme，布局需要当前activity的content，所以强制传Context
     */
    @Deprecated
    public BaseAdapter(List<T> list) {
        init(null, list);
    }

    public BaseAdapter(Context context) {
        init(context, new ArrayList<T>());
    }

    public BaseAdapter(Context context, List<T> list) {
        init(context, list);
    }

    private void init(Context context, List<T> list) {
        adapterAction = new AdapterAction(context, list);
        this.context = adapterAction.getContext();
    }


    public void clear() {
        adapterAction.clear();
        notifyDataSetChanged();
    }

    public void addAll(List<T> list) {
        adapterAction.addAll(list);
        showContentView();
        notifyDataSetChanged();
    }

    public Context getContext() {
        return context;
    }

    /**
     * list为空，会显示没有数据的图片, 需传入viewGroup
     */
    public void addAllCanEmpty(List<T> list, ViewGroup viewGroup) {
        if (list == null || list.isEmpty()) {
            showNoContentView(viewGroup);
            return;
        } else {
            addAll(list);
        }
    }

    /**
     * 再次刷新有数据时，需要把viewGroup显示出来
     */
    private void showContentView() {
        if (viewGroup == null || viewGroup.isShown() || viewParent == null) {
            return;
        }

        viewParent.removeView(noContentView);
        viewGroup.setVisibility(View.VISIBLE);
    }

    /**
     * 如果addAllCanEmpty的参数list为空调用此方法，也可在updateResponseError直接调用此方法
     * 参数viewGroup不能为空，viewGroup为使用此adapter的ListView或GridView
     */
    public void showNoContentView(ViewGroup viewGroup) {
        if (this.viewGroup == null) {
            setNoContentView(viewGroup);
        }
        if (noContentView.isShown()) {
            return;
        }
        viewParent.addView(noContentView, new LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT));
        this.viewGroup.setVisibility(View.GONE);
    }

    private void setNoContentView(ViewGroup viewGroup) {
        this.viewGroup = viewGroup;
        this.viewParent = (ViewGroup) viewGroup.getParent();

        noContentView = inflate(R.layout.esoplv_no_content, null);
        noContentView.setVisibility(View.VISIBLE);
    }

    @Override
    public int getCount() {
        return adapterAction.getCount();
    }

    protected int getColumn() {
        return adapterAction.getColumn();
    }

    protected void setColumn(int column) {
        adapterAction.setColumn(column);
    }


    @Override
    public T getItem(int position) {
        return adapterAction.getItem(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * 加载布局，推荐使用inflate(int layoutResID, ViewGroup root)
     *
     * @param layoutResID
     * @return View [返回类型说明]
     * @deprecated Use {@link #inflate(int, ViewGroup)}
     */
    protected View inflate(int layoutResID) {
        return adapterAction.inflate(layoutResID, null);
    }

    protected View inflate(int layoutResID, ViewGroup root) {
        return adapterAction.inflate(layoutResID, root);
    }


    protected <V extends View> V get(View convertView, int resId) {
        return ViewHolder.<V>get(convertView, resId);
    }

}
