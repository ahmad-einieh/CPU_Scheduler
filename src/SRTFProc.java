public class SRTFProc {

    private final String pId;
    private final int arrivalT;
    private final int burstT;
    private int remainingT;

    public SRTFProc(String pId, int arrivalT, int burstT) {
        this.pId = pId;
        this.burstT = burstT;
        this.arrivalT = arrivalT;
        remainingT = burstT;
    }

    public String getPId() {
        return pId;
    }

    public int getArrivalT () {
        return arrivalT;
    }

    public int getBurstT () {
        return burstT;
    }

    public int getRemainingT () {
        return remainingT;
    }

    public void setRemainingT ( int remainingT){
        this.remainingT = remainingT;
    }

    @Override
    public String toString () {
        return "SRTF Process{" +
                "pId='" + pId + '\'' +
                ", burstT=" + burstT +
                ", arrivalT=" + arrivalT +
                ", burstT=" + burstT +
                ", remainingT=" + remainingT +
                '}';
    }
}