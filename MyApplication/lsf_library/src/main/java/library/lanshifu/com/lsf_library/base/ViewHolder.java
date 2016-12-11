
package library.lanshifu.com.lsf_library.base;

import android.util.SparseArray;
import android.view.View;

import library.lanshifu.com.lsf_library.R;

/**
 * 用于listview的ViewHolder </br>用法：</br> 直接调用TextView view =
 * (TextView)ViewHolder.get(view, id);
 * 
 * @author lWX215833
 * @version [版本号, 2014-8-16]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class ViewHolder {
    /**
     * @param view listview的item的converView
     * @param id 控件的id
     * @return 返回控件
     */
    @SuppressWarnings("unchecked")
    public static <T extends View> T get(View view, int id) {
        SparseArray<View> viewHolder = (SparseArray<View>) view.getTag(R.id.viewholder_key);
        if (null == viewHolder) {
            viewHolder = new SparseArray<View>();
            view.setTag(R.id.viewholder_key, viewHolder);
        }
        View childView = viewHolder.get(id);
        if (null == childView) {
            childView = view.findViewById(id);
            viewHolder.put(id, childView);

        }
        return (T) childView;
    }

}
