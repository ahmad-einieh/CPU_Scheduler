public class RRProc {

    private final String pId;
    private final int arrivalT;
    private final int burstT;
    private int remainingT;
    private int completeTime;
    private boolean dispatched;
    private int waitingT;
    private int completionT;

    public RRProc(String pId, int arrivalT, int burstT) {
        this.pId = pId;
        this.arrivalT = arrivalT;
        this.burstT = burstT;
        remainingT = burstT;
        this.completeTime = -1;
        this.dispatched = false;
    }

    public String getPId() {
        return pId;
    }

    public int getArrivalT() {
        return arrivalT;
    }

    public int getBurstT() {
        return burstT;
    }

    public int getRemainingT() {
        return remainingT;
    }

    /*public void setRemainingT(int remainingT) {
        this.remainingT = remainingT;
    }*/

    public void reduceRemainingTime(int q) {
        remainingT -= (int) Math.min(remainingT, q);
    }

    public void setCompleteTime(int time) {
        this.completeTime = time;
    }

    public int getCompleteTime() {
        return this.completeTime;
    }

    public boolean isCompleted() {
        return this.completeTime != -1;
    }

    public void setDispatched() {
        this.dispatched = true;
    }

    public boolean isDispatched() {
        return this.dispatched;
    }

    public int getWaitingT() {
        return waitingT;
    }

    public void setWaitingT(int waitingT) {
        this.waitingT = waitingT;
    }

    public int getCompletionT() {
        return completionT;
    }

    public void setCompletionT(int completionT) {
        this.completionT = completionT;
    }

    @Override
    public String toString() {
        return "RRProc{" +
                "pId='" + pId + '\'' +
                ", arrivalT=" + arrivalT +
                ", burstT=" + burstT +
                ", remainingT=" + remainingT +
                ", completeTime=" + completeTime +
                ", dispatched=" + dispatched +
                ", waitingT=" + waitingT +
                ", completionT=" + completionT +
                '}';
    }
}
