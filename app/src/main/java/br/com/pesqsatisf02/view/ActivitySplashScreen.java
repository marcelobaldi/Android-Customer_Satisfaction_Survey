package br.com.pesqsatisf02.view;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import br.com.pesqsatisf02.R;

public class ActivitySplashScreen extends AppCompatActivity {

    @Override protected void onCreate(Bundle savedInstanceState){super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        getSupportActionBar().hide();                       //tirar toolbar

        //Executa "X" Ação A Cada "x" Tempo (após splashScreen, chama a tela principal)
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getBaseContext(), ActivityMain.class));
                finish();
            }
        },3000);

    }
}

