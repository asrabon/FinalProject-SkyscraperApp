package com.example.junkai.finalproject;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sloan.skyscraperbuilder.MainActivity;

/**
 * @author Junkai Xing
 * @Version 1
 * @Date 2017/4/8
 * @Class CSCI343
 */

public class MenuScreen extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView nameOfGameTextView;
    private Button gameStartButton,scoreButton;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_screen);

        //set the background picture
        //Drawable drBack = getResources().getDrawable(R.drawable.empire);
        //Bitmap bitmapBack = ((BitmapDrawable) drBack).getBitmap();

        Bitmap bitmapBack = BitmapFactory.decodeResource(getResources(), R.drawable.empire);
        int heightOfScreen = Resources.getSystem().getDisplayMetrics().heightPixels;
        int widthOfScreen = Resources.getSystem().getDisplayMetrics().widthPixels;
        Drawable newDrBack = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmapBack,widthOfScreen,heightOfScreen,true));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            getWindow().getDecorView().setBackground(newDrBack);
        }

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // modify the size of drawable being used
        // and then set the icon on the toolbar(NavigationIcon)
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.audioicon);
        final Drawable d = new BitmapDrawable(getResources(),Bitmap.createScaledBitmap(bitmap,100,100,true));
        Bitmap mutedAudioBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.mutedaudioicon);
        final Drawable mutedAudioDrawable = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(mutedAudioBitmap, 100, 100, true));
        toolbar.setNavigationIcon(d);

        // do something when click the audio play button
        // basically stop or continue the music
        toolbar.setNavigationOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(mediaPlayer.isPlaying()) {
                    toolbar.setNavigationIcon(mutedAudioDrawable);
                    mediaPlayer.pause();
                }else{
                    toolbar.setNavigationIcon(d);
                    mediaPlayer.start();
                }
            }
        });
        getSupportActionBar().setTitle("");

        //set color of TextView
        nameOfGameTextView = (TextView)findViewById(R.id.nameOfGameTextView);
        nameOfGameTextView.setTextColor(Color.BLACK);

        gameStartButton = (Button)findViewById(R.id.gameStartButton);
        scoreButton = (Button)findViewById(R.id.ScoreButton);
        scoreButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),HighestScore.class);
                HighestScore.restartButtonName = "START";
                startActivity(intent);
            }
        });


        // go to "game play" activity when click "Start" button
        gameStartButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(v.getContext(),MainActivity.class);
                startActivity(intent);


            }

        });

    }

    @Override
    public void onResume(){
        super.onResume();


        mediaPlayer = MediaPlayer.create(this, R.raw.intro);
        mediaPlayer.start();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        // Inflate the menu; this add items to the action bar if it is present
        getMenuInflater().inflate(R.menu.menu_menuscreen,menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        // Handle action bar item clicks here
        int id = item.getItemId();

        switch(id){
            case R.id.task_turnMusic:

                if(mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                }else{
                    mediaPlayer.start();
                }

                break;
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPause(){
        super.onPause();

        mediaPlayer.stop();
        mediaPlayer.release();
        mediaPlayer = null;

    }



}
