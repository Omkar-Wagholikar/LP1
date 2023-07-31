package LP1.Assignment_1_Scheduling.scheduling;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import LP1.Assignment_1_Scheduling.process.Process;

public class Priority extends Scheduling {

    public Priority(ArrayList<Process> all_processes) {
        System.out.println("Priority");
        this.all_processes = all_processes;
    }

    public void schedule() {
        sortByArrivalAndPriority();
        scheduledProcesses = all_processes;
        // Collections.sort(all_processes, new Comparator<Process>() {
        //     @Override
        //     public int compare(Process o1, Process o2) {
        //         return Float.compare(o1.getPriority(), o2.getPriority());
        //     }
        // });
        for(Process process: scheduledProcesses){
            process.setWaitTime(totalWaitTime);
            process.setTurnAroundTime(totalWaitTime + process.getBurstTime());
            totalWaitTime += process.getBurstTime();
        }
    }
}
