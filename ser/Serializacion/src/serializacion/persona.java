/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serializacion;

import java.sql.Timestamp;

/**
 *
 * @author neyer
 */
public class persona {
    String curp,ultComida, nombre;
    
        Timestamp fechaNac;
        char genero;
        int edad;
       
        double estatura;
        
        
    
    
    persona(String nombre,String curp, Timestamp fechaNac,char genero, int edad, double estatura){
        this.nombre=nombre;
        this.curp=curp;
        this.fechaNac=fechaNac;
        this.genero=genero;
        this.edad=edad;
        this.estatura=estatura;
    }
    
    
    public boolean respirar(){
           return true;
    }
    public void comer(String comida){
    
        this.ultComida=comida;
    }
    @Override
    public String toString() { 
    return "[Nombre: '" + this.nombre 
         + "', Curp: '" + this.curp 
        + "', FechaNacimiento: '" + this.fechaNac 
        +"', Genero: '"+ this.genero
        +"', Edad: "+this.edad
        +"', Estatura: '"+this.estatura+"']";
    } 

    
}
