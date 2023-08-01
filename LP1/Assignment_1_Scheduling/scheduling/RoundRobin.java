package LP1.Assignment_1_Scheduling.scheduling;
import LP1.Assignment_1_Scheduling.process.Process;
import java.util.*;

public class RoundRobin extends Scheduling{
    public RoundRobin(ArrayList<Process> all_processes) {
        System.out.println("RoundRobin");
        this.all_processes = all_processes;
    }
    public void schedule(int qt) {
        float  bt[], wt[], tat[], rem_bt[], temp, sq = 0;
        int n, i, count = 0;

        float awt = 0, atat = 0;
        bt = new float[all_processes.size()];
        wt = new float[all_processes.size()];
        tat = new float[all_processes.size()];
        rem_bt = new float[all_processes.size()];
        // System.out.print("Enter the number of process (maximum 10) = ");
        n = all_processes.size();
        System.out.print("Enter the burst time of the process\n");
        for (i = 0; i < n; i++) {
            // System.out.print("P" + i + " = ");
            bt[i] = all_processes.get(i).getBurstTime();
            rem_bt[i] = bt[i];
        }
        // System.out.print("Enter the quantum time: ");
        // qt = s.nextInt();
        while (true) {
            for (i = 0, count = 0; i < n; i++) {
                temp = qt;
                if (rem_bt[i] == 0) {
                    count++;
                    continue;
                }
                if (rem_bt[i] > qt)
                    rem_bt[i] = rem_bt[i] - qt;
                else if (rem_bt[i] >= 0) {
                    temp = rem_bt[i];
                    rem_bt[i] = 0;
                }
                sq = sq + temp;
                tat[i] = sq;
            }
            if (n == count)
                break;
        }
        System.out.print("--------------------------------------------------------------------------------");
        System.out.print("\nProcess\t      Burst Time\t       Turnaround Time\t          Waiting Time\n");
        System.out.print("--------------------------------------------------------------------------------");
        for (i = 0; i < n; i++) {
            wt[i] = tat[i] - bt[i];
            awt = awt + wt[i];
            atat = atat + tat[i];
            System.out.print("\n " + (i + 1) + "\t " + bt[i] + "\t\t " + tat[i] + "\t\t " + wt[i] + "\n");
        }
        awt = awt / n;
        atat = atat / n;
        System.out.println("\nAverage waiting Time = " + awt + "\n");
        System.out.println("Average turnaround time = " + atat);
    }
}