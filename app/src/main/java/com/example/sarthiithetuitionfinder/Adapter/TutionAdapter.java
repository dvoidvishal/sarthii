package com.example.sarthiithetuitionfinder.Adapter;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

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
        TutionModal modal = tutionModalList.get(position);
        holder.name.setText(modal.getName());
        holder.contact.setText(modal.getContact());
        holder.subjects.setText(String.join(", ", modal.getSubjects()));

    }

    @Override
    public int getItemCount() {
        return tutionModalList.size();
    }

    public class TutionViewHolder extends RecyclerView.ViewHolder {
        TextView name, contact, email, subjects;
        public TutionViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.headingTv);
            contact = itemView.findViewById(R.id.contactTv);
//            email = itemView.findViewById(R.id.email);
            subjects = itemView.findViewById(R.id.subjectTv);

        }
    }
}
