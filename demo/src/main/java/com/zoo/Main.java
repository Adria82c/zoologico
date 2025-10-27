package com.zoo;
import com.zoo.model.*;
import java.util.Map;


public class Main {
    public static void main(String[] args) {
        System.out.println("--Bienvenido al Zoo--");
        Zoo zoo = crearZoo();
        mostrarListadoAnimales(zoo);
    }

    public static Zoo crearZoo(){

        boolean valido = true;
        Zoo zoo = new Zoo("Zoo Empordà");
        Jaula jaulaMonos = new Jaula("jMonos01", 3, "primates");
        Jaula jaulaTigres = new Jaula("jaulaTigre01", 5, "felinos");
        Jaula jaulaCocodrilos = new Jaula("jaulaCocodrilos01", 7, "reptiles");


        try{
            zoo.addJaula(jaulaMonos);
            zoo.addJaula(jaulaTigres);
            zoo.addJaula(jaulaCocodrilos);
            // zoo.addJaula(jaulaTigres); // No puedes meter la jaula dos veces (tiene el mismo código)
        }catch (Exception e){
            System.out.println("Error al añadir la jaula: " + e.getMessage());
            valido = false;

        }

        Animal mono1 = new Animal("m001", "Chita", "Chimpancé", false);
        Animal mono2 = new Animal("m002", "Floquet", "Gorila", false);
        Animal mono3 = new Animal("m003", "Abú","Chimpancé", true);
        Animal mono4 = new Animal("m004", "Chimpi","Chimpancé", true);
        Animal mono5 = new Animal("m005", "Pancho","Chimpancé", false);

        try{
            zoo.addAnimal(mono1, jaulaMonos);
            // zoo.addAnimal(mono1, jaulaMonos);
            zoo.addAnimal(mono3, jaulaMonos);
            // zoo.addAnimal(mono2, jaulaMonos);
            zoo.addAnimal(mono4, jaulaMonos);
            // zoo.addAnimal(mono5, jaulaMonos); // Como mucho 3 monos por jaula hemos dicho...
        } catch(Exception e){
            System.out.println("Error al añadir el animal: " + e.getMessage());
            valido = false;
        }

        if (valido){
            System.out.println("El zoo " + zoo.getNombreZoo() +" fue creado correctamente.");
            System.out.println(zoo); //imprimirá el objeto zoo (buscará toString() de zoo)

        }
        return zoo; 
    }

    public static void mostrarListadoAnimales(Zoo zoo){ //CORREGIR: PONDRIA ESTE METODO EN CLASE ZOO. QUÉ PASARIA CON EL STATIC, COMO LO LLAMAMOS?
        Map<String,Animal> animalMap = zoo.getAllAnimalsMap();
        
        System.out.println("--- LISTADO DE TODOS LOS ANIMALES de " + zoo.getNombreZoo() + " ---");
        for (Map.Entry<String, Animal> entry : animalMap.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
}