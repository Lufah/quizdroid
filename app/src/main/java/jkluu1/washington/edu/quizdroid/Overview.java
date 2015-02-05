package jkluu1.washington.edu.quizdroid;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Overview extends ActionBarActivity {
    private String category;
    private HashMap<String, List<String>> questions;
    private String[] allQuestions;
    private int score;
    private int qNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);

        Intent intent = getIntent();
        questions = (HashMap<String, List<String>>) intent.getSerializableExtra("questions");
        allQuestions = (String[]) intent.getSerializableExtra("allQuestions");
        category = intent.getStringExtra("category");

        TextView title = (TextView) findViewById(R.id.title);
        title.setTextSize(40);
        title.setText(category);

        TextView summary = (TextView) findViewById(R.id.overviewMsg);
        getMessage(summary, category);

        TextView questionsOverview = (TextView) findViewById(R.id.numOfQuestions);
        questionsOverview.setText("");
        String num = "There will be " + allQuestions.length + " question(s).";
        questionsOverview.setText(num);
        
        score = 0;
        qNumber = 0;


    }

    public void getMessage(TextView summary, String c) {
        String category = c;
        summary.setText("");
        if (category.equals("Math")) {
            summary.setText("This category is about numbers.");
        } else if (category.equals("Physics")) {
            summary.setText("This category is about physics.");
        } else if (category.equals("Marvel Super Heroes")) {
            summary.setText("This category is about Marvel super heroes.");
        }

    }

    public void getQs(View v) {
        Intent intent = new Intent(this, Questions.class);
        intent.putExtra("map", questions);
        intent.putExtra("category", category);
        intent.putExtra("score", score);
        intent.putExtra("qNumber", qNumber);
        intent.putExtra("allQuestions", allQuestions);
        startActivity(intent);
        finish();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_overview, menu);
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
