package com.denztri.postdudeclient.ui.response;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.webkit.WebViewAssetLoader;

import com.denztri.postdudeclient.R;
import com.denztri.postdudeclient.databinding.FragmentResponseBinding;
import com.denztri.postdudeclient.utils.LocalContentWebViewClient;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class ResponseFragment extends Fragment {

    private FragmentResponseBinding binding;
    private ResponseViewModel viewModel;
    private WebView webView;
    private final WebViewAssetLoader assetLoader;

    public ResponseFragment(Context context) {
        assetLoader = new WebViewAssetLoader.Builder()
                .addPathHandler("/assets/", new WebViewAssetLoader.AssetsPathHandler(context))
                .addPathHandler("/res/", new WebViewAssetLoader.ResourcesPathHandler(context))
                .build();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentResponseBinding.inflate(inflater,container,false);
        viewModel = new ViewModelProvider(requireActivity()).get(ResponseViewModel.class);
        webView = binding.resFragWebview;

        webView.setWebChromeClient(new WebChromeClient());
        WebSettings webSetting = webView.getSettings();
        webSetting.setJavaScriptEnabled(true);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel.getData().observe(requireActivity(), s -> {
            binding.resFragProgressbar.setVisibility(View.GONE);
            webView.setWebViewClient(new LocalContentWebViewClient(assetLoader));
            try {
                String postData = "data=" + URLEncoder.encode(s.get(0), "UTF-8");
                String postCookie = "dataCookie="+ URLEncoder.encode(s.get(2), "UTF-8");
                String code = s.get(3);
                String contentLength = s.get(4);
                TextView resCode = requireActivity().findViewById(R.id.res_status_value);
                TextView resLength =  requireActivity().findViewById(R.id.res_size_value);
//                resLength.setText(contentLength);
                resCode.setText(code);
                webView.loadUrl("https://appassets.androidplatform.net/assets/index.html?" +
                        postData);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
//
            webView.setVisibility(View.VISIBLE);
        });

    }

}