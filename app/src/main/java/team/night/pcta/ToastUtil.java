package team.night.pcta;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

/**
 * Created by GregoriRakhlin on 4/28/2018.
 */

public class ToastUtil extends Activity {
    public static void showToast(Context mContext, String message){
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
    }
}
