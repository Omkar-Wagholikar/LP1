package LP1.Assignment_1_Scheduling.scheduling;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.lang.Float;

import LP1.Assignment_1_Scheduling.process.Process;

public class Scheduling {
    protected ArrayList<Process> all_processes; 
    protected ArrayList<Process> scheduledProcesses = new ArrayList<>();
    protected long totalWaitTime = 0L;
    protected long totalTurnAroundTime = 0L;

    

    public Map<String, Float> getAverageTimes(){
        Map<String, Float> map = new HashMap<String, Float>();
        float avg_wt_tm = getAverageWaitTime();
        float avg_tat_tm = getAverageTurnArountTime();
        map.put("avg_wt_tm", avg_wt_tm);
        map.put("avg_tat_tm", avg_tat_tm);
        return map;
    }

    public void sort() {
        Collections.sort(all_processes, new Comparator<Process>() {
            @Override
            public int compare(Process o1, Process o2) {
                return Float.compare(o1.getArrival(), o2.getArrival());
            }
        });
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
        System.out.println("Average Wait Time: " + getAverageTimes().toString());
    }
}
