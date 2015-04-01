package com.example.shahab123.seng301;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;


public class page2 extends ActionBarActivity implements View.OnClickListener{
    Intent page3Intent;
    Intent page1Intent;
    ImageButton calculate;
    ImageButton back;
    EditText numTills;
    EditText numPeople;
    TextView editTextError;
    String choice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page2);
        // set up sent parameters
        Intent intent = getIntent();
        choice = intent.getStringExtra("key");
        if(choice == null || choice.equals("")){    // error in parameters
            choice = "0";
        }
        page3Intent = new Intent(page2.this, page3.class);  // init the page 3 intent
        page1Intent = new Intent(page2.this, MainActivity.class);
        // init buttons
        calculate = (ImageButton) findViewById(R.id.imageButton4);  // init the calculate button
        calculate.setOnClickListener(this);
        back = (ImageButton) findViewById(R.id.imageButton3);  // init the calculate button
        back.setOnClickListener(this);
        // init text box
        numTills = (EditText) findViewById(R.id.editText2);  // number of tills textbox
        editTextError = (TextView) findViewById(R.id.textView8); // text error message
        numPeople = (EditText) findViewById(R.id.editText);    // number of people textbox
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_page2, menu);
        return true;
    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.imageButton4: // case for calculate button
            calculate();
            break;

            case R.id.imageButton3:
            back();
            break;
        }

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
    public void calculate(){


        if (choice.equals("0")){    // starBucks
            calculateTime(0);
        }
        else if (choice.equals("1")){   // tims
            calculateTime(1);
        }
        if(editTextError.getText().toString().equals("")){
            page2.this.startActivity(page3Intent);
        }

    }

    public void back(){
        // goes from page 2 to page 1
        this.finish();

    }

    public void calculateTime(int a){
        int mins;
        int seconds;
        double time;
        int numPpl = 0;
        int numTls= 0;
        if(numPeople.getText().toString().equals("")||numTills.getText().toString().equals("")){
            editTextError.setText("Please fill all required fields.");
            return;
        }
        try{
            numTls = Integer.parseInt(numTills.getText().toString());   // gets the number of tills
            numPpl = Integer.parseInt(numPeople.getText().toString()); // gets number of people
        }
        catch(NullPointerException e){

            editTextError.setText("Please fill all required fields.");
            return;
        }catch(Exception e) {
            System.out.println(e);
        }
        editTextError.setText("");  // reset the error message
        switch (a){
            case 0:
                if(numPpl != 0 && numTls != 0){ // no 0 values
                    time = (numPpl * data.starBucksTime)/(numTls); // calculate total time
                    if(time >= 1){
                        mins = (int) Math.floor(time);      // derive the mins
                        seconds = (int) ((time-mins)* 60);    // derive the seconds
                    }else{
                        mins = 0;
                        seconds = (int) (time*60);
                    }
                    // set the constants
                    data.waitMins = mins;
                    data.waitSeconds = seconds;
                }

                break;
            case 1:
                time = (numPpl * data.timHortonsTime)/(numTls); // calculate total time
                mins = (int) Math.floor(time);      // derive the mins
                seconds = (int) (time-mins)* 60;    // derive the seconds
                // set the constants
                data.waitMins = mins;
                data.waitSeconds = seconds;

                break;
        }
    }
}
