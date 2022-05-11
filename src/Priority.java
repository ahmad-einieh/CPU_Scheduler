import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class Priority {

    private final LinkedList<PCB> processes = new LinkedList<>();
    private final LinkedList<Job> timeline = new LinkedList<>();

    public static void main(String[] args) {
        new Priority();
    }

    public Priority() {
        getProcesses();
        schedule();
        Main.output(processes, timeline, "Priority");
    }

    private void getProcesses() {
        File file = new File("src/testdata1 for priortiy.txt");
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(file));

            int arrivalT = 0;
            while (true) {
                String pId = br.readLine();

                if (pId == null)
                    break;

                String[] nums = br.readLine().split(",");

                int burstT = Integer.parseInt(nums[0].strip());
                int priority = Integer.parseInt(nums[1].strip());

                processes.add(new PCB(pId, burstT, arrivalT++, priority));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void schedule() {
        PriorityQueue<PCB> queue = new PriorityQueue<>(processes);
        int time = 0;

        while (!queue.isEmpty()) {
            PCB proc = queue.poll();
            int finishT = time + proc.getBurstT();

            proc.setWaitingT(time);
            proc.setCompletionT(finishT);

            timeline.add(new Job(proc.getPId(), time, finishT));

            time = finishT;
        }
    }

}
