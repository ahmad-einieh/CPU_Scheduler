import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class Priority {

    private final LinkedList<PCB> processes = new LinkedList<>();
    private final LinkedList<Job> timeline = new LinkedList<>();

    public Priority() {
        getProcesses();
        schedule();
        Main.output(processes, timeline, "Priority");
    }

    private void getProcesses() {
        File file = new File("src/job2.txt");
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(file));
            br.readLine();  // the start line which we don't want
            int arrivalT = 0;
            while (true) {
                String pId = br.readLine();
                if (pId.equals("[End of job.txt]"))
                    break;

                int[] nums = getNums(br.readLine());

                processes.add(new PCB(pId, nums[0], arrivalT++, nums[1]));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int[] getNums(String line) {
        int burstT, priority, counter = 0;
        StringBuilder strBurstT = new StringBuilder();

        while (true) {
            char c = line.charAt(counter++);
            try {
                Integer.parseInt(String.valueOf(c));
                strBurstT.append(c);
            } catch (NumberFormatException e) {
                break;
            }
        }

        burstT = Integer.parseInt(strBurstT.toString());
        priority = Integer.parseInt(String.valueOf(line.charAt(line.length()-1)));
        return new int[]{burstT, priority};
    }

    private void schedule() {
        PriorityQueue<PCB> queue = new PriorityQueue<>(processes);
        int time = 0;

        while (!queue.isEmpty()) {
            PCB proc = queue.poll();

            int finishT = time + proc.getBurstT();
            proc.setWaitingT(time);
            proc.setCompletionT(finishT);

            Job job = new Job(proc.getPId(), time, finishT);
            timeline.add(job);

            time = finishT;
        }
    }

    public static void main(String[] args) {
        new Priority();
    }
}
