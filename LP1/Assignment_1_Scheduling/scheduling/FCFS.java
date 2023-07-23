package LP1.Assignment_1_Scheduling.scheduling;
import java.util.ArrayList;

import LP1.Assignment_1_Scheduling.process.Process;
public class FCFS extends Scheduling{

    public FCFS(ArrayList<Process> all_processes){
        System.out.println("FCFS");
        this.all_processes = all_processes;
    }

    public void schedule(){
        // long totalWaitTime = 0L;
        for(Process process: all_processes){
            
            process.setWaitTime(totalWaitTime);
            process.setTurnAroundTime(totalWaitTime + process.getBurstTime());
            scheduledProcesses.add(process);
            totalWaitTime += process.getBurstTime();
            totalTurnAroundTime += process.getTurnAroundTime();
        }
    }
}
