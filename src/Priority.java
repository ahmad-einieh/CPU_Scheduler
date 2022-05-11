import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class Priority {

    public Priority() {
        LinkedList<PCB> processes = getProcesses();
        LinkedList<Job> timeline = schedule(processes);
        Main.output(processes, timeline, "Priority");
    }

    private LinkedList<PCB> getProcesses() {
        LinkedList<PCB> processes = new LinkedList<>();

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

                processes.add(new PCB(pId, arrivalT++, burstT, priority));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return processes;
    }

    private LinkedList<Job> schedule(LinkedList<PCB> processes) {
        LinkedList<Job> timeline = new LinkedList<>();
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

        return timeline;
    }

}
