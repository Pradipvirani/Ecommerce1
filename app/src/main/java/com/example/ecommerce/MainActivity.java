package com.example.ecommerce;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.airbnb.lottie.LottieAnimationView;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    public static SharedPreferences preferences;
    public static SharedPreferences.Editor editor;
    boolean islogin = false;
    LottieAnimationView lottieAnimationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lottieAnimationView=findViewById(R.id.Lottie_view);

      lottieAnimationView.addAnimatorUpdateListener(valueAnimator -> {

      });
        lottieAnimationView.pauseAnimation();
        if (networkconnected())
      {
          preferences=getSharedPreferences("pre",0);
          editor=preferences.edit();
          islogin= preferences.getBoolean("islogin",false);
          if (islogin)
          {

          }
          else
          {

          }
      }
       else
      {
          AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
          builder.setTitle("Alert...");
          builder.setMessage("Please Start Internet");
          builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
              @Override
              public void onClick(DialogInterface dialogInterface, int i) {
                  dialogInterface.dismiss();
              }
          });
          builder.show();
      }
    }

    private boolean networkconnected() {
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        return (info != null && info.isConnected());
    }
}