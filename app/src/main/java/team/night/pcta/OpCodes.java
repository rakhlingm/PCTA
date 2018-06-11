package team.night.pcta;

/**
 * Created by GregoriRakhlin on 6/7/2018.
 */

public class OpCodes {
    final static long ACK = 0x1;                      //!< PLU->PCTA OpCode for Operation Response

    final static long GPIO_SET_PIN_DIRECTION = 0x100; //!< PCTA->PLU OpCode for Set GPIO Pin direction (IN/OUT)
    final static long GPIO_CUR_PIN_VALUE = 0x102;     //!< PCTA->PLU OpCode for Get current GPIO Pin value (0/1)
    final static long GPIO_OUT_DRIVE = 0x104;         //!< PCTA->PLU OpCode for Set GPIO Pin out drive (High/Low)

    final static long MEM_READ_LONG = 0x200;          //!< PCTA->PLU OpCode for Get 4-bytes-value from the given memory address
    final static long MEM_WRITE_LONG = 0x202;         //!< PCTA->PLU OpCode for Set 4-bytes-value to the given memory address
    final static long MEM_READ_BUFFER = 0x204;        //!< PCTA->PLU OpCode for Get given size buffer from the given memory address (DUMP)
    final static long MEM_WRITE_BUFFER = 0x206;       //!< PCTA->PLU OpCode for Set given size buffer to the given memory address (LOAD)

    final static long TI_RF_READ_REG = 0x300;         //!< Reads a TI RF modules register value
    final static long TI_RF_WRITE_REG = 0x302;        //!< Writes a TI RF modules register value
    final static long TI_RF_WRITE_SYNTH_REGS = 0x304; //!< Writes all registers of the TI RF Synthesizer module
    final static long TI_RF_WRITE_DEM_REGS = 0x306;   //!< Writes all registers of the TI RF Demodulator module

    final static long FLASH_ERASE = 0x400;            //!< Erase NOR Flash command
    final static long FLASH_PROGRAM = 0x402;          //!< Program NOR Flash with given buffer
    final static long FLASH_BNDL_INFO_PROGRAM = 0x404;//!< Burn Info sector for a bundle of images on Flash
    final static long FLASH_BNDL_BIN_PROGRAM = 0x406; //!< Burn bundle of images on Flash
    final static long FLASH_IMG_HDR_PROGRAM = 0x408;  //!< Burn Header sector of an image on Flash
    final static long FLASH_IMG_BIN_PROGRAM = 0x40A;  //!< Burn given image of the given bundle on Flash

    final static long IQ_SAMPLING_START = 0x500;      //!< Start RF I/Q sampling and save samples in DSP memory buffer
    final static long IQ_SAMPLING_STOP = 0x502;       //!< Stop RF I/Q sampling and report how much samples were taken since Start command
    final static long IQ_SAMPLING_GET = 0x504;        //!< Return the required number of samples

    final static int FW_VERSION_GET = 0x600;         //!< FW version of the requested unit (currently ARM / DSP)

    final static long WDM_SET_DISABLE = 0x700;        //!< Disable Watchdog functionality
    final static long WDM_SET_ENABLE = 0x702;         //!< Enable Watchdog functionality
    final static long WDM_GET_STATUS = 0x704;         //!< Ask ARM does Watchdog enabled or disabled  !!! Is not used yet
    final static long WDM_KEEP_ALIVE = 0x706;         //!< ARM_INTERNAL Keep Alive msgs that ARM sends to devices under control

    final static long PLU_OPERATION_MODE = 0x802;     //!< Set Operation mode data
    final static long PLU_CALIBRATION_MODE = 0x804;	//!< Set PLU calibration parameters (4 antennas)
    final static long PLU_CALIBRATION_MODE_GET_DATA = 0x806;	//!< Get PLU calibration parameters (4 antennas)
    final static long PLU_FILTER_CONFIGURATION = 0x808; //!< Set PLU Filter configuration (type and parameter)
    final static long TRANSMISSION_ON_OFF = 0x810;				//<! TRANSMISSION On/Off (BLE/Cellular)

    final static long PLU_FILTER_SIGNAL_DETECTION = 0x812;     //<! Set PLU Filter - Signal Detection (type and parameter)
    final static long PLU_FILTER_TRACKER = 0x814;     //<! Set PLU Filter - Tracker (type and parameter)
    final static long PLU_FILTER_GATWAY_3G = 0x816;   //!< Set PLU Filter - Gateway 3G
    final static long PLU_FILTER_GATWAY_4G = 0x818;   //!< Set PLU Filter - Gateway 4G
    final static long PLU_FILTER_GATWAY_BLE = 0x820;  //!< Set PLU Filter - Gateway BLE
    final static long PLU_FILTER_LOCATION = 0x822;    //!< Set PLU Filter - Location
    final static long PLU_FILTER_FUSION = 0x824;      //!< Set PLU Filter - Fusion

    final static long PLU_ACCELEROMETER_LOGS = 0x826; //!< Get number of Accelerometer logs
    final static long PLU_DDRAM_MEMORY_TEST = 0x828;      //!< DDRAM memory test
    final static long PLU_NOTIFICATIONS = 0x830;     //!< PLU Notifications
    final static long PLU_BLE_MAC = 0x832;           //!< Get BLE MAC
    final static long PLU_iPHONE_SAFE_MODE = 0x900;           //!< Sent iPhone Safe mode
    final static long PLU_BLE_SERIAL = 0x902;           //!< Get BLE MAC
    final static long PLU_FRIENDLY_UNFRIENDLY_TABLE = 0x904; //!< Get PLU_FRIENDLY_UNFRIENDLY_TABLE
    final static long ALARM_ON_OFF = 0x906;				//<! Alarm (buzzer) On/Off
    final static long LOG_EVENT_INFO = 0x80000001L;    //!< PLU->PCTA OpCode for Report Logger Event information

    final static long OPCODE_MAX = 0x7FFFL;          //!< MAX limit value for OpCode
}
