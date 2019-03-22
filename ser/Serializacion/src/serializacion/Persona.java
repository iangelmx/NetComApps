package serializacion;

public class Persona {
    private String curp, nombre;
    private String fechaNac;
    private char genero;
    private int edad;
    private double altura;
    
    Persona(String nombre,String curp, String fechaNac,char genero, int edad, double estatura){
        this.nombre=nombre;
        this.curp=curp;
        this.fechaNac=fechaNac;
        this.genero=genero;
        this.edad=edad;
        this.altura=estatura;
    }

    public boolean respirar(){
        return true;
    }
    
    public void comer(String food){
    }
    
    @Override
    public String toString(){
        return "(Name =>  " + this.nombre + "\nAge => " +this.edad+"\nBirthday => "
                +this.fechaNac+"\nGenre  => "+this.genero+"\nHeight => "+this.altura+")";
    }
}
