package team.night.pcta;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.security.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by GregoriRakhlin on 5/12/2018.
 */

public class Log_PLU_Event_Info {
    private long OpCode;       // 4  bytes
    private int  Length;       // 4  bytes
    private long EventCode;    // 4  bytes
    private int SID;           // 4  bits
    private int SVR;           // 4  bits
    private int FileID;        // 8  bits
    private int LineID;        // 12 bits
    private int ModuleID;      // 4  bits
    private long TimeStamp;    // 4  bytes
    private long UserVar0;     // 4  bytes
    private long UserVar1;     // 4  bytes
    private long UserVar2;     // 4  bytes
    private long UserVar3;     // 4  bytes

    PCTA_Logger pcta_logger = PCTA_Logger.getInstance();
    Superposition superposition = Superposition.getInstance();
    Superposition_Logger superposition_logger = Superposition_Logger.getInstance();
    EventString eventString = new EventString();

//    public Handler mHandler;

    public Log_PLU_Event_Info() {
        super();
    }

/*    public Log_PLU_Event_Info(Handler mHandler) {
        this.mHandler = mHandler;
    }  */

	/* Length = 0x0C */

    public Log_PLU_Event_Info(long opCode, int length, long eventCode, int sID, int sVR, int fileID, int lineID,
                              int moduleID, long timeStamp) {
        super();
        OpCode = opCode;
        Length = length;
        EventCode = eventCode;
        SID = sID;
        SVR = sVR;
        FileID = fileID;
        LineID = lineID;
        ModuleID = moduleID;
        TimeStamp = timeStamp;
    }

	/* Length = 0x10 */

    public Log_PLU_Event_Info(long opCode, int length, long eventCode, int sID, int sVR, int fileID, int lineID,
                              int moduleID, long timeStamp, long userVar0) {
        super();
        OpCode = opCode;
        Length = length;
        EventCode = eventCode;
        SID = sID;
        SVR = sVR;
        FileID = fileID;
        LineID = lineID;
        ModuleID = moduleID;
        TimeStamp = timeStamp;
        UserVar0 = userVar0;
    }

	/* Length = 0x14 */

    public Log_PLU_Event_Info(long opCode, int length, long eventCode, int sID, int sVR, int fileID, int lineID,
                              int moduleID, long timeStamp, long userVar0, long userVar1) {
        super();
        OpCode = opCode;
        Length = length;
        EventCode = eventCode;
        SID = sID;
        SVR = sVR;
        FileID = fileID;
        LineID = lineID;
        ModuleID = moduleID;
        TimeStamp = timeStamp;
        UserVar0 = userVar0;
        UserVar1 = userVar1;
    }


	/* Length = 0x18 */

    public Log_PLU_Event_Info(long opCode, int length, long eventCode, int sID, int sVR, int fileID, int lineID,
                              int moduleID, long timeStamp, long userVar0, long userVar1, long userVar2) {
        super();
        OpCode = opCode;
        Length = length;
        EventCode = eventCode;
        SID = sID;
        SVR = sVR;
        FileID = fileID;
        LineID = lineID;
        ModuleID = moduleID;
        TimeStamp = timeStamp;
        UserVar0 = userVar0;
        UserVar1 = userVar1;
        UserVar2 = userVar2;
    }

	/* Length = 0x1C */

    public Log_PLU_Event_Info(long opCode, int length, long eventCode, int sID, int sVR, int fileID, int lineID,
                              int moduleID, long timeStamp, long userVar0, long userVar1, long userVar2, long userVar3) {
        super();
        OpCode = opCode;
        Length = length;
        EventCode = eventCode;
        SID = sID;
        SVR = sVR;
        FileID = fileID;
        LineID = lineID;
        ModuleID = moduleID;
        TimeStamp = timeStamp;
        UserVar0 = userVar0;
        UserVar1 = userVar1;
        UserVar2 = userVar2;
        UserVar3 = userVar3;
    }

    public long getOpCode() {
        return OpCode;
    }

    public int getLength() {
        return Length;
    }

    public int getLineID() {
        return LineID;
    }

    public int getFileID() {
        return FileID;
    }

    public int getModuleID() {
        return ModuleID;
    }

    public long getEventCode() {
        return EventCode;
    }

    public long getTimeStamp() {
        return TimeStamp;
    }

    public long getUserVar0() {
        return UserVar0;
    }

    public long getUserVar1() {
        return UserVar1;
    }

    public long getUserVar2() {
        return UserVar2;
    }

    public long getUserVar3() {
        return UserVar3;
    }

/*    public void setmHandler(Handler mHandler) {
        this.mHandler = mHandler;
    }  */

    @Override
    public String toString() {
        return "Log_PLU_Event_Info [OpCode=" + OpCode + ", Length=" + Length + ", EventCode=" + EventCode + ", SID="
                + SID + ", SVR=" + SVR + ", FileID=" + FileID + ", LineID=" + LineID + ", ModuleID=" + ModuleID
                + ", TimeStamp=" + TimeStamp + ", UserVar0=" + UserVar0 + ", UserVar1=" + UserVar1 + ", UserVar2="
                + UserVar2 + ", UserVar3=" + UserVar3 + "]";
    }

