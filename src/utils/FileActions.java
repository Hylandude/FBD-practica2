package utils;
import entidades.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
* Clase que define metodos que operan sobre archivos
*/
public class FileActions{

    /**
    * Crea o verifica los archivos necesarios para la ejecución del programa
    * @return estado de la operacion
    */
    public static boolean createFiles(){
        try {
            File index = new File("tableIndex.csv");
            if (index.createNewFile()) {
                FileWriter indexWriter = new FileWriter("tableIndex.csv");
                indexWriter.write("chofer,dueño,taxi\n0,0,0");
                indexWriter.close();
                System.out.println("File created: " + index.getName());
            }else{
                System.out.println("Index file already exists.");
            }

            File chofer = new File("ChoferData.csv");
            if (chofer.createNewFile()) {
                FileWriter choferWriter = new FileWriter("ChoferData.csv");
                choferWriter.write("choferId,nombre,apellidoPaterno,apellidoMaterno,foto,email,calle,colonia,municipio,estado,numeroExterior,numeroInterior,numLicencia,fechaInicio\n");
                choferWriter.close();
                System.out.println("File created: " + chofer.getName());
            }else{
                System.out.println("ChoferData file already exists.");
            }

            File dueño = new File("DueñoData.csv");
            if (dueño.createNewFile()) {
                FileWriter dueñoWriter = new FileWriter("DueñoData.csv");
                dueñoWriter.write("dueñoId,nombre,apellidoPaterno,apellidoMaterno,foto,email,calle,colonia,municipio,estado,numeroExterior,numeroInterior,numLicencia,fechaInicio,RFC\n");
                dueñoWriter.close();
                System.out.println("File created: " + dueño.getName());
            }else{
                System.out.println("DueñoData file already exists.");
            }

            File taxi = new File("TaxiData.csv");
            if (taxi.createNewFile()) {
                FileWriter taxiWriter = new FileWriter("TaxiData.csv");
                taxiWriter.write("taxiId,año,numPuertas,numCilindros,marca,modelo,tieneRefaccion\n");
                taxiWriter.close();
                System.out.println("File created: " + taxi.getName());
            }else{
                System.out.println("TaxiData file already exists.");
            }

            return true;
        }catch (IOException e){
            e.printStackTrace();
            return false;
        }
    }
    /**
    * Define los indices para las instancias de cada clase
    * @return estado de la operacion
    */
    public static boolean setTableIndex(){
        try{
            File index = new File("tableIndex.csv");
            Scanner indexReader = new Scanner(index);
            indexReader.nextLine();
            String[] indexData = indexReader.nextLine().split(",");

            Chofer.setTableIndex(Integer.parseInt(indexData[0]));
            Dueño.setTableIndex(Integer.parseInt(indexData[1]));
            Taxi.setTableIndex(Integer.parseInt(indexData[2]));
            indexReader.close();
            return true;
        }catch(IOException e){
            e.printStackTrace();
            return false;
        }
    }

    /**
    * Agrega una linea a un archivo
    * @param data Datos a escribir
    * @param fileName Nombre del archivo donde se va a escribir
    * @return estado de la operacion
    */
    public static boolean appendToTable(String data, String fileName){
        try{
            File table = new File(fileName);
            FileWriter tableWriter = new FileWriter(table, true);
            tableWriter.write(data+"\n");
            tableWriter.close();
            return true;
        }catch(IOException e){
            e.printStackTrace();
            return false;
        }
    }

    /**
    * Elimina todas las entradas de una tabla
    * @param tableName Nombre de la tabla a elimiar
    * @return estado de la operacion
    */
    public static boolean dropTable(String tableName){
        try{
            String[] properties;
            switch(tableName){
                case "Chofer":
                    properties = Chofer.properties;
                    break;
                case "Dueño":
                    properties = Dueño.properties;
                    break;
                case "Taxi":
                    properties = Taxi.properties;
                    break;
                default:
                    throw new IOException("Invalid table name");
            }
            String header = "";
            for(String p : properties){
                header += p+",";
            }
            header = header.substring(0, header.length()-1);
            File table = new File(tableName+"Data.csv");
            FileWriter tableWriter = new FileWriter(table);
            tableWriter.write(header+"\n");
            tableWriter.close();
            return true;
        }catch(IOException e){
            e.printStackTrace();
            return false;
        }
    }

