public class PCB implements Comparable<PCB> {

    private final String pId;
    private final int burstT;
    private final int arrivalT;
    private final int priority;
    private int remainingT;
    private int waitingT;
    private int completionT;

    public PCB(String pId, int burstT, int arrivalT, int priority) {
        this.pId = pId;
        this.burstT = remainingT = burstT;
        this.arrivalT = arrivalT;
        this.priority = priority;
    }

    public String getPId() {
        return pId;
    }

    public int getBurstT() {
        return burstT;
    }

    public int getArrivalT() {
        return arrivalT;
    }

    public int getPriority() {
        return priority;
    }

    public int getRemainingT() {
        return remainingT;
    }

    public void setRemainingT(int remainingT) {
        this.remainingT = remainingT;
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
        return "PCB{" +
                "pId='" + pId + '\'' +
                ", burstT=" + burstT +
                ", priority=" + priority +
                ", remainingT=" + remainingT +
                ", waitingT=" + waitingT +
                ", completionT=" + completionT +
                '}';
    }

    @Override
    public int compareTo(PCB o) {
        int result = Integer.compare(priority, o.priority);
        if (result != 0)
            return result;
        return Integer.compare(arrivalT, o.arrivalT);
    }
}