    public String fromArrayToObject(byte[] OpCode, byte[] Length, byte[] array) {
    //    Log_PLU_Event_Info eventLog = null;
        Log_PLU_Event_Info eventLog = null;
        String strEventLog = "";
        int length = 0;
        try {
            length = lengthFromByteArray(Length);
            pcta_logger.i("Length: " + Integer.toString(length));
        } catch (Exception e) {
            pcta_logger.e(e.toString());
            e.printStackTrace();
        }

        switch (length) {
            case 0x0C:
                eventLog = new Log_PLU_Event_Info(opCodeFromByteArray(OpCode), lengthFromByteArray(Length), eveneCode_FromByteArray(array)
                        , SID_FromByteArray(array), SVR_FromByteArray(array), fileID_FromByteArray(array), lineID_FromByteArray(array), moduleID_FromByteArray(array)
                        , timeStamp_FromByteArray(array));
                break;
            case 0x10:
                eventLog = new Log_PLU_Event_Info(opCodeFromByteArray(OpCode), lengthFromByteArray(Length), eveneCode_FromByteArray(array)
                        , SID_FromByteArray(array), SVR_FromByteArray(array), fileID_FromByteArray(array), lineID_FromByteArray(array), moduleID_FromByteArray(array)
                        , timeStamp_FromByteArray(array), userVar0_FromByteArray(array));
                break;
            case 0x14:
                eventLog = new Log_PLU_Event_Info(opCodeFromByteArray(OpCode), lengthFromByteArray(Length), eveneCode_FromByteArray(array)
                        , SID_FromByteArray(array), SVR_FromByteArray(array), fileID_FromByteArray(array), lineID_FromByteArray(array), moduleID_FromByteArray(array)
                        , timeStamp_FromByteArray(array), userVar0_FromByteArray(array), userVar1_FromByteArray(array));
                break;
            case 0x18:
                eventLog = new Log_PLU_Event_Info(opCodeFromByteArray(OpCode), lengthFromByteArray(Length), eveneCode_FromByteArray(array)
                        , SID_FromByteArray(array), SVR_FromByteArray(array), fileID_FromByteArray(array), lineID_FromByteArray(array), moduleID_FromByteArray(array)
                        , timeStamp_FromByteArray(array), userVar0_FromByteArray(array), userVar1_FromByteArray(array), userVar2_FromByteArray(array));
                break;
            case 0x1C:
                eventLog = new Log_PLU_Event_Info(opCodeFromByteArray(OpCode), lengthFromByteArray(Length), eveneCode_FromByteArray(array)
                        , SID_FromByteArray(array), SVR_FromByteArray(array), fileID_FromByteArray(array), lineID_FromByteArray(array), moduleID_FromByteArray(array)
                        , timeStamp_FromByteArray(array), userVar0_FromByteArray(array), userVar1_FromByteArray(array), userVar2_FromByteArray(array)
                        , userVar3_FromByteArray(array));
                break;
            default:
                break;
        }
    //    System.out.println(eventLog.toString());
//          Log.i("PLU_Event_Info", eventLog.toString());
            switch ((int) eventLog.getEventCode()) {
                case 448: {
                    switch (superposition.getCounterLog448()) {
                        case  0: {
                            setFirstSuperpositionLog(eventLog);
                            superposition.setCounterLog448(1);
                            break;
                        }
                        case  1: {
                            setSecondSuperpositionLog(eventLog);
                            superposition.setCounterLog448(0);
                            Log.i("Antenna 1", superposition.toString());
                            superposition_logger.log(1, superposition);
                            try{
                      /*          if (mHandler != null) {
                                    String data = "MESSAGE";
                                    mHandler.obtainMessage(UsbService.MESSAGE_FROM_SERIAL_PORT, data).sendToTarget();
                                }   */
                                Message msg = new Message();
                                msg.obj = "Ali send message";
                         //       mHandler.sendMessage(msg);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            break;
                        }
                        default: {

                            break;
                        }
                    }
                    break;
                }
                case 449: {
                    switch (superposition.getCounterLog449()) {
                        case  0: {
                            setFirstSuperpositionLog(eventLog);
                            superposition.setCounterLog449(1);
                            break;
                        }
                        case  1: {
                            setSecondSuperpositionLog(eventLog);
                            superposition.setCounterLog449(0);
                            Log.i("Antenna 2", superposition.toString());
                            superposition_logger.log(2, superposition);
                            break;
                        }
                        default: {
                            break;
                        }
                    }
                    break;
                }
                case 450: {
                    switch (superposition.getCounterLog450()) {
                        case  0: {
                            setFirstSuperpositionLog(eventLog);
                            superposition.setCounterLog450(1);
                            break;
                        }
                        case  1: {
                            setSecondSuperpositionLog(eventLog);
                            superposition.setCounterLog450(0);
                            Log.i("Antenna 3", superposition.toString());
                            superposition_logger.log(3, superposition);
                            break;
                        }
                        default: {
                            break;
                        }
                    }
                    break;
                }
                case 451: {
                    switch (superposition.getCounterLog451()) {
                        case  0: {
                            setFirstSuperpositionLog(eventLog);
                            superposition.setCounterLog451(1);
                            break;
                        }
                        case  1: {
                            setSecondSuperpositionLog(eventLog);
                            superposition.setCounterLog451(0);
                            Log.i("Antenna 4", superposition.toString());
                            superposition_logger.log(4, superposition);
                            break;
                        }
                        default: {
                            break;
                        }
                    }
                    break;
                }
                default: {
                    try {
                    //    String strEventLog = eventString.getEventString(eventLog).get((int)eventLog.getEventCode());
                        strEventLog = eventString.getEventString(eventLog).get((int)eventLog.getEventCode());
                        Log.i("strEventLog", strEventLog);
                        pcta_logger.i("strEventLog: " + strEventLog);
                    } catch (Exception e) {
                        Log.e("strEventLog", "Unknown Event code: " + Integer.toString((int)eventLog.getEventCode()));
                        pcta_logger.e("Unknown Event code: " + Integer.toString((int)eventLog.getEventCode()));
                    }

                    break;
                }
            }
        return strEventLog;
    }
    private void setSecondSuperpositionLog(Log_PLU_Event_Info eventLog) {
        superposition.setBin5(eventLog.getUserVar0());
        superposition.setSize(eventLog.getUserVar1());
        superposition.setSub_band(eventLog.getUserVar2());
        superposition.setChannel(eventLog.getUserVar3());
    }

    private void setFirstSuperpositionLog(Log_PLU_Event_Info eventLog) {
        superposition.setSystemTimastamp(getCurrentTimeStamp());
        superposition.setPLUTimastamp(eventLog.getTimeStamp());
        superposition.setBin1(eventLog.getUserVar0());
        superposition.setBin2(eventLog.getUserVar1());
        superposition.setBin3(eventLog.getUserVar2());
        superposition.setBin4(eventLog.getUserVar3());
    }

    public static final long unsignedIntToLong(byte[] b) {
        long l = 0;
        l |= b[0] & 0xFF;
        l <<= 8;
        l |= b[1] & 0xFF;
        l <<= 8;
        l |= b[2] & 0xFF;
        l <<= 8;
        l |= b[3] & 0xFF;
        return l;
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
    long eveneCode_FromByteArray(byte[] bytes) {
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
    int SID_FromByteArray(byte[] bytes) {
        return (bytes[4] & 0xF);
    }
    int SVR_FromByteArray(byte[] bytes) {
        return (bytes[4] >> 4) & 0xF;
    }
    int fileID_FromByteArray(byte[] bytes) {
        return (bytes[5] & 0xFF);
    }
    int lineID_FromByteArray(byte[] bytes) {
        return (bytes[7] & 0xF) << 8 | (bytes[6] & 0xFF);
    }
    int moduleID_FromByteArray(byte[] bytes) {
        return (bytes[7] >> 4) & 0xF;
    }
    long timeStamp_FromByteArray(byte[] bytes) {
        long l = 0;
        l |= bytes[11] & 0xFF;
        l <<= 8;
        l |= bytes[10] & 0xFF;
        l <<= 8;
        l |= bytes[9] & 0xFF;
        l <<= 8;
        l |= bytes[8] & 0xFF;
        return l;
    }
    long userVar0_FromByteArray(byte[] bytes) {
        long l = 0;
        l |= bytes[15] & 0xFF;
        l <<= 8;
        l |= bytes[14] & 0xFF;
        l <<= 8;
        l |= bytes[13] & 0xFF;
        l <<= 8;
        l |= bytes[12] & 0xFF;
        return l;
    }
    long userVar1_FromByteArray(byte[] bytes) {
        long l = 0;
        l |= bytes[19] & 0xFF;
        l <<= 8;
        l |= bytes[18] & 0xFF;
        l <<= 8;
        l |= bytes[17] & 0xFF;
        l <<= 8;
        l |= bytes[16] & 0xFF;
        return l;
    }
    long userVar2_FromByteArray(byte[] bytes) {
        long l = 0;
        l |= bytes[23] & 0xFF;
        l <<= 8;
        l |= bytes[22] & 0xFF;
        l <<= 8;
        l |= bytes[21] & 0xFF;
        l <<= 8;
        l |= bytes[20] & 0xFF;
        return l;
    }
    long userVar3_FromByteArray(byte[] bytes) {
        long l = 0;
        l |= bytes[27] & 0xFF;
        l <<= 8;
        l |= bytes[26] & 0xFF;
        l <<= 8;
        l |= bytes[25] & 0xFF;
        l <<= 8;
        l |= bytes[24] & 0xFF;
        return l;
    }
    int lengthFromByteArray(byte[] bytes) {
        return bytes[3] << 24 | (bytes[2] & 0xFF) << 16 | (bytes[1] & 0xFF) << 8 | (bytes[0] & 0xFF);
    }
    public static String getCurrentTimeStamp(){
        try {

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String currentDateTime = dateFormat.format(new Date()); // Find todays date

            return currentDateTime;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}

