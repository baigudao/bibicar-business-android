package com.bibicar.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.bibicar.R;

/**
 * Created by jackie on 2017/6/20 15:59.
 * QQ : 971060378
 * Used as : MorePopupWindow
 */
public class MorePopupWindow extends PopupWindow implements View.OnClickListener {

    public enum MORE_POPUP_WINDOW_TYPE {
        TYPE_ADD_PERSONAL_CARD,//注册中的添加个人名片
        TYPE_SALE_CAR,
        TYPE_ADD_PICTURE_MODEL,
        TYPE_IN_MY_LOVE_CAR,
        TYPE_IN_MY_SALE_CAR,
        TYPE_SAVE_PHOTO;
    }

    private MorePopupWindowClickListener morePopupWindowClickListener;
    private Activity context;
    private MORE_POPUP_WINDOW_TYPE type;

    private int mCurrentIndex1;
    private int mCurrentIndex2;
    private int mCurrentIndex3;

    public interface MorePopupWindowClickListener {
        void onFirstBtnClicked();

        void onSecondBtnClicked();

        void onThirdBtnClicked();

        void onFourthBtnClicked();

        void onCancelBtnClicked();
    }

    public MorePopupWindow(Activity context, MorePopupWindowClickListener listener, MORE_POPUP_WINDOW_TYPE type) {
        super(context);
        setContentView(LayoutInflater.from(context).inflate(R.layout.popup_window_more, null));
        this.morePopupWindowClickListener = listener;
        this.context = context;
        this.type = type;
        // initView(morePopup, type);
        // setWindowLayoutMode(LayoutParams.MATCH_PARENT,
        // LayoutParams.MATCH_PARENT);
        setFocusable(true);
        // 设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        // 设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);

        // 设置弹出窗体需要软键盘，
        this.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
        // 再设置模式，和Activity的一样，覆盖，调整大小。
        this.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        // 设置SelectPicPopupWindow弹出窗体可点击
        // this.setFocusable(true);
        // 设置SelectPicPopupWindow弹出窗体动画效果
        // 实例化一个ColorDrawable颜色为半透明
        // ColorDrawable dw = new ColorDrawable(0xb0000000);
        // 设置SelectPicPopupWindow弹出窗体的背景
        setAnimationStyle(R.style.PopupWindowAnimation);
        this.setBackgroundDrawable(null);
        // mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
        getContentView().setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int height = getMoreMenuView().getTop();
                int y = (int) event.getY();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y < height) {
                        dismiss();
                    }
                }
                return true;
            }
        });

        getContentView().setFocusableInTouchMode(true); // 设置view能够接听事件 标注2
        getContentView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View arg0, int arg1, KeyEvent arg2) {
                if (arg1 == KeyEvent.KEYCODE_BACK) {
                    dismiss();
                }
                return false;
            }
        });
    }

    public void setCurrentIndex(int index1, int index2, int index3) {
        mCurrentIndex1 = index1;
        mCurrentIndex2 = index2;
        mCurrentIndex3 = index3;
    }

    public void initView() {
        if (type == null) {
            return;
        }
        //第一个按钮
        getFirstBtn().setVisibility(View.VISIBLE);
        getPopupFirstLine().setVisibility(View.VISIBLE);
        getFirstBtn().setOnClickListener(this);
        //        getFirstBtn().setTextColor(context.getResources().getColor(R.color.color_194_158_103));

        //第二个按钮
        getSecondBtn().setVisibility(View.VISIBLE);
        getSecondBtn().setOnClickListener(this);
        getPopupSecondLine().setVisibility(View.VISIBLE);
        //        getSecondBtn().setTextColor(context.getResources().getColor(R.color.color_194_158_103));

        //第三个按钮
        getThirdBtn().setVisibility(View.GONE);
        getThirdBtn().setOnClickListener(this);
        getPopupThirdLine().setVisibility(View.GONE);
        getThirdBtn().setTextColor(context.getResources().getColor(R.color.color_194_158_103));

        //第四个按钮
        getFourthBtn().setTextColor(context.getResources().getColor(R.color.color_194_158_103));
        getFourthBtn().setOnClickListener(this);


        if (type == MORE_POPUP_WINDOW_TYPE.TYPE_ADD_PERSONAL_CARD) {
            getFirstBtn().setText(context.getResources().getString(R.string.take_picture));
            getSecondBtn().setText(context.getResources().getString(R.string.take_from_album));
        }
        getCancelBtn().setTextColor(context.getResources().getColor(R.color.color_111_111_111));
        getCancelBtn().setOnClickListener(this);
    }


    private Button getFirstBtn() {
        return (Button) getContentView().findViewById(R.id.btnFirst);
    }

    private Button getSecondBtn() {
        return (Button) getContentView().findViewById(R.id.btnSecond);
    }

    private Button getThirdBtn() {
        return (Button) getContentView().findViewById(R.id.btnThird);
    }

    private Button getFourthBtn() {
        return (Button) getContentView().findViewById(R.id.btnFourth);
    }

    private Button getCancelBtn() {
        return (Button) getContentView().findViewById(R.id.btnCancel);
    }

    private Button getFifthBtn() {
        return (Button) getContentView().findViewById(R.id.btnFifth);
    }

    private View getViewG() {
        return getContentView().findViewById(R.id.view_full);
    }

    private View getPopupFirstLine() {
        return getContentView().findViewById(R.id.popupFirstLine);
    }

    private View getPopupSecondLine() {
        return getContentView().findViewById(R.id.popupSecondLine);
    }

    private View getPopupThirdLine() {
        return getContentView().findViewById(R.id.popupThirdLine);
    }

    private View getPopupFourthLine() {
        return getContentView().findViewById(R.id.popupFourthLine);
    }

    @Override
    public void showAtLocation(View parent, int gravity, int x, int y) {
        super.showAtLocation(parent, gravity, x, y);
        viewShowLocation();
    }

    private void viewShowLocation() {
        ObjectAnimator fadeAnim = ObjectAnimator.ofFloat(getViewG(), "alpha", 0.5f).setDuration(250);
        fadeAnim.setStartDelay(250);
        fadeAnim.start();
    }

    @Override
    public void update() {
        super.update();
    }

    private LinearLayout getMoreMenuView() {
        return (LinearLayout) getContentView().findViewById(R.id.pop_layout);
    }


    @Override
    public void dismiss() {
        ObjectAnimator fadeAnim = ObjectAnimator.ofFloat(getViewG(), "alpha", 0f).setDuration(250);
        fadeAnim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
            }

            public void onAnimationEnd(Animator animation) {
                MorePopupWindow.super.dismiss();
            }
        });
        fadeAnim.start();
    }

    @Override
    public void onClick(View v) {
        dismiss();
        switch (v.getId()) {
            case R.id.btnFirst:
                if (morePopupWindowClickListener != null) {
                    morePopupWindowClickListener.onFirstBtnClicked();
                }
                break;
            case R.id.btnSecond:
                if (morePopupWindowClickListener != null) {
                    morePopupWindowClickListener.onSecondBtnClicked();
                }
                break;
            case R.id.btnThird:
                if (morePopupWindowClickListener != null) {
                    morePopupWindowClickListener.onThirdBtnClicked();
                }
                break;
            case R.id.btnFourth:
                if (morePopupWindowClickListener != null) {
                    morePopupWindowClickListener.onFourthBtnClicked();
                }
                break;
            case R.id.btnCancel:
                if (morePopupWindowClickListener != null) {
                    morePopupWindowClickListener.onCancelBtnClicked();
                }
                break;
            default:
                break;
        }
    }
}