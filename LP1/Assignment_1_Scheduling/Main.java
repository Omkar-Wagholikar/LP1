package LP1.Assignment_1_Scheduling;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import LP1.Assignment_1_Scheduling.process.Process;
import LP1.Assignment_1_Scheduling.scheduling.*;

public class Main {
    public static void main(String[] args) {

        ArrayList<Process> processes = new ArrayList<Process>();

        // long[] burstTimes = { 300, 125, 40, 150, 100, 3 };
                            // p0  p1  p2  p3  p4   p5
        long[] burstTimes = { 50, 100, 50, 50, 100, 150 };
        long[] arrivalTimes = { 50, 100, 50, 50, 100, 0 };
        float[] processPriority = { 11, 2, 33, 4, 15, 56 };

        for (int i = 0; i < burstTimes.length; i++) {
            Process p = new Process("P" + Integer.toString(i), burstTimes[i], processPriority[i], arrivalTimes[i]);
            processes.add(p);
        }

        System.out.println("Hello world");

        FCFS scheduler = new FCFS(processes);
        // SJF scheduler = new SJF(processes);
        // Priority scheduler = new Priority(processes);
        // Round scheduler = new Round(processes, 50);

        scheduler.sort();
        scheduler.schedule();
        scheduler.printScheduledSequence();
    }
}
