package com.denztri.postdudeclient.ui.history;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.denztri.postdudeclient.databinding.FragmentHistoryBinding;

public class HistoryFragment extends Fragment {

    private HistoryViewModel mViewModel;
    private HistoryAdapter historyAdapter;
    private RecyclerView recyclerView;
    private FragmentHistoryBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(requireActivity()).get(HistoryViewModel.class);
        binding = FragmentHistoryBinding.inflate(inflater, container, false);

        initRecycler();

        mViewModel.getAllHistory().observe(getViewLifecycleOwner(), requestHistoryModels -> historyAdapter.setHistoryModels(requestHistoryModels));

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void initRecycler(){
        recyclerView = binding.historyRecycler;
        recyclerView.setLayoutManager(new LinearLayoutManager(binding.getRoot().getContext(),
                LinearLayoutManager.VERTICAL,
                false));

        historyAdapter = new HistoryAdapter(binding.getRoot().getContext());
        recyclerView.setAdapter(historyAdapter);
    }
}