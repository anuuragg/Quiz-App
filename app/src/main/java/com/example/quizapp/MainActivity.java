package com.example.quizapp;

import android.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.graphics.text.TextRunShaper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    TextView questionTextView;
    TextView totalQuestionTextView;
    Button opt1, opt2, opt3, opt4;
    Button submit_btn;

    int score=0;
    int totalQuestion = QuestionAnswers.question.length;
    int currentQuestionIndex = 0;
    String selectedAnswer = "";

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        totalQuestionTextView = findViewById(R.id.Total_Question);
        questionTextView = findViewById(R.id.Question);
        opt1 = findViewById(R.id.option_1);
        opt2 = findViewById(R.id.option_2);
        opt3 = findViewById(R.id.option_3);
        opt4 = findViewById(R.id.option_4);
        submit_btn = findViewById(R.id.submit_btn);

        opt1.setOnClickListener(this);
        opt2.setOnClickListener(this);
        opt3.setOnClickListener(this);
        opt4.setOnClickListener(this);
        submit_btn.setOnClickListener(this);

        totalQuestionTextView.setText("Total Questions: "+totalQuestion);
        loadNewQuestion();
    }

    private void loadNewQuestion(){
        if (currentQuestionIndex == totalQuestion){
            finishQuiz();
            return;
        }

        questionTextView.setText(QuestionAnswers.question[currentQuestionIndex]);
        opt1.setText(QuestionAnswers.choices[currentQuestionIndex][0]);
        opt2.setText(QuestionAnswers.choices[currentQuestionIndex][1]);
        opt3.setText(QuestionAnswers.choices[currentQuestionIndex][2]);
        opt4.setText(QuestionAnswers.choices[currentQuestionIndex][3]);

        selectedAnswer = "";
    }

    private void finishQuiz(){
        String passStatus;
        if (score >= totalQuestion*0.6){
            passStatus = "Passed";
        } else{
            passStatus = "Failed";
        }

        new AlertDialog.Builder(this)
                .setTitle(passStatus)
                .setMessage("Your score is "+score+" out of "+totalQuestion)
                .setPositiveButton("Restart", ((dialog, i) -> restartQuiz()))
                .setCancelable(false)
                .show();
    }

    private void restartQuiz(){
        score = 0;
        currentQuestionIndex = 0;
        loadNewQuestion();
    }

    @Override
    public void onClick(View view){
//        opt1.setBackgroundColor(Color.YELLOW);
//        opt2.setBackgroundColor(Color.YELLOW);
//        opt3.setBackgroundColor(Color.YELLOW);
//        opt4.setBackgroundColor(Color.YELLOW);

        Button clickedButton = (Button) view;

        if (clickedButton.getId() == R.id.submit_btn){
            if (!selectedAnswer.isEmpty()){
                if (selectedAnswer.equals(QuestionAnswers.correctAnswers[currentQuestionIndex])){
                    score++;
                } else {
                }
                currentQuestionIndex++;
                loadNewQuestion();
            } else {

            }
        } else {
            selectedAnswer=clickedButton.getText().toString();
        }
    }

}
