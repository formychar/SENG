package com.example.shahab123.seng301;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {
    ImageButton starbucks;
    ImageButton tim_hortons;
    Intent page2Intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        page2Intent = new Intent(MainActivity.this, page2.class); // set up the intent for page 2

        starbucks = (ImageButton) findViewById(R.id.imageButton);   // init the starbucks button from XML
        tim_hortons = (ImageButton) findViewById(R.id.imageButton2);    // init the tim hortons button from XML

        starbucks.setOnClickListener(this);
        tim_hortons.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.imageButton:  // Starbucks is pressed
                starbucks();
                break;
            case R.id.imageButton2: // tim hortons is pressed
                timHortons();
                break;
        }
    }

    public void starbucks(){ // open the second activity
        page2Intent.putExtra("key", 0);             // 0 for starBucks
        MainActivity.this.startActivity(page2Intent);
    }

    public void timHortons(){
        page2Intent.putExtra("key", 1);         // 1 for tim Hortons
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
