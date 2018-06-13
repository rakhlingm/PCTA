package team.night.pcta;

import android.app.FragmentManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.AsyncTask;
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

import static team.night.pcta.FW_Images.*;

public class MainActivity extends AppCompatActivity {

    /*
     * Notifications from UsbService will be received here.
     */
    PCTA_Logger log;
    Superposition_Logger superposition_logger;
    QueueBytesFromSerial qbfs;
    int count = 0;
    FW_Images fw_image;
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
    private TextView textView_ARM;
    private TextView textView_DSP;
    private TextView textView_Conf;
    private TextView textView_BT;
    private TextView textView_BNDL_CONF;
    private TextView textView_Bundle;
    private EditText editText;
    private MyHandler mHandler;
    private Handler mTextBoxHandler;
    private boolean gameOn;
    long startTime;
    private final ServiceConnection usbConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName arg0, IBinder arg1) {
            usbService = ((UsbService.UsbBinder) arg1).getService();
        //    usbService.setHandler(mHandler);
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
        QueueBytesFromSerial queueBytesFromSerial = new QueueBytesFromSerial();
        queueBytesFromSerial.setHandler(mHandler);
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
    //    Log_PLU_Event_Info log_plu_event_info = new Log_PLU_Event_Info(mTextBoxHandler);
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

    //    editText = (EditText) findViewById(R.id.editText1);
        textView_ARM = (TextView) findViewById(R.id.textViewARM);
        textView_DSP = (TextView) findViewById(R.id.textViewDSP);
        textView_Conf = (TextView) findViewById(R.id.textViewConf);
        textView_BT = (TextView) findViewById(R.id.textViewBT);
        textView_BNDL_CONF = (TextView) findViewById(R.id.textViewBNDL_CONF);
        textView_Bundle = (TextView) findViewById(R.id.textViewBundle);
        Button ARM_Button = (Button) findViewById(R.id.buttonARM);
        ARM_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
        //        if (editText.getText().toString().equals("")) {
                //    String data = editText.getText().toString();
                    fw_image = ARM;
                    FW_Version.getVersion(usbService, FW_Version.armVersionImage);
        //        }
            }
        });
        Button DSP_Button = (Button) findViewById(R.id.buttonDSP);
        DSP_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    fw_image = DSP;
                    FW_Version.getVersion(usbService, FW_Version.dspVersionImage);
            }
        });
        Button Conf_Button = (Button) findViewById(R.id.buttonConf);
        Conf_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fw_image = CONF;
                FW_Version.getVersion(usbService, FW_Version.confVersionImage);
            }
        });
        Button BT_Button = (Button) findViewById(R.id.buttonBT);
        BT_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fw_image = BT;
                FW_Version.getVersion(usbService, FW_Version.btVersionImage);
            }
        });
        Button  BNDL_CONF_Button= (Button) findViewById(R.id.buttonBNDL_CONF);
        BNDL_CONF_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fw_image = BNDL_CONF;
                FW_Version.getVersion(usbService, FW_Version.bundle_conf);
            }
        });
        Button BUNDLE_Button = (Button) findViewById(R.id.buttonBundle);
        BUNDLE_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fw_image = BUNDLE;
                FW_Version.getVersion(usbService, FW_Version.bundle);
            }
        });
        final FragmentManager fm=getFragmentManager();
        final BinFilesListFragment bflf = new BinFilesListFragment();
        Button Find_FW_Button = (Button) findViewById(R.id.buttonFindFW);
        Find_FW_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bflf.show(fm, "Files");
            }
        });
    //    qbfs = new QueueBytesFromSerial();
    //    qbfs.execute();

        QueueBytesFromSerialPrivate qbfs = new QueueBytesFromSerialPrivate();
        qbfs.execute();
    }


    /*TO ADD*/
    @Override
    public void onResume() {
        super.onResume();
        setFilters();  // Start listening notifications from UsbService
        startService(UsbService.class, usbConnection, null); // Start UsbService(if it was not started before) and Bind it
    //    startService(new Intent(this, QueueFromSerial.class));
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
                    mActivity.get().display.append(data);
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



    private class QueueBytesFromSerialPrivate extends AsyncTask<Void, Void, Void> {



        QueueFromSerial qfs = QueueFromSerial.getInstance();
        Log_PLU_Event_Info log_plu_event_info = null;
        Handler mHandler;
        final static int TIMEOUT = 20;

        public void setHandler(Handler mHandler) {
            this.mHandler = mHandler;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        /*   public byte peekQueue() {
               byte byteFromQueue = 0;
               if(qfs.getQueue().size() > 0) {
                   try {

                       byteFromQueue = qfs.getQueue().poll();
                       Log.i("peekQueue: ", "peek: " + String.format("0x%02X ", byteFromQueue));
                   } catch (IllegalStateException e) {
                       e.printStackTrace();
                   }
               }
               return byteFromQueue;
           }   */
        @Override
        protected Void doInBackground(Void... params) {
            PCTA_Logger log = PCTA_Logger.getInstance();

            while(true){
                Log.i("Loop", "TRUE");
                int addedBytes = 0;
                int read_timeout_idx = 0;
                byte[] opCodeArray = new byte[4];
                byte[] lengthArray = new byte[4];
                byte[] messageArray = null;
                long opCode = 0;
                int length = 0;
                int exception_counter = 0;
                while ((addedBytes < 4)) { //&& (read_timeout_idx < 5000)) {
                    try {
                        opCodeArray[addedBytes] = qfs.peekQueue();
                        Log.i("opCodeArray PeekQueue", String.format("0x%02X ", opCodeArray[addedBytes]));
                        addedBytes++;
                        if(addedBytes == 1 && opCodeArray[addedBytes - 1] != 0x01) {
                            addedBytes = 0;
                        } else {

                        }
                    } catch (Exception e) {
                        Log.e("PeekQueue() timeout - 1", Integer.toString(++read_timeout_idx));
                        Log.e("Queue size - 1", Integer.toString(qfs.getQueue().size()));
                        Log.e("addedBytes - 1", Integer.toString(addedBytes));
                        //        e.printStackTrace();
                        if(qfs.getQueue()  != null ){ //&& exception_counter > 100) {
                            Log.e("qfs.getQueue() - 1", "Not null");
                            //    qfs = null;
                            //    qfs = QueueFromSerial.getInstance();
                            //    exception_counter ++;
                        } else {
                            Log.e("qfs.getQueue() - 1", "Null");
                        }


                        //    e.printStackTrace();
                        try {
                            Thread.sleep(TIMEOUT);
                        } catch (InterruptedException e1) {
                            e1.printStackTrace();
                        }
                    }
                }
                StringBuilder sbO = new StringBuilder();
                sbO.append("[ ");
                for (byte b : opCodeArray) {
                    sbO.append(String.format("0x%02X ", b));
                }
                sbO.append("]");
                //    Log.i("opCodeArray", "opCodeArray: " + sbO);
                //    log.i("OpCod: " + sbO);
                opCode = opCodeFromByteArray(opCodeArray);
                log.i("opCode: " + Long.toString(opCode));
                addedBytes = 0;
                read_timeout_idx = 0;
                //    exception_counter = 0;
                while ((addedBytes < 4) ) {//&& (read_timeout_idx < 500)) {
                    try {
                        lengthArray[addedBytes] = qfs.peekQueue();
                        //    Log.i("lengthArray PeekQueue", String.format("0x%02X ", lengthArray[addedBytes]));
                        addedBytes++;
                    } catch (Exception e) {
                        if(qfs.getQueue()  != null ){ // && exception_counter > 100) {
                            Log.e("PeekQueue()timeout - 2", Integer.toString(++read_timeout_idx));
                            Log.e("Queue size - 2", Integer.toString(qfs.getQueue().size()));
                            //    qfs = null;
                            //    qfs = QueueFromSerial.getInstance();
                            //    exception_counter++;
                        }
                        //   e.printStackTrace();
                        try {
                            Thread.sleep(TIMEOUT);
                        } catch (InterruptedException e1) {
                            e1.printStackTrace();
                        }
                    }
                }
                StringBuilder sbL = new StringBuilder();
                sbL.append("[ ");
                for (byte b : lengthArray) {
                    sbL.append(String.format("0x%02X ", b));
                }
                sbL.append("]");
                //    Log.i("lengthArray", "lengthArray: " + sbL);
                //    log.i("Length: " + sbL);
                try {
                    length = lengthFromByteArray(lengthArray);
                } catch (Exception e) {
                    //    Log.e("lengthFromByteArray", "Exception");
                }
                //    length = lengthFromByteArray(lengthArray);
                log.i("length: " + Long.toString(length));
                addedBytes = 0;
                read_timeout_idx = 0;
                //     exception_counter = 0;
                if(length != 0 && length <= 0x1C){
                    messageArray = new byte[length]; /* Exception */
                    while ((addedBytes < length) ) {//&& (read_timeout_idx < 500)) {
                        try {
                            messageArray[addedBytes] = qfs.peekQueue();
                            if(addedBytes < 4) {
                                log.i("eventCode: " + Integer.toHexString( messageArray[addedBytes]));
                            }
                            addedBytes++;
                        } catch (Exception e) {
                            if(qfs.getQueue()  != null ) {//&& exception_counter > 100) {
                                Log.e("PeekQueue()timeout - 3", Integer.toString(++read_timeout_idx));
                                Log.e("Queue size - 3", Integer.toString(qfs.getQueue().size()));
                                //    qfs = null;
                                //    qfs = QueueFromSerial.getInstance();
                                //    exception_counter++;
                                //    e.printStackTrace();
                            }
                            try {
                                Thread.sleep(50);
                            } catch (InterruptedException e1) {
                                e1.printStackTrace();
                            }

                        }
                    }
                    if (messageArray != null) {
                        StringBuilder sbM = new StringBuilder();
                        sbM.append("[ ");
                        for (byte b : messageArray) {
                            sbM.append(String.format("0x%02X ", b));
                        }
                        sbM.append("]");
                        //    Log.i("Message", "Message: " + sbM);
                        //    log.i("Message: " + sbM);
                    }
                    addedBytes = 0;
                    read_timeout_idx = 0;
                    //    exception_counter = 0;
                }
                try {
                    log_plu_event_info = new Log_PLU_Event_Info();
                    final String lpei = log_plu_event_info.fromArrayToObject(opCodeArray, lengthArray, messageArray);
                //    final FW_Version lpei = log_plu_event_info.fromArrayToObject(opCodeArray, lengthArray, messageArray);
                    Log.i("ARM version main: ", lpei.toString());
                    if(lpei != null) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                            //    display.append(lpei + "\n");
                                display.append(Integer.toString(++count) + "\n");
                                int scrollAmount = display.getLayout().getLineTop(display.getLineCount()) - display.getHeight();
                                // if there is no need to scroll, scrollAmount will be <=0
                                if (scrollAmount > 0) {
                                    display.scrollTo(0, scrollAmount);
                                }
                                if(lpei.contains("v_")) {
                                    switch (fw_image) {
                                        case ARM : {
                                            Log.i("ARM", lpei);
                                            textView_ARM.setText(" " + lpei);
                                            break;
                                        }
                                        case DSP:
                                            Log.i("DSP", lpei);
                                            textView_DSP.setText("  " + lpei);
                                            break;
                                        case CONF:
                                            Log.i("CONF", lpei);
                                            textView_Conf.setText(lpei);
                                            break;
                                        case BT:
                                            Log.i("BT", lpei);
                                            textView_BT.setText("     " + lpei);
                                            break;
                                        case BNDL_CONF:
                                            String bundle_conf = lpei.substring((lpei.length() - 1),lpei.length()) +
                                                    "/" + lpei.substring((lpei.length() - 3), (lpei.length() - 2));
                                            Log.i("BUNDLE_CONF", bundle_conf);
                                            textView_BNDL_CONF.setText("         " + bundle_conf);
                                            break;
                                        case BUNDLE:
                                            Log.i("BUNDLE", lpei);
                                            textView_Bundle.setText("        " + lpei);
                                            break;
                                        default: {
                                            Log.i("FW get version", "ERROR");
                                            break;
                                        }
                                    }

                                }

                            }
                        });
                    }
               /*     } else {
                        runOnUiThread(new Runnable(){
                            @Override
                            public void run() {
                                //    display.append(log_plu_event_info.toString() + "\n");
                            //    display.append(Integer.toString(++count) + "\n");
                                display.append("------------------------" + "\n");
                                int scrollAmount = display.getLayout().getLineTop(display.getLineCount()) - display.getHeight();
                                // if there is no need to scroll, scrollAmount will be <=0
                                if (scrollAmount > 0) {
                                    display.scrollTo(0, scrollAmount);
                                }

                            }});
                    }  */

                    exception_counter = 0;
                } catch (Exception e) {
                    e.printStackTrace();
                }

                //    addedBytes = 0;
                //    read_timeout_idx = 0;
          /*  if (mHandler != null) {
            //    String data = "MESSAGE" + "\n";
                String data = log_plu_event_info.toString() + "\n";
                mHandler.obtainMessage(UsbService.MESSAGE_FROM_SERIAL_PORT, data).sendToTarget();
            } else {
                Log.e("mHandler is null", "Handler is null");
            }
*/
                //      return log_plu_event_info;
            }

        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
        }
        long opCodeFromByteArray(byte[] bytes) {
            long l = 0;
            l |= bytes[3] & 0xFF;
            l <<= 8;
            l |= bytes[2] & 0xFF;
            l <<= 8;
            l |= bytes[1] & 0xFF;
            l <<= 8;
            l |= bytes[0] & 0xFF;
            return l;
        }
        int lengthFromByteArray(byte[] bytes) {
            return bytes[3] << 24 | (bytes[2] & 0xFF) << 16 | (bytes[1] & 0xFF) << 8 | (bytes[0] & 0xFF);
        }
    }


}