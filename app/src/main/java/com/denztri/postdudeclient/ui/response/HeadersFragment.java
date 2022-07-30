package com.denztri.postdudeclient.ui.response;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.webkit.WebViewAssetLoader;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.denztri.postdudeclient.databinding.FragmentHeadersBinding;
import com.denztri.postdudeclient.databinding.FragmentResponseBinding;
import com.denztri.postdudeclient.utils.LocalContentWebViewClient;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;


public class HeadersFragment extends Fragment {
    private FragmentHeadersBinding binding;
    private ResponseViewModel viewModel;
    private WebView webView;
    private final WebViewAssetLoader assetLoader;
    public HeadersFragment(Context context) {
        // Required empty public constructor
        assetLoader = new WebViewAssetLoader.Builder()
                .addPathHandler("/assets/", new WebViewAssetLoader.AssetsPathHandler(context))
                .addPathHandler("/res/", new WebViewAssetLoader.ResourcesPathHandler(context))
                .build();
    }





    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentHeadersBinding.inflate(inflater,container,false);
        viewModel = new ViewModelProvider(requireActivity()).get(ResponseViewModel.class);
        webView = binding.resHeadWebview;

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
                String postHeaders = "headers=" + URLEncoder.encode(s.get(1), "UTF-8");
                webView.loadUrl("https://appassets.androidplatform.net/assets/index.html?" + postHeaders);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
//
            webView.setVisibility(View.VISIBLE);
        });

    }
}