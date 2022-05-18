import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.List;

public class FCFS {

    public static void main(String[] args) {
        new FCFS();
    }

    public FCFS() {
        List<PCB> processes = getProcesses();
        List<Job> timeline = schedule(processes);
        Main.output(processes, timeline, "FCFS");
    }

    private List<PCB> getProcesses() {
        List<PCB> processes = new LinkedList<>();

        File file = new File("HW2 Q7.txt");
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            while (true) {
                String line = br.readLine();
                if (line == null)
                    break;

                String[] values = line.split(",");
                int burstT = Integer.parseInt(values[1].strip());
                int priority = Integer.parseInt(values[2].strip());

                processes.add(new PCB(values[0], 0, burstT, priority));
            }
            br.close();
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
            PCB proc = queue.remove(0);

            int newTime = time + proc.getBurstT();

            proc.setWaitingT(time);
            proc.setTurnaroundT(newTime);

            timeline.add(new Job(proc.getPId(), time, newTime));

            time = newTime;
        }

        return timeline;
    }

}
