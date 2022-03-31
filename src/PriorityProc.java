public class PriorityProc implements Comparable<PriorityProc> {

    private final String pId;
    private final int priority;
    private final int burstT;
    private int waitingT;
    private int completionT;
    private boolean done;

    public PriorityProc(String pId, int priority, int burstT) {
        this.pId = pId;
        this.priority = priority;
        this.burstT = burstT;
    }

    public String getPId() {
        return pId;
    }

    public int getPriority() {
        return priority;
    }

    public int getBurstT() {
        return burstT;
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

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    @Override
    public String toString() {
        return "PriorityProc{" +
                ", pId='" + pId + '\'' +
                ", priority=" + priority +
                ", burstT=" + burstT +
                ", waitingT=" + waitingT +
                ", completionT=" + completionT +
                ", done=" + done +
                '}';
    }

    @Override
    public int compareTo(PriorityProc o) {
        return Integer.compare(priority, o.priority);
    }
}
