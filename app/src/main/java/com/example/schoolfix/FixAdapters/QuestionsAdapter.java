package com.example.schoolfix.FixAdapters;

import android.content.Context;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.schoolfix.Models.BodyParams.AnswerDTO;
import com.example.schoolfix.Models.BodyParams.QueryDTO;
import com.example.schoolfix.Models.BodyParams.SubmitDTO;
import com.example.schoolfix.Models.OptionsDTO;
import com.example.schoolfix.Models.QuestionsDTO;
import com.example.schoolfix.R;
import com.google.gson.Gson;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class QuestionsAdapter extends RecyclerView.Adapter<QuestionsAdapter.QuestionsViewHolder>{

    private Context context;
    private List<QuestionsDTO> questionsDTOList;
    private List<AnswerDTO> answerDTOList=new ArrayList<>();
    private List<QueryDTO> queryDTOList=new ArrayList<>();
    private SubmitDTO submitDTO=new SubmitDTO();
    private QueryDTO queryDTO;
    private AnswerDTO answerDTO;
    private int rowLayout;

    HashMap<String,String> answerMap=new HashMap<>();
    HashMap<String,String> queryMap=new HashMap<>();

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

        }

        holder.selected.setOnCheckedChangeListener((group, checkedId) -> {
            View radioButton=group.findViewById(checkedId);
            int option_id=group.indexOfChild(radioButton) + 1;
            int question_id=questionsDTOList.get(position).getQuestionId();

//            this.queryDTO.setId(String.valueOf(question_id-1));
//            this.queryDTO.setQuestion_id(String.valueOf(question_id));
//            this.queryDTOList.add(queryDTO);

            queryMap.put(String.valueOf(question_id-1),String.valueOf(question_id));
            answerMap.put(String.valueOf(question_id),String.valueOf(option_id));

//            this.answerDTO.setOption_id(String.valueOf(option_id));
//            this.answerDTO.setQuestion_id(String.valueOf(question_id));
//            this.answerDTOList.add(answerDTO);

        });

    }

    public  SubmitDTO getResult(){
        for (Map.Entry<String,String> entry : queryMap.entrySet()){
            this.queryDTO=new QueryDTO();
            this.queryDTO.setId(entry.getKey());
            this.queryDTO.setQuestion_id(entry.getValue());
            this.queryDTOList.add(queryDTO);
        }

        for (Map.Entry<String,String> entry: answerMap.entrySet()){
            this.answerDTO=new AnswerDTO();
            this.answerDTO.setQuestion_id(entry.getKey());
            this.answerDTO.setOption_id(entry.getValue());
            this.answerDTOList.add(answerDTO);
        }

        this.submitDTO.setQuery(this.queryDTOList);
        this.submitDTO.setAnswer(this.answerDTOList);
        return this.submitDTO;
    }

    @Override
    public int getItemCount() {
        return questionsDTOList.size();
    }

    public class QuestionsViewHolder extends RecyclerView.ViewHolder{
        TextView question;
        RadioGroup selected;
        RadioButton answerOption1,answerOption2,answerOption3,answerOption4;

        public QuestionsViewHolder(@NonNull View itemView) {
            super(itemView);
            selected=itemView.findViewById(R.id.option_group);
            question=itemView.findViewById(R.id.question);
            answerOption1=itemView.findViewById(R.id.answer_option);
            answerOption2=itemView.findViewById(R.id.answer_option2);
            answerOption3=itemView.findViewById(R.id.answer_option3);
            answerOption4=itemView.findViewById(R.id.answer_option4);
        }

    }
}
