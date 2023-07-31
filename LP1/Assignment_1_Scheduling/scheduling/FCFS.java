package LP1.Assignment_1_Scheduling.scheduling;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import LP1.Assignment_1_Scheduling.process.Process;

public class FCFS extends Scheduling {

    public FCFS(ArrayList<Process> all_processes) {
        System.out.println("FCFS");
        this.all_processes = all_processes;
    }

    

    public void schedule() {
        sortByArrival();

        for (Process process : all_processes) {
            process.setWaitTime(totalWaitTime);
            process.setTurnAroundTime(totalWaitTime + process.getBurstTime());
            scheduledProcesses.add(process);
            totalWaitTime += process.getBurstTime();
            totalTurnAroundTime += process.getBurstTime() + process.getArrival();
        }
    }
}
