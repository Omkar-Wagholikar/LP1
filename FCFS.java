public class FCFS{
    
    int size;
    float time;
    static Chart chart;

    public static void main(String [] args){
        
        int [] pid = {1,2,3,4,5};
        float [] time = {300, 125,400,150,100};
        chart = new Chart(5, pid, time);
        chart.compute();
        System.out.println("Hello world!");
    }
}

class Chart{
    static float [][] chart;
    static int TW = 0, TAT;
    static int size;

    Chart(int size, int[] pid, float [] time){
        this.size = size;
        chart = new float[size][2];

        for(int i=0; i<size; i++){
            chart[i][0] = pid[i];
            chart[i][1] = time[i];
        }
    }

    public static void compute(){
        for(int i=0; i<size; i++){
            System.out.println(chart[i][0] + " : "+chart[i][1]);

            TAT = TW;
            TW += chart[i][1]; 
        }
        System.out.println(TAT+" "+TW);
    }
}

class ChartOps{

}
