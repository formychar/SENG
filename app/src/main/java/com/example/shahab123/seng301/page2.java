package com.example.shahab123.seng301;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;


public class page2 extends ActionBarActivity implements View.OnClickListener{
    Intent page3Intent;
    Intent page1Intent;
    ImageButton calculate;
    ImageButton back;
    EditText numTills;
    EditText numPeople;
    TextView location;
    TextView people;
    TextView tills;
    TextView editTextError;
    String choice;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page2);
        choice = String.valueOf(data.optionFlag); // load previous choice made by user
        initIntent();   // init transition intents
        initButtons();  // init buttons
        initEditText(); // init edit texts
        initTextView(); // init textview
        checkLanguage();    // check to see which language is set
    }


    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_page2, menu);
        return true;
    }*/
    // inits the text views

    public void initIntent(){
        page3Intent = new Intent(page2.this, page3.class);  // init the page 3 intent
        page1Intent = new Intent(page2.this, MainActivity.class);
    }
    // inits the buttons
    public void initButtons(){
        calculate = (ImageButton) findViewById(R.id.imageButton4);  // init the calculate button
        calculate.setOnClickListener(this);
        back = (ImageButton) findViewById(R.id.imageButton3);  // init the calculate button
        back.setOnClickListener(this);

    }
    // init text views
    public void initTextView(){
        editTextError = (TextView) findViewById(R.id.textView8); // text error message
        location = (TextView) findViewById(R.id.textView4);
        people = (TextView) findViewById(R.id.textView3);
        tills = (TextView) findViewById(R.id.textView2);
    }
    // inits the edit text fields
    public void initEditText(){
        numTills = (EditText) findViewById(R.id.editText2);  // number of tills textbox
        numPeople = (EditText) findViewById(R.id.editText);    // number of people textbox
    }
    // on click listener for the UI widgets
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
    // checks to see if language is french.
    public void checkLanguage(){
        if (data.language == 1){ // french
               translateFrench();
        }
    }
    // translates the current activity to french
    public void translateFrench(){
        // reset the buttons to french
        back.setImageResource(R.drawable.back_french);
        calculate.setImageResource(R.drawable.calculate_french);
        // reset the texts to French
        tills.setText(R.string.numTills_french);
        people.setText(R.string.numPeople_french);
        location.setText(R.string.location_french);
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
    // controller for the calculations
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
    // this method will calculate the wait time based on the number of people in line and number of open tills
    // Does bound checking on all the parameters, and also can distinguish between peak and none peak data
    public void calculateTime(int a){
        int mins;   // used in calculations
        int seconds;    // used in calculations
        double time;    // used in calculations
        int numPpl = 0; // used to recieve values from edit text fields
        int numTls= 0;  // used to recieve values of edit text fields
        if(numPeople.getText().toString().equals("")||numTills.getText().toString().equals("")){ // check to see if fields are empty
            if(data.language == 0){
                editTextError.setText("Please fill all required fields.");
                return;
            }else{  // french
                editTextError.setText("Se il vous plait remplir tous les champs.");
                return;
            }

        }
        try{
            numTls = Integer.parseInt(numTills.getText().toString());   // gets the number of tills
            numPpl = Integer.parseInt(numPeople.getText().toString()); // gets number of people
        }
        catch(NullPointerException e){ // in case of null pointer exception from the objects

            if(data.language == 0){
                editTextError.setText("Please fill all required fields.");
                return;
            }else{ // french
                editTextError.setText("Se il vous plait remplir tous les champs.");
                return;
            }
        }catch(Exception e) {
            System.out.println(e);
        }
        editTextError.setText("");  // reset the error message
        if (numTls == 0 && numPpl == 0){ // invalid number of people and tills
            if(data.language == 0){
                editTextError.setText("Please enter valid number of tills and people.");

            }else{  // french
                editTextError.setText("Se il vous plait entrer le numero valide de caisses ou les personnes.");

            }
        }
        else if(numTls == 0 && numPpl != 0){ // number of people valid but # tills = 0
            if(data.language == 0){
                editTextError.setText("Please enter valid number of tills.");

            }else{
                editTextError.setText("Se il vous plait entrer le numero valide de tills.");

            }
        }
        else if (numTls != 0 && numPpl == 0){
            data.waitMins = 0;
            data.waitSeconds = 0;

        }
        else if (numTls >= 5 || numPpl >= 50){    // unrealistic input
            if(data.language == 0){
                editTextError.setText("Please enter valid number of tills and people.");

            }else{
                editTextError.setText("Se il vous plait entrer le numero valide de caisses ou les personnes.");

            }
        }
        // this switch does the calculations for which ever selected establishment
        switch (a){
            case 0: // starbucks
                if(numPpl != 0 && numTls != 0 && (numPpl < 50 && numTls <5)){ // no 0 values
                    if(numPpl < 10) // none peak time
                        time = (numPpl * data.starBucksTime)/(numTls); // calculate total time
                    else
                        time = (numPpl * data.starBucksTimePeak)/(numTls); // calculate total time
                    if(time >= 1){
                        mins = (int) Math.floor(time);      // derive the mins
                        seconds = (int) ((time-mins)* 60);    // derive the seconds
                    }else{  // time less than 1
                        mins = 0;
                        seconds = (int) (time*60);
                    }
                    // set the constants
                    data.waitMins = mins;
                    data.waitSeconds = seconds;
                }


                break;
            case 1: // tim hortons
                if(numPpl != 0 && numTls != 0 && (numPpl < 50 && numTls <5)){ // no 0 values
                    if(numPpl < 10)
                        time = (numPpl * data.timHortonsTime)/(numTls); // calculate total time
                    else
                        time = (numPpl * data.timHortonsTimePeak)/(numTls); // calculate total time
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
        }
    }
}
