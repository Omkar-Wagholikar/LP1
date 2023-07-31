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

    // public Map<String, Float> getAverageTimes() {
    //     Map<String, Float> map = new HashMap<String, Float>();
    //     float avg_wt_tm = getAverageWaitTime();
    //     float avg_tat_tm = getAverageTurnArountTime();
    //     map.put("avg_wt_tm", avg_wt_tm);
    //     map.put("avg_tat_tm", avg_tat_tm);
    //     return map;
    // }

    void findWaitingTime(float[] wt) {
        float service_time[] = new float[all_processes.size()];
        service_time[0] = all_processes.get(0).getArrival();
        wt[0] = 0;

        // calculating waiting time
        for (int i = 1; i < all_processes.size(); i++) {
            float wasted = 0;
            service_time[i] = service_time[i - 1] + all_processes.get(i - 1).getBurstTime();
            wt[i] = service_time[i] - all_processes.get(i).getArrival();
            if (wt[i] < 0) {
                wasted = wt[i];
                wt[i] = 0;
            }
            service_time[i] = service_time[i] + wasted;
        }
    }

    void findTurnAroundTime(float wt[], float tat[]) {
        for (int i = 0; i < all_processes.size(); i++)
            tat[i] = all_processes.get(i).getBurstTime() + wt[i];
    }

    void findavgTime() {
        float wt[] = new float[all_processes.size()], tat[] = new float[all_processes.size()];
        
        findWaitingTime(wt);
        findTurnAroundTime(wt, tat);
        
        // for (float a : wt) {
        //     System.out.print(a+ " ");
        // }
        // System.out.println();
        
        // for (float a : tat) {
        //     System.out.print(a+ " ");
        // }
        // System.out.println();
        
        float total_wt = 0, total_tat = 0;
        for (int i = 0; i < all_processes.size(); i++) {
            total_wt = total_wt + wt[i];
            total_tat = total_tat + tat[i];
        }

        System.out.print("Average waiting time = "
                + (float) total_wt / (float) all_processes.size());
        System.out.print("\nAverage turn around time = "
                + (float) total_tat / (float) all_processes.size() + "\n");
    }

    public void sortByArrival() {
        Collections.sort(all_processes, new Comparator<Process>() {
            @Override
            public int compare(Process o1, Process o2) {
                return Float.compare(o1.getArrival(), o2.getArrival());
            }
        });
    }

    public void sortByArrivalAndBurst() {
        Collections.sort(all_processes, new Comparator<Process>() {
            @Override
            public int compare(Process t1, Process t2) {
                // First, compare by arrival_time (since it's constant in this case)
                int arrivalComparison = Float.compare(t1.getArrival(), t2.getArrival());
                if (arrivalComparison != 0) {
                    return arrivalComparison;
                }

                // If arrival_time is the same, compare by burst_time
                return Float.compare(t1.getBurstTime(), t2.getBurstTime());
            }
        });
    }

        public void sortByArrivalAndPriority() {
        Collections.sort(all_processes, new Comparator<Process>() {
            @Override
            public int compare(Process t1, Process t2) {
                // First, compare by arrival_time (since it's constant in this case)
                int arrivalComparison = Float.compare(t1.getArrival(), t2.getArrival());
                if (arrivalComparison != 0) {
                    return arrivalComparison;
                }

                // If arrival_time is the same, compare by burst_time
                return Float.compare(t2.getPriority(), t1.getPriority());
            }
        });
    }

    // public float getAverageWaitTime() {
    //     return totalWaitTime / scheduledProcesses.size();
    // }

    // public float getAverageTurnArountTime() {
    //     return totalTurnAroundTime / scheduledProcesses.size();
    // }

    public void printScheduledSequence() {
        for (Process process : scheduledProcesses) {
            String processMap = process.getProcess().toString();
            System.out.println(processMap);
        }
        // System.out.println("Average Wait Time: " + getAverageTimes().toString());
        findavgTime();
    }

    public void showAllProcesses() {
        for (Process process : all_processes) {
            String processMap = process.getProcess().toString();
            System.out.println(processMap);
        }
    }
}
