package com.zoo.model;
import java.util.List;
import java.io.Serializable;
import java.util.ArrayList;

public class Jaula implements Serializable {

    private String codigoJaula;
    private String nombreJaula;
    private int capacidadJaula;
    private String tipoJaula;
    private List<Animal> listaAnimales;
    
    //constructur
    public Jaula(String codigoJaula, String nombreJaula, int capacidadJaula, String tipoJaula){
        this.codigoJaula = codigoJaula;
        this.nombreJaula = nombreJaula;
        this.capacidadJaula = capacidadJaula;
        this.tipoJaula = tipoJaula;
        this.listaAnimales = new ArrayList<>();
    }

    //getters i setters
    public String getCodigoJaula(){
        return codigoJaula;
    }

    public int getCapacidadJaula(){
        return capacidadJaula;
    }


    public String getNombreJaula() {
        return nombreJaula;
    }

    public void setNombreJaula(String nombreJaula) {
        this.nombreJaula = nombreJaula;
    }

    public String getTipoJaula(){
        return tipoJaula;
    }

    public void setCodigoJaula(String codigoJaula){
        this.codigoJaula = codigoJaula;
    }

    public void setCapacidadJaula(int capacidadJaula){
        this.capacidadJaula = capacidadJaula;
    }

    public void setTipoJaula(String tipoJaula){
        this.tipoJaula = tipoJaula;
    }

    public List<Animal> getListaAnimales(){
        return listaAnimales;
    }
    
    //Override
    public String toString(){
        String result = this.nombreJaula + ":\n"
            + "código: " + this.codigoJaula + "\t"
            + ", capacidad: " + this.capacidadJaula + "\t"
            + ", tipo jaula: " + this.tipoJaula + "\t";
        if (this.listaAnimales.isEmpty()){
            result += "\nLa jaula está vacía\n";
        }else{
            result += " contiene: \n";
            for (Animal animal: this.listaAnimales){
                result += animal + "\n";
            }
        }
        return result;
    }

    // Otros métodos
    public void addAnimalToJaula(Animal animal) throws Exception {
        listaAnimales.add(animal);
    }

    public void deleteAnimalToJaula(Animal animal){
        listaAnimales.remove(animal);
    }

    public boolean isFull(){
        return (listaAnimales.size() == capacidadJaula);

        // if(listaAnimales.size() == capacidadJaula){
        //     return true;
        // } else{
        //     return false;
        // }
    }

    /*
     * Devuelve las especies de los animales de la lista
     */
    public String getEspecieEnjaulada(){   
        StringBuilder especies = new StringBuilder();
        if (this.listaAnimales.isEmpty()){
            especies.append("La jaula está vacía");
        }else{
            especies.append(listaAnimales.get(0).getEspecie());
        }
        return especies.toString();
    }
}
