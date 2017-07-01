package com.bibicar.fragment;

import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import com.bibicar.R;

/**
 * Created by jackie on 2017/6/26 13:37.
 * QQ : 971060378
 * Used as : 用户协议的页面
 */
public class UserProtocolFragment extends BaseFragment {

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_user_protocol;
    }

    @Override
    protected void initView(View view) {
        view.findViewById(R.id.iv_back).setOnClickListener(this);
        ((TextView) view.findViewById(R.id.tv_title)).setText("用户协议");
        view.findViewById(R.id.iv_add).setVisibility(View.INVISIBLE);

        WebView webView = (WebView) view.findViewById(R.id.wv_protocol);
        String protocolUrl = "https://api.bibicar.cn/protocol.html";
        if (!TextUtils.isEmpty(protocolUrl)) {
            webView.getSettings().setJavaScriptEnabled(true);
            webView.getSettings().setSupportZoom(true);
            webView.getSettings().setUseWideViewPort(true);
            webView.getSettings().setLoadWithOverviewMode(true);
            webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
            if (Build.VERSION.SDK_INT < 19) {
                if (Build.VERSION.SDK_INT > 8) {
                    webView.getSettings().setPluginState(WebSettings.PluginState.ON);
                }
            }
            webView.loadUrl(protocolUrl);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                goBack();
                break;
            default:
                break;
        }
    }
}
