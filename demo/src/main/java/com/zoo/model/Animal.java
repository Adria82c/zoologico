package com.zoo.model;

public class Animal {

    private String codigoAnimal;
    private String nombreAnimal;
    private String especie;
    private boolean peligroso;

    //constructur
    public Animal(String codigoAnimal, String nombreAnimal, String especie, boolean peligroso) {
        this.codigoAnimal = codigoAnimal;
        this.nombreAnimal = nombreAnimal;
        this.especie = especie;
        this.peligroso = peligroso;
    }

    public String getCodigoAnimal() {
        return codigoAnimal;
    }

    public void setCodigoAnimal(String codigoAnimal) {
        this.codigoAnimal = codigoAnimal;
    }

    public String getNombreAnimal() {
        return nombreAnimal;
    }

    public void setNombreAnimal(String nombreAnimal) {
        this.nombreAnimal = nombreAnimal;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public boolean isPeligroso() {
        return peligroso;
    }

    public void setPeligroso(boolean peligroso) {
        this.peligroso = peligroso;
    }

    public String toString(){
        
        String peligroso = (this.isPeligroso()) ? "Es peligroso." : "No es peligroso.";
        return this.codigoAnimal + ", " + this.nombreAnimal + ", " + this.especie + ", " + peligroso + "\n";
    }
    

    


}
