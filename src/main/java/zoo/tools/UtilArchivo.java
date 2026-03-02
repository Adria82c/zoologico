package zoo.tools;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Map;
import com.google.gson.Gson;
 
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

/*
1,jaula de monos
    m1,chita
    m2,kingkong
    m3,abu
2,jaula de tigres
    t1,tigreton
    t2,tigresa
 */

    public static boolean guardarAnimalesCSV(Zoo zoo, String fileName){
        try{
            FileWriter fw = new FileWriter(fileName);
            Map<String,Jaula> jaulasZoo = zoo.getAllJaulasMap();
            for (Map.Entry<String,Jaula> entryJaula : jaulasZoo.entrySet()){
                String codigoJaula = entryJaula.getKey();
                Jaula jaula = entryJaula.getValue();
                ArrayList<Animal> animalesJaula = (ArrayList<Animal>) jaula.getListaAnimales();
                for(Animal animal : animalesJaula){
                    String csvLine = codigoJaula + ";" + animal.getAnimalCSV();
                    fw.write(csvLine);
                }
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
            String animalLine = br.readLine();
            while(animalLine != null){
                String[] line = br.readLine().split(";");
                String codigoJaula = line[0];
                Animal animal = new Animal(line[1], line[2], line[3], Boolean.parseBoolean(line[4]));
                Jaula jaula = zoo.getJaulaFromZoo(codigoJaula);
                jaula.addAnimalToJaula(animal);
                animalLine = br.readLine();
            }    
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
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();
            while(line != null){
                String[] v = line.split(";");
                Jaula jaula = new Jaula(v[0], v[1], Integer.parseInt(v[2]), v[3]);
                zoo.addJaula(jaula);
                line = br.readLine(); // leer la siguiente línea para que el while no se quede en un bucle infinito. Si no, el while se quedaría leyendo la misma línea una y otra vez, porque no avanzaría a la siguiente línea.
            }            
            br.close(); // Cerrando el buffer se cierra el filereader
        } catch(Exception e){
            return false;
        }
        return true;
    }

    public static Zoo recuperarZoologicoJSON(String fileName){
        Zoo zoo;
        try{
            FileReader fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);
            StringBuilder sb = new StringBuilder();
            String line;
            while((line = br.readLine()) != null){
                sb.append(line);
            }
            String zooJson = sb.toString();
            Gson gson = new Gson();
            zoo = gson.fromJson(zooJson, Zoo.class);
            br.close();
            fr.close();
        } catch(Exception e){
            System.out.println("El zoo " + fileName + " no ha podido cargarse: " + e.getMessage());
            return null;
        }
        System.out.println("El zoo " + fileName + " ha sido cargado con éxito.");
        return zoo;
    }
}
