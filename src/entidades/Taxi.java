package entidades;

/**
* Clase que define a un taxi
*/
public class Taxi{
    private static int taxiCount;
    public static String fileName = "TaxiData.csv";
    public static String[] properties = {"taxiId", "año", "numPuertas", "numCilindros", "marca", "modelo", "tieneRefaccion"};
    private int taxiId;
    private int numPuertas;
    private int año;
    private int numCilindros;
    private String modelo;
    private String marca;
    private boolean tieneRefaccion;

    /**
    * Constructor de la clase. Unicamente define el id del taxi
    */
    public Taxi(){
        taxiCount++;
        this.taxiId = taxiCount;
    }

    /**
    * Metodo personalizado para toString regresa el taxi en un formato amigable para CSV
    */
    public String toString(){
        String prettyPrinted = "";
        for(String p : properties){
            prettyPrinted += this.get(p)+",";
        }
        prettyPrinted = prettyPrinted.substring(0, prettyPrinted.length()-1);
        return prettyPrinted;
    }

    /**
    * Regresa el valor de una propiedad en particular del taxi
    * @param property La propiedad que se requiere
    * @return Valor de la propiedad del taxi
    * @throws IllegalArgumentException Si la propiedad no esta definida para taxi
    */
    public String get(String property){
        switch (property){
            case "taxiId":
                return this.taxiId+"";
            case "numPuertas":
                return this.numPuertas+"";
            case "año":
                return this.año+"";
            case "numCilindros":
                return this.numCilindros+"";
            case "modelo":
                return this.modelo;
            case "marca":
                return this.marca;
            case "tieneRefaccion":
                return this.tieneRefaccion+"";
            default:
                throw new IllegalArgumentException(property+" is not defined in Taxi");
        }
    }

    /**
    * Define el valor de una propiedad en particular del taxi
    * @param property La propiedad que se requiere
    * @param value El valor a definir
    * @return El taxi con los datos editados
    * @throws IllegalArgumentException Si la propiedad no esta definida para taxi
    */
    public Taxi set(String property, String value){
        switch (property){
            case "numPuertas":
                this.numPuertas = Integer.parseInt(value);
                break;
            case "año":
                this.año = Integer.parseInt(value);
                break;
            case "numCilindros":
                this.numCilindros = Integer.parseInt(value);
                break;
            case "modelo":
                this.modelo = value;
                break;
            case "marca":
                this.marca = value;
                break;
            case "tieneRefaccion":
                this.tieneRefaccion = (value == "true" || value == "1" || value == "si");
                break;
            default:
                throw new IllegalArgumentException(property+" is not defined in Taxi");
        }
        return this;
    }

    /**
    * Define el indice para la tabla taxi
    * @param index el nuevo indice
    */
    public static void setTableIndex(int index){
        taxiCount = index;
    }

    /**
    * Regresa la cantidad de taxis creados
    * @return La cantidad de taxis creados
    */
    public static int getCount(){
        return taxiCount;
    }
}
