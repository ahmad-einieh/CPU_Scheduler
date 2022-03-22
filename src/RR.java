import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.LinkedList;

public class RR {

    private final int QUANTUM;
    private final LinkedList<RRProc> processes = new LinkedList<>();
    private final LinkedList<Job> timeline = new LinkedList<>();

    public RR(int quantum) {
        QUANTUM = quantum;
        getProcesses();
        schedule();
        output();
    }

    private void getProcesses() {
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

                processes.add(new RRProc(pId, arrivalTime++, burstTime));
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
        System.out.println("RR Processes are:");
        for (RRProc process : processes)
            System.out.println(process);
    }

    private void output() {
        System.out.println("/////////////////// RR OUTPUT ////////////////////");

        //TODO
    }
}
