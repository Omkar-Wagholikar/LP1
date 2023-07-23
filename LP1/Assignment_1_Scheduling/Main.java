package LP1.Assignment_1_Scheduling;

import java.util.ArrayList;

import LP1.Assignment_1_Scheduling.process.Process;
import LP1.Assignment_1_Scheduling.scheduling.*;

public class Main {
    public static void main(String [] args){
        
        ArrayList<Process> processes = new ArrayList<Process>(); 
        long[] burstTimes = { 300 , 125 , 400 , 150 , 100, 3 } ;
        float[] processPriority = { 11 , 2 , 33 , 4 , 15 , 56 } ;
        for( int i = 0 ; i < burstTimes.length ; i++ ) {
            Process p = new Process("P" + Integer.toString(i), burstTimes[i], processPriority[i]);
            processes.add( p );
        }
        System.out.println("Hello world");
        FCFS scheduler = new FCFS(processes);
        // SJF scheduler = new SJF(processes);
        // Priority scheduler = new Priority(processes);
        scheduler.schedule();
        scheduler.printScheduledSequence();
    }
}
