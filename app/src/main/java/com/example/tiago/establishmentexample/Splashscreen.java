package com.example.tiago.establishmentexample;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.util.Log;
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
import com.example.tiago.establishmentexample.domain.ErrorModel;
import com.example.tiago.establishmentexample.domain.ErrorUtils;
import com.example.tiago.establishmentexample.domain.MessagePush;
import com.example.tiago.establishmentexample.domain.Push;
import com.example.tiago.establishmentexample.domain.Tags;
import com.example.tiago.establishmentexample.network.EstablishmentProvider;
import com.example.tiago.establishmentexample.network.EstablishmentService;
import com.example.tiago.establishmentexample.product.MVP;
import com.example.tiago.establishmentexample.utils.AppPreferenceTools;
import com.onesignal.OSPermissionSubscriptionState;
import com.onesignal.OneSignal;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.facebook.FacebookSdk.getApplicationContext;


public class Splashscreen extends Activity {
    private AppPreferenceTools mAppPreferenceTools;
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
    private Button btnSendPush;

    private EstablishmentService mTService;
    private MVP.PresenterProduct mPresenter;

    private String oneSignalId;



    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Window window = getWindow();
        window.setFormat(PixelFormat.RGBA_8888);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        mAppPreferenceTools = new AppPreferenceTools(this);
        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        btnRestaurant = (Button) findViewById(R.id.btn_restaurant);
        btnShop = (Button) findViewById(R.id.btn_shop);
        btnBar = (Button) findViewById(R.id.btn_bar);
        btnPizzaria = (Button) findViewById(R.id.btn_pizzaria);
        btnSendPush = (Button) findViewById(R.id.btn_sendPush);

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



        btnPizzaria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivityPizza.class);
                intent.putExtra("from", "pizza");
                startActivity(intent);
            }
        });

        btnSendPush.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessage();
            }
        });


        animations.add("https://media.giphy.com/media/TO200GkRbqqXe/giphy.gif");

        Random rand = new Random();
        String random = animations.get(rand.nextInt(animations.size()));






        startAnimation((RelativeLayout) findViewById(R.id.lin_lay), (ImageView) findViewById(R.id.splash));

        if(!mAppPreferenceTools.isAlreadyRegisterOneSinal()){
            getOneSignalId();
        }




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

    public void getOneSignalId(){
        OSPermissionSubscriptionState status = OneSignal.getPermissionSubscriptionState();
        status.getPermissionStatus().getEnabled();
        oneSignalId  = status.getSubscriptionStatus().getUserId();
        registerPush(oneSignalId);
    }

    private void registerPush(String oneSignalId){
        EstablishmentProvider provider  = new EstablishmentProvider();
        mTService = provider.getmService();
        Tags tags =new Tags();
        tags.tag1 = oneSignalId;
        tags.establishment= "restaurant";


        Push push = new Push();
        push.tags = tags;
        push.oneSignalId = oneSignalId;

        Call<Push> call = mTService.regsterPush(push);
        call.enqueue(new Callback<Push>() {
            @Override
            public void onResponse(Call<Push> call, Response<Push> response) {
                if(response.isSuccess()){
                    Log.i("teste", "sucesso");
                }else{
                    Log.i("teste", "erro");
                }
            }

            @Override
            public void onFailure(Call<Push> call, Throwable t) {

            }
        });
    }

    private void sendMessage(){
        EstablishmentProvider provider  = new EstablishmentProvider();
        mTService = provider.getmService();

        MessagePush push = new MessagePush();
        push.key = "tag1";
        push.value = oneSignalId;
        push.message = "vai que vai";

        Call<MessagePush> call = mTService.sendPushMessage(push);
        call.enqueue(new Callback<MessagePush>() {
            @Override
            public void onResponse(Call<MessagePush> call, Response<MessagePush> response) {
                if(response.isSuccess()){
                    Log.i("teste", "sucesso");
                }else{
                    Log.i("teste", "erro");
                }
            }

            @Override
            public void onFailure(Call<MessagePush> call, Throwable t) {

            }
        });
    }


}