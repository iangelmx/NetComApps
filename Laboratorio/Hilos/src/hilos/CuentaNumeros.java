package hilos;

public class CuentaNumeros extends Thread{
    private int idHilo;
    private long timestamp;
    public CuentaNumeros(int numHilo, long timestamp){
        this.idHilo = numHilo;
        this.timestamp = timestamp;
    }
    
    @Override
    public void run(){
        for(int a = 1; a<=100; a++){
            System.out.println( "H"+this.idHilo+" (Nums): "+ a + " en: "+ ((System.currentTimeMillis()-this.timestamp)/1000) +" segs.");
            this.esperaPor(5);
        }
    }
    
    private void esperaPor(int ms){
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
    
    
}
