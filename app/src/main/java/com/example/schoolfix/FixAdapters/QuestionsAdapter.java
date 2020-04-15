package com.example.schoolfix.FixAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.schoolfix.Models.OptionsDTO;
import com.example.schoolfix.Models.QuestionsDTO;
import com.example.schoolfix.R;

import java.util.ArrayList;
import java.util.Collections;
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
        List<OptionsDTO> optionsDTOS=new ArrayList<>(questionsDTOList.get(position).getOptions());

        Collections.sort(optionsDTOS, (lhs, rhs) -> lhs.getOptionDescription().compareTo(lhs.getOptionDescription()));

        holder.question.setText(String.valueOf(questionsDTOList.get(position).getQuestionId())+". "+questionsDTOList.get(position).getQuestion());

        String[] options= new String[4];

        for (int i = 0; i < optionsDTOS.size() ; i++) {
            options[i] =optionsDTOS.get(i).getOptionDescription();
        }

        if (optionsDTOS.size() == 4){
            holder.answerOption1.setText(options[0]);
            holder.answerOption2.setText(options[1]);
            holder.answerOption3.setText(options[2]);
            holder.answerOption4.setText(options[3]);

        }else{
            holder.answerOption1.setText(options[0]);
            holder.answerOption2.setText(options[1]);
        }


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
