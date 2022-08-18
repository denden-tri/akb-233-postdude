package com.denztri.postdudeclient.ui.query;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.denztri.postdudeclient.R;
import com.denztri.postdudeclient.databinding.FragmentQueryBinding;

public class QueryFragment extends Fragment {

    private QueryViewModel mViewModel;
    private FragmentQueryBinding binding;

    private RecyclerView recyclerView;
    private QueryAdapter queryAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(requireActivity()).get(QueryViewModel.class);
        binding = FragmentQueryBinding.inflate(inflater,container,false);

        initRecycler();

        mViewModel.setList(queryAdapter.getList());
        return binding.getRoot();
    }

    private void initRecycler(){
        recyclerView = binding.queryRecycler;
        recyclerView.setLayoutManager(new LinearLayoutManager(binding.getRoot().getContext(),
                LinearLayoutManager.VERTICAL,
                false));
        queryAdapter = new QueryAdapter(binding.getRoot().getContext());
        recyclerView.setAdapter(queryAdapter);
    }
}