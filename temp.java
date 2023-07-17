import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class FCFS1 {
    static String fileName;
    static String read;

    public static String[] splittedArray;
    public static String[] splittedArray2;
    public static String[] splittedArray3;
    public static String[] splittedArray4;

    public static int currentCpu = 0;
    public static int currentIo = 0;

    public static List<process> processes = new ArrayList<>();

    static int maksLength = 0;
    static double tat = 0;
    static double wt = 0;

    public static void main(String[] args) {
        try {
            String fileName = "/home/pict/31282/jobs.txt";
            read(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void read(String fileName) throws FileNotFoundException, IOException {
        File file = new File(fileName);
        BufferedReader buff = new BufferedReader(new FileReader(file));
        String readLine = "";

        while ((readLine = buff.readLine()) != null) {
            read = readLine;
            String[] splittedArray = read.split(":", 2);
            int pid = Integer.valueOf(splittedArray[0]); 
            System.out.println(splittedArray[1]);             
            processes.add(new process(pid, splittedArray[1]));
        }
        buff.close();

    }

    public static class process {

        public int pid;
        public final int arrivalTime = 0; // specifically at that case

        public int[] cpuBurst;
        public int[] IOBurst;

        public int lastSeenCpu;
        public int lastSeenIo;
        public int waitTime = 0;
        public int counter = 0; // ++ everytime we read, so ++ is made inside the updateWaitTime()
        public int firstAppear;

        public process(int pid, String str) {
            this.pid = pid;
            fillStr(str);
        }

        public void fillStr(String str) {
            splittedArray2 = str.split(";"); // splittedArray2 ==> (a,b) (c,d)
            System.out.println(splittedArray2);  
            for (int i = 0; i < splittedArray2.length; i++) {                         
                splittedArray3[i] = splittedArray2[i].substring(1, splittedArray2[i].length()); // ==> a,b c,d
            }
            declareArrays(); // to prevent from overflow
            for (int i = 0; i < splittedArray3.length; i++) {
                splittedArray4 = splittedArray3[i].split(","); // a b c d
                cpuBurst[i] = Integer.valueOf(splittedArray4[0]);
                IOBurst[i] = Integer.valueOf(splittedArray4[1]);
                // here, splittedArray4[0] = a and splittedArray4[1] = b
            }

        }

        public void updateWaitTime() {
            this.waitTime += currentCpu - this.lastSeenIo;
            this.counter++;
        }

        public void declareArrays() {
            cpuBurst = new int[splittedArray3.length * 2];
            IOBurst = new int[splittedArray3.length * 2];
        }

        public boolean isFinished() {
            return (IOBurst[this.counter] == -1);
        }

        public int getPid() {
            return pid;
        }

        public void setPid(int pid) {
            this.pid = pid;
        }

        public int getCpu(int i) {
            return cpuBurst[i];
        }

        public int getIo(int i) {
            return IOBurst[i];
        }

        public int getLastSeenCpu() {
            return lastSeenCpu;
        }

        public void setLastSeenCpu(int lastSeenCpu) {
            this.lastSeenCpu = lastSeenCpu;
        }

        public int getLastSeenIo() {
            return lastSeenIo;
        }

        public void setLastSeenIo(int lastSeenIo) {
            this.lastSeenIo = lastSeenIo;
        }

        public int getFirstAppear() {
            return firstAppear;
        }

        public void setFirstAppear(int firstAppear) {
            this.firstAppear = firstAppear;
        }

    }

    public static void program() { // main logic of the program
        for (int i = 0; i < maksLength(); i++) {
            for (int k = 0; k < processes.size(); k++) {

                if (processes.get(k).counter == 0) {
                    processes.get(k).setFirstAppear(currentCpu);
                }

                if (!processes.get(k).isFinished()) {
                    processes.get(k).updateWaitTime();
                    if (currentCpu > currentIo) {
                        currentCpu += processes.get(k).getCpu(i);
                        currentIo += processes.get(k).getCpu(i);
                    } else {
                        currentCpu += processes.get(k).getCpu(i);
                        currentIo += processes.get(k).getIo(i);
                    }
                    processes.get(k).setLastSeenCpu(currentCpu);
                    processes.get(k).setLastSeenIo(currentIo);
                } else {
                    continue;
                }
            }
        }
    }

    public static int maksLength() { // to find the maximum length of a line ( to determine when to finish in the
                                     // method program() )
        for (int i = 0; i < processes.size() - 1; i++) {
            maksLength = Math.max(processes.get(i).IOBurst.length, processes.get(i + 1).IOBurst.length);
        }
        return maksLength;
    }

    public static void printRes() {
        for (int i = 0; i < processes.size(); i++) {
            tat += (processes.get(i).getLastSeenCpu() - processes.get(i).arrivalTime) / (processes.size());
        }
        for (int i = 0; i < processes.size(); i++) {
            wt += processes.get(i).waitTime / (processes.size());
        }
        for (int i = 0; i < processes.size(); i++) {
            wt += ((processes.get(i).getFirstAppear() - processes.get(i).arrivalTime) / processes.size());
        }

        System.out.println("tat : " + tat);
        System.out.println("wt : " + wt);

    }

}