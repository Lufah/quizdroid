package jkluu1.washington.edu.quizdroid;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;


public class Summary extends ActionBarActivity {
    private String category;
    private HashMap<String, List<String>> questions;
    private String correctAnswer;
    private int selected;
    private String[] allQuestions;
    private int qNumber;
    private int score;
    private boolean finish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        Intent intent = getIntent();
        questions = (HashMap<String, List<String>>) intent.getSerializableExtra("map");
        allQuestions = (String[]) intent.getSerializableExtra("allQuestions");
        category = intent.getStringExtra("category");
        correctAnswer = intent.getStringExtra("correctAnswer");
        qNumber = intent.getIntExtra("qNumber", qNumber);
        selected = intent.getIntExtra("selected", selected);
        score = intent.getIntExtra("score", score);


        TextView r = (TextView) findViewById(R.id.results);

        TextView c = (TextView) findViewById(R.id.correct);
        TextView i = (TextView) findViewById(R.id.incorrect);

        String key = allQuestions[qNumber];

        //i.setText(questions.get(key).get(1));

        if (correctAnswer.equals(questions.get(key).get(selected))) {
            score++;
            r.setText("Correct!!");
        } else {
            r.setText("Sorry, that was incorrect.");
            i.setText("Your response was: " + questions.get(key).get(selected));
        }

        c.setText("The correct Answer was : " + correctAnswer);

        TextView state = (TextView) findViewById(R.id.status);
        state.setText("You have gotten " + score + " correct out of " + allQuestions.length);
        qNumber++;

        Button b = (Button) findViewById(R.id.next);
        if (allQuestions.length >= (qNumber + 1)) {
            b.setText("Next");
            finish = false;
        } else {
            b.setText("Finish");
            finish = true;
        }
    }

    public void controller(View v) {
        if (finish) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            Intent intent = new Intent(this, Questions.class);
            intent.putExtra("map", questions);
            intent.putExtra("allQuestions", allQuestions);
            intent.putExtra("category", category);
            intent.putExtra("correctAnswer", correctAnswer);
            intent.putExtra("selected", selected);
            intent.putExtra("score", score);
            intent.putExtra("qNumber", qNumber);
            startActivity(intent);
            finish();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_summary, menu);
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
