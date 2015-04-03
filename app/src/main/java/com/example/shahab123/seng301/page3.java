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
    ImageButton back;
    ImageButton donePayment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page3);
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //this.setContentView(R.layout.activity_page3);

        // init the Intents
        page2Intent = new Intent(page3.this, page2.class);
        // init the buttons
        back = (ImageButton) findViewById(R.id.imageButton3);
        back.setOnClickListener(this);
        donePayment = (ImageButton) findViewById(R.id.imageButton5);
        donePayment.setOnClickListener(this);

        message = (TextView) findViewById(R.id.textView7);
        estimatedTime = (TextView) findViewById(R.id.textView5);
        timer = (TextView) findViewById(R.id.textView6);
        checkLanguage();
        updateTimer();
    }

    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_page3, menu);
        return true;
    }*/

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

    public void updateTimer(){
        String temp = "";

        temp += (data.waitMins+":"+data.waitSeconds);
        timer.setText(temp);
    }

    public void onClick(View v){
        switch(v.getId()){
            case R.id.imageButton3:// back button
                back();
                break;
        }
    }

    public void checkLanguage(){
        if (data.language == 1){ // french is selected
            changeLanguage();
        }
    }
    public void changeLanguage(){
        // reset he buttons
        back.setImageResource(R.drawable.back_french);
        donePayment.setImageResource(R.drawable.done_payment_french);
        // reset the texts
        message.setText(R.string.message_french);
        estimatedTime.setText(R.string.waitTime_french);

    }
    public void back(){
        this.finish();
    }
}
