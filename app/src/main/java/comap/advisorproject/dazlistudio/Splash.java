package comap.advisorproject.dazlistudio;

import android.app.Activity;

import android.os.Bundle;
import android.content.Intent;
import java.util.Timer;
import java.util.TimerTask;

public class Splash extends Activity {

    private final int DURACION = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        TimerTask task =new TimerTask() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                Intent mainIntent= new Intent().setClass(Splash.this, Login.class);
                startActivity(mainIntent);
                finish();
            }
        };

        Timer timer = new Timer();
        timer.schedule(task, DURACION);
    }
}
