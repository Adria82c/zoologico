package com.zoo.model;
import java.util.List;
import java.util.ArrayList;

public class Jaula {

    private String codigoJaula;
    private int capacidadJaula;
    private String tipoJaula;
    private List<Animal> listaAnimales;
    
    //constructur
    public Jaula(String codigoJaula, int capacidadJaula, String tipoJaula){
        this.codigoJaula = codigoJaula;
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

    // Override toString()
    public String toString(){
        return  this.codigoJaula + ", capacidad: " + this.capacidadJaula + ", tipo jaula: " + this.tipoJaula + 
                " contiene: \n"
                + this.getListaAnimales();
    }

    // Otros métodos
    public void addAnimal(Animal animal) throws Exception {
        listaAnimales.add(animal);
    }

    public void deleteAnimal(Animal animal){
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
     * CORREGIR
     */
    public String getEspecieEnjaulada(){
        
        StringBuilder especies = new StringBuilder();
        for (Animal animal : listaAnimales){
            especies.append(animal.getEspecie());
        }
        return especies.toString();
    }

    


    


}
