package team.night.pcta;

import android.util.Log;

import java.util.logging.Logger;

/**
 * Created by GregoriRakhlin on 6/7/2018.
 */

public class FW_Version {

    private long opCode;
    private long length;
    private long retCode;  //!< (4 bytes) RetCode = RETCODE_OK = 0 on success, error code on failure
    private long major;
    private long minor;
    private long fix;

    final static byte[] armVersionImage  =   new byte[] {0x00, 0x00, 0x00, 0x00};
    final static byte[] dspVersionImage  =   new byte[] {0x01, 0x00, 0x00, 0x00};
    final static byte[] btVersionImage   =   new byte[] {0x03, 0x00, 0x00, 0x00};
    final static byte[] confVersionImage =   new byte[] {0x02, 0x00, 0x00, 0x00};
    final static byte[] bundle_conf =   new byte[] {0x04, 0x00, 0x00, 0x00};
    final static byte[] bundle =   new byte[] {0x05, 0x00, 0x00, 0x00};

    public FW_Version() {
    }

    public FW_Version(long opCode, long length, long retCode, long major, long minor, long fix) {
        this.opCode = opCode;
        this.length = length;
        this.retCode = retCode;
        this.major = major;
        this.minor = minor;
        this.fix = fix;
    }

    public long getOpCode() {
        return opCode;
    }

    public long getLength() {
        return length;
    }

    public long getRetCode() {
        return retCode;
    }

    public long getMajor() {
        return major;
    }

    public long getMinor() {
        return minor;
    }

    public long getFix() {
        return fix;
    }

    public void setOpCode(long opCode) {
        this.opCode = opCode;
    }

    public void setLength(long length) {
        this.length = length;
    }

    public void setRetCode(long retCode) {
        this.retCode = retCode;
    }

    public void setMajor(long major) {
        this.major = major;
    }

    public void setMinor(long minor) {
        this.minor = minor;
    }

    public void setFix(long fix) {
        this.fix = fix;
    }

    public static void getVersion(UsbService usbService, byte[] fwVersionImage) {
        PCTA_Logger pcta_logger = PCTA_Logger.getInstance();
        if (usbService != null) {
            byte[] fwVersion = new byte[] {0x00, 0x06, 0x00, 0x00, 0x04, 0x00, 0x00, 0x00};
            StringBuilder sb = new StringBuilder();
            sb.append("[ ");
            for (byte b : fwVersion) {
                sb.append(String.format("0x%02X ", b));
            }
            sb.append("]");
            Log.i("FW version 1", sb.toString());
            pcta_logger.i("FW version 1: " + sb.toString());
            usbService.write(fwVersion);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        //    fwVersionImage = new byte[] {0x00, 0x00, 0x00, 0x00};
            StringBuilder sb1 = new StringBuilder();
            sb1.append("[ ");
            for (byte b : fwVersionImage) {
                sb1.append(String.format("0x%02X ", b));
            }
            sb1.append("]");
            Log.i("FW version 2", sb1.toString());
            pcta_logger.i("FW version 2: " + sb.toString());
            usbService.write(fwVersionImage);
        }
    }


    @Override
    public String toString() {
        return "v_" + major + "." + minor + "." + fix;
    }
/*    @Override
    public String toString() {
        return "FW_Version{" +
                "opCode=" + opCode +
                ", length=" + length +
                ", retCode=" + retCode +
                ", major=" + major +
                ", minor=" + minor +
                ", fix=" + fix +
                '}';
    }  */
}
