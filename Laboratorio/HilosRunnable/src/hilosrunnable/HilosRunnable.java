package hilosrunnable;

public class HilosRunnable implements Runnable{
    
    private Alfabeto hilo1;
    private Numeros hilo2;
    
    public HilosRunnable( Alfabeto h1, Numeros h2 ){
        if (h1!= null)
            this.hilo1 = h1;
        if(h2 != null)
            this.hilo2 = h2;
        
    }
    
    public static void main(String[] args){
        long now = System.currentTimeMillis();
        Alfabeto alfa = new Alfabeto(1, now);
        Numeros numb = new Numeros(2, now);
        
        Runnable process01 = new HilosRunnable(alfa, null);
        Runnable process02 = new HilosRunnable(null, numb);
        
        new Thread(process01).start();
        new Thread(process02).start();
    }
    
    @Override
    public void run(){
        if(hilo1 != null)
            this.hilo1.imprimeAlfabeto();
        if(hilo2 != null)
            this.hilo2.imprimeNumeros();
    }
    
}
