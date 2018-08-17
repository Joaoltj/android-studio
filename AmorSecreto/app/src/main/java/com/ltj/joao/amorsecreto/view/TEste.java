package com.ltj.joao.amorsecreto.view;


import android.media.AudioManager;
import android.media.MediaPlayer;
;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;



import com.ltj.joao.amorsecreto.R;

import java.io.IOException;


public class TEste extends AppCompatActivity implements MediaPlayer.OnBufferingUpdateListener ,MediaPlayer.OnPreparedListener,MediaPlayer.OnSeekCompleteListener,MediaPlayer.OnCompletionListener,MediaPlayer.OnErrorListener{

    private TextView txtTime;
    private ImageButton btnPlay,btnPause,btnStop;
    private long duration;
    private long currentTime;
    private boolean isPlaying;
    private MediaPlayer player;
    private SeekBar seek;
    private String url = "http://jsproblems.000webhostapp.com/Indom%C3%A1vel%202%20-%20Xam%C3%A3%20.%20Tulio%20Dek%20e%20Mano%20Fler.mp3";
    private String timeFormatado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teste);

        btnPlay = (ImageButton)findViewById(R.id.btnPlay);
        btnPause = (ImageButton)findViewById(R.id.btnPause);
        btnStop = (ImageButton)findViewById(R.id.btnStop);
        txtTime = (TextView)findViewById(R.id.txtTime);
        seek = (SeekBar)findViewById(R.id.seek);


        if(savedInstanceState != null){
            duration = savedInstanceState.getLong("duration");
            currentTime = savedInstanceState.getLong("currentTime");
            isPlaying = savedInstanceState.getBoolean("isPlaying");
            if(isPlaying){
                Toast.makeText(this,"s",Toast.LENGTH_LONG).show();
               playMusic(null);
            }
        }
        seek.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(player != null){
                    SeekBar sb = (SeekBar)view;
                     int position = (player.getDuration()/100)*seek.getProgress();
                     player.seekTo(position);

                }
                return false;

            }
        });

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if(player != null){
            outState.putLong("duration",player.getDuration());
            outState.putLong("currentTime",player.getCurrentPosition());
            outState.putBoolean("isPlaying",isPlaying);
        }
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        if (player != null) {
            player.stop();
            player.release();
            player = null;
        }
    }

    @Override
    public void onPause(){
        super.onPause();
        if (player != null) {
          duration = player.getDuration();
          currentTime = player.getCurrentPosition();
        }
    }

    public void playMusic(View v){

        if(player == null){

            try {
                player = new MediaPlayer();
                player.setAudioStreamType(AudioManager.STREAM_MUSIC);
                player.setDataSource(url);
                player.prepareAsync();

            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(TEste.this,e.toString(),Toast.LENGTH_LONG).show();
            }

            player.setOnPreparedListener(this);
            player.setOnCompletionListener(this);
            player.setOnErrorListener(this);
            player.setOnBufferingUpdateListener(this);
            player.setOnSeekCompleteListener(this);

        }else{
            isPlaying = true;
            player.start();
            updateMusicThread(player,txtTime);
        }

    }
    public void pauseMusic(View v){
        isPlaying = false;
        if (player != null) {
            player.pause();

        }
    }
    public void stopMusic(View v){
        isPlaying = false;
        if (player != null) {
            player.stop();
            player.release();
            player = null;
            currentTime = 0;
            txtTime.setText(timeFormatado+" / 00:00");
            seek.setProgress(0);
        }
    }

    public String formatTime(long time){
        long aux = time / 1000;
        int minute = (int) aux / 60;
        int seconds = (int)(aux % 60);
        String formatado = minute < 10 ? "0"+minute:minute+"";
        formatado += ":"+ (seconds < 10 ? "0"+seconds:seconds+"");
        return formatado;
    }

    public void updateMusic(final long duration, final long currentTime,final TextView view){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                double p = currentTime;
                double p2 = duration;
                int p3 =(int)((p/p2)*100);
                seek.setProgress(p3);
                Log.i("MrScript","progress - "+ p);
                Log.i("MrScript","progress - "+ p2);
                Log.i("MrScript","progress - "+ p3);
                Log.i("MrScript","duration - "+ duration);
                Log.i("MrScript","current - "+ currentTime);

                String sDuration = formatTime(duration);
                String sCurrentTime = formatTime(currentTime);

                view.setText(sDuration + " / "+ sCurrentTime);


            }
        });
    }

    public void updateMusicThread(final MediaPlayer mp,final TextView view){
        new Thread(){
            @Override
            public void run(){
                while(isPlaying){
                    try {

                        Log.i("MrScript","update");
                        Log.i("MrScript",""+isPlaying);
                        updateMusic(mp.getDuration(),mp.getCurrentPosition(),view);
                        Thread.sleep(1000);
                    } catch (IllegalStateException e){e.printStackTrace();
                    } catch (InterruptedException e){e.printStackTrace();}
                }

            }

        }.start();
    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        Log.i("MrScript","onCompletion()");
        nextMusic();
    }

    @Override
    public boolean onError(MediaPlayer mediaPlayer, int i, int i1) {
        Log.i("MrScript","onError()");
        return false;
    }

    @Override
    public void onPrepared(MediaPlayer mp) {

        isPlaying = true;
        mp.start();
        timeFormatado = formatTime(mp.getDuration());
        mp.seekTo((int)(currentTime));
        updateMusicThread(mp,txtTime);
    }

    @Override
    public void onSeekComplete(MediaPlayer mediaPlayer) {
        Log.i("MrScript","onSeekComplete()");
    }

    @Override
    public void onBufferingUpdate(MediaPlayer mediaPlayer, int i) {
        Log.i("MrScript","onBufferingUpdate()");
    }

    public void nextMusic(){

            stopMusic(null);
            playMusic(null);
        Log.i("HAHA","NEXT()");

    }
}
