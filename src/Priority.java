import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.LinkedList;

public class Priority {

    private final LinkedList<PriorityJob> jobs = new LinkedList<>();

    public Priority() {
        getJobs();
        schedule();
    }

    private void getJobs() {
        int arrivalTime = 0;

        File file = new File("src/job2.txt");
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(file));
            br.readLine();  // the start line which we don't want
            while (true) {
                String pId = br.readLine();
                if (pId.equals("[End of job.txt]"))
                    break;

                int[] nums = getNums(br.readLine());

                jobs.add(new PriorityJob(pId, arrivalTime++, nums[1], nums[0]));
            }
            display();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int[] getNums(String line) {
        int burstT, priority, counter = 0;
        String strBurstT = "";

        while (line.charAt(counter) != '\t')
            strBurstT += line.charAt(counter++);
        counter++;

        burstT = Integer.parseInt(strBurstT);
        priority = Integer.parseInt(String.valueOf(line.charAt(counter)));
        return new int[]{burstT, priority};
    }

    private void schedule() {
        //TODO
    }

    private void display() {
        System.out.println("Priority Jobs are:");
        for (PriorityJob job : jobs) {
            System.out.println(job);
        }
    }
}
