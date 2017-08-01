package org.lanqiao.oom;

public class StackOverFlow {
    int count;
    void stackLeak(){
        count++;
        stackLeak();
    }
    public static void main(String[] args){
        new StackOverFlow().stackLeak();
    }
}
