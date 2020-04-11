package com.example.schoolfix.FixAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.schoolfix.Models.QuestionsDTO;
import com.example.schoolfix.R;

import java.util.List;


public class QuestionsAdapter extends RecyclerView.Adapter<QuestionsAdapter.QuestionsViewHolder> {

    private Context context;
    private List<QuestionsDTO> questionsDTOList;
    private int rowLayout;

    public QuestionsAdapter(Context context, List<QuestionsDTO> questionsDTOList, int rowLayout) {
        this.context = context;
        this.questionsDTOList = questionsDTOList;
        this.rowLayout = rowLayout;
    }

    @NonNull
    @Override
    public QuestionsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(rowLayout,parent,false);
        return new QuestionsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionsViewHolder holder, int position) {
            holder.question.setText((CharSequence) questionsDTOList.get(position).getQuestions().get(0));
    }

    @Override
    public int getItemCount() {
        return questionsDTOList.size();
    }

    public class QuestionsViewHolder extends RecyclerView.ViewHolder {
        TextView question;
        RadioButton answerOption1,answerOption2,answerOption3,answerOption4;

        public QuestionsViewHolder(@NonNull View itemView) {
            super(itemView);
            question=itemView.findViewById(R.id.question);
            answerOption1=itemView.findViewById(R.id.answer_option);
            answerOption2=itemView.findViewById(R.id.answer_option2);
            answerOption3=itemView.findViewById(R.id.answer_option3);
            answerOption4=itemView.findViewById(R.id.answer_option4);
        }
    }
}
