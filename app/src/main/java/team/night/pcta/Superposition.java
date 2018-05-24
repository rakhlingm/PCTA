package team.night.pcta;

/**
 * Created by GregoriRakhlin on 5/14/2018.
 */

public class Superposition {
    private String systemTimastamp;
    private long PLUTimastamp;
    private long bin1;
    private long bin2;
    private long bin3;
    private long bin4;
    private long bin5;
    private long size;
    private long sub_band;
    private long channel;

    private int counterLog448 = 0;
    private int counterLog449 = 0;
    private int counterLog450 = 0;
    private int counterLog451 = 0;

    public static Superposition instance;


    public static synchronized Superposition getInstance() {
        if (instance == null) {
            instance = new Superposition();
        }
        return instance;
    }
    public Superposition() {
    }

    public void setSystemTimastamp(String systemTimastamp) {
        this.systemTimastamp = systemTimastamp;
    }

    public void setPLUTimastamp(long PLUTimastamp) {
        this.PLUTimastamp = PLUTimastamp;
    }

    public void setBin1(long bin1) {
        this.bin1 = bin1;
    }

    public void setBin2(long bin2) {
        this.bin2 = bin2;
    }

    public void setBin3(long bin3) {
        this.bin3 = bin3;
    }

    public void setBin4(long bin4) {
        this.bin4 = bin4;
    }

    public void setBin5(long bin5) {
        this.bin5 = bin5;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public void setSub_band(long sub_band) {
        this.sub_band = sub_band;
    }

    public void setChannel(long channel) {
        this.channel = channel;
    }

    public void setCounterLog448(int counterLog448) {
        this.counterLog448 = counterLog448;
    }

    public void setCounterLog449(int counterLog449) {
        this.counterLog449 = counterLog449;
    }

    public void setCounterLog450(int counterLog450) {
        this.counterLog450 = counterLog450;
    }

    public void setCounterLog451(int counterLog451) {
        this.counterLog451 = counterLog451;
    }

    public String getSystemTimastamp() {
        return systemTimastamp;
    }

    public long getPLUTimastamp() {
        return PLUTimastamp;
    }

    public long getBin1() {
        return bin1;
    }

    public long getBin2() {
        return bin2;
    }

    public long getBin3() {
        return bin3;
    }

    public long getBin4() {
        return bin4;
    }

    public long getBin5() {
        return bin5;
    }

    public long getSize() {
        return size;
    }

    public long getSub_band() {
        return sub_band;
    }

    public long getChannel() {
        return channel;
    }

    public int getCounterLog448() {
        return counterLog448;
    }

    public int getCounterLog449() {
        return counterLog449;
    }

    public int getCounterLog450() {
        return counterLog450;
    }

    public int getCounterLog451() {
        return counterLog451;
    }

    @Override
    public String toString() {
        return "Superposition{" +
                "systemTimastamp=" + systemTimastamp +
                ", PLUTimastamp=" + PLUTimastamp +
                ", bin1=" + bin1 +
                ", bin2=" + bin2 +
                ", bin3=" + bin3 +
                ", bin4=" + bin4 +
                ", bin5=" + bin5 +
                ", size=" + size +
                ", sub_band=" + sub_band +
                ", channel=" + channel +
                '}';
    }
    //    Super possition: T1: 17:30:47:187 T: 3635376 Super position Ant 1: Bin 1: 34808 Bin 2: 34462 Bin 3: 35624 Bin 4: 21591 Bin 5: 24478 Size: 5 Sub-band ID: 18 Channel ID 0

}
