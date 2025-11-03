package com.zoo.tools;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.zoo.model.Zoo;

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
            return null;
        }
        return zoo;
    }
}
