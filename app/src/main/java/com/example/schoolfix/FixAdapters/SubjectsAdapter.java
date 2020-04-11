package com.example.schoolfix.FixAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.schoolfix.Models.ResponseModels.SubjectResponse;
import com.example.schoolfix.R;

import java.util.List;

public class SubjectsAdapter extends RecyclerView.Adapter<SubjectsAdapter.SubjectViewHolder> {
    Context context;
    List<SubjectResponse> subjects;
    private int rowLayout;

    public SubjectsAdapter(Context context, List<SubjectResponse> subjects, int rowLayout) {
        this.context = context;
        this.subjects = subjects;
        this.rowLayout = rowLayout;
    }

    public void setSubjects(List<SubjectResponse> subjects) {
        this.subjects = subjects;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SubjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new SubjectsAdapter.SubjectViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubjectViewHolder holder, int position) {
        holder.subject.setText(subjects.get(position).getSubject().getDescription());
        holder.numberOfPapers.setText(String.valueOf(subjects.get(position).getTotalPapers()));
        Glide.with(context).load(subjects.get(position).getImage_url()).into(holder.subject_icon);

    }

    @Override
    public int getItemCount() {
        return subjects.size();
    }

    public class SubjectViewHolder extends RecyclerView.ViewHolder{
        TextView subject,numberOfPapers;
        ImageView subject_icon;

        public  SubjectViewHolder(View view){
            super(view);
            numberOfPapers=view.findViewById(R.id.numberOfPapers);
            subject=view.findViewById(R.id.subject_name);
            subject_icon=view.findViewById(R.id.subject_icon);
        }
    }
}
