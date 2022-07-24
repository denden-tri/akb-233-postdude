package com.denztri.postdudeclient.ui.response;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.denztri.postdudeclient.databinding.FragmentResponseBinding;

public class ResponseFragment extends Fragment {

    private FragmentResponseBinding binding;
    private ResponseViewModel viewModel;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentResponseBinding.inflate(inflater,container,false);
        viewModel = new ViewModelProvider(requireActivity()).get(ResponseViewModel.class);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel.getData().observe(requireActivity(), s -> {
            binding.resFragProgressbar.setVisibility(View.GONE);
            binding.resFragText.setText(s);
            binding.resFragText.setVisibility(View.VISIBLE);
        });

    }

}