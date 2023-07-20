package Assignment_1_Scheduling;
import java.util.ArrayList;

import Assignment_1_Scheduling.process.Process;
import Assignment_1_Scheduling.scheduling.FCFS;

public class Main {
    public static void main(String [] args){
        ArrayList<Process> processes = new ArrayList<Process>(); 
        long[] burstTimes = { 300 , 125 , 400 , 150 , 100 } ; 
        for( int i = 0 ; i < 5 ; i++ ) {
            Process p = new Process("P" + Integer.toString(i), burstTimes[i]);
            processes.add( p );
        }

        FCFS scheduler = new FCFS(processes);
        scheduler.schedule();
        scheduler.printScheduledSequence();
    }
}
