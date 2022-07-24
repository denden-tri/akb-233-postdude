package com.denztri.postdudeclient.ui.request;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.android.volley.Request;
import com.denztri.postdudeclient.R;
import com.denztri.postdudeclient.databinding.FragmentRequestBinding;
import com.denztri.postdudeclient.ui.response.ResponseDialogFragment;
import com.denztri.postdudeclient.ui.response.ResponseViewModel;
import com.denztri.postdudeclient.utils.RequestBuilder;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.io.IOException;
import java.util.concurrent.Executors;

public class RequestFragment extends Fragment {

    private FragmentRequestBinding binding;

    private BottomSheetBehavior<ConstraintLayout> sheetBehavior;
    private ResponseViewModel resViewModel;
    RequestBuilder requestBuilder = new RequestBuilder();



    private int method;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentRequestBinding.inflate(inflater, container, false);
        resViewModel = new ViewModelProvider(requireActivity()).get(ResponseViewModel.class);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Spinner spinner = binding.reqMethod;
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(requireActivity(),
                R.array.method_list, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d("SELECTED", adapterView.getItemAtPosition(i).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ConstraintLayout constraintLayout = requireActivity().findViewById(R.id.res_bottom_sheet_constrain);
        sheetBehavior = BottomSheetBehavior.from(constraintLayout);

        showReqTab();
        showResTab();

        binding.reqSendBtn.setOnClickListener(view1 -> {
            requireActivity().findViewById(R.id.res_frag_progressbar).setVisibility(View.VISIBLE);
            requireActivity().findViewById(R.id.res_frag_webview).setVisibility(View.INVISIBLE);
            sendRequest();
            sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void showResTab(){
        ViewPager2 resPager = requireActivity().findViewById(R.id.res_pager);
        TabLayout resTab = requireActivity().findViewById(R.id.res_tab);
        ResponseDialogFragment.DemoCollectionAdapter demoCollectionAdapter = new ResponseDialogFragment.DemoCollectionAdapter(this,requireActivity());
        resPager.setAdapter(demoCollectionAdapter);
        resPager.setUserInputEnabled(false);
        new TabLayoutMediator(resTab, resPager,
                (tab, position) -> {
                    switch (position){
                        case 0:
                            tab.setText(R.string.response);
                            break;
                        case 1:
                            tab.setText(R.string.headers);
                            break;
                        case 2:
                            tab.setText(R.string.cookies);
                            break;
                    }
                }
        ).attach();
    }

    private void showReqTab(){
        ViewPager2 reqPager = requireActivity().findViewById(R.id.req_pager);
        TabLayout reqTab = requireActivity().findViewById(R.id.req_tab);
        ReqAdapter reqAdapter = new ReqAdapter(this);
        reqPager.setAdapter(reqAdapter);
        new TabLayoutMediator(reqTab, reqPager,
                (tab, position) -> {
                    switch (position){
                        case 0:
                            tab.setText(R.string.query);
                            break;
                        case 1:
                            tab.setText(R.string.header);
                            break;
                        case 2:
                            tab.setText(R.string.auth);
                            break;
                        case 3:
                            tab.setText(R.string.body);
                            break;
                    }
                }
        ).attach();
    }

    public static class ReqAdapter extends FragmentStateAdapter {
        public ReqAdapter(Fragment fragment) {
            super(fragment);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            // Return a NEW fragment instance in createFragment(int)

            return new ResponseDialogFragment.DemoObjectFragment();
        }

        @Override
        public int getItemCount() {
            return 4;
        }
    }

    private void getHttpMethod(String choice){
        switch (choice){
            case "GET":
                method = Request.Method.GET;
                break;
            case "POST":
                method = Request.Method.POST;
                break;
            case "PUT":
                method = Request.Method.PUT;
                break;
            case "DELETE":
                method = Request.Method.DELETE;
        }
    }

    private void sendRequest(){
        Executors.newSingleThreadExecutor().execute(() -> {
            try {
                String wow = requestBuilder.run("https://jsonplaceholder.typicode.com/posts/");
                new Handler(Looper.getMainLooper()).post(() -> resViewModel.setResponseBody(wow));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}