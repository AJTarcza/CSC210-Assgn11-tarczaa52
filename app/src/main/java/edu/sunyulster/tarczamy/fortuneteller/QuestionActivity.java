package edu.sunyulster.tarczamy.fortuneteller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;

public class QuestionActivity extends AppCompatActivity {

    protected Button vButton;
    protected static String userQuestion;
    private EditText editQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        vButton = (Button) findViewById(R.id.button);
        editQuestion = (EditText) findViewById(R.id.questionField);

        vButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userQuestion = editQuestion.getText().toString();
                startActivity(new Intent(QuestionActivity.this, MainActivity.class));
            }   //End onClick
        });
    }   //End onCreate

}   //End QuestionActivity
