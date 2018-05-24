package team.night.pcta;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by GregoriRakhlin on 5/15/2018.
 */
//     formatString - format of the corresponding Variable Item.
//                    For example: {1:X8} - 1st Variable Item printed in 8 hexadecimal uppercase digits
//                                 {2,5}  - 2nd Variable Item printed in 5 symbols
//
//  No need to add "\r\n" at the end of string for "Carriage Return and New Line"
//  because the string will be printed with WriteLine() method.
//  If you want to emphasize the string by empty strings
//  you can put additional "\r\n" in the beginning and at the end of the string
//
//  The following Variable Items are available in the constant order:
//      {0} - SID - Sequence Number
//      {1} - SVR - Severity Level
//      {2} - FileID
//      {3} - LineID
//      {4} - ModuleID
//      {5} - TimeStamp
//      {6} - UserVar0
//      {7} - UserVar1
//      {8} - UserVar2
//      {9} - UserVar3
//       , log_plu_event_info.getUserVar3()


public class EventString {

    Map<Integer, String> eventString = new HashMap<Integer, String>();

    public Map<Integer, String> getEventString(Log_PLU_Event_Info log_plu_event_info) {
        //Accelerometer
        eventString.put(38, String.format("T2: %d M: %d  F: %d  L:%d X: %x  Y: %x  Z: %x S: %x Accelerometer sample", log_plu_event_info.getTimeStamp(), log_plu_event_info.getModuleID(), log_plu_event_info.getFileID(), log_plu_event_info.getLineID(), log_plu_event_info.getUserVar0(), log_plu_event_info.getUserVar1(), log_plu_event_info.getUserVar2(), log_plu_event_info.getUserVar3()));
        //Accelerometer overflow
        eventString.put(39, String.format("T2: {5} M: %d  F: %d  L:%d X: %d  Y: %x  Z: %x S: %x Accelerometer overflow", log_plu_event_info.getTimeStamp(), log_plu_event_info.getModuleID(), log_plu_event_info.getFileID(), log_plu_event_info.getLineID(), log_plu_event_info.getUserVar0(), log_plu_event_info.getUserVar1(), log_plu_event_info.getUserVar2(), log_plu_event_info.getUserVar3()));
        // Movement recognition%d
        eventString.put(100,  String.format("T: %d   M: %d  F: %d  L:%d  X movement threshold triggered %x, %x, %x, %x", log_plu_event_info.getTimeStamp(), log_plu_event_info.getModuleID(), log_plu_event_info.getFileID(), log_plu_event_info.getLineID(), log_plu_event_info.getUserVar0(), log_plu_event_info.getUserVar1(), log_plu_event_info.getUserVar2(), log_plu_event_info.getUserVar3()));
        eventString.put(101,  String.format("T: %d   M: %d  F: %d  L:%d  Y movement threshold triggered %x, %x, %x, %x", log_plu_event_info.getTimeStamp(), log_plu_event_info.getModuleID(), log_plu_event_info.getFileID(), log_plu_event_info.getLineID(), log_plu_event_info.getUserVar0(), log_plu_event_info.getUserVar1(), log_plu_event_info.getUserVar2(), log_plu_event_info.getUserVar3()));
        // IQ Sampling
        eventString.put(200,  String.format("T: %d   M: %d  F: %d  L:%d  Sampling started", log_plu_event_info.getTimeStamp(), log_plu_event_info.getModuleID(), log_plu_event_info.getFileID(), log_plu_event_info.getLineID()));
        eventString.put(201,  String.format("T: %d   M: %d  F: %d  L:%d  Sampling stopped", log_plu_event_info.getTimeStamp(), log_plu_event_info.getModuleID(), log_plu_event_info.getFileID(), log_plu_event_info.getLineID()));
        // Monitor debug
        eventString.put(300,  String.format("T: %d   M: %d  F: %d  L:%d  Host request %x completed with status %x", log_plu_event_info.getTimeStamp(), log_plu_event_info.getModuleID(), log_plu_event_info.getFileID(), log_plu_event_info.getLineID(), log_plu_event_info.getUserVar0(), log_plu_event_info.getUserVar1()));
        // Watchdog
        eventString.put(400,  String.format("T: %d   M: %d  F: %d  L:%d  Watchdog Activated", log_plu_event_info.getTimeStamp(), log_plu_event_info.getModuleID(), log_plu_event_info.getFileID(), log_plu_event_info.getLineID()));
        eventString.put(401,  String.format("T: %d   M: %d  F: %d  L:%d  Watchdog Enabled", log_plu_event_info.getTimeStamp(), log_plu_event_info.getModuleID(), log_plu_event_info.getFileID(), log_plu_event_info.getLineID()));
        eventString.put(402,  String.format("T: %d   M: %d  F: %d  L:%d  Watchdog Disabled", log_plu_event_info.getTimeStamp(), log_plu_event_info.getModuleID(), log_plu_event_info.getFileID(), log_plu_event_info.getLineID()));
        eventString.put(410,  String.format("T: %d   M: %d  F: %d  L:%d  Watchdog ARM is alive", log_plu_event_info.getTimeStamp(), log_plu_event_info.getModuleID(), log_plu_event_info.getFileID(), log_plu_event_info.getLineID()));
        eventString.put(411,  String.format("T: %d   M: %d  F: %d  L:%d  Watchdog DSP is alive", log_plu_event_info.getTimeStamp(), log_plu_event_info.getModuleID(), log_plu_event_info.getFileID(), log_plu_event_info.getLineID()));
        eventString.put(412,  String.format("T: %d   M: %d  F: %d  L:%d  Watchdog BT is alive", log_plu_event_info.getTimeStamp(), log_plu_event_info.getModuleID(), log_plu_event_info.getFileID(), log_plu_event_info.getLineID()));
        eventString.put(413,  String.format("T: %d   M: %d  F: %d  L:%d  Watchdog WIFI is alive", log_plu_event_info.getTimeStamp(), log_plu_event_info.getModuleID(), log_plu_event_info.getFileID(), log_plu_event_info.getLineID()));
        eventString.put(420,  String.format("T: %d   M: %d  F: %d  L:%d  Watchdog DSP keep alive failed", log_plu_event_info.getTimeStamp(), log_plu_event_info.getModuleID(), log_plu_event_info.getFileID(), log_plu_event_info.getLineID()));
        eventString.put(421,  String.format("T: %d   M: %d  F: %d  L:%d  Watchdog DSP keep alive bad", log_plu_event_info.getTimeStamp(), log_plu_event_info.getModuleID(), log_plu_event_info.getFileID(), log_plu_event_info.getLineID()));
        eventString.put(422,  String.format("T: %d   M: %d  F: %d  L:%d  Watchdog DSP keep alive timeout", log_plu_event_info.getTimeStamp(), log_plu_event_info.getModuleID(), log_plu_event_info.getFileID(), log_plu_event_info.getLineID()));
        eventString.put(423,  String.format("T: %d   M: %d  F: %d  L:%d  Watchdog BT keep alive failed", log_plu_event_info.getTimeStamp(), log_plu_event_info.getModuleID(), log_plu_event_info.getFileID(), log_plu_event_info.getLineID()));
        eventString.put(424,  String.format("T: %d   M: %d  F: %d  L:%d  Watchdog WIFI keep alive failed", log_plu_event_info.getTimeStamp(), log_plu_event_info.getModuleID(), log_plu_event_info.getFileID(), log_plu_event_info.getLineID()));
        eventString.put(447,  String.format("Phone info: %x T: {5:X8} Phone location: %x  Conf level: %x", log_plu_event_info.getTimeStamp(), log_plu_event_info.getModuleID(), log_plu_event_info.getUserVar0(), log_plu_event_info.getUserVar1(), log_plu_event_info.getUserVar2()));
  //      eventString.put(448,  String.format("T: {5:X8} Super position Ant 1: Bin 1: %x Bin 2 %x Bin 3 %x Bin 4 %x", log_plu_event_info.getTimeStamp(), log_plu_event_info.getModuleID()));
  //      eventString.put(449,  String.format("T: {5:X8} Super position Ant 2: Bin 1: %x Bin 2 %x Bin 3 %x Bin 4 %x", log_plu_event_info.getTimeStamp(), log_plu_event_info.getModuleID()));
  //      eventString.put(450,  String.format("T: {5:X8} Super position Ant 3: Bin 1: %x Bin 2 %x Bin 3 %x Bin 4 %x", log_plu_event_info.getTimeStamp(), log_plu_event_info.getModuleID()));
  //      eventString.put(451,  String.format("T: {5:X8} Super position Ant 4: Bin 1: %x Bin 2 %x Bin 3 %x Bin 4 %x", log_plu_event_info.getTimeStamp(), log_plu_event_info.getModuleID()));
        eventString.put(452,  String.format("T: %d   M: %d  >>> DSP Algo - NOISE1: STD = %d, MEAN = %x", log_plu_event_info.getTimeStamp(), log_plu_event_info.getModuleID(), log_plu_event_info.getUserVar0(), log_plu_event_info.getUserVar1()));
        eventString.put(453,  String.format("T: %d   M: %d  >>> DSP Algo - NOISE2: STD = %d, MEAN = %x", log_plu_event_info.getTimeStamp(), log_plu_event_info.getModuleID(), log_plu_event_info.getUserVar0(), log_plu_event_info.getUserVar1()));
        eventString.put(454,  String.format("T: %d   M: %d  >>> DSP Algo - NOISE3: STD = %d, MEAN = %x", log_plu_event_info.getTimeStamp(), log_plu_event_info.getModuleID(), log_plu_event_info.getUserVar0(), log_plu_event_info.getUserVar1()));
        eventString.put(455,  String.format("T: %d   M: %d  >>> DSP Algo - NOISE4: STD = %d, MEAN = %x", log_plu_event_info.getTimeStamp(), log_plu_event_info.getModuleID(), log_plu_event_info.getUserVar0(), log_plu_event_info.getUserVar1()));
        eventString.put(500,  String.format("T: %d   M: %d  F: %d  L:%d  DSP Scheduler log data available at %x, %x, %x, %x", log_plu_event_info.getTimeStamp(), log_plu_event_info.getModuleID(), log_plu_event_info.getFileID(), log_plu_event_info.getLineID(), log_plu_event_info.getUserVar0(), log_plu_event_info.getUserVar1(), log_plu_event_info.getUserVar2(), log_plu_event_info.getUserVar3()));
        eventString.put(501,  String.format("T: %d   M: %d  F: %d  L:%d  DSP Scan Start %x", log_plu_event_info.getTimeStamp(), log_plu_event_info.getModuleID(), log_plu_event_info.getFileID(), log_plu_event_info.getLineID(), log_plu_event_info.getUserVar0()));
        eventString.put(502,  String.format("T: %d   M: %d  F: %d  L:%d  DSP Scan Stop %x", log_plu_event_info.getTimeStamp(), log_plu_event_info.getModuleID(), log_plu_event_info.getFileID(), log_plu_event_info.getLineID(), log_plu_event_info.getUserVar0()));
        eventString.put(503,  String.format("T: %d   M: %d  BLE Connect from MPA", log_plu_event_info.getTimeStamp(), log_plu_event_info.getModuleID()));
        eventString.put(504,  String.format("T: %d   M: %d  BLE Get MPA ID request from MPA", log_plu_event_info.getTimeStamp(), log_plu_event_info.getModuleID()));
        eventString.put(505,  String.format("T: %d   M: %d  BLE Sent MPA ID %x to MPA", log_plu_event_info.getTimeStamp(), log_plu_event_info.getModuleID(), log_plu_event_info.getUserVar0()));
        eventString.put(506,  String.format("T: %d   M: %d  BLE Sent ACK to MPA", log_plu_event_info.getTimeStamp(), log_plu_event_info.getModuleID()));
        eventString.put(507,  String.format("T: %d   M: %d  BLE Sent NACK to MPA", log_plu_event_info.getTimeStamp(), log_plu_event_info.getModuleID()));
       // eventString.put(508,  String.format("T: %d   M: %d  BLE CMD T:%x L:%x from MPA", log_plu_event_info.getTimeStamp(), log_plu_event_info.getModuleID(), log_plu_event_info.getUserVar0(), log_plu_event_info.getUserVar1()));
     //  eventString.put(508, String.format("T: %d  M: {4}>  BLE CMD T:{6:X4} L:{7:X4} from MPA", log_plu_event_info.getTimeStamp()));
        eventString.put(508,  String.format("T: %d   M: %d  BLE CMD T:%x L:%x from MPA", log_plu_event_info.getTimeStamp(), log_plu_event_info.getModuleID(), log_plu_event_info.getUserVar0(), log_plu_event_info.getUserVar1()));
        eventString.put(509,  String.format("T: %d   M: %d  BLE Reply T:%x L:%x to MPA", log_plu_event_info.getTimeStamp(), log_plu_event_info.getModuleID(), log_plu_event_info.getUserVar0(), log_plu_event_info.getUserVar1()));
        eventString.put(510,  String.format("T: %d   M: %d  BLE F/W Update Start (PKT:%d) from MPA", log_plu_event_info.getTimeStamp(), log_plu_event_info.getModuleID(), log_plu_event_info.getUserVar0()));
        eventString.put(511,  String.format("T: %d   M: %d  BLE F/W Update Cont. (PKT: %d) from MPA", log_plu_event_info.getTimeStamp(), log_plu_event_info.getModuleID(), log_plu_event_info.getUserVar0()));
        eventString.put(512,  String.format("T: %d   M: %d  BLE F/W Update Done (PKT:%d) from MPA", log_plu_event_info.getTimeStamp(), log_plu_event_info.getModuleID(), log_plu_event_info.getUserVar0()));
        eventString.put(513,  String.format("T: %d   M: %d  BLE Calibration Start from MPA", log_plu_event_info.getTimeStamp(), log_plu_event_info.getModuleID()));
        eventString.put(514,  String.format("T: %d   M: %d  BLE Calibration Point %d position %x", log_plu_event_info.getTimeStamp(), log_plu_event_info.getModuleID(), log_plu_event_info.getUserVar0(), log_plu_event_info.getUserVar1()));
        eventString.put(515,  String.format("T: %d   M: %d  BLE Validation Point %d", log_plu_event_info.getTimeStamp(), log_plu_event_info.getModuleID(), log_plu_event_info.getUserVar0()));
        eventString.put(516,  String.format("T: %d   M: %d  BLE Calibration End from MPA", log_plu_event_info.getTimeStamp(), log_plu_event_info.getModuleID()));
        eventString.put(517,  String.format("T: %d   M: %d  BLE Calibration Data to MPA", log_plu_event_info.getTimeStamp(), log_plu_event_info.getModuleID()));
        eventString.put(518,  String.format("T: %d   M: %d  BLE Disconnect", log_plu_event_info.getTimeStamp(), log_plu_event_info.getModuleID()));
        eventString.put(519,  String.format("T: %d   M: %d  BLE Reset", log_plu_event_info.getTimeStamp(), log_plu_event_info.getModuleID()));
        eventString.put(520,  String.format("T: %d   M: %d  BLE Get PLU Log", log_plu_event_info.getTimeStamp(), log_plu_event_info.getModuleID()));
        eventString.put(521,  String.format("T: %d   M: %d  F: %d  L: %d  ACC Raw Sample X:%x, Y:%x, Z:%x N:%x", log_plu_event_info.getTimeStamp(), log_plu_event_info.getModuleID(), log_plu_event_info.getFileID(), log_plu_event_info.getLineID(), log_plu_event_info.getUserVar0(), log_plu_event_info.getUserVar1(), log_plu_event_info.getUserVar2(), log_plu_event_info.getUserVar3()));
        eventString.put(522,  String.format("T: %d   M: %d  F: %d  L:%d  Accelerometer FIFO Overflow", log_plu_event_info.getTimeStamp(), log_plu_event_info.getModuleID(), log_plu_event_info.getFileID(), log_plu_event_info.getLineID()));
        eventString.put(523,  String.format("T: %d   M: %d  F: %d  L:%d  SYS Location Notification", log_plu_event_info.getTimeStamp(), log_plu_event_info.getModuleID(), log_plu_event_info.getFileID(), log_plu_event_info.getLineID()));
        eventString.put(524,  String.format("T: %d   M: %d  Added new MPA %x%x", log_plu_event_info.getTimeStamp(), log_plu_event_info.getModuleID(), log_plu_event_info.getUserVar0(), log_plu_event_info.getUserVar1()));
        eventString.put(525,  String.format("T: %d   M: %d  Existing MPA %x%x", log_plu_event_info.getTimeStamp(), log_plu_event_info.getModuleID(), log_plu_event_info.getUserVar0(), log_plu_event_info.getUserVar1()));
        eventString.put(526,  String.format("T: %d   M: %d  Deleting MPA %x%x", log_plu_event_info.getTimeStamp(), log_plu_event_info.getModuleID(), log_plu_event_info.getUserVar0(), log_plu_event_info.getUserVar1()));
        eventString.put(527,  String.format("T: %d   M: %d  Set Cellular Params: MNC=%d MCC=%x MPA Type %x Flags 0x%x", log_plu_event_info.getTimeStamp(), log_plu_event_info.getModuleID(), log_plu_event_info.getUserVar0(), log_plu_event_info.getUserVar1(), log_plu_event_info.getUserVar2(), log_plu_event_info.getUserVar3()));
        eventString.put(528,  String.format("T: %d   M: %d  Got TX request ACK from MPA %d", log_plu_event_info.getTimeStamp(), log_plu_event_info.getModuleID(), log_plu_event_info.getUserVar0()));
        eventString.put(529,  String.format("T: %d   M: %d  TX request from MPA %d TIMEDOUT", log_plu_event_info.getTimeStamp(), log_plu_event_info.getModuleID(), log_plu_event_info.getUserVar0()));
        eventString.put(530,  String.format("T: %d   M: %d  *** Activated Correlation Task", log_plu_event_info.getTimeStamp(), log_plu_event_info.getModuleID()));
        eventString.put(531,  String.format("T: %d   M: %d  Number of Sub-bands = %d, OpID = %x", log_plu_event_info.getTimeStamp(), log_plu_event_info.getModuleID(), log_plu_event_info.getUserVar0(), log_plu_event_info.getUserVar1()));
        eventString.put(532,  String.format("T: %d   M: %d  Correlation Task Finished", log_plu_event_info.getTimeStamp(), log_plu_event_info.getModuleID()));
        eventString.put(533,  String.format("T: %d   M: %d  *** ARM Correlation Task got finished for OpID = %d", log_plu_event_info.getTimeStamp(), log_plu_event_info.getModuleID(), log_plu_event_info.getUserVar0()));
        eventString.put(534,  String.format("T: %d   M: %d  Detection Table: active/was_updated = %d, OpId/SB/CH(%x) = %x", log_plu_event_info.getTimeStamp(), log_plu_event_info.getModuleID(), log_plu_event_info.getUserVar0(), log_plu_event_info.getUserVar2(), log_plu_event_info.getUserVar1()));
        eventString.put(535,  String.format("T: %d   M: %d  *** Correlation Calculation, max = %d, sec_max = %x", log_plu_event_info.getTimeStamp(), log_plu_event_info.getModuleID(), log_plu_event_info.getUserVar0(), log_plu_event_info.getUserVar1()));
        eventString.put(536,  String.format("T: %d   M: %d  *** Wrong Operator ID (%d) during correlation task", log_plu_event_info.getTimeStamp(), log_plu_event_info.getModuleID(), log_plu_event_info.getUserVar0()));
        eventString.put(537,  String.format("T: %d   M: %d  *** Bt900 task - Simulation is started ***", log_plu_event_info.getTimeStamp(), log_plu_event_info.getModuleID()));
        eventString.put(538,  String.format("T: %d   M: %d  *** Correlation task - Simulation is started ***", log_plu_event_info.getTimeStamp(), log_plu_event_info.getModuleID()));
        eventString.put(540,  String.format("T: %d   M: %d  DSP Location Notification: det #%d, OpID/SB/CH = %x, Driver = %x, LocTimeStamp = %x", log_plu_event_info.getTimeStamp(), log_plu_event_info.getModuleID(), log_plu_event_info.getUserVar0(), log_plu_event_info.getUserVar1(), log_plu_event_info.getUserVar3(), log_plu_event_info.getUserVar2()));
        eventString.put(541,  String.format("T: %d   M: %d  DSP Start Correlation for Sub-band", log_plu_event_info.getTimeStamp(), log_plu_event_info.getModuleID()));
        eventString.put(542,  String.format("T: %d   M: %d  DSP Stop Correlation for Sub-band", log_plu_event_info.getTimeStamp(), log_plu_event_info.getModuleID()));
        eventString.put(543,  String.format("T: %d   M: %d  *************** DSP Correlation - Sub-band Number = %d", log_plu_event_info.getTimeStamp(), log_plu_event_info.getModuleID(), log_plu_event_info.getUserVar0()));
        eventString.put(544,  String.format("T: %d   M: %d  *************** DSP Correlation - Current SB_ID = %d, SB_ID_INDEX = %x", log_plu_event_info.getTimeStamp(), log_plu_event_info.getModuleID(), log_plu_event_info.getUserVar0(), log_plu_event_info.getUserVar1()));
        eventString.put(551,  String.format("T: %d   M: %d  >>>>>>>>>>>>>>> DSP Algo - New entity, SB_ID = %d, sdmn = %x, fusion_maxdist = %x, sdmd= %x", log_plu_event_info.getTimeStamp(), log_plu_event_info.getModuleID(), log_plu_event_info.getUserVar0(), log_plu_event_info.getUserVar1(), log_plu_event_info.getUserVar2(), log_plu_event_info.getUserVar3()));
        eventString.put(552,  String.format("T: %d   M: %d  >>>>>>>>>>>>>>> DSP Algo - Combine with closest entity, SB_ID = %d", log_plu_event_info.getTimeStamp(), log_plu_event_info.getModuleID(), log_plu_event_info.getUserVar0()));
        eventString.put(553,  String.format("T: %d   M: %d  >>>>>>>>>>>>>>> DSP Algo - Entry to Fusion, sdn = %d, sdd = %x", log_plu_event_info.getTimeStamp(), log_plu_event_info.getModuleID(), log_plu_event_info.getUserVar0(), log_plu_event_info.getUserVar1()));
        eventString.put(554,  String.format("T: %d   M: %d  >>>>>>>>>>>>>>> DSP Algo - Fusion, Aging reset i = %d", log_plu_event_info.getTimeStamp(), log_plu_event_info.getModuleID(), log_plu_event_info.getUserVar0()));
        eventString.put(600,  String.format("T: %d   M: %d  *************** DimaDeb: Deleting active MPA (%d)", log_plu_event_info.getTimeStamp(), log_plu_event_info.getModuleID(), log_plu_event_info.getUserVar0()));
        eventString.put(911,  String.format("T: %d   M: %d  Unfriendly Table: MPA = %d, Delay/Timeout = %x/%x, OpID/SB/CH = %x", log_plu_event_info.getTimeStamp(), log_plu_event_info.getModuleID(), log_plu_event_info.getUserVar0(), log_plu_event_info.getUserVar1(), log_plu_event_info.getUserVar3(), log_plu_event_info.getUserVar2()));
        eventString.put(927,  String.format("T: %d   M: %d  >>> DimaDeb: Config Update, Bundle = %d, ConfigPage = %x", log_plu_event_info.getTimeStamp(), log_plu_event_info.getModuleID(), log_plu_event_info.getUserVar0(), log_plu_event_info.getUserVar1()));
        eventString.put(928,  String.format("T: %d   M: %d  >>> Hasta la vista, baby", log_plu_event_info.getTimeStamp(), log_plu_event_info.getModuleID()));
        eventString.put(1000,  String.format("T: %d   M: %d  Bundle Image is valid, Len = %d B, BUNDLE_ID = %x", log_plu_event_info.getTimeStamp(), log_plu_event_info.getModuleID(), log_plu_event_info.getUserVar0(), log_plu_event_info.getUserVar1()));
        eventString.put(1001,  String.format("T: %d   M: %d  Bundle Image is invalid!!!", log_plu_event_info.getTimeStamp(), log_plu_event_info.getModuleID()));
        //Accelerometer
 /*       eventString.put(38,String.format("T2: %d M: {4}  F: {2}  L:{3} X: {6:X8}  Y: {7:X8}  Z: {8:X8} S: {9:X8} Accelerometer sample", log_plu_event_info.getTimeStamp()));
        //Accelerometer overflow
        eventString.put(39,String.format("T2: %d M: {4}  F: {2}  L:{3} X: {6}  Y: {7}  Z: {8} S: {9:X4} Accelerometer overflow", log_plu_event_info.getTimeStamp()));
        // Movement recognition
        eventString.put(100,String.format( "T: %d  M: {4}  F: {2}  L:{3}  X movement threshold triggered {6:X8}, {7:X8}, {8:X8}, {9:X8}", log_plu_event_info.getTimeStamp()));
        eventString.put(101,String.format( "T: %d  M: {4}  F: {2}  L:{3}  Y movement threshold triggered {6:X8}, {7:X8}, {8:X8}, {9:X8}", log_plu_event_info.getTimeStamp()));
        // IQ Sampling
        eventString.put(200, String.format("T: %d  M: {4}  F: {2}  L:{3}  Sampling started", log_plu_event_info.getTimeStamp()));
        eventString.put(201, String.format("T: %d  M: {4}  F: {2}  L:{3}  Sampling stopped", log_plu_event_info.getTimeStamp()));
        // Monitor debug
        eventString.put(300, String.format("T: %d}  M: {4}  F: {2}  L:{3}  Host request {6:X8} completed with status {7:X8}", log_plu_event_info.getTimeStamp()));
        // Watchdog
        eventString.put(400, String.format("T: %d  M: {4}  F: {2}  L:{3}  Watchdog Activated", log_plu_event_info.getTimeStamp()));
        eventString.put(401, String.format("T: %d  M: {4}  F: {2}  L:{3}  Watchdog Enabled", log_plu_event_info.getTimeStamp()));
        eventString.put(402, String.format("T: %d  M: {4}  F: {2}  L:{3}  Watchdog Disabled", log_plu_event_info.getTimeStamp()));
        eventString.put(410, String.format("T: %d  M: {4}  F: {2}  L:{3}  Watchdog ARM is alive", log_plu_event_info.getTimeStamp()));
        eventString.put(411, String.format("T: %d  M: {4}  F: {2}  L:{3}  Watchdog DSP is alive", log_plu_event_info.getTimeStamp()));
        eventString.put(412, String.format("T: %d  M: {4}  F: {2}  L:{3}  Watchdog BT is alive", log_plu_event_info.getTimeStamp()));
        eventString.put(413, String.format("T: %d  M: {4}  F: {2}  L:{3}  Watchdog WIFI is alive", log_plu_event_info.getTimeStamp()));
        eventString.put(420, String.format("T: %d  M: {4}  F: {2}  L:{3}  Watchdog DSP keep alive failed", log_plu_event_info.getTimeStamp()));
        eventString.put(421, String.format("T: %d  M: {4}  F: {2}  L:{3}  Watchdog DSP keep alive bad", log_plu_event_info.getTimeStamp()));
        eventString.put(422, String.format("T: %d  M: {4}  F: {2}  L:{3}  Watchdog DSP keep alive timeout", log_plu_event_info.getTimeStamp()));
        eventString.put(423, String.format("T: %d  M: {4}  F: {2}  L:{3}  Watchdog BT keep alive failed", log_plu_event_info.getTimeStamp()));
        eventString.put(424, String.format("T: %d  M: {4}  F: {2}  L:{3}  Watchdog WIFI keep alive failed", log_plu_event_info.getTimeStamp()));
        eventString.put(447, String.format("Phone info: {6:X8} T: %d Phone location: {7:X8}  Conf level: {8:X8}", log_plu_event_info.getTimeStamp()));
        eventString.put(448, String.format("T: {5:X8} Super position Ant 1: Bin 1: {6:X8} Bin 2 {7:X8} Bin 3 {8:X8} Bin 4 {9:X8}"));
        eventString.put(449, String.format("T: {5:X8} Super position Ant 2: Bin 1: {6:X8} Bin 2 {7:X8} Bin 3 {8:X8} Bin 4 {9:X8}"));
        eventString.put(450, String.format("T: {5:X8} Super position Ant 3: Bin 1: {6:X8} Bin 2 {7:X8} Bin 3 {8:X8} Bin 4 {9:X8}"));
        eventString.put(451, String.format("T: {5:X8} Super position Ant 4: Bin 1: {6:X8} Bin 2 {7:X8} Bin 3 {8:X8} Bin 4 {9:X8}"));
        eventString.put(452, String.format("T: %d  M: {4}>  >>> DSP Algo - NOISE1: STD = {6}, MEAN = {7}", log_plu_event_info.getTimeStamp()));
        eventString.put(453, String.format("T: %d  M: {4}>  >>> DSP Algo - NOISE2: STD = {6}, MEAN = {7}", log_plu_event_info.getTimeStamp()));
        eventString.put(454, String.format("T: %d  M: {4}>  >>> DSP Algo - NOISE3: STD = {6}, MEAN = {7}", log_plu_event_info.getTimeStamp()));
        eventString.put(455, String.format("T: %d}  M: {4}>  >>> DSP Algo - NOISE4: STD = {6}, MEAN = {7}", log_plu_event_info.getTimeStamp()));
        eventString.put(500, String.format("T: %d  M: {4}  F: {2}  L:{3}  DSP Scheduler log data available at {6:X8}, {7:X8}, {8:X8}, {9:X8}", log_plu_event_info.getTimeStamp()));
        eventString.put(501, String.format("T: %d  M: {4}  F: {2}  L:{3}  DSP Scan Start {6:X8}", log_plu_event_info.getTimeStamp()));
        eventString.put(502, String.format("T: %d  M: {4}  F: {2}  L:{3}  DSP Scan Stop {6:X8}", log_plu_event_info.getTimeStamp()));
        eventString.put(503, String.format("T: %d  M: {4}>  BLE Connect from MPA", log_plu_event_info.getTimeStamp()));
        eventString.put(504, String.format("T: %d  M: {4}>  BLE Get MPA ID request from MPA", log_plu_event_info.getTimeStamp()));
        eventString.put(505, String.format("T: %d  M: {4}>  BLE Sent MPA ID {6:X2} to MPA", log_plu_event_info.getTimeStamp()));
        eventString.put(506, String.format("T: %d  M: {4}>  BLE Sent ACK to MPA", log_plu_event_info.getTimeStamp()));
        eventString.put(507, String.format("T: %d  M: {4}>  BLE Sent NACK to MPA", log_plu_event_info.getTimeStamp()));
        eventString.put(508, String.format("T: %d  M: {4}>  BLE CMD T:{6:X4} L:{7:X4} from MPA", log_plu_event_info.getTimeStamp()));
        eventString.put(509, String.format("T: %d  M: {4}>  BLE Reply T:{6:X4} L:{7:X4} to MPA", log_plu_event_info.getTimeStamp()));
        eventString.put(510, String.format("T: %d  M: {4}>  BLE F/W Update Start (PKT:{6}) from MPA", log_plu_event_info.getTimeStamp()));
    //    eventString.put(511, String.format("T: {5}  M: {4}>  BLE F/W Update Cont. (PKT:{6}) from MPA", log_plu_event_info.getTimeStamp()));
        eventString.put(511, String.format("T: %d  M: {4}>  BLE F/W Update Cont. (PKT: %d) from MPA", log_plu_event_info.getTimeStamp(), log_plu_event_info.getUserVar0()));
        eventString.put(512, String.format("T: %d  M: {4}>  BLE F/W Update Done (PKT:{6}) from MPA", log_plu_event_info.getTimeStamp()));
        eventString.put(513, String.format("T: %d  M: {4}>  BLE Calibration Start from MPA", log_plu_event_info.getTimeStamp()));
        eventString.put(514, String.format("T: %d  M: {4}>  BLE Calibration Point {6} position {7:X2}", log_plu_event_info.getTimeStamp()));
        eventString.put(515, String.format("T: %d  M: {4}>  BLE Validation Point {6}", log_plu_event_info.getTimeStamp()));
        eventString.put(516, String.format("T: %d  M: {4}>  BLE Calibration End from MPA", log_plu_event_info.getTimeStamp()));
        eventString.put(517, String.format("T: %d  M: {4}>  BLE Calibration Data to MPA", log_plu_event_info.getTimeStamp()));
        eventString.put(518, String.format("T: %d  M: {4}>  BLE Disconnect", log_plu_event_info.getTimeStamp()));
        eventString.put(519, String.format("T: %d  M: {4}>  BLE Reset", log_plu_event_info.getTimeStamp()));
        eventString.put(520, String.format("T: %d  M: {4}>  BLE Get PLU Log", log_plu_event_info.getTimeStamp()));
        eventString.put(521, String.format("T: %d  M: {4}  F: {2}  L: {3}  ACC Raw Sample X:{6:X4}, Y:{7:X4}, Z:{8:X4} N:{9}", log_plu_event_info.getTimeStamp()));
        eventString.put(522, String.format("T: %d  M: {4}  F: {2}  L:{3}  Accelerometer FIFO Overflow", log_plu_event_info.getTimeStamp()));
        eventString.put(523, String.format("T: %d  M: {4}  F: {2}  L:{3}  SYS Location Notification", log_plu_event_info.getTimeStamp()));
        eventString.put(524, String.format("T: %d  M: {4}>  Added new MPA {6:X8}{7:X8}", log_plu_event_info.getTimeStamp()));
        eventString.put(525, String.format("T: %d  M: {4}>  Existing MPA {6:X8}{7:X8}", log_plu_event_info.getTimeStamp()));
        eventString.put(526, String.format("T: %d  M: {4}>  Deleting MPA {6:X8}{7:X8}", log_plu_event_info.getTimeStamp()));
        eventString.put(527, String.format("T: %d  M: {4}>  Set Cellular Params: MNC={6} MCC={7} MPA Type {8} Flags 0x{9:X2}", log_plu_event_info.getTimeStamp()));
        eventString.put(528, String.format("T: %d  M: {4}>  Got TX request ACK from MPA {6}", log_plu_event_info.getTimeStamp()));
        eventString.put(529, String.format("T: %d  M: {4}>  TX request from MPA {6} TIMEDOUT", log_plu_event_info.getTimeStamp()));
        eventString.put(530, String.format("T: %d  M: {4}>  *** Activated Correlation Task", log_plu_event_info.getTimeStamp()));
        eventString.put(531, String.format("T: %d  M: {4}>  Number of Sub-bands = {6}, OpID = {7}", log_plu_event_info.getTimeStamp()));
        eventString.put(532, String.format("T: %d  M: {4}>  Correlation Task Finished", log_plu_event_info.getTimeStamp()));
        eventString.put(533, String.format("T: %d  M: {4}>  *** ARM Correlation Task got finished for OpID = {6}", log_plu_event_info.getTimeStamp()));
        eventString.put(534, String.format("T: %d  M: {4}>  Detection Table: active/was_updated = {6}, OpId/SB/CH({8}) = {7}", log_plu_event_info.getTimeStamp()));
        eventString.put(535, String.format("T: %d  M: {4}>  *** Correlation Calculation, max = {6}, sec_max = {7}", log_plu_event_info.getTimeStamp()));
        eventString.put(536, String.format("T: %d  M: {4}>  *** Wrong Operator ID ({6}) during correlation task", log_plu_event_info.getTimeStamp()));
        eventString.put(537, String.format("T: %d  M: {4}>  *** Bt900 task - Simulation is started ***", log_plu_event_info.getTimeStamp()));
        eventString.put(538, String.format("T: %d  M: {4}>  *** Correlation task - Simulation is started ***", log_plu_event_info.getTimeStamp()));
        eventString.put(540, String.format("T: %d  M: {4}>  DSP Location Notification: det #{6}, OpID/SB/CH = {7}, Driver = {9}, LocTimeStamp = {8}", log_plu_event_info.getTimeStamp()));
        eventString.put(541, String.format("T: %d  M: {4}>  DSP Start Correlation for Sub-band", log_plu_event_info.getTimeStamp()));
        eventString.put(542, String.format("T: %d  M: {4}>  DSP Stop Correlation for Sub-band", log_plu_event_info.getTimeStamp()));
        eventString.put(543, String.format("T: %d  M: {4}>  *************** DSP Correlation - Sub-band Number = {6}", log_plu_event_info.getTimeStamp()));
        eventString.put(544, String.format("T: %d  M: {4}>  *************** DSP Correlation - Current SB_ID = {6}, SB_ID_INDEX = {7}", log_plu_event_info.getTimeStamp()));
        eventString.put(551, String.format("T: %d  M: {4}>  >>>>>>>>>>>>>>> DSP Algo - New entity, SB_ID = {6}, sdmn = {7}, fusion_maxdist = {8}, sdmd= {9}", log_plu_event_info.getTimeStamp()));
        eventString.put(552, String.format("T: %d  M: {4}>  >>>>>>>>>>>>>>> DSP Algo - Combine with closest entity, SB_ID = {6}", log_plu_event_info.getTimeStamp()));
        eventString.put(553, String.format("T: %d  M: {4}>  >>>>>>>>>>>>>>> DSP Algo - Entry to Fusion, sdn = {6}, sdd = {7}", log_plu_event_info.getTimeStamp()));
        eventString.put(554, String.format("T: %d  M: {4}>  >>>>>>>>>>>>>>> DSP Algo - Fusion, Aging reset i = {6}", log_plu_event_info.getTimeStamp()));
        eventString.put(600, String.format("T: %d  M: {4}>  *************** DimaDeb: Deleting active MPA ({6})", log_plu_event_info.getTimeStamp()));
        eventString.put(911, String.format("T: %d  M: {4}>  Unfriendly Table: MPA = {6}, Delay/Timeout = {7}/{9}, OpID/SB/CH = {8}", log_plu_event_info.getTimeStamp()));
        eventString.put(927, String.format("T: %d  M: {4}>  >>> DimaDeb: Config Update, Bundle = {6}, ConfigPage = {7}", log_plu_event_info.getTimeStamp()));
        eventString.put(928, String.format("T: %d  M: {4}>  >>> Hasta la vista, baby", log_plu_event_info.getTimeStamp()));
        eventString.put(1000, String.format("T: %d  M: {4}>  Bundle Image is valid, Len = {6} B, BUNDLE_ID = {7}", log_plu_event_info.getTimeStamp()));
        eventString.put(1001, String.format("T: %d  M: {4}>  Bundle Image is invalid!!!", log_plu_event_info.getTimeStamp()));
*/
        return eventString;
    }
}
