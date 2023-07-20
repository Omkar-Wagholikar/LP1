public class FCFS{
    
    int size;
    float time;
    static Chart chart;

    public static void main(String [] args){
        
        int [] pid = {1,2,3,4,5};
        float [] time = {300, 125,400,150,100};
        chart = new Chart(5, pid, time);
        chart.compute();
    }
}

class Chart{
    static float [][] chart;
    static int TW = 0, TAT;
    int size;

    Chart(int size, int[] pid, float [] time){
        this.size = size;
        chart = new float[size][3];

        for(int i=0; i<size; i++){
            chart[i][0] = pid[i];
            chart[i][1] = time[i];
        }
    }

    public void compute(){
        for(int i=0; i<size; i++){
            chart[i][2] = TW;
            TAT = TW;
            System.out.println(chart[i][0] + " - "+chart[i][1] + " - " + chart[i][2]);
            TW += chart[i][1]; 
        }
        System.out.println("TW: "+TW);
        System.out.println("TAT: "+TAT);
    }
}

class Process{
    static int pid;
    static float time;
    static int priority = 0;
    static Boolean hasPriority;
    Process(int pid, float time, int priority){
        this.pid = pid;
        this.time = time;
        this.priority = priority;
        hasPriority = true;
    }

    Process(int pid, float time){
        this.pid = pid;
        this.time = time;
        hasPriority = false;
    }

    public static void print(){
        if(hasPriority){
            System.out.print(pid + "" + time +"" +priority);
        }
        else{
            System.out.print(pid + "" + time +"" +priority);
        }
    }
}
