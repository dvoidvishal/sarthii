package com.example.sarthiithetuitionfinder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.searchViewholder>{
    private Context mCtx;
    private List<SearchModal> searchModalList;
    public SearchAdapter(Context mCtx, List<SearchModal> searchModalList) {
        this.mCtx = mCtx;
        this.searchModalList = searchModalList;
    }

    @Override
    public void
    onBindViewHolder(@NonNull searchViewholder holder, int position)
    {
        SearchModal searchModal = searchModalList.get(position);
        holder.heading.setText(searchModal.getHeading());
    }

    @NonNull
    @Override
    public searchViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_list_item, parent, false);
        return new SearchAdapter.searchViewholder(view);
    }
    @Override
    public int getItemCount() {
        return searchModalList.size();
    }


    public class searchViewholder extends RecyclerView.ViewHolder {

        TextView heading, rating;
        public searchViewholder(@NonNull View itemView) {
            super(itemView);
            heading = itemView.findViewById(R.id.headingTv);
        }
    }
}
