package LP1.Assignment_1_Scheduling;

import java.util.ArrayList;
import java.util.Scanner;

import LP1.Assignment_1_Scheduling.process.Process;
import LP1.Assignment_1_Scheduling.scheduling.*;

public class Main {
    public static void main(String[] args) {
        ArrayList<Process> processes = new ArrayList<Process>();
        processes = getProcesses(processes);
        while (true) {
            int choice = menu();
            if (choice == 5)
                break;
            checkSchedule(processes, choice);
        }
    }

    public static Integer menu() {
        System.out.println("=============Menu=============");
        System.out.println("1. FCFS\n2. SJF\n3. Priority\n4. Round Robin\n5. New input");
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        sc.close();
        return choice;
    }

    public static ArrayList<Process> getProcesses(ArrayList<Process> processes) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of processes");
        int num = sc.nextInt();
        float burst_time, priority, arrival_times;
        while (num-- > 0) {
            System.out.print("name, burst time, priority, arrival time ");
            String name = sc.nextLine();
            System.out.print("name, burst time, priority, arrival time: ");
            burst_time = sc.nextFloat();
            priority = sc.nextFloat();
            arrival_times = sc.nextFloat();
            Process p = new Process(name, burst_time, priority, arrival_times);
            processes.add(p);
        }
        sc.close();
        return processes;
    }

    public static void checkSchedule(ArrayList<Process> processes, int choice) {
        Scanner sc = new Scanner(System.in);
        switch (choice) {
            case 1:
                FCFS fcfs = new FCFS(processes);
                fcfs.schedule();
                fcfs.printScheduledSequence();
                break;

            case 2:
                SJF sjf = new SJF(processes);
                sjf.schedule();
                sjf.printScheduledSequence();
                break;
            case 3:
                Priority pri = new Priority(processes);
                pri.schedule();
                pri.printScheduledSequence();
                break;
            case 4:
                RoundRobin rrb = new RoundRobin(processes);
                System.out.print("CPU Time: ");
                int qt = sc.nextInt();
                rrb.schedule(qt);
                rrb.printScheduledSequence();
                break;
            default:
                System.out.print("Please Enter a valid chice");
        }
        sc.close();
    }
}
