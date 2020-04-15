package com.example.schoolfix.Networking;

import com.example.schoolfix.Models.BodyParams.LoginParam;
import com.example.schoolfix.Models.BodyParams.PapersBodyParam;
import com.example.schoolfix.Models.BodyParams.QuestionBodyParam;
import com.example.schoolfix.Models.Kids;
import com.example.schoolfix.Models.PapersDTO;
import com.example.schoolfix.Models.QuestionsDTO;
import com.example.schoolfix.Models.ResponseModels.LoginResponse;
import com.example.schoolfix.Models.BodyParams.SubjectBodyParam;
import com.example.schoolfix.Models.ResponseModels.SubjectResponse;
import com.example.schoolfix.Models.User;


import java.util.List;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiInterface {

    @Retry
    @POST("login")
//    Call<LoginResponse> isValidUser(@Body LoginParam loginParam);
    Single<LoginResponse> isValidUser(@Body LoginParam loginParam);

    @Retry
    @POST("user")
    Call<User> User();

    @Retry
    @POST("user_details")
    Single<List<Kids>> kids();

//    @POST("subjects")
//    Call<List<Subject>> getSubjects(
//            @Body SubjectBodyParam subject);

    @Retry
    @POST("subjects")
    Call<List<SubjectResponse>> allSubjects(
            @Body SubjectBodyParam subject);

    @Retry
    @POST("papers")
    Call<List<PapersDTO>> getPapers(
            @Body PapersBodyParam papersBodyParam);

    @Retry
    @POST("questions")
    Call<List<QuestionsDTO>> allQuestions(
            @Body QuestionBodyParam questionBodyParam);

}
