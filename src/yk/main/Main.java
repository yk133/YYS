package yk.main;

import java.util.Timer;
import java.util.TimerTask;
import java.util.Date;

public class Main {

    public static void main(String args[]) throws Exception {
        Timer timer = new Timer();
        Task task = new Task();
        timer.schedule(task, 0, 1004);
        new Start("1", "12");
    }

}
class Task extends TimerTask {
    @Override
    public void run() {
        Com.setClose--;
        if(Com.setClose==0)
        {

        }
        System.out.println("  "+Com.setClose);
    }
}
