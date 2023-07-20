package Assignment_1_Scheduling.scheduling;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Assignment_1_Scheduling.process.Process;

public class FCFS {
    protected ArrayList<Process> all_processes; 
    protected ArrayList<Process> scheduledProcesses = new ArrayList<>();
    protected long totalWaitTime = 0L;
    protected long totalTurnAroundTime = 0L;

    public FCFS(ArrayList<Process> all_processes){
        this.all_processes = all_processes;
    }

    public void schedule(){
        // long totalWaitTime = 0L;
        for(Process process: all_processes){
            
            process.setWaitTime(totalWaitTime);
            process.setTurnAroundTime(totalWaitTime + process.getBurstTime());
            scheduledProcesses.add(process);
            totalWaitTime += process.getBurstTime();
        }
    }
    public Map<String, Float> getAverageTimes(){
        Map<String, Float> map = new HashMap<String, Float>();
        float avg_wt_tm = getAverageWaitTime();
        float avg_tat_tm = getAverageTurnArountTime();
        map.put("avg_wt_tm", avg_wt_tm);
        map.put("avg_tat_tm", avg_tat_tm);
        return map;
    }

    public float getAverageWaitTime(){
        return totalWaitTime / scheduledProcesses.size();
    }
    public float getAverageTurnArountTime(){
        return totalTurnAroundTime / scheduledProcesses.size();
    }
    public void printScheduledSequence(){
        for(Process process : scheduledProcesses){
            String processMap = process.getProcess().toString();
            System.out.println(processMap);
        }
    }
}
