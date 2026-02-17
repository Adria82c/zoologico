package zoo;

import zoo.model.*;
import zoo.tools.UtilArchivo;

import java.util.Map;
import java.util.Scanner;


public class Main {

    private static Scanner scan = new Scanner(System.in);
    private static Zoo zoo;
    private static boolean hayCambios = false;

    public static void main(String[] args) {
        System.out.println("--Bienvenido al sistema de gestion de Zoologicos--");
        // Zoo zoo = UtilArchivo.recuperarZoologico("Zoo_Emporda.bin");
        
        // Si no ha podido recuperar el Zoo, lo crea
        // if (zoo == null){ 
        //     zoo = crearZoo();
        
        // Mostramos los nombres de los animales del zoo
        // getAnimales(zoo);

        // Mostrar los códigos de las jaulas
        // getJaulas(zoo);

        // datosPrueba(zoo);

        // interfaz

        // gestionZoo(zoo);
        gestionZoo();
       
        // Guardar el Zoo al acabar el programa
        // UtilArchivo.guardarZoologico(zoo, "Zoo_Emporda.bin");
    }

    private static void gestionZoo(){
        boolean salir = false;
        int option;
        do{
            option = menu();
            switch (option){
                case 1 : crearJaula();
                break;
                case 2 : crearAnimal();
                break;
                case 3 : getJaulas();
                break;
                case 4: getAnimales();
                break;
                case 5: saveFile();
                break;
                case 6: openFile();
                    // Zoo newZoo = openFile(); // Asignamos el archivo abierto a un nuevo zoo
                    // if (newZoo != null){
                        // zoo = newZoo; // Sustituimos el zoo por el nuevo zoo, siempre que no hayan habido errores al abrir el archivo
                        // System.out.println("Nuevo Zoo " + zoo.getNombreZoo() + " cargado con éxito.");
                    // } else
                        // System.out.println("No pudo abrirse el archivo seleccionado.");
                    // }

                break;
                case 7 : nuevoZoo();
                break;
                case 8 : exportarZoo();
                break;
                case 9 : importarZoo();
                break;
                case 0 : salir = true;
                break;
                default : System.out.println("Opcion incorrecta");
            }

        } while(!salir);
    }

    private static void exportarZoo(){
        System.out.println( 
            """
                EXPORTAR:
                1 - EXPORTAR ANIMALES
                2 - EXPORTAR JAULAS
                0 - VOLVER
                
                Introduzca opción: 
                    """);
        int option = scan.nextInt();
        scan.nextLine();
        switch (option) {
            case 1:
                exportarAnimales();
                break;
            case 2:
                exportarJaulas();
                break;
            case 0:
                break;    
            default:
                System.out.println("Opción errónea.");
                break;
        }             
    }
    
    private static void importarZoo(){
        System.out.println( 
            """
                IMPORTAR:
                1 - IMPORTAR ANIMALES
                2 - IMPORTAR JAULAS
                0 - VOLVER
                
                Introduzca opción: 
                    """);
        int option = scan.nextInt();
        scan.nextLine();
        switch (option) {
            case 1:
                importarAnimales();
                break;
            case 2:
                importarJaulas();
                break;
            case 0:
                break;    
            default:
                System.out.println("Opción errónea.");
                break;
        }
                    
    }

    private static void exportarAnimales(){
        if (!existeZoo()){
            return;
        }

        System.out.println("Introduzca el nombre del archivo");
        String fileName = scan.nextLine();
        UtilArchivo.guardarAnimalesCSV(zoo, fileName + ".csv");
    }

    private static void exportarJaulas(){
        if (!existeZoo()){
            return;
        }

        System.out.println("Introduzca el nombre del archivo");
        String fileName = scan.nextLine();
        UtilArchivo.guardarJaulasCSV(zoo, fileName + ".csv");
    }

    private static void importarAnimales(){
        if (!existeZoo()){
            return;
        }

        System.out.println("Introduzca el nombre del archivo");
        String fileName = scan.nextLine();
        UtilArchivo.abrirAnimalesCSV(zoo, fileName + ".csv");
    }

