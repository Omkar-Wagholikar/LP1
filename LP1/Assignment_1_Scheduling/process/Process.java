package LP1.Assignment_1_Scheduling.process;

import java.util.HashMap;
import java.util.Map;

public class Process {
    
    String name;
    float turn_around_time, burst_time, wait_time, priority, arrival_times;

    public Process(String name, float burst_time){
        this.burst_time = burst_time;
        this.name = name;
    }
    
    public Process(String name, float burst_time, float priority){
        this.burst_time = burst_time;
        this.name = name;
        this.priority = priority;
    }
    // public Process(String name, float turn_around_time, float burst_time, float wait_time){
    //     this.burst_time = burst_time;
    //     this.name = name;
    //     this.turn_around_time = turn_around_time;
    //     this.wait_time = wait_time;
    // }

    public Process(String name, float burst_time, float priority, float arrival_times){
        this.burst_time = burst_time;
        this.arrival_times = arrival_times;
        this.name = name;
        this.priority = priority;
    }

    public String getName(){
        return name;
    }
    public float getBurstTime(){
        return burst_time;
    }
    public float getWaitTime(){
        return wait_time;
    }
    public float getTurnAroundTime(){
        return turn_around_time;
    }
    public float getPriority(){
        return priority;
    }

    public float getArrival(){
        return arrival_times;
    }
    public void setArrival(float arrival_times){
        this.arrival_times = arrival_times;
    }

    public void setPriority(float priority){
        this.priority = priority;
    }
    public void setName(String name){
        this.name = name;
    } 
    public void setBurstTime(float burst_time){
        this.burst_time = burst_time;
    }
    public void setTurnAroundTime(float turn_around_time){
        this.turn_around_time = turn_around_time;
    }
    public void setWaitTime(float wait_time){
        this.wait_time = wait_time;
    }
    public Map<String, String> getProcess(){
        Map<String, String> map = new HashMap<String,String>();
        map.put("name", name);
        map.put("Arrival_time", Float.toString(arrival_times));
        map.put("burst_time", Float.toString(burst_time));
        map.put("turn_around_time", Float.toString(turn_around_time));
        map.put("wait_time", Float.toString(wait_time));
        map.put("priority", Float.toString(priority));
        return map;
    }
}
