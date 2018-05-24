package team.night.pcta;

/**
 * Created by GregoriRakhlin on 5/14/2018.
 */

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Superposition_Logger {

    public static Superposition_Logger instance;

    public Superposition_Logger () {}

    public static synchronized Superposition_Logger getInstance() {
        if (instance == null) {
            instance = new Superposition_Logger();
        }
        return instance;
    }

    File logFile;

    public String getMainFolder() throws IOException {
        String folder_main = Constants.MAIN_FOLDER;
        final File f = Environment.getExternalStoragePublicDirectory ("/" + Constants.MAIN_FOLDER + "/");
        if (!f.exists()) {
            f.mkdirs();
        }
        Log.e("Logfile", f.getAbsolutePath());
        logFile = createLogFile();
        // Save your stream, don't forget to flush() it before closing it.
        try  {
            FileOutputStream fOut = new FileOutputStream(logFile);
            OutputStreamWriter myOutWriter = new OutputStreamWriter(fOut);
            myOutWriter.append(Constants.PCTA_VERSION + "\r\n");
            myOutWriter.append("\r\n");
            myOutWriter.close();
            fOut.flush();
            fOut.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
        return folder_main;
    }
    public String getCurrentTime() {
        SimpleDateFormat format = new SimpleDateFormat("M-d_HH:mm:ss");
        String currentTime = format.format(Calendar.getInstance().getTime());
        return currentTime;
    }
    public File createLogFile() {
        try {
            logFile = new File(Environment.getExternalStoragePublicDirectory ("/" + Constants.MAIN_FOLDER + "/") + "/" + Constants.SUPERPOSITION_LOG_FILE + "_" + getCurrentTime() + ".txt");//"_" + format.format(Calendar.getInstance().getTime()) + ".txt");
            Log.e("Logfile", logFile.getAbsolutePath());
            if (!logFile.exists()) {
                logFile.createNewFile();
                Log.e("Log", "File created");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return logFile;
    }
    public void log(int antenna, Superposition superposition) {
        try {
            String log = "Super possition: T1:" + superposition.getSystemTimastamp() + " T2: " + superposition.getPLUTimastamp()
            + " Super position Ant " + antenna + ": Bin 1: " + superposition.getBin1() + " Bin 2: " + superposition.getBin2()
            +  " Bin 3: " + superposition.getBin3() + " Bin 4: " + superposition.getBin4() + " Bin 5: " + superposition.getBin5()
            + " Size: " + superposition.getSize() + " Sub-band ID: " + superposition.getSub_band()
            + " Channel: " + superposition.getChannel() + "\n";
            FileOutputStream fileOutputStream = new FileOutputStream(logFile,true);
            byte[] buffer = log.getBytes();
            fileOutputStream.write(buffer, 0, buffer.length);
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}