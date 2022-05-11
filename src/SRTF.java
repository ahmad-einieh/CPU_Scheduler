import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.LinkedList;

public class SRTF {

    public SRTF() {
        LinkedList<PCB> processes = getProcesses();
        LinkedList<Job> timeline = schedule(processes);
        cleanup(timeline);
        Main.output(processes, timeline, "SRTF");
    }

    private LinkedList<PCB> getProcesses() {
        LinkedList<PCB> processes = new LinkedList<>();

        File file = new File("src/testdata1 for SRTF.txt");
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(file));

            while (true) {
                String line = br.readLine();
                if (line.equals("//PID,AT,BT\t(AT: Arrival Time, BT: Burst Time)"))
                    break;

                String[] info = line.split(",");
                processes.add(new PCB(info[0], Integer.parseInt(info[1]), Integer.parseInt(info[2]), 0));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return processes;
    }

    private LinkedList<Job> schedule(LinkedList<PCB> processes) {
        LinkedList<Job> timeline = new LinkedList<>();

        int clock = 0;
        while (!checkCompleted(processes)) {
            PCB minBurstProc = null;
            int minBurst = Integer.MAX_VALUE;

            for (PCB p : processes) {
                if (!p.isCompleted() && p.getArrivalT() <= clock && p.getRemainingT() < minBurst) {
                    minBurst = p.getRemainingT();
                    minBurstProc = p;
                }
            }

            if (minBurstProc != null) {
                timeline.add(new Job(minBurstProc.getPId(), clock, clock + 1));
                minBurstProc.setRemainingT(minBurstProc.getRemainingT() - 1);

                clock++;

                if (minBurstProc.getRemainingT() == 0) {
                    minBurstProc.setWaitingT(clock - minBurstProc.getArrivalT() - minBurstProc.getBurstT());
                    minBurstProc.setCompletionT(clock);
                }
            }
        }

        return timeline;
    }

    private boolean checkCompleted(LinkedList<PCB> parr) {
        for (PCB p : parr) {
            if (!p.isCompleted())
                return false;
        }
        return true;
    }

    private void cleanup(LinkedList<Job> timeline) {
        int i = 0;
        while (i+1 < timeline.size()) {
            if (timeline.get(i).getPId().equals(timeline.get(i+1).getPId())) {
                timeline.get(i).setEndT(timeline.get(i+1).getEndT());
                timeline.remove(i+1);
            }
            else
                i++;
        }
    }

}
