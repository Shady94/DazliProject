package comap.advisorproject.dazlistudio;

import android.app.Activity;
import android.os.Bundle;

import com.facebook.appevents.AppEventsLogger;

/**
 * Created by zshady on 4/02/16.
 */
public class Visualizador extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizador);

    }

    @Override
    protected void onPause() {
        super.onPause();

        // Logs 'app deactivate' App Event.
        AppEventsLogger.deactivateApp(this);
    }
}
