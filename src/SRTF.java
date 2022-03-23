import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.LinkedList;

public class SRTF {

    private final LinkedList<SRTFProc> processes = new LinkedList<>();
    private final LinkedList<Job> timeline = new LinkedList<>();

    public SRTF() {
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

                processes.add(new SRTFProc(pId, arrivalTime++, burstTime));
            }
            display();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void schedule() {


    }

    private void display() {
        System.out.println("SRTF Processes are:");
        for (SRTFProc process : processes)
            System.out.println(process);
    }

    private void output() {
        System.out.println("/////////////////// SRTF OUTPUT ////////////////////");

        //TODO
    }
}
