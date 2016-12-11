
package library.lanshifu.com.lsf_library.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class AdapterAction<T> {
    private List<T> list;

    protected Context context;
    
    private int column = 1;

    public AdapterAction(Context context, List<T> list) {
        this.list = list;
        this.context = context;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public void clear() {
        this.list.clear();
    }

    public void addAll(List<T> list) {
        this.list.addAll(list);
    }

    public Context getContext() {
        return context;
    }

    public int getCount() {
        if (list == null) {
            return 0;
        }
        int col = getColumn();
        if (list.size() % col == 0) {
            return list.size() / col;
        }
        return list.size() / col + 1;
    }

    protected int getColumn() {
        return this.column;
    }

    public void setColumn(int column) {
		this.column = column;
	}

	public int getFactCount() {
        return getCount();
    }

    public T getItem(int position) {
        if (position < list.size()) {
            return list.get(position);
        }
        return null;
    }

    public long getItemId(int position) {
        return position;
    }

    protected View inflate(int layoutResID, ViewGroup root) {
        LayoutInflater layoutInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(layoutResID, root, false);
        return view;
    }

}
