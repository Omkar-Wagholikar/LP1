package LP1.Assignment_1_Scheduling.scheduling;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;

import LP1.Assignment_1_Scheduling.process.Process;

public class Round extends Scheduling {
    float time_slice = 0;
    protected ArrayList<Process> all_processes; 
    Map<String, Integer> waiting_time = new HashMap<String, Integer>();

    public Round(ArrayList<Process> all_processes, float time_slice) {
        System.out.println("ROUND");
        this.all_processes = all_processes;
        this.time_slice = time_slice;
    }

    public void schedule()
    {
        System.out.println("\nImplementing Round Robin: \n\nSCHEDULING ORDER IS AS FOLLOWS: ");

        //calculating wait time
        float waitime = 0;
        int processed = 0, sum = 0;
        float CPU_time;
        
        CPU_time = time_slice;
        System.out.print("CPU cycle: "+ CPU_time);
        
        while(processed!= all_processes.size())
        {
            for(int i = 0; i< all_processes.size(); i++)
            {
                if(all_processes.get(i).getBurstTime() > CPU_time)
                {   
                    all_processes.get(i).setBurstTime(all_processes.get(i).getBurstTime() - CPU_time);
                    waitime += CPU_time;
                    totalWaitTime += CPU_time;
                }
                else if(all_processes.get(i).getBurstTime()>0)
                {
                    waitime+= all_processes.get(i).getBurstTime();
                    sum+=waitime;
                    all_processes.get(i).setBurstTime(all_processes.get(i).getBurstTime() - CPU_time);
                    System.out.print("\nTask Completed!");
                    System.out.println(all_processes.get(i).getProcess().toString());
                    System.out.println("Turnaround time is: " + waitime);
                    totalTurnAroundTime += waitime;
                    processed++;
                }
            }
        }

        System.out.println("\nAvg wait time: "+ ((sum-waitime)/all_processes.size()));
    }
}
