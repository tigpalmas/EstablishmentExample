package com.example.tiago.establishmentexample;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.facebook.FacebookSdk.getApplicationContext;


public class Splashscreen extends Activity {
    private Button btnLogin, btnSignUp;
    private Button btnFacebook;
    private TextView txtDescriptionface, txtLinkTermos;
    private TextView txtEntry;
    private List<String> animations = new ArrayList<>();
    private Thread splashTread;

    private Button btnRestaurant;
    private Button btnShop;
    private Button btnBar;
    private Button btnPizzaria;



    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Window window = getWindow();
        window.setFormat(PixelFormat.RGBA_8888);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        btnRestaurant = (Button) findViewById(R.id.btn_restaurant);
        btnShop = (Button) findViewById(R.id.btn_shop);
        btnBar = (Button) findViewById(R.id.btn_bar);
        btnPizzaria = (Button) findViewById(R.id.btn_pizzaria);

        btnRestaurant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivityRestaurant.class);
                intent.putExtra("from", "restaurant");
                startActivity(intent);
            }
        });

        btnShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("from", "shop");
                startActivity(intent);
            }
        });

        btnBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivityBar.class);
                intent.putExtra("from", "bar");
                startActivity(intent);
            }
        });

        btnBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivityBar.class);
                intent.putExtra("from", "bar");
                startActivity(intent);
            }
        });

        btnPizzaria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivityPizza.class);
                intent.putExtra("from", "pizza");
                startActivity(intent);
            }
        });



        animations.add("https://media.giphy.com/media/TO200GkRbqqXe/giphy.gif");

        Random rand = new Random();
        String random = animations.get(rand.nextInt(animations.size()));





        startAnimation((RelativeLayout) findViewById(R.id.lin_lay), (ImageView) findViewById(R.id.splash));

    }

    public void startAnimation(RelativeLayout l, ImageView iv) {
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.alpha);
        anim.reset();

        l.clearAnimation();
        l.startAnimation(anim);

        anim = AnimationUtils.loadAnimation(this, R.anim.translate);
        anim.reset();

        iv.clearAnimation();
        iv.startAnimation(anim);

        splashTread = new Thread() {
            @Override
            public void run() {
                try {
                    int waited = 0;
                    // Splash screen pause time
                    while (waited < 3000) {
                        sleep(100);
                        waited += 100;
                    }


                } catch (InterruptedException e) {
                    // do nothing
                } finally {

                }

            }


        };
        splashTread.start();
    }


}