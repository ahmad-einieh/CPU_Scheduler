public class Job {

    private final String pId;
    private final int startT;
    private int endT;

    public Job(String pId, int startT, int endT) {
        this.pId = pId;
        this.startT = startT;
        this.endT = endT;
    }

    public String getPId() {
        return pId;
    }

    public int getStartT() {
        return startT;
    }

    public int getEndT() {
        return endT;
    }

    public void setEndT(int endT) {
        this.endT = endT;
    }

    @Override
    public String toString() {
        return "Job{" +
                ", pId='" + pId + '\'' +
                ", startT=" + startT +
                ", endT=" + endT +
                '}';
    }
}
