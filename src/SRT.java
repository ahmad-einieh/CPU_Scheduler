import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.LinkedList;

public class SRT {

    private final LinkedList<SRTJob> jobs = new LinkedList<>();

    public SRT() {
        getJobs();
        schedule();
    }

    private void getJobs() {
        int arrivalTime = 0;

        File file = new File("src/job1.txt");
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(file));
            br.readLine();  // the start line which we don't want
            while (true) {
                String pId = br.readLine();
                if (pId.equals("[End of job.txt]"))
                    break;
                int burstTime = Integer.parseInt(br.readLine());

                jobs.add(new SRTJob(pId, burstTime, arrivalTime++));
            }
            display();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void schedule() {
        //TODO
    }

    private void display() {
        System.out.println("SRT Jobs are:");
        for (SRTJob job : jobs) {
            System.out.println(job);
        }
    }
}