package com.example.schoolfix.FixAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.schoolfix.Models.Kids;
import com.example.schoolfix.R;

import java.util.List;

public class KidsAdapter extends RecyclerView.Adapter<KidsAdapter.KidsViewHolder> {
    private Context context;
    private List<Kids> kids;
    private int rowLayout;

    public void setKids(List<Kids> kids) {
        this.kids = kids;
        notifyDataSetChanged();
    }

    public KidsAdapter(Context context, List<Kids> kids, int rowLayout) {
        this.context = context;
        this.kids = kids;
        this.rowLayout = rowLayout;
    }

    @NonNull
    @Override
    public KidsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new KidsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KidsViewHolder holder, int position) {
        holder.firstname.setText(kids.get(position).getKid_firstname());
        holder.lastname.setText(kids.get(position).getKid_lastname());
        holder.school.setText(kids.get(position).getSchool_name());
        holder.kid_class.setText(kids.get(position).getClass_name());
    }

    @Override
    public int getItemCount() {
        return kids.size();
    }

    public class KidsViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.firstname)
        TextView firstname;

        @BindView(R.id.lastname)
        TextView lastname;

        @BindView(R.id.school_name)
        TextView school;

        @BindView(R.id.class_name)
        TextView kid_class;

        public KidsViewHolder(View view) {
            super(view);
            ButterKnife.bind(this,view);
        }

    }
}

