package com.example.shahab123.seng301;
// Needs to connect the radio button to the french version
import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {
    ImageButton starbucks;
    ImageButton tim_hortons;
    ImageButton exit;
    Intent page2Intent;

    RadioButton english;
    RadioButton french;
    RadioGroup radioGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initIntent();   // init Intents
        initButtons();  // init Buttons
        initRadioButtons(); // init radio buttons
    }
    @Override
    // Listener for all the UI interactions
    public void onClick(View v){
        languageSelect();
        switch (v.getId()){
            case R.id.imageButton:  // Starbucks is pressed
                starbucks();
                break;
            case R.id.imageButton2: // tim hortons is pressed
                timHortons();
                break;
            case R.id.imageButton7:  // exit is pressed
                System.exit(1);
        }
    }
    // inits the radio buttons used for language
    public void initRadioButtons(){
        english = (RadioButton) findViewById(R.id.radioButton);
        french = (RadioButton) findViewById(R.id.radioButton2);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
    }
    // inits the Intents used for page transitions
    public void initIntent(){
        page2Intent = new Intent(MainActivity.this, page2.class); // set up the intent for page 2
    }
    // inits all the Image Buttons used in the first activity
    public void initButtons(){
        starbucks = (ImageButton) findViewById(R.id.imageButton);   // init the starbucks button from XML
        tim_hortons = (ImageButton) findViewById(R.id.imageButton2);    // init the tim hortons button from XML
        exit = (ImageButton) findViewById(R.id.imageButton7);
        starbucks.setOnClickListener(this);
        tim_hortons.setOnClickListener(this);
        exit.setOnClickListener(this);
    }
    // selects the language based on the radio button input
    public void languageSelect(){
        if(english.isChecked()){    // english
            data.language = 0;
        }else{  // french
            data.language = 1;
        }
    }

    // opens page for Starbucks
    public void starbucks(){ // open the second activity
            data.optionFlag = 0;    // 0 for Starbucks
            MainActivity.this.startActivity(page2Intent);
    }
    // opens page for tim hortons
    public void timHortons(){
            data.optionFlag = 1;         // 1 for tim Hortons
            MainActivity.this.startActivity(page2Intent);
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
