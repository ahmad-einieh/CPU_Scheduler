import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.LinkedList;

public class Priority {

    private final LinkedList<PriorityProc> processes = new LinkedList<>();
    private final LinkedList<Job> timeline = new LinkedList<>();

    public Priority() {
        getProcesses();
        schedule();
        output();
    }

    private void getProcesses() {
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

                processes.add(new PriorityProc(pId, arrivalTime++, nums[1], nums[0]));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int[] getNums(String line) {
        int burstT, priority, counter = 0;
        StringBuilder strBurstT = new StringBuilder();

        while (line.charAt(counter) != '\t')
            strBurstT.append(line.charAt(counter++));
        counter++;

        burstT = Integer.parseInt(strBurstT.toString());
        priority = Integer.parseInt(String.valueOf(line.charAt(counter)));
        return new int[]{burstT, priority};
    }

    private void schedule() {
        int time = 0;
        PriorityProc proc;
        while (true) {
            proc = getHighest();

            if (proc == null)
                break;

            int finishT = time + proc.getBurstT();

            Job job = new Job(proc.getPId(), time, finishT);
            proc.setWaitingT(finishT-proc.getBurstT());
            proc.setCompletionT(finishT-time);

            timeline.add(job);

            proc.setDone(true);

            time = finishT;
        }
    }

    private PriorityProc getHighest() {
        int hPriority = 0;
        int hProc = -1;
        for (int i = 0; i < processes.size(); i++) {
            if (processes.get(i).getPriority() > hPriority && !processes.get(i).isDone()) {
                hProc = i;
                hPriority = processes.get(i).getPriority();
            }
        }
        if (hProc == -1)
            return null;
        else
            return processes.get(hProc);
    }

    private void output() {
        int fullL = 0;
        LinkedList<Integer> ls = new LinkedList<>();
        for (Job job : timeline) {
            int l = job.getEndT() - job.getStartT();
            ls.add(l);
            fullL += l;
        }

        System.out.println("\n/////////////////// Priority OUTPUT ////////////////////\n");
        for (int i = 0; i < fullL; i++)
            System.out.print("---");
        System.out.print("\n|");

        for (int i = 0; i < timeline.size(); i++) {
            for (int j = 0; j < ls.get(i); j++)
                System.out.print(" ");

            System.out.print(timeline.get(i).getPId());

            for (int j = 0; j < ls.get(i); j++)
                System.out.print(" ");

            System.out.print("|");
        }

        System.out.println();
        for (int i = 0; i < fullL; i++)
            System.out.print("---");

        System.out.print("\n0");
        for (int i = 0; i < timeline.size(); i++) {
            for (int j = 0; j < ls.get(i)*2+4; j++)
                System.out.print(" ");

            System.out.print(timeline.get(i).getEndT());
        }

        System.out.println("\n");
        for (PriorityProc proc : processes) {
            System.out.println(proc.getPId() + ":-");
            System.out.println("Waiting Time: " + proc.getWaitingT());
            System.out.println("Completion Time: " + proc.getCompletionT());
        }
        System.out.println();

        int sumWT = 0, sumCT = 0;
        for (PriorityProc process : processes) {
            sumWT += process.getWaitingT();
            sumCT += process.getCompletionT();
        }

        System.out.println("Average Waiting Time: " + 1.0 * sumWT / processes.size());
        System.out.println("Average Completion Time: " + 1.0 * sumCT / processes.size());
    }

    public static void main(String[] args) {
        new Priority();
    }
}
