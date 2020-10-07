package com.example.sarthiithetuitionfinder.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sarthiithetuitionfinder.Activity.TutionProfile;
import com.example.sarthiithetuitionfinder.R;
import com.example.sarthiithetuitionfinder.TutionModal;

import java.util.List;

public class TutionAdapter extends RecyclerView.Adapter<TutionAdapter.TutionViewHolder> {
    private Context mCtx;
    private List<TutionModal> tutionModalList;

    public TutionAdapter(Context mCtx, List<TutionModal> tutionModalList) {
        this.mCtx = mCtx;
        this.tutionModalList = tutionModalList;
    }

    @NonNull
    @Override
    public TutionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_list_item, parent, false);
        return new TutionAdapter.TutionViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull TutionViewHolder holder, int position) {
        final TutionModal modal = tutionModalList.get(position);
        final Context thisCon = this.mCtx;
        holder.name.setText(modal.getName());
        holder.single_search_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(thisCon, TutionProfile.class);
                i.putExtra("Name", modal.getName());
                i.putExtra("Email", modal.getEmail());
                i.putExtra("Contact", modal.getContact());
                i.putExtra("NoOfBatches", modal.getNoOfBatches());
                i.putExtra("Subjects", String.join(", ", modal.getSubjects()));
                i.putExtra("Qualifications", String.join(", ", modal.getQualifications()));
                i.putExtra("Classes", String.join(", ", modal.getClasses()));
                thisCon.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return tutionModalList.size();
    }

    public class TutionViewHolder extends RecyclerView.ViewHolder {
        TextView name, contact, email, subjects;
        CardView single_search_item;
        public TutionViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.headingTv);
            single_search_item = itemView.findViewById(R.id.single_search_item);

        }
    }
}
