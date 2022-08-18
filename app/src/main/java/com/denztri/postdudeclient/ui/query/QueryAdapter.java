package com.denztri.postdudeclient.ui.query;


import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.denztri.postdudeclient.R;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QueryAdapter extends RecyclerView.Adapter<QueryAdapter.QueryViewHolder> {
    private final Context context;

    private List<QueryModel> list;

    public QueryAdapter(Context context) {
        this.context = context;
        this.list = new ArrayList<>(Collections.singletonList(new QueryModel("", "", true)));
    }

    public List<QueryModel> getList() {
        return list;
    }

    public void setList(List<QueryModel> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public QueryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_query, parent,false);

        return new QueryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QueryViewHolder holder, int position) {
      holder.valueInput.addTextChangedListener(new TextWatcher() {
          @Override
          public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

          }

          @Override
          public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
              list.get(holder.getAdapterPosition()).setValue(charSequence.toString());
          }

          @Override
          public void afterTextChanged(Editable editable) {

          }
      });

      holder.keyInput.addTextChangedListener(new TextWatcher() {
          @Override
          public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

          }

          @Override
          public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
              list.get(holder.getAdapterPosition()).setKey(charSequence.toString());
          }

          @Override
          public void afterTextChanged(Editable editable) {

          }
      });
      holder.addBtn.setOnClickListener(view -> {
          list.add(new QueryModel("", "",true));
          notifyItemInserted(list.size() - 1);
      });
      holder.removeBtn.setOnClickListener(view -> {
          if (list.size() > 1) {
              list.remove(holder.getAdapterPosition());
              notifyItemRemoved(holder.getAdapterPosition());
          }
      });
    }

    @Override
    public int getItemCount() {
        if (list != null) return list.size();
        return 1;
    }

    public static class QueryViewHolder extends RecyclerView.ViewHolder{
        TextInputEditText keyInput;
        TextInputEditText valueInput;
        ImageButton addBtn;
        ImageButton removeBtn;
        public QueryViewHolder(@NonNull View itemView) {
            super(itemView);
            keyInput = itemView.findViewById(R.id.query_key);
            valueInput = itemView.findViewById(R.id.query_value);
            addBtn = itemView.findViewById(R.id.query_add);
            removeBtn = itemView.findViewById(R.id.query_remove);
        }
    }
}
