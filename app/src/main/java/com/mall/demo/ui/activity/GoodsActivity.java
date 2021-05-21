package com.mall.demo.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;

import com.blankj.utilcode.util.ToastUtils;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.interfaces.OnSelectListener;
import com.mall.demo.R;
import com.mall.demo.base.activity.BaseActivity;
import com.mall.demo.base.utils.Utils;
import com.mall.demo.bean.MallBo;
import com.mall.demo.custom.CustomEditText;
import com.mall.demo.custom.loading.LoadingView;
import com.mall.demo.utils.InfoUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created with Android Studio.
 * Description:
 *
 * @author: Wangjianxian
 * @date: 2020/01/26
 * Time: 15:26
 */
public class GoodsActivity extends BaseActivity {

    @BindView(R.id.register_toolbar)
    Toolbar mToolbar;

    @BindView(R.id.tvGoodImg)
    ImageView tvGoodImg;

    @BindView(R.id.tvGoodsName)
    TextView tvGoodsName;

    @BindView(R.id.tvGoodsPrice)
    TextView tvGoodsPrice;

    @BindView(R.id.tvGoodsContent)
    TextView tvGoodsContent;

    @BindView(R.id.loading_view)
    LoadingView mLoading;

    private Context mContext;

    public static void launchActivity(Activity activity, MallBo itemBo) {
        Intent intent = new Intent(activity, GoodsActivity.class);
        intent.putExtra("mall_info", itemBo);
        activity.startActivity(intent);
    }


    @Override
    protected int getContentViewId() {
        return R.layout.activity_goods;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        mContext = getApplicationContext();
        initToolbar();
        if (getIntent() != null) {
            MallBo infoBo = (MallBo) getIntent().getSerializableExtra("mall_info");
            if (infoBo != null) {
                InfoUtil.setImg(infoBo.img, tvGoodImg);
                tvGoodsName.setText(infoBo.name);
                tvGoodsPrice.setText("¥" + infoBo.price);
                tvGoodsContent.setText(infoBo.content);
            }
        }
    }

    @Override
    protected void initPresenter() {

    }

    private void initToolbar() {
        getWindow().setStatusBarColor(Utils.getColor(mContext));
        mToolbar.setBackgroundColor(Utils.getColor(mContext));
        mToolbar.setTitle("商品详情");
        mToolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }


    @OnClick(R.id.tvBuyBtn)
    public void buy() {
        ToastUtils.showShort("去购买");
    }

    private void showSelectPop() {
       new XPopup.Builder(activity)
                .isDarkTheme(false)
                .isDestroyOnDismiss(true)
                .asCenterList("请选择需要注册的角色", new String[]{"学生", "老师", "管理员", "关闭弹窗"},
                        new OnSelectListener() {
                            @Override
                            public void onSelect(int position, String text) {
                                if (position != 3) {
                                    //发起注册
                                    //startAnim();
                                    //mPresenter.register(mUsername.getText().toString(), mPassword.getText().toString());
                                    MainActivity.launchActivity(activity);
                                }
                            }
                        }).show();
    }

    private void startAnim() {
        mLoading.setVisibility(View.VISIBLE);
        mLoading.startTranglesAnimation();
    }

    private void stopAnim() {
        mLoading.setVisibility(View.GONE);
    }

}