    private static void importarJaulas(){
        if (!existeZoo()){
            return;
        }

        System.out.println("Introduzca el nombre del archivo");
        String fileName = scan.nextLine();
        UtilArchivo.abrirJaulasCSV(zoo, fileName + ".csv");
    }

    private static void nuevoZoo(){
        System.out.println("Introduzca el nombre del zoo: ");
        String nombre = scan.nextLine();
        zoo = new Zoo(nombre);
        hayCambios = false;

    }

    private static void saveFile(){
    //     String fileName = zoo.getSourceFileName();
    //     if (fileName == null || fileName.isEmpty()){
    //         System.out.println("Qué nombre quiere darle al archivo?");
    //         fileName = scan.nextLine();
    //     } else{
    //         System.out.println("Guardando el archivo con el nombre "  + fileName);
    //     }

    //     boolean success = UtilArchivo.guardarZoologico(zoo, fileName);
    //     if (success){
    //         zoo.setSourceFileName(fileName);
    //         System.out.println("Archivo " + fileName + " guardado con éxito.");
    //     } else{
    //         System.out.println("Error al guardar el archivo");
    //     }
        System.out.println("Introduzca el nombre del archivo");
        String fileName = scan.nextLine();
        UtilArchivo.guardarZoologico(zoo, fileName + ".za");

    }

    private static void openFile(){
        // System.out.println("¿Qué archivo desea abrir?: ");
        // String fileName = scan.nextLine();
        // Zoo zoo = UtilArchivo.recuperarZoologico(fileName);
        // if (zoo != null){
        //     zoo.setSourceFileName(fileName);
        //     System.out.println("Zoológico cargado con éxito desde " + fileName);
        // } else{
        //     System.out.println("No se pudo cargar el archivo " + fileName);
        // }
        // return zoo;

        if (hayCambios){
            System.out.println("Los cambios no han sido guardados. Desea continuar?¿");
            String respuesta = scan.nextLine();
            if (respuesta.equals("N")){
                return;
            } 
        }
        System.out.println("Introduzca el nombre del archivo");
        String fileName = scan.nextLine();
        zoo = UtilArchivo.recuperarZoologico(fileName + ".za");
    }

    private static void crearAnimal(){
        if (!existeZoo()){
            return;
        }
        System.out.println("Introduzca el código del Animal: ");
        String idAnimal = scan.nextLine();
        System.out.println("Introduzca el nombre del Animal: ");
        String nombreAnimal = scan.nextLine();
        System.out.println("Introduzca la especie del Animal: ");
        String especieAnimal = scan.nextLine();
        System.out.println("Es peligroso? (s/n): ");
        String peligroso = scan.nextLine();
        Boolean isPeligroso = (peligroso == "s" || peligroso == "S");
        Animal animal = new Animal(idAnimal, nombreAnimal, especieAnimal, isPeligroso);

        System.out.println("Indique el código de la jaula donde va el animal: ");
        String codigoJaula = scan.nextLine();
        Jaula jaula = zoo.getJaulaFromZoo(codigoJaula);

        try {
            zoo.addAnimal(animal, jaula);
            hayCambios = true;
        } catch (Exception e) {
            System.out.println("Error al meter el animal: " + e.getMessage());
        }

    }

    private static void crearJaula(){
        if (!existeZoo()){
            return;
        }
        
        System.out.println("Introduzca el código de la Jaula: ");
        String idJaula = scan.nextLine();
        System.out.println("Introduzca el nombre de la Jaula: ");
        String nombreJaula = scan.nextLine();
        System.out.println("Introduzca la capacidad de la Jaula (entero): ");
        int capacidadJaula = scan.nextInt();
        scan.nextLine();
        System.out.println("Introduzca el tipo de jaula: ");
        String tipoJaula = scan.nextLine();

        Jaula jaula = new Jaula(idJaula, nombreJaula, capacidadJaula, tipoJaula);
        try {
            zoo.addJaula(jaula);
            hayCambios = true;
        } catch (Exception e) {
            System.out.println("Error al crear la jaula: " + e.getMessage());
        }
    }

