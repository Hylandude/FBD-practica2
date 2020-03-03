import entidades.*;
import utils.*;

public class Practica02 {
    /**
    * Metodo principal maneja las acciones en la entrada de la consola
    * @param args parametros de la consola (no usados)
    */
    public static void main(String[] args) {
        System.out.println("Inciando base de datos...");
        if(!FileActions.createFiles() || !FileActions.setTableIndex()){
            System.out.println("Error al inciar base de datos");
            return;
        }
        boolean doActions = true;
        while(doActions){
            printMenu();
            int action = getAction();
            switch (action){
                case 1:
                    handleShow();
                    break;
                case 2:
                    handleEdit();
                    System.out.println("Entrada editada");
                    break;
                case 3:
                    handleDestroy();
                    System.out.println("Entrada eliminada");
                    break;
                case 4:
                    handleCreate();
                    System.out.println("Entrada creada");
                    break;
                case 5:
                    handleDrop();
                    System.out.println("Tabla eliminada");
                    break;
                case 6:
                    System.out.println("Saliendo...");
                    handleExit();
                    doActions = false;
                    break;
                default:
                    System.out.println("Ingresa solo el numero de la acción deseada (1-4)");
            }
        }
    }

    /**
    * Imprime el menu principal
    */
    public static void printMenu(){
        System.out.println("**********MENU PRINCIPAL**********");
        System.out.println("Escribe solo el numero de la acción que deseas realizar");
        System.out.println("1.- Consultar datos");
        System.out.println("2.- Modificar una entrada");
        System.out.println("3.- Borrar una entrada");
        System.out.println("4.- Crear una entrada");
        System.out.println("5.- Borrar una tabla");
        System.out.println("6.- Salir");
    }

    /**
    * Regresa un entrero a partir de la entrada en la terminal
    * @return Un numero entero que representa la entrada de la terminal
    */
    public static int getAction(){
        try{
            int action = Integer.parseInt(System.console().readLine());
            return action;
        }catch(NumberFormatException e){
            return 0;
        }
    }

    /**
    * Realiza las operaciones necesarias para crear una entrada en una tabla
    */
    public static void handleCreate(){
        System.out.println("Selecciona la tabla en la cual quieres crear una entidad");
        System.out.println("1.- Chofer");
        System.out.println("2.- Dueño");
        System.out.println("3.- Taxi");
        int action = getAction();
        String[] properties;
        boolean validAction = false;
        String tableName = "";
        while(!validAction){
            switch (action){
                case 1:
                    tableName = "Chofer";
                    properties = Chofer.properties;
                    validAction = true;
                    Chofer chofer = new Chofer();
                    System.out.println("Creando una entrada para: "+tableName);
                    for(String p : properties){
                        if(p != "choferId"){
                            System.out.println("Ingresa un valor para: "+p);
                            boolean validValue = false;
                            while(!validValue){
                                try{
                                    String value = System.console().readLine();
                                    chofer.set(p,value);
                                    validValue = true;
                                }catch(Exception e){
                                    System.out.println("Valor invalido para: "+p+" intenta de nuevo");
                                    validValue = false;
                                }
                            }
                        }
                    }
                    FileActions.appendToTable(chofer.toString(), tableName+"Data.csv");
                    break;
                case 2:
                    tableName = "Dueño";
                    properties = Dueño.properties;
                    validAction = true;
                    Dueño dueño = new Dueño();
                    System.out.println("Creando una entrada para: "+tableName);
                    for(String p : properties){
                        if(p != "dueñoId"){
                            System.out.println("Ingresa un valor para: "+p);
                            boolean validValue = false;
                            while(!validValue){
                                try{
                                    String value = System.console().readLine();
                                    dueño.set(p,value);
                                    validValue = true;
                                }catch(Exception e){
                                    System.out.println("Valor invalido para: "+p+" intenta de nuevo");
                                    validValue = false;
                                }
                            }
                        }
                    }
                    FileActions.appendToTable(dueño.toString(), tableName+"Data.csv");
                    break;
                case 3:
                    tableName = "Taxi";
                    properties = Taxi.properties;
                    validAction = true;
                    Taxi taxi = new Taxi();
                    System.out.println("Creando una entrada para: "+tableName);
                    for(String p : properties){
                        if(p != "taxiId"){
                            System.out.println("Ingresa un valor para: "+p);
                            boolean validValue = false;
                            while(!validValue){
                                try{
                                    String value = System.console().readLine();
                                    taxi.set(p,value);
                                    validValue = true;
                                }catch(Exception e){
                                    System.out.println("Valor invalido para: "+p+" intenta de nuevo");
                                    validValue = false;
                                }
                            }
                        }
                    }
                    FileActions.appendToTable(taxi.toString(), tableName+"Data.csv");
                    break;
                default:
                    System.out.println("Ingresa solo el numero de la acción deseada (1-3)");
                    action = getAction();
            }
        }
    }

