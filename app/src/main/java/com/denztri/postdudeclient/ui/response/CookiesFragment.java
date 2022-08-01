package com.denztri.postdudeclient.ui.response;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.webkit.WebViewAssetLoader;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.denztri.postdudeclient.R;
import com.denztri.postdudeclient.databinding.FragmentCookiesBinding;
import com.denztri.postdudeclient.databinding.FragmentHeadersBinding;
import com.denztri.postdudeclient.utils.LocalContentWebViewClient;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class CookiesFragment extends Fragment {
    private FragmentCookiesBinding binding;

    private ResponseViewModel viewModel;
    private WebView webView;
    private final WebViewAssetLoader assetLoader;

    public CookiesFragment(Context context) {
        // Required empty public constructor
        assetLoader = new WebViewAssetLoader.Builder()
                .addPathHandler("/assets/", new WebViewAssetLoader.AssetsPathHandler(context))
                .addPathHandler("/res/", new WebViewAssetLoader.ResourcesPathHandler(context))
                .build();
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentCookiesBinding.inflate(inflater,container,false);

        viewModel = new ViewModelProvider(requireActivity()).get(ResponseViewModel.class);
        webView = binding.resCookieWebview;

        webView.setWebChromeClient(new WebChromeClient());
        WebSettings webSetting = webView.getSettings();
        webSetting.setJavaScriptEnabled(true);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel.getData().observe(requireActivity(), s -> {
            binding.resHeadProgressbar.setVisibility(View.GONE);
            webView.setWebViewClient(new LocalContentWebViewClient(assetLoader));
            try {
                String postCookies = "cookies=" + URLEncoder.encode(s.get(2), "UTF-8");
                Log.d("WOW", postCookies);
                webView.loadUrl("https://appassets.androidplatform.net/assets/index.html?" + postCookies);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
//
            webView.setVisibility(View.VISIBLE);
        });
    }
}