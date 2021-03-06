public class PCB implements Comparable<PCB> {

    private final String pId;
    private final int arrivalT;
    private final int burstT;
    private final int priority;
    private int remainingT;
    private int waitingT;
    private int turnaroundT;

    public PCB(String pId, int arrivalT, int burstT, int priority) {
        this.pId = pId;
        this.arrivalT = arrivalT;
        this.burstT = remainingT = burstT;
        this.priority = priority;
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

    public int getTurnaroundT() {
        return turnaroundT;
    }

    public void setTurnaroundT(int turnaroundT) {
        this.turnaroundT = turnaroundT;
    }

    public boolean isCompleted() {
        return turnaroundT != 0;
    }

    @Override
    public String toString() {
        return "PCB{" +
                "pId='" + pId + '\'' +
                ", arrivalT=" + arrivalT +
                ", burstT=" + burstT +
                ", priority=" + priority +
                ", remainingT=" + remainingT +
                ", waitingT=" + waitingT +
                ", turnaroundT=" + turnaroundT +
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
