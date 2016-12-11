package library.lanshifu.com.lsf_library.utils;

import android.app.Activity;
import android.content.Context;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

import library.lanshifu.com.lsf_library.R;


/**
 * @author cWX146282 说 明: <activity和view的动画控制类>
 */
public class AnimUtil {
    /* 淡入淡出效果 */
    public static final int ANIM_STYLE_FADING = 0;
    /* 放大淡出效果 */
    public static final int ANIM_STYLE_ZOOM_OUT = 1;
    /* 转动淡出效果1 */
    public static final int ANIM_STYLE_TURN_OUT_ONE = 2;
    /* 转动淡出效果2 */
    public static final int ANIM_STYLE_TURN_OUT_TWO = 3;
    /* 左上角展开淡出效果 */
    public static final int ANIM_STYLE_OF_THE_TOP_LEFT_CORNER = 4;
    /* 压缩变小淡出效果 */
    public static final int ANIM_STYLE_SMALLER_COMPRESSION_FADE_OUT = 5;
    /* 右往左推出效果 */
    public static final int ANIM_STYLE_RIGHT_TO_LEFT_TO_LAUNCH = 6;
    /* 下往上推出效果 */
    public static final int ANIM_STYLE_NEXT_UPGRADE_TO_LAUNCH = 7;
    /* 左右交错效果 */
    public static final int ANIM_STYLE_STAGGERED_AROUND = 8;
    /* 缩小效果 */
    public static final int ANIM_STYLE_NARROW = 9;
    /* 上下交错效果 */
    public static final int ANIM_STYLE_STAGGERED_UP_AND_DOWN = 10;

    /**
     * activity的过度动画 A-->B时 A的startActivityForResult或者startActivity之后调用
     * 若从B-->A也需要设置动画，则在B中调用finish后调用下列方法
     * <p>
     * 注意事项：
     * 1、android系统版本2.0以下，这个没办法，想其他办法解决切换动画吧。
     * 2、在ActivityGroup等的嵌入式Activity中，这个比较容易解决，用如下方法就可以了：
     * this.getParent().overridePendingTransition 就可以解决。
     * 3、在一个Activity的内部类中，或者匿名类中，这时候只好用handler来解决了。
     *
     * @param activity
     * @param animStyle
     */
    public static void startActivityAnim(Activity activity, int animStyle) {
        switch (animStyle) {
            case ANIM_STYLE_FADING:
                activity.overridePendingTransition(R.anim.fade, R.anim.hold);
                break;
            case ANIM_STYLE_ZOOM_OUT:
                activity.overridePendingTransition(R.anim.my_scale_action,
                        R.anim.my_alpha_action);
                break;
            case ANIM_STYLE_TURN_OUT_ONE:
                activity.overridePendingTransition(R.anim.scale_rotate,
                        R.anim.my_alpha_action);
                break;
            case ANIM_STYLE_TURN_OUT_TWO:
                activity.overridePendingTransition(R.anim.scale_translate_rotate,
                        R.anim.my_alpha_action);
                break;
            case ANIM_STYLE_OF_THE_TOP_LEFT_CORNER:
                activity.overridePendingTransition(R.anim.scale_translate,
                        R.anim.my_alpha_action);
                break;
            case ANIM_STYLE_SMALLER_COMPRESSION_FADE_OUT:
                activity.overridePendingTransition(R.anim.hyperspace_in,
                        R.anim.hyperspace_out);
                break;
            case ANIM_STYLE_RIGHT_TO_LEFT_TO_LAUNCH:
                activity.overridePendingTransition(R.anim.push_left_in,
                        R.anim.push_left_out);
                break;
            case ANIM_STYLE_NEXT_UPGRADE_TO_LAUNCH:
                activity.overridePendingTransition(R.anim.push_up_in,
                        R.anim.push_up_out);
                break;
            case ANIM_STYLE_NARROW:
                activity.overridePendingTransition(R.anim.wave_scale,
                        R.anim.my_alpha_action);
                break;
            case ANIM_STYLE_STAGGERED_UP_AND_DOWN:
                activity.overridePendingTransition(R.anim.slide_up_in,
                        R.anim.slide_down_out);
                break;
            default://ANIM_STYLE_STAGGERED_AROUND
                activity.overridePendingTransition(R.anim.slide_left,
                        R.anim.slide_right);
                break;
        }
    }

    /**
     * 设置父类为ViewGroup组件的 动画效果
     *
     * @param context
     * @param viewGroup
     * @param animId    见anim目录
     */
    public static void setViewGroupAnimation(Context context, ViewGroup viewGroup, int animId) {
        Animation animation = (Animation) AnimationUtils.loadAnimation(context, animId);
        if (animation != null) {
            LayoutAnimationController lac = new LayoutAnimationController(animation);
            lac.setOrder(LayoutAnimationController.ORDER_NORMAL);  //设置动画执行顺序
            lac.setDelay(0.1f);   //设置动画与动画之间的间隔时间
            viewGroup.setLayoutAnimation(lac);
        }
    }


}
