package com.example.schoolfix.FixAdapters;

import android.content.Context;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.schoolfix.Models.ResponseModels.OptionsDTO;
import com.example.schoolfix.Models.ResponseModels.ResultResponseDTO;
import com.example.schoolfix.Models.ResponseModels.ResultsDTO;
import com.example.schoolfix.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ResultsAdapter extends RecyclerView.Adapter<ResultsAdapter.ResultViewHolder> {

    private Context context;
    private List<ResultResponseDTO> resultResponseDTOList;
    private  int rowLayout;

    public ResultsAdapter(Context context, List<ResultResponseDTO> resultResponseDTOList, int rowLayout) {
        this.context = context;
        this.resultResponseDTOList = resultResponseDTOList;
        this.rowLayout = rowLayout;
    }

    @NonNull
    @Override
    public ResultsAdapter.ResultViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(rowLayout,parent,false);
        return new ResultViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ResultsAdapter.ResultViewHolder holder, int position) {
        List<OptionsDTO> optionsDTOS=new ArrayList<>(resultResponseDTOList.get(position).getOptions());
        List<ResultsDTO> result=new ArrayList<>(resultResponseDTOList.get(position).getResults());

        Collections.sort(optionsDTOS, (o1, o2) -> o1.getOptionDescription().compareTo(o2.getOptionDescription()));

        String correct=context.getResources().getString(R.string.correct_answer);
        holder.question.setText(String.valueOf(resultResponseDTOList.get(position).getQuestionId())+". "+resultResponseDTOList.get(position).getQuestion());

        String[] options= new String[4];

        for (int i = 0; i < optionsDTOS.size() ; i++) {
            if (i == result.get(0).getCorrectOptionId() -1 ){
                options[i] =optionsDTOS.get(i).getOptionDescription() + " "+Html.fromHtml(correct);
            }else options[i] =optionsDTOS.get(i).getOptionDescription();
        }


        if (optionsDTOS.size() == 4){
            holder.answerOption1.setText(options[0]);
            holder.answerOption2.setText(options[1]);
            holder.answerOption3.setText(options[2]);
            holder.answerOption4.setText(options[3]);
        }


    }

    @Override
    public int getItemCount() {
        return resultResponseDTOList.size();
    }

    public class ResultViewHolder extends RecyclerView.ViewHolder {
        TextView question;
        RadioGroup selected;
        RadioButton answerOption1,answerOption2,answerOption3,answerOption4;

        public ResultViewHolder(@NonNull View itemView) {
            super(itemView);
            selected=itemView.findViewById(R.id.option_group1);
            question=itemView.findViewById(R.id.question1);
            answerOption1=itemView.findViewById(R.id.answer_option1);
            answerOption2=itemView.findViewById(R.id.answer_option21);
            answerOption3=itemView.findViewById(R.id.answer_option31);
            answerOption4=itemView.findViewById(R.id.answer_option41);
        }
    }
}
