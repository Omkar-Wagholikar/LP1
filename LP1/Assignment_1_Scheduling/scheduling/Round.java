package LP1.Assignment_1_Scheduling.scheduling;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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

    public void schedule() {
        float new_burst = 4;
        while(new_burst > 0){
            single_iteration();
            System.out.print("========="+ new_burst + "=========\n");
            new_burst -=1;
        }
        for(Process p : scheduledProcesses){
            System.out.println(p.getProcess().toString());
        }
    }

    protected void single_iteration(){
        float new_burst=0;
        for(int i=0 ;i<all_processes.size(); i++){
            // Process process = new Process(all_processes.get(i));
            Process process = all_processes.get(i);
            System.out.println(process.getName() + "\t" + process.getBurstTime() + "\t" + (process.getBurstTime() - time_slice));
            new_burst = process.getBurstTime() - time_slice;
            process.setBurstTime(new_burst);
            
            // if(new_burst >= 0){
            //     scheduledProcesses.add(process);
            // } else {
            //     all_processes.remove(i);
            // }

            if(new_burst < 0) all_processes.remove(i);
        }
    }
}
