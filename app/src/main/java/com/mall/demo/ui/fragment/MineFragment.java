package com.mall.demo.ui.fragment;

import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.WindowManager;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.graphics.ColorUtils;

import com.bumptech.glide.Glide;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.interfaces.OnCancelListener;
import com.lxj.xpopup.interfaces.OnConfirmListener;
import com.mall.demo.R;
import com.mall.demo.base.fragment.BaseFragment;
import com.mall.demo.base.utils.GlideImageLoader;
import com.mall.demo.ui.activity.HealthActivity;
import com.mall.demo.ui.activity.LoginActivity;
import com.mall.demo.ui.activity.PlanActivity;
import com.mall.demo.utils.MyConstant;
import com.tencent.mmkv.MMKV;

import butterknife.BindView;
import butterknife.OnClick;


public class MineFragment extends BaseFragment {

    @BindView(R.id.tvMineName)
    AppCompatTextView tvMineName;

    @BindView(R.id.ivMineHead)
    AppCompatImageView ivMineHead;

    public static MineFragment getInstance() {
        MineFragment fragment = new MineFragment();
        return fragment;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.mine_fragment;
    }

    @Override
    protected void initPresenter() {
        //chatPresenter = new MainPresenter(new DataManager());
    }

    @Override
    protected void init() {
        String name = MMKV.defaultMMKV().decodeString(MyConstant.USERNAME);
        String headurl = MMKV.defaultMMKV().decodeString(MyConstant.HEADURL);
        tvMineName.setText(name);
        Glide.with(activity).load(headurl).into(ivMineHead);
    }
    @OnClick({R.id.clMineItem02, R.id.clMineItem03, R.id.clMineItem04})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.clMineItem02: {
                new XPopup.Builder(activity).asConfirm("", "确认要退出登录吗?", "取消", "确定",
                        new OnConfirmListener() {
                            @Override
                            public void onConfirm() {
                                LoginActivity.launchActivity(activity);
                                MMKV.defaultMMKV().encode("login_flag", 0);
                                activity.finish();
                            }
                        }, new OnCancelListener() {
                            @Override
                            public void onCancel() {

                            }
                        }, false).show();
                break;
            }
            case R.id.clMineItem03: {
                HealthActivity.launchActivity(activity);
                break;
            }
            case R.id.clMineItem04: {
                PlanActivity.launchActivity(activity);
                break;
            }
            default: {
                break;
            }
        }
    }

    private void initStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getActivity().getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        if (ColorUtils.calculateLuminance(Color.TRANSPARENT) >= 0.5) {
            // 设置状态栏中字体的颜色为黑色
            getActivity().getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        } else {
            // 跟随系统
            getActivity().getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        initStatusBar();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}