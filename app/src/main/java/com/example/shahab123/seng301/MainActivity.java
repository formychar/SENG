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
    Intent page2Intent;
    Intent page2IntentFrench;
    RadioButton english;
    RadioButton french;
    RadioGroup radioGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //this.setContentView(R.layout.activity_main);

        page2Intent = new Intent(MainActivity.this, page2.class); // set up the intent for page 2
        page2IntentFrench = new Intent (MainActivity.this, page2French.class); //  set up intent for page 2 french

        starbucks = (ImageButton) findViewById(R.id.imageButton);   // init the starbucks button from XML
        tim_hortons = (ImageButton) findViewById(R.id.imageButton2);    // init the tim hortons button from XML

        english = (RadioButton) findViewById(R.id.radioButton);
        french = (RadioButton) findViewById(R.id.radioButton2);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);


        starbucks.setOnClickListener(this);
        tim_hortons.setOnClickListener(this);
    }

    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }*/

    @Override
    public void onClick(View v){
        radioGroup();
        languageSelect();
        switch (v.getId()){

            case R.id.imageButton:  // Starbucks is pressed
                starbucks();
                break;
            case R.id.imageButton2: // tim hortons is pressed
                timHortons();
                break;
        }
    }
    // selects the language based on the radio button input
    public void languageSelect(){
        if(english.isChecked()){    // english
            data.language = 0;
        }else{  // french
            data.language = 1;
        }
    }
    // toggles the radio buttons if pressed
    public void radioGroup(){

    }
    public void starbucks(){ // open the second activity

            data.optionFlag = 0;    // 0 for Starbucks
            MainActivity.this.startActivity(page2Intent);


    }

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
