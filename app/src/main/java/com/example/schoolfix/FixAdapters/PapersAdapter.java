package com.example.schoolfix.FixAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.schoolfix.Models.PapersDTO;
import com.example.schoolfix.R;

import java.util.List;

public class PapersAdapter extends RecyclerView.Adapter<PapersAdapter.PapersViewHolder> {

    private Context context;
    private List<PapersDTO> papersDTOList;
    private int rowLayout;

    public PapersAdapter(Context context, List<PapersDTO> papersDTOList, int rowLayout) {
        this.context = context;
        this.papersDTOList = papersDTOList;
        this.rowLayout = rowLayout;
    }

    @NonNull
    @Override
    public PapersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new PapersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PapersViewHolder holder, int position) {
        holder.papername.setText(papersDTOList.get(position).getPaperName());
        holder.timetotake.setText(papersDTOList.get(position).getTimeToTake());
    }

    @Override
    public int getItemCount() {
        return papersDTOList.size();
    }

    public class PapersViewHolder extends RecyclerView.ViewHolder {

        TextView papername,timetotake;
        public PapersViewHolder(@NonNull View itemView) {
            super(itemView);
            papername=itemView.findViewById(R.id.paper_name);
            timetotake=itemView.findViewById(R.id.time_to_take);
        }
    }
}
