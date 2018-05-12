package team.night.pcta;

import android.app.Application;
import android.content.Context;

/**
 * Created by GregoriRakhlin on 4/28/2018.
 */

public class PCTA extends Application {
    private static Context context;

    public void onCreate() {
        super.onCreate();
        PCTA.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return PCTA.context;
    }
}
