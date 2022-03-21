public class PriotityNode {
    String PID;
    int burstTime;
    int remainTime;
    int priority;

    public PriotityNode(String PID, int burstTime, int priority) {
        this.PID = PID;
        this.remainTime = this.burstTime = burstTime;
        this.priority = priority;
    }

    public String getPID() {
        return PID;
    }

    public void setPID(String PID) {
        this.PID = PID;
    }

    public int getBurstTime() {
        return burstTime;
    }

    public void setBurstTime(int burstTime) {
        this.burstTime = burstTime;
    }

    public int getRemainTime() {
        return remainTime;
    }

    public void setRemainTime(int remainTime) {
        this.remainTime = remainTime;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
