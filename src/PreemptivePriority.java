import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.List;

public class PreemptivePriority {

    public static void main(String[] args) {
        new PreemptivePriority();
    }

    public PreemptivePriority() {
        List<PCB> processes = getProcesses();
        List<Job> timeline = schedule(processes);
        Main.output(processes, timeline, "Priority");
    }

    private List<PCB> getProcesses() {
        List<PCB> processes = new LinkedList<>();

        File file = new File("testdata1 for pPriority.txt");
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(file));

            while (true) {
                String pId = br.readLine();

                if (pId == null)
                    break;

                String[] nums = br.readLine().split(",");
                int burstT = Integer.parseInt(nums[0].strip());
                int priority = Integer.parseInt(nums[1].strip());
                int arrivalT = 0;
                if (nums.length == 3)
                    arrivalT = Integer.parseInt(nums[2]);

                processes.add(new PCB(pId, arrivalT, burstT, priority));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return processes;
    }

    private List<Job> schedule(List<PCB> processes) {
        List<Job> timeline = new LinkedList<>();
        List<PCB> queue = new LinkedList<>(processes);

        int time = 0;
        while (!queue.isEmpty()) {
            PCB proc = getProcess(queue, time);
            while (proc == null)
                proc = getProcess(queue, ++time);

            int newTime = time + 1;

            if (proc.getRemainingT() == 1) {
                int oldRunTime = proc.getBurstT() - 1;
                proc.setWaitingT((time - proc.getArrivalT()) - oldRunTime);
                proc.setTurnaroundT(newTime - proc.getArrivalT());
            }
            else {
                proc.setRemainingT(proc.getRemainingT()-1);
                queue.add(proc);
            }

            timeline.add(new Job(proc.getPId(), time, newTime));

            time = newTime;
        }

        return timeline;
    }

    private PCB getProcess(List<PCB> processes, int time) {
        int lowest = -1;

        for (int i = 0; i < processes.size(); i++) {
            if (processes.get(i).getArrivalT() <= time &&
                    (lowest == -1 || processes.get(i).compareTo(processes.get(lowest)) < 0))
                lowest = i;
        }

        if (lowest == -1)
            return null;
        return processes.remove(lowest);
    }

}
