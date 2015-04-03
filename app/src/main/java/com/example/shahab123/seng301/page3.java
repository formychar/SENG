package com.example.shahab123.seng301;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.TextView;
import android.view.View.OnClickListener;
import android.view.*;

import org.w3c.dom.Text;


public class page3 extends ActionBarActivity implements OnClickListener {
    TextView timer;
    TextView message;
    TextView estimatedTime;
    Intent page2Intent;
    Intent page1Intent;
    ImageButton back;
    ImageButton donePayment;
    ImageButton mainMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page3);
        initButtons();
        initIntent();
        initTextView();
        checkLanguage(); // check the language
        updateTimer();  // update the time
    }

    // init intents
    public void initIntent(){
        // init the Intents
        page2Intent = new Intent(page3.this, page2.class);
        page1Intent = new Intent(page3.this, MainActivity.class);
    }
    // init buttons
    public void initButtons(){
        // init the buttons
        back = (ImageButton) findViewById(R.id.imageButton3);
        back.setOnClickListener(this);
        donePayment = (ImageButton) findViewById(R.id.imageButton5);
        donePayment.setOnClickListener(this);
        mainMenu = (ImageButton) findViewById(R.id.imageButton6);
        mainMenu.setOnClickListener(this);
    }
    // init text view
    public void initTextView(){
        message = (TextView) findViewById(R.id.textView7);
        estimatedTime = (TextView) findViewById(R.id.textView5);
        timer = (TextView) findViewById(R.id.textView6);
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
    // updates the time on the 3rd screen
    public void updateTimer(){
        String temp = "";
        if(data.waitMins < 10 && data.waitSeconds < 10){
            temp += 0+data.waitMins+":"+0+data.waitSeconds;
        }
        else if( data.waitMins < 10 && data.waitSeconds >= 10){
            temp += 0+data.waitMins+":"+data.waitSeconds;
        }
        else if(data.waitMins >= 10 && data.waitSeconds <10){
            temp += data.waitMins+":"+0+data.waitSeconds;
        }
        else{
            temp += data.waitMins+":"+data.waitSeconds;
        }
        timer.setText(temp);
    }
    // on click listener
    public void onClick(View v){
        switch(v.getId()){
            case R.id.imageButton3:// back button
                back();
                break;
            case R.id.imageButton6:
                mainMenu();
                break;
        }
    }
    // controller for language changes
    public void checkLanguage(){
        if (data.language == 1){ // french is selected
            changeLanguage();
        }
    }
    // goes back to main menu
    public void mainMenu(){
        this.finish();
        page3.this.startActivity(page1Intent);
    }
    // changes the language of the page
    public void changeLanguage(){
        // reset he buttons
        back.setImageResource(R.drawable.back_french);
        mainMenu.setImageResource(R.drawable.mainmenu_french);
        donePayment.setImageResource(R.drawable.done_payment_french);
        // reset the texts
        message.setText(R.string.message_french);
        estimatedTime.setText(R.string.waitTime_french);

    }
    public void back(){
        this.finish();
    }
}
