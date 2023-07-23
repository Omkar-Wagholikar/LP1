package LP1.Assignment_1_Scheduling.scheduling;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import LP1.Assignment_1_Scheduling.process.Process;

public class SJF extends Scheduling{

    public SJF(ArrayList<Process> all_processes) {
        System.out.println("SJF");
        this.all_processes = all_processes;
    }

    public void schedule() {
        scheduledProcesses = all_processes;
        Collections.sort(all_processes, new Comparator<Process>() {
            @Override
            public int compare(Process o1, Process o2) {
                return Float.compare(o1.getBurstTime(), o2.getBurstTime());
            }
        });
        for(Process process: scheduledProcesses){
            process.setWaitTime(totalWaitTime);
            process.setTurnAroundTime(totalWaitTime + process.getBurstTime());
            totalWaitTime += process.getBurstTime();
        }
    }
}
