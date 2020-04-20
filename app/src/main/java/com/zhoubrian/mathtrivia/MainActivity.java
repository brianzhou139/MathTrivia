package com.zhoubrian.mathtrivia;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView myTimerText,firstNum,secNum,symbol;
    int myFirstNumber=0,mySecondNumber=0,answer=0;
    String mySymbol;

    ///scores for teams
    TextView PlayerAscore, PlayerBscore;

    //Buttons
    Button Player_a_AChoice, Player_a_BChoice, Player_a_CChoice, Player_a_DChoice;
    Button player_b_AChoice, player_b_BChoice, player_b_CChoice, player_b_DChoice;
    Button ResetGame;

    int PlayerA_points=0;
    int PLayerB_points=0;

    int Points_timer=5;

    CountDownTimer myCountDownTimer;

    Boolean player_A_miss=false,player_B_miss=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //references to the question textViews
        myTimerText=(TextView)findViewById(R.id.myTimerText_id);
        firstNum=(TextView)findViewById(R.id.firstNumber_id);
        secNum=(TextView)findViewById(R.id.secondNumber_id);
        symbol=(TextView)findViewById(R.id.symbol_id);

        //references to the scores
        PlayerAscore =(TextView)findViewById(R.id.team_a_score);
        PlayerBscore =(TextView)findViewById(R.id.team_b_score);

        //get The Button References for Team A..
        Player_a_AChoice =(Button)findViewById(R.id.team_a_A);
        Player_a_BChoice =(Button)findViewById(R.id.team_a_B);
        Player_a_CChoice =(Button)findViewById(R.id.team_a_C);
        Player_a_DChoice =(Button)findViewById(R.id.team_a_D);

        //get The Button References for Team B..
        player_b_AChoice =(Button)findViewById(R.id.team_b_A);
        player_b_BChoice =(Button)findViewById(R.id.team_b_B);
        player_b_CChoice =(Button)findViewById(R.id.team_b_C);
        player_b_DChoice =(Button)findViewById(R.id.team_b_D);

        ResetGame=(Button)findViewById(R.id.reset_Score);

        Initialize_Scores();
        showTimerText();
        generateNumbers();
        setQuestion();
        setMultipleChoice();
        myCountDownTimer.start();

        Player_a_AChoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String choice= Player_a_AChoice.getText().toString();
                int player_choice=Integer.parseInt(choice);
                checkAnswer(player_choice, Player_a_AChoice,"A");
           }
        });//listen to Button A clicks by Player A


        Player_a_BChoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String choice= Player_a_BChoice.getText().toString();
                int player_choice=Integer.parseInt(choice);
                checkAnswer(player_choice, Player_a_BChoice,"A");
            }
        });//listen to Button B Clicks by Player A


        Player_a_CChoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String choice= Player_a_CChoice.getText().toString();
                int player_choice=Integer.parseInt(choice);
                checkAnswer(player_choice, Player_a_CChoice,"A");
            }
        });//listen to Button C clicks by Player A


        Player_a_DChoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String choice= Player_a_DChoice.getText().toString();
                int player_choice=Integer.parseInt(choice);
                checkAnswer(player_choice, Player_a_DChoice,"A");
            }
        });//listen to Button D clicks by Player A


        ///Listen to click by PlayerB
        player_b_AChoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String choice= player_b_AChoice.getText().toString();
                int player_choice=Integer.parseInt(choice);
                checkAnswer(player_choice, player_b_AChoice,"B");
            }
        });//listen to Button A clicks by Player A


        player_b_BChoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String choice= player_b_BChoice.getText().toString();
                int player_choice=Integer.parseInt(choice);
                checkAnswer(player_choice, player_b_BChoice,"B");
            }
        });//listen to Button B Clicks by Player A


        player_b_CChoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String choice= player_b_CChoice.getText().toString();
                int player_choice=Integer.parseInt(choice);
                checkAnswer(player_choice, player_b_CChoice,"B");
            }
        });//listen to Button C clicks by Player A


        player_b_DChoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String choice= player_b_DChoice.getText().toString();
                int player_choice=Integer.parseInt(choice);
                checkAnswer(player_choice, player_b_DChoice,"B");
            }
        });//listen to Button D clicks by Player A

        ResetGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myCountDownTimer.cancel();
                myTimerText.setText("5");
                Initialize_Scores();
                generateNumbers();
                EnAbleAllButtons();
                setQuestion();
                setMultipleChoice();
            }
        });//listen to Rest Button click


    }//end of onCreate

    public void Initialize_Scores(){
        PlayerA_points=0;
        PLayerB_points=0;
        PlayerAscore.setText(""+PlayerA_points);
        PlayerBscore.setText(""+PLayerB_points);
        Points_timer=5;
        //Set_New_Question();
    }

    private void checkAnswer(int player_choice, Button team_Choice,String player) {

        if(player_choice==answer){
            team_Choice.setBackgroundResource(R.color.buttonCorrectAnswer);
            Set_New_Question();

            if(player=="A"){
                player_A_miss=false;
                PlayerA_points+=Points_timer;
                PlayerAscore.setText(""+PlayerA_points);
                myCountDownTimer.cancel();
                Toast.makeText(MainActivity.this, Points_timer+" for Player A" , Toast.LENGTH_SHORT).show();
            }

            if(player=="B"){
                player_B_miss=false;
                PLayerB_points +=Points_timer;
                PlayerBscore.setText(""+PLayerB_points);
                myCountDownTimer.cancel();
                Toast.makeText(MainActivity.this, Points_timer+" for Player B" , Toast.LENGTH_SHORT).show();
            }

        }else{
            team_Choice.setBackgroundResource(R.color.buttonWrongAnswer);
            if(player=="A"){
                DisablePlayerAButtons();
                player_A_miss=true;
                PlayerA_points--;
                PlayerAscore.setText(""+PlayerA_points);
                Toast.makeText(MainActivity.this,"negative point : A" , Toast.LENGTH_SHORT).show();
            }

            if(player=="B"){
                DisablePlayerBButtons();
                player_B_miss=true;
                PLayerB_points --;
                PlayerBscore.setText(""+PLayerB_points);
                Toast.makeText(MainActivity.this,"negative point : B" , Toast.LENGTH_SHORT).show();
            }
            //Set_New_Question();
        }

        if(player_A_miss && player_B_miss){
            Set_New_Question();
        }

    }//end of chackAnswerMethod

    public void Set_New_Question(){
        DisableAllButtons();
        new CountDownTimer(6000, 1000) { //40000 milli seconds is total time, 1000 milli seconds is time interval
            public void onTick(long millisUntilFinished) {
            }
            public void onFinish() {
                generateNumbers();
                setQuestion();
                setMultipleChoice();
                ResetButtonColors();
                EnAbleAllButtons();
                Points_timer=5;
                myTimerText.setText(""+Points_timer);
                myCountDownTimer.start();
            }
        }.start();
    }//end of set_new Question emthod..

    public void DisableAllButtons(){
        Player_a_AChoice.setEnabled(false);
        Player_a_BChoice.setEnabled(false);
        Player_a_CChoice.setEnabled(false);
        Player_a_DChoice.setEnabled(false);

        //get The Button References for Team B..
        player_b_AChoice.setEnabled(false);
        player_b_BChoice.setEnabled(false);
        player_b_CChoice.setEnabled(false);
        player_b_DChoice.setEnabled(false);
    }

    public void DisablePlayerAButtons(){
        Player_a_AChoice.setEnabled(false);
        Player_a_BChoice.setEnabled(false);
        Player_a_CChoice.setEnabled(false);
        Player_a_DChoice.setEnabled(false);
    }

    public void DisablePlayerBButtons(){
        //get The Button References for Team B..
        player_b_AChoice.setEnabled(false);
        player_b_BChoice.setEnabled(false);
        player_b_CChoice.setEnabled(false);
        player_b_DChoice.setEnabled(false);
    }

    public void EnAbleAllButtons(){
        Player_a_AChoice.setEnabled(true);
        Player_a_BChoice.setEnabled(true);
        Player_a_CChoice.setEnabled(true);
        Player_a_DChoice.setEnabled(true);

        //get The Button References for Team B..
        player_b_AChoice.setEnabled(true);
        player_b_BChoice.setEnabled(true);
        player_b_CChoice.setEnabled(true);
        player_b_DChoice.setEnabled(true);
    }

    public void ResetButtonColors(){
        Player_a_AChoice.setBackgroundResource(android.R.drawable.btn_default);
        Player_a_BChoice.setBackgroundResource(android.R.drawable.btn_default);
        Player_a_CChoice.setBackgroundResource(android.R.drawable.btn_default);
        Player_a_DChoice.setBackgroundResource(android.R.drawable.btn_default);

        //get The Button References for Team B..
        player_b_AChoice.setBackgroundResource(android.R.drawable.btn_default);
        player_b_BChoice.setBackgroundResource(android.R.drawable.btn_default);
        player_b_CChoice.setBackgroundResource(android.R.drawable.btn_default);
        player_b_DChoice.setBackgroundResource(android.R.drawable.btn_default);
    }

    public void setDelay(int tot){
        new CountDownTimer(tot, 1000) { //40000 milli seconds is total time, 1000 milli seconds is time interval
            public void onTick(long millisUntilFinished) {
            }
            public void onFinish() {
            }
        }.start();
    }//end of showFlashText


    public void showTimerText(){
        myCountDownTimer = new CountDownTimer(30000, 3000) {
            public void onTick(long millisUntilFinished) {
                Log.e("seconds remaining : ", "seconds remaining : " + millisUntilFinished / 1000);
                String temp_index=myTimerText.getText().toString();
                int fin_index=Integer.parseInt(temp_index);

                if(fin_index>=2){
                    fin_index--;
                }

                if(fin_index==1){
                    fin_index=1;
                }

                Points_timer=fin_index;
                myTimerText.setText(""+Points_timer);
            }

            public void onFinish() {
                Log.e("done!", "done!");
                Points_timer=1;
                myTimerText.setText(""+Points_timer);
            }
        };

    }//end of showFlashText


    public void generateNumbers(){
        final int min = 1;
        final int max = 20;
        int f_Random = new Random().nextInt((max - min) + 1) + min;
        int s_Random = new Random().nextInt((max - min) + 1) + min;

        myFirstNumber=f_Random;
        mySecondNumber=s_Random;
        answer=myFirstNumber*mySecondNumber;
    }

    public void setQuestion(){
        firstNum.setText(""+myFirstNumber);
        secNum.setText(""+mySecondNumber);
    }


    public void setMultipleChoice(){
        int A=myFirstNumber*mySecondNumber;
        int B=myFirstNumber+mySecondNumber;
        int C=(myFirstNumber+mySecondNumber)*2;
        int D=A+1;
        Integer[] answers=new Integer[4];
        //Integer[] A_answers=new Integer[4];
        //Integer[] B_answers=new Integer[4];
        //check to see if two answers are the same
        if(B==A){
            B=B+1;
        }

        if(C==A){
            C=C+1;
        }
        answers[0]=A;
        answers[1]=B;
        answers[2]=C;
        answers[3]=D;

        List intList= Arrays.asList(answers);
        Collections.shuffle(intList);
        intList.toArray(answers);

        for(int a=0;a<answers.length;a++){
            Log.d("tito",answers[a].toString());
        }
        //set the answers into Buttons for A
        Player_a_AChoice.setText(answers[0].toString());
        Player_a_BChoice.setText(answers[1].toString());
        Player_a_CChoice.setText(answers[2].toString());
        Player_a_DChoice.setText(answers[3].toString());


        for(int a=0;a<2;a++){
            Collections.shuffle(intList);
        }
        intList.toArray(answers);

        //set the answers into Buttons for B
        player_b_AChoice.setText(answers[0].toString());
        player_b_BChoice.setText(answers[1].toString());
        player_b_CChoice.setText(answers[2].toString());
        player_b_DChoice.setText(answers[3].toString());
    }//end of method setMultiple_choice

}//end of MAinActivity
