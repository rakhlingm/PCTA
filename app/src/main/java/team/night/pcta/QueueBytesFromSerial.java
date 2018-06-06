package team.night.pcta;

import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by GregoriRakhlin on 6/3/2018.
 */

public class QueueBytesFromSerial extends AsyncTask <Void, Void, Void> {



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
                    //    Log.i("opCodeArray PeekQueue", String.format("0x%02X ", opCodeArray[addedBytes]));
                    addedBytes++;
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
            addedBytes = 0;
            read_timeout_idx = 0;
       //     exception_counter = 0;
            if(length != 0){
                messageArray = new byte[length]; /* Exception */
                while ((addedBytes < length) ) {//&& (read_timeout_idx < 500)) {
                    try {
                        messageArray[addedBytes] = qfs.peekQueue();
                        addedBytes++;
                    } catch (Exception e) {
                        if(qfs.getQueue()  != null ) {//&& exception_counter > 100) {
                            Log.e("PeekQueue()timeout - 3", Integer.toString(++read_timeout_idx));
                            Log.e("Queue size - 3", Integer.toString(qfs.getQueue().size()));
                        //    qfs = null;
                        //    qfs = QueueFromSerial.getInstance();
                        //    exception_counter++;
                         //       e.printStackTrace();
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
                log_plu_event_info.fromArrayToObject(opCodeArray, lengthArray, messageArray);
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