    private static int menu(){
        
        System.out.println( """
            1 - CREAR JAULA
            2 - CREAR ANIMAL
            3 - LISTAR JAULAS
            4 - LISTAR ANIMALES
            5 - GUARDAR ZOO
            6 - ABRIR ZOO
            7 - NUEVO ZOO
            8 - EXPORTAR CSV
            9 - IMPORTAR CSV
            0 - SALIR              
        """);
        System.out.println("Seleccione una opción: ");
        int option = scan.nextInt();
        scan.nextLine();
        return option;
    }

    private static void datosPrueba(){
         
        // Creamos un animal y una jaula
        Animal animal = new Animal("coco1", "Lagarto Guanxo", "Cocodrilo", true);        
        Jaula jaula = new Jaula("jaulaLagartos01", "Jaula Cocodrilos", 10, "reptiles");
        
        // Intentamos añadir la jaula al zoo y el animal a la jaula.
        try{
            zoo.addJaula(jaula); // Si el objeto zoo ya contiene esa jaula, el método corta (no añade Jaula) y lanza excepcion que capturamos
            zoo.addAnimal(animal, jaula); // Este método ya no se ejecuta si con el anterior saltó excepción        
        } catch(Exception e){
            System.out.println("ERROR " + e.getMessage()); // Con la excepción capturada, mostramos el mensaje lanzado de error.
        }

        // Mostramos el listado 
        mostrarListadoAnimales();      
        Map<String,Jaula> jaulas = zoo.getAllJaulasMap();
        for (Map.Entry<String, Jaula> entry : jaulas.entrySet()){
            System.out.println(entry.getKey() + " especies: " + entry.getValue().getEspecieEnjaulada());
        }
    }

    private static void getJaulas() {
        if (!existeZoo()){
            return;
        }
        System.out.println("\n=== JAULAS DEL ZOO ===");
        Map<String, Jaula> jaulas = zoo.getAllJaulasMap();
        for (Map.Entry<String, Jaula> entry: jaulas.entrySet()){
            System.out.print(entry.getValue().getNombreJaula() + " - ");
            System.out.println(entry.getKey());
        }
    }

    private static void getAnimales() {
        if (!existeZoo()){
            return;
        }
        System.out.println("\n=== NOMBRES DE LOS ANIMALES DEL ZOO ===");
        Map<String, Animal> animales = zoo.getAllAnimalsMap();
        for (Map.Entry<String, Animal> entry: animales.entrySet()){
            System.out.println(entry.getValue().getNombreAnimal());
        }
    }

    public static Zoo crearZoo(){

        boolean valido = true;
        Zoo zoo = new Zoo("Zoo Empordà");
        Jaula jaulaMonos = new Jaula("jaulaMonos01", "Jaula Monos", 3, "primates");
        Jaula jaulaTigres = new Jaula("jaulaTigre01", "Jaula Tigres",5, "felinos");
        Jaula jaulaCocodrilos = new Jaula("jaulaCocodrilos01", "Jaula Cocodrilos", 7, "reptiles");


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
        // Animal mono2 = new Animal("m002", "Floquet", "Gorila", false);
        Animal mono3 = new Animal("m003", "Abú","Chimpancé", true);
        Animal mono4 = new Animal("m004", "Chimpi","Chimpancé", true);
        // Animal mono5 = new Animal("m005", "Pancho","Chimpancé", false);

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

    public static void mostrarListadoAnimales(){ 
        Map<String,Animal> animalMap = zoo.getAllAnimalsMap();
        
        System.out.println("===LISTADO DE TODOS LOS ANIMALES de " + zoo.getNombreZoo() + "===");
        for (Map.Entry<String, Animal> entry : animalMap.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }

    public static boolean existeZoo(){
        if(zoo == null){
            System.out.println("Debe crear un zoologico nuevo o abrir uno existente");
            return false;
        }
        return true;
    }

}