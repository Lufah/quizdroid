package jkluu1.washington.edu.quizdroid;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;


public class Questions extends ActionBarActivity {
    private String category;
    private HashMap<String, List<String>> questions;
    private String correctAnswer;
    private int selected;
    private String[] allQuestions;
    private int qNumber;
    private int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        Intent intent = getIntent();
        questions = (HashMap<String, List<String>>) intent.getSerializableExtra("map");
        allQuestions = (String[]) intent.getSerializableExtra("allQuestions");
        category = intent.getStringExtra("category");
        qNumber = intent.getIntExtra("qNumber", qNumber);
        correctAnswer = "";
        score = intent.getIntExtra("score", score);

        TextView total = (TextView) findViewById(R.id.totalQs);
        total.setText("Question Number " + (qNumber + 1));

        TextView q = (TextView) findViewById(R.id.question);

        String key = allQuestions[qNumber]; // returns question

        //for (String getQ : questions.get(key)) {

        List<String> answer = questions.get(key);
        q.setText(key);
        correctAnswer = answer.get(Integer.parseInt(answer.get(0)));

        RadioButton a1 = (RadioButton) findViewById(R.id.a);
        a1.setText(answer.get(1));
        RadioButton a2 = (RadioButton) findViewById(R.id.b);
        a2.setText(answer.get(2));
        RadioButton a3 = (RadioButton) findViewById(R.id.c);
        a3.setText(answer.get(3));
        RadioButton a4 = (RadioButton) findViewById(R.id.d);
        a4.setText(answer.get(4));



    }

    public void answered(View v) {
        RadioGroup rGroup = (RadioGroup) findViewById(R.id.radioGroup);
        RadioButton rButton = (RadioButton) rGroup.findViewById(rGroup.getCheckedRadioButtonId());
        selected = rGroup.indexOfChild(rButton) + 1;

        Button b = (Button) findViewById(R.id.submit);
        b.setText("" + selected);
        b.setVisibility(View.VISIBLE);
    }

    public void submitAnswer(View v) {
        Intent intent = new Intent(this, Summary.class);
        intent.putExtra("map", questions);
        intent.putExtra("allQuestions", allQuestions);
        intent.putExtra("category", category);
        intent.putExtra("correctAnswer", correctAnswer);
        intent.putExtra("selected", selected);
        intent.putExtra("score", score);
        intent.putExtra("qNumber", qNumber);
        //intent.putExtra("selected", selected);

        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_questions, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
