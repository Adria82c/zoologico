package com.zoo.model;
import java.util.HashMap;
import java.util.Map;

public class Zoo {

    private Map<String, Animal> allAnimalsMap;
    private Map<String, Jaula> allJaulaMap;
    private String nombreZoo;

    public Zoo(String nombreZoo){
        this.nombreZoo = nombreZoo;
        allAnimalsMap = new HashMap<>();
        allJaulaMap = new HashMap<>();
    }

    public String getNombreZoo() {
        return nombreZoo;
    }

    public void setNombreZoo(String nombreZoo) {
        this.nombreZoo = nombreZoo;
    }

    public void addJaula(Jaula jaula) throws Exception {

        if(allJaulaMap.containsKey(jaula.getCodigoJaula())){
            throw new Exception("Código de jaula repetido");
        }
        allJaulaMap.put(jaula.getCodigoJaula(), jaula);
    }
    
    public void addAnimal(Animal animal, Jaula jaula) throws Exception{
        
        if (allAnimalsMap.containsKey(animal.getCodigoAnimal())){
            throw new Exception("El animal ya se encuentra en el Zoo " + nombreZoo);
        }

        if (!allJaulaMap.containsKey(jaula.getCodigoJaula())){
            throw new Exception("Esa jaula no existe en el Zoo");
        }

        if (jaula.isFull()){
            throw new Exception("La jaula supera su capacidad maxima");
        }
        // comprueba que en la jaula no hayan animales de otras especies
        //CORREGIR
        if (!jaula.listaAnimales.contains(animal.getEspecie())){
            throw new Exception("Esta jaula contiene otras especies");
        }
        //AÑADO DIRECTAMENTE LA VALIDACION YA LA HACE ADDANIMAL()
        jaula.addAnimal(animal);
        
        allAnimalsMap.put(animal.getCodigoAnimal(), animal);
    }
}
