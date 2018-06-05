package team.night.pcta;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.LinkedList;
import java.util.Queue;


public class QueueFromSerial {//} extends Service {

//    private IBinder binder = (IBinder) new QueueFromSerial();

    public static QueueFromSerial instance;

    public QueueFromSerial () {}

  /*  @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }  */

    public static synchronized QueueFromSerial getInstance() {
        if (instance == null) {
            instance = new QueueFromSerial();
        }
        return instance;
    }
    public Queue<Byte> queue = new LinkedList<Byte>();

/*    public Handler mHandler;   */

    public Queue<Byte> getQueue() {
        return queue;
    }

/*    public void setmHandler(Handler mHandler) {
        this.mHandler = mHandler;
    }  */

    public void addToQueue(byte byteFromSerial) {
        try {
            queue.add(byteFromSerial);
            Log.i("QueueFromSerial: ", "added: " + String.format("0x%02X ", byteFromSerial));
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }
    public void removeFromQueue() {
        try {
            queue.remove();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }
    public byte peekQueue() {
        byte byteFromQueue =  0;
                try {
                    byteFromQueue = queue.poll();
                    Log.i("peekQueue: ", "peek: " + String.format("0x%02X ", byteFromQueue));
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                }
        return byteFromQueue;
    }
  /*  public byte peekQueue() {
        byte byteFromQueue =  0;
        synchronized(queue) {
            if(!queue.isEmpty()) {
                try {
                    byteFromQueue = queue.poll();
                    Log.i("peekQueue: ", "peek: " + String.format("0x%02X ", byteFromQueue));
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                }
            }
        }
        return byteFromQueue;
    }  */
}