    /**
    * Muestra todas las entradas de una tabla
    * @param tableName Nombre de la tabla a mostrar
    * @return estado de la operacion
    */
    public static boolean showTable(String tableName){
        try{
            File table = new File(tableName+"Data.csv");
            Scanner tableReader = new Scanner(table);
            while (tableReader.hasNextLine()){
                System.out.println(tableReader.nextLine());
            }
            tableReader.close();
            return true;
        }catch(IOException e){
            e.printStackTrace();
            return false;
        }
    }

    /**
    * Edita una de las entradas de una tabla
    * @param tableName Nombre de la tabla a elimiar
    * @param newData Datos actualizados de la entrada
    * @param id Id del elemento a actualizar
    * @return estado de la operacion
    */
    public static boolean editEntry(String tableName, String newData, int id){
        try{
            File table = new File(tableName+"Data.csv");
            Scanner tableReader = new Scanner(table);
            String fileContent = tableReader.nextLine()+"\n";
            boolean lineFound = false;
            while (tableReader.hasNextLine()){
                String currentLine = tableReader.nextLine();
                int lineId = Integer.parseInt(currentLine.split(",")[0]);
                if(lineId != id){
                    fileContent += currentLine+"\n";
                }else{
                    lineFound = true;
                }
            }
            tableReader.close();
            if(lineFound){
                fileContent += id+","+newData+"\n";
                FileWriter tableWriter = new FileWriter(table);
                String[] fileLines = fileContent.split("\n");
                for(String l : fileLines){
                    tableWriter.write(l+"\n");
                }
                tableWriter.close();
                return true;
            }else{
                System.out.println("No se encontró una entrada con el id: "+id);
                return false;
            }

        }catch(IOException e){
            e.printStackTrace();
            return false;
        }
    }

    /**
    * Borra una de las entradas de una tabla
    * @param tableName Nombre de la tabla a elimiar
    * @param id Id del elemento a actualizar
    * @return estado de la operacion
    */
    public static boolean destroyEntry(String tableName, int id){
        try{
            File table = new File(tableName+"Data.csv");
            Scanner tableReader = new Scanner(table);
            String fileContent = tableReader.nextLine()+"\n";
            boolean lineFound = false;
            while (tableReader.hasNextLine()){
                String currentLine = tableReader.nextLine();
                int lineId = Integer.parseInt(currentLine.split(",")[0]);
                if(lineId != id){
                    fileContent += currentLine+"\n";
                }else{
                    lineFound = true;
                }
            }
            tableReader.close();
            if(lineFound){
                FileWriter tableWriter = new FileWriter(table);
                String[] fileLines = fileContent.split("\n");
                for(String l : fileLines){
                    tableWriter.write(l+"\n");
                }
                tableWriter.close();
                return true;
            }else{
                System.out.println("No se encontró una entrada con el id: "+id);
                return false;
            }

        }catch(IOException e){
            e.printStackTrace();
            return false;
        }
    }

    /**
    * Guarda los indices de las tablas en un archivo para que haya persistencia al cargar el programa de nuevo
    * @param choferIndex indice de la tabla chofer
    * @param dueñoIndex indice de la tabla dueño
    * @param taxiIndex indice de la tabla taxi
    * @return estado de la operacion
    */
    public static boolean saveIndex(int choferIndex, int dueñoIndex, int taxiIndex){
        try{
            FileWriter indexWriter = new FileWriter("tableIndex.csv");
            indexWriter.write("chofer,dueño,taxi\n"+choferIndex+","+dueñoIndex+","+taxiIndex);
            indexWriter.close();
            return true;
        }catch(IOException e){
            return false;
        }
    }
}
