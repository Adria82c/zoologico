package zoo.tools;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Map;


import zoo.model.Zoo;
import zoo.model.Animal;
import zoo.model.Jaula;

public class UtilArchivo {
    
    public static boolean guardarZoologico(Zoo zoo, String fileName){
        
        try{
            FileOutputStream fos = new FileOutputStream(fileName);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(zoo);
            oos.close();
        } catch(Exception e){
            return false;
        }
        return true;
    }


    public static Zoo recuperarZoologico(String fileName){
        Zoo zoo;
        try{
            FileInputStream fis = new FileInputStream(fileName);
            ObjectInputStream ois = new ObjectInputStream(fis);
            zoo = (Zoo) ois.readObject();    
            ois.close();
        } catch(Exception e){
            System.out.println("El zoo " + fileName + " no ha podido cargarse: " + e.getMessage());
            return null;
        }
        System.out.println("El zoo " + fileName + " ha sido cargado con éxito.");
        return zoo;
    }

    public static boolean guardarAnimalesCSV(Zoo zoo, String fileName){
        try{
            FileWriter fw = new FileWriter(fileName);
            Map <String,Animal> animales = zoo.getAllAnimalsMap();
            for (Animal animal : animales.values()){
                fw.write(animal.getAnimalCSV());
            }
            fw.close();
        } catch(Exception e){
            return false;
        }
        return true;
    }
    public static boolean guardarJaulasCSV(Zoo zoo, String fileName){
         try{
            FileWriter fw = new FileWriter(fileName);
            Map <String,Jaula> jaulas = zoo.getAllJaulasMap();
            for (Jaula jaula: jaulas.values()){
                fw.write(jaula.getJaulaCSV());
            }
            fw.close();
        } catch(Exception e){
            return false;
        }
        return true;
    }

    public static boolean abrirAnimalesCSV(Zoo zoo, String fileName){
        try{
            FileReader fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);
            Map<String,Animal> animales = zoo.getAllAnimalsMap();
            while(br.readLine() != null){
                String[] line = br.readLine().split(";");
                for (String codigoAnimal: animales.keySet()){ // 
                    boolean codigoRepetido = codigoAnimal.equals(line[0]);
                    if (codigoRepetido){
                        System.out.println("Animal " + line[1] + " no añadido. Código de animal " + line[0] + " repetido.");
                        break; // no return false porque queremos seguir leyendo el resto de animales, aunque este no se añada.
                    }
                }
                Animal animal = new Animal(line[0], line[1], line[2], Boolean.parseBoolean(line[3]));
                animales.put(line[0], animal);
                System.out.println("Animal " + animal.getNombreAnimal() + " añadido.");
            }
            
            br.readLine();
            br.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }
    public static boolean abrirJaulasCSV(Zoo zoo, String fileName){
        try{
            FileReader fr = new FileReader(fileName);
            Map <String,Jaula> jaulas = zoo.getAllJaulasMap();
            BufferedReader br = new BufferedReader(fr);
            // FALTA LEER LAS JAULAS. si cada jaula tiene una lista de animales, ¿cómo sabemos qué animales están en cada jaula? ¿Habría que añadir el código de la jaula a cada animal en el CSV de animales?
            for (String line = br.readLine(); line != null; line = br.readLine()){
                String[] lineSplit = line.split(";");
                Jaula jaula = new Jaula(lineSplit[0], lineSplit[1], Integer.parseInt(lineSplit[2]), lineSplit[3]);
                // verificamos si la nueva jaula está presente en el mapa de jaulas del zoo, si es así, no la añadimos y mostramos un mensaje de error, si no, la añadimos al mapa de jaulas del zoo.
                if (jaulas.containsKey(lineSplit[0])){
                    System.out.println("Jaula " + lineSplit[1] + " no añadida. Código de jaula " + lineSplit[0] + " repetido.");
                    continue; // no return false porque queremos seguir leyendo el resto de jaulas, aunque esta no se añada.
                }
                jaulas.put(lineSplit[0], jaula); 
                System.out.println("Jaula " + jaula.getNombreJaula() + " añadida.");
            }
            br.close();
            fr.close();
        } catch(Exception e){
            return false;
        }
        return true;
    }
}
