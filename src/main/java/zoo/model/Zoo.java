package zoo.model;
import java.util.HashMap;
import java.util.Map;
import java.io.Serializable;


public class Zoo implements Serializable { //implementamos esta interfaz para poder serializar, guardar objetos en binario

    private Map<String, Animal> allAnimalsMap;
    private Map<String, Jaula> allJaulasMap;
    private String nombreZoo;
    private String sourceFileName = null;

    public Zoo(String nombreZoo){
        this.nombreZoo = nombreZoo;
        allAnimalsMap = new HashMap<>();
        allJaulasMap = new HashMap<>();
        this.sourceFileName = "";
    }

    public String getSourceFileName(){
        return sourceFileName;
    }

    public void setSourceFileName(String sourceFileName){
        this.sourceFileName = sourceFileName;
    }

    public String getNombreZoo() {
        return nombreZoo;
    }

    public void setNombreZoo(String nombreZoo) {
        this.nombreZoo = nombreZoo;
    }

    public Map<String,Animal> getAllAnimalsMap(){
        return allAnimalsMap;
    }

    public Map<String, Jaula> getAllJaulasMap(){
        return allJaulasMap;
    }

    public Jaula getJaulaFromZoo(String codigoJaula){
        return allJaulasMap.get(codigoJaula);
    }
    // Override toString()
    public String toString(){ 
        return this.nombreZoo + "\n" 
        + ", --- LISTADO DE JAULAS ---\n"
        + showJaulas();
    }

    // Otros métodos

    public void addJaula(Jaula jaula) throws Exception {

        if(allJaulasMap.containsKey(jaula.getCodigoJaula())){
            throw new Exception("Código de jaula repetido");
        }
        allJaulasMap.put(jaula.getCodigoJaula(), jaula);
    }
    
    public void addAnimal(Animal animal, Jaula jaula) throws Exception{
        
        if (allAnimalsMap.containsKey(animal.getCodigoAnimal())){
            throw new Exception("El animal ya se encuentra en el Zoo " + nombreZoo);
        }

        if (!allJaulasMap.containsKey(jaula.getCodigoJaula())){
            throw new Exception("Esa jaula no existe en el Zoo");
        }

        if (jaula.isFull()){
            throw new Exception("La jaula supera su capacidad maxima");
        }

        if (!jaula.getListaAnimales().isEmpty()){
            if(!jaula.getListaAnimales().get(0).getEspecie().equals(animal.getEspecie())){
                throw new Exception("El animal es de otra especie de los de la jaula");
            }
        }
        jaula.addAnimalToJaula(animal);
        
        allAnimalsMap.put(animal.getCodigoAnimal(), animal);
    }

    // public List<Jaula> showJaulas(){ 
    //     List<Jaula> list = new ArrayList<>();
    //     for (Map.Entry<String,Jaula> entry : this.allJaulasMap.entrySet()){
    //         Jaula jaula = entry.getValue();
    //         list.add(jaula);
    //     }
    //     return list;
    // }

    public String showJaulas(){
        String result = "";
        for (Map.Entry<String, Jaula> entry: this.allJaulasMap.entrySet()){
            Jaula jaula = entry.getValue();
            result += jaula + "\n"; // al sacar el objeto busca el toString() de jaula y devuelve cadena
        }
        return result;
    }
}
