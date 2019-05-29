
package threadpoolejemplo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolEjemplo{
    public static void main (String args[]){
        System.out.println("Comienza la ejecución de los hilos");
        ExecutorService ex = Executors.newFixedThreadPool(10);
        Task t;
        for(int i = 0;i<200; i++){
            t = new Task("H_"+i);
            ex.execute(t);
        }
        ex.shutdown();
    }
}
