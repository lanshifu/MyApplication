
package library.lanshifu.com.lsf_library.widget.dialog;

import android.view.View;
import android.widget.Button;
import android.widget.ListView;

/**
 * 对话框的布局拆割
 * 
 * @author [wWX173427,王木宗]
 * @date 2015-1-8
 */
public interface IDialogViewInterface {

    /**
     * 对话框的layout
     * 
     * @return [参数说明]
     */
    public int getContentView();

    /**
     * 处理对话框的顶部-标题 [参数说明]
     */
    public void processTitle();

    /**
     * 处理对话框的中间-内容区：msg，listview，自定义view [参数说明]
     */
    public void processContent();

    /**
     * 处理对话框的底部-按钮
     * 
     * @param onClick [参数说明]
     */
    public void processBottomButton(View.OnClickListener onClick);

    /**
     * 显示 [参数说明]
     */
    public void show();

    /**
     * 指定宽高
     * 
     * @param width
     * @param height [参数说明]
     */
    public void show(int width, int height);

    /**
     * 获取按钮
     * 
     * @param which
     * @return [参数说明]
     */
    public Button getButton(int which);

    /**
     * 获取内容区的列表
     * 
     * @return [参数说明]
     */
    public ListView getListView();

    /**
     * 内容区的message如果多行调整布局方式，单行居中，多行靠左
     */
    public void adjustMsgLines();

}
