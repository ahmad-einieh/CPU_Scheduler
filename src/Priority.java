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

                processes.add(new PriorityProc(pId, nums[1], nums[0]));
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
        int time = 0;
        PriorityProc proc;
        while (true) {
            proc = getNext();

            if (proc == null)
                break;

            int finishT = time + proc.getBurstT();
            proc.setWaitingT(time);
            proc.setCompletionT(finishT);

            Job job = new Job(proc.getPId(), time, finishT);
            timeline.add(job);

            proc.setDone(true);

            time = finishT;
        }
    }

    private PriorityProc getNext() {
        int targetPriority = 5;
        int targetProc = -1;
        for (int i = 0; i < processes.size(); i++) {
            if (processes.get(i).getPriority() < targetPriority && !processes.get(i).isDone()) {
                targetProc = i;
                targetPriority = processes.get(i).getPriority();
            }
        }
        if (targetProc == -1)
            return null;
        else
            return processes.get(targetProc);
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

        int margin = 0;
        System.out.print("\n0");
        for (int i = 0; i < timeline.size(); i++) {
            int times = ls.get(i) * 2 + timeline.get(i).getPId().length() - margin;
            for (int j = 0; j < times; j++)
                System.out.print(" ");

            int endT = timeline.get(i).getEndT();
            System.out.print(endT);

            if (endT > 9)
                margin = String.valueOf(endT).length()-1;
        }

        System.out.println("\n");
        System.out.println("PID\t\t\tWaiting Time\t\tCompletion Time");
        for (PriorityProc proc : processes)
            System.out.println(proc.getPId() + "\t\t\t" + proc.getWaitingT() + "\t\t\t\t\t" + proc.getCompletionT());
        System.out.println();

        int sumWT = 0, sumCT = 0;
        for (PriorityProc process : processes) {
            sumWT += process.getWaitingT();
            sumCT += process.getCompletionT();
        }

        System.out.printf("Average Waiting Time: %.2f%n", 1.0 * sumWT / processes.size());
        System.out.printf("Average Completion Time: %.2f%n", 1.0 * sumCT / processes.size());
    }

    public static void main(String[] args) {
        new Priority();
    }
}
