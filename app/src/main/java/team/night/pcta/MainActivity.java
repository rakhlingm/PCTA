package team.night.pcta;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    /*
     * Notifications from UsbService will be received here.
     */
    PCTA_Logger log;
    Superposition_Logger superposition_logger;
    private final BroadcastReceiver mUsbReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            switch (intent.getAction()) {
                case UsbService.ACTION_USB_PERMISSION_GRANTED: // USB PERMISSION GRANTED
                    Toast.makeText(context, "USB Ready", Toast.LENGTH_SHORT).show();
                    break;
                case UsbService.ACTION_USB_PERMISSION_NOT_GRANTED: // USB PERMISSION NOT GRANTED
                    Toast.makeText(context, "USB Permission not granted", Toast.LENGTH_SHORT).show();
                    break;
                case UsbService.ACTION_NO_USB: // NO USB CONNECTED
                    Toast.makeText(context, "No USB connected", Toast.LENGTH_SHORT).show();
                    break;
                case UsbService.ACTION_USB_DISCONNECTED: // USB DISCONNECTED
                    Toast.makeText(context, "USB disconnected", Toast.LENGTH_SHORT).show();
                    break;
                case UsbService.ACTION_USB_NOT_SUPPORTED: // USB NOT SUPPORTED
                    Toast.makeText(context, "USB device not supported", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };
    private UsbService usbService;
    private Log_PLU_Event_Info log_plu_event_info;
    private TextView display;
    private EditText editText;
    private MyHandler mHandler;
    private Handler mTextBoxHandler;
    private boolean gameOn;
    long startTime;
    private final ServiceConnection usbConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName arg0, IBinder arg1) {
            usbService = ((UsbService.UsbBinder) arg1).getService();
            usbService.setHandler(mHandler);
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            usbService = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startTime = System.currentTimeMillis();

        display = (TextView) findViewById(R.id.textView1);
        display.setMovementMethod(new ScrollingMovementMethod());
        startTime = System.currentTimeMillis();
        mTextBoxHandler = new Handler() {
            public void handleMessage(Message msg) {
                super.handleMessage(msg);

                if (gameOn) {
                    long seconds = ((System.currentTimeMillis() - startTime)) / 1000;
                    Log.i("info", "seconds = " + seconds);
                    display.setText("seconds = " + seconds);
                }
            //    mTextBoxHandler.sendEmptyMessageDelayed(0, 1000);
            }
        };

        gameOn = true;
        Log_PLU_Event_Info log_plu_event_info = new Log_PLU_Event_Info(mTextBoxHandler);
        mTextBoxHandler.sendEmptyMessage(0);

        log = PCTA_Logger.getInstance();
        try {
            log.getMainFolder();
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.i(Constants.PCTA_INITIALIZATION);
        superposition_logger = Superposition_Logger.getInstance();
        try {
            superposition_logger.getMainFolder();
            log.i(Constants.SUPERPOSITION_LOG_FILE_CREATED);
        } catch (IOException e) {
            e.printStackTrace();
        }
        mHandler = new MyHandler(this);
    //    log_plu_event_info.setmHandler(mHandler);

        editText = (EditText) findViewById(R.id.editText1);
        Button sendButton = (Button) findViewById(R.id.buttonSend);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!editText.getText().toString().equals("")) {
                    String data = editText.getText().toString();
                    if (usbService != null) { // if UsbService was correctly binded, Send data
                        display.append(data);
                        usbService.write(data.getBytes());
                    }
                }
            }
        });
        QueueBytesFromSerial qbfs = new QueueBytesFromSerial();
        qbfs.execute();
    }
    /*TO ADD*/
    @Override
    public void onResume() {
        super.onResume();
        setFilters();  // Start listening notifications from UsbService
        startService(UsbService.class, usbConnection, null); // Start UsbService(if it was not started before) and Bind it
    }
    /*TO ADD*/
    @Override
    public void onPause() {
        super.onPause();
        unregisterReceiver(mUsbReceiver);
        unbindService(usbConnection);
    }

    private void startService(Class<?> service, ServiceConnection serviceConnection, Bundle extras) {
        if (!UsbService.SERVICE_CONNECTED) {
            Intent startService = new Intent(this, service);
            if (extras != null && !extras.isEmpty()) {
                Set<String> keys = extras.keySet();
                for (String key : keys) {
                    String extra = extras.getString(key);
                    startService.putExtra(key, extra);
                }
            }
            startService(startService);
        }
        Intent bindingIntent = new Intent(this, service);
        bindService(bindingIntent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    private void setFilters() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(UsbService.ACTION_USB_PERMISSION_GRANTED);
        filter.addAction(UsbService.ACTION_NO_USB);
        filter.addAction(UsbService.ACTION_USB_DISCONNECTED);
        filter.addAction(UsbService.ACTION_USB_NOT_SUPPORTED);
        filter.addAction(UsbService.ACTION_USB_PERMISSION_NOT_GRANTED);
        registerReceiver(mUsbReceiver, filter);
    }

    /*
     * This handler will be passed to UsbService. Data received from serial port is displayed through this handler
     */

    private class MyHandler extends Handler {
        private final WeakReference<MainActivity> mActivity;
        public MyHandler(MainActivity activity) {
            mActivity = new WeakReference<>(activity);
        }
        int counter = 0;
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case UsbService.MESSAGE_FROM_SERIAL_PORT:
                    String data = (String) msg.obj;
            //        mActivity.get().display.append(Integer.toString(++counter));  /*was (data)*/
//                    mActivity.get().display.append(data);  /*was (data)*/
                    log.i(data);
                    try {
                        log.i(data);
                    } catch (Exception e) {
                        log.e(e.toString());
                    }
                    int scrollAmount = display.getLayout().getLineTop(display.getLineCount()) - display.getHeight();
                    // if there is no need to scroll, scrollAmount will be <=0
                    if (scrollAmount > 0)
                        display.scrollTo(0, scrollAmount);
                    break;
            }
        }
    }

}