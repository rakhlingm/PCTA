package team.night.pcta;

/**
 * Created by GregoriRakhlin on 6/10/2018.
 */

public abstract class PLU_Log {

    private long opCode;
    private long length;
    private long eventCode;

    public long getOpCode() {
        return opCode;
    }

    public long getLength() {
        return length;
    }

    public long getEventCode() {
        return eventCode;
    }
}