    /**
    * Realiza las operaciones necesarias para borrar una tabla entera
    */
    public static void handleDrop(){
        System.out.println("Selecciona la tabla que quieres eliminar");
        System.out.println("1.- Chofer");
        System.out.println("2.- Dueño");
        System.out.println("3.- Taxi");
        boolean validAction = false;
        int action = getAction();
        while(!validAction){
            switch(action){
                case 1:
                    FileActions.dropTable("Chofer");
                    validAction = true;
                    break;
                case 2:
                    FileActions.dropTable("Dueño");
                    validAction = true;
                    break;
                case 3:
                    FileActions.dropTable("Taxi");
                    validAction = true;
                    break;
                default:
                    System.out.println("Ingresa solo el numero de la acción deseada (1-3)");
                    action = getAction();
            }
        }
    }

    /**
    * Realiza las operaciones necesarias para mostrar una tabla
    */
    public static void handleShow(){
        System.out.println("Selecciona la tabla que quieres mostrar");
        System.out.println("1.- Chofer");
        System.out.println("2.- Dueño");
        System.out.println("3.- Taxi");
        boolean validAction = false;
        int action = getAction();
        while(!validAction){
            switch(action){
                case 1:
                    FileActions.showTable("Chofer");
                    validAction = true;
                    break;
                case 2:
                    FileActions.showTable("Dueño");
                    validAction = true;
                    break;
                case 3:
                    FileActions.showTable("Taxi");
                    validAction = true;
                    break;
                default:
                    System.out.println("Ingresa solo el numero de la acción deseada (1-3)");
                    action = getAction();
            }
        }
    }

    /**
    * Realiza las operaciones necesarias para editar una entrada en una tabla
    */
    public static void handleEdit(){
        System.out.println("Selecciona la tabla que quieres editar");
        System.out.println("1.- Chofer");
        System.out.println("2.- Dueño");
        System.out.println("3.- Taxi");
        boolean validAction = false;
        int action = getAction();
        String[] properties = {};
        String tableName = "";
        while(!validAction){
            switch(action){
                case 1:
                    tableName = "Chofer";
                    properties = Chofer.properties;
                    validAction = true;
                    break;
                case 2:
                    tableName = "Dueño";
                    properties = Dueño.properties;
                    validAction = true;
                    break;
                case 3:
                    tableName = "Taxi";
                    properties = Taxi.properties;
                    validAction = true;
                    break;
                default:
                    System.out.println("Ingresa solo el numero de la acción deseada (1-3)");
                    action = getAction();
            }
        }

        System.out.println("Ingresa el ID del elemento a editar");
        int id = getAction();
        String newData = "";
        for(String p : properties){
            if(p!=properties[0]){
                System.out.println("Ingresa el nuevo valor para: "+p);
                String value = System.console().readLine();
                newData += value+",";
            }
        }
        newData = newData.substring(0, newData.length()-1);
        FileActions.editEntry(tableName, newData, id);
    }

    /**
    * Realiza las operaciones necesarias para borrar una entrada en una tabla
    */
    public static void handleDestroy(){
        System.out.println("Selecciona la tabla en la que quieres borrar una entrada");
        System.out.println("1.- Chofer");
        System.out.println("2.- Dueño");
        System.out.println("3.- Taxi");
        boolean validAction = false;
        int action = getAction();
        String[] properties = {};
        String tableName = "";
        while(!validAction){
            switch(action){
                case 1:
                    tableName = "Chofer";
                    properties = Chofer.properties;
                    validAction = true;
                    break;
                case 2:
                    tableName = "Dueño";
                    properties = Dueño.properties;
                    validAction = true;
                    break;
                case 3:
                    tableName = "Taxi";
                    properties = Taxi.properties;
                    validAction = true;
                    break;
                default:
                    System.out.println("Ingresa solo el numero de la acción deseada (1-3)");
                    action = getAction();
            }
        }

        System.out.println("Ingresa el ID del elemento a borrar");
        int id = getAction();
        FileActions.destroyEntry(tableName, id);
    }

    /**
    * Realiza las operaciones necesarias para terminar el programa
    */
    public static void handleExit(){
        FileActions.saveIndex(Chofer.getCount(), Dueño.getCount(), Taxi.getCount());
    }
}
