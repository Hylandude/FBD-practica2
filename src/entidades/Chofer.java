package entidades;
import java.util.Calendar;

/**
* Clase que define a un chofer
*/
public class Chofer extends Asociado{
    private static int choferCount;
    public static String fileName = "ChoferData.csv";
    public static String[] properties = {"choferId", "nombre", "apellidoPaterno", "apellidoMaterno", "foto", "email", "calle",
    "colonia", "municipio", "estado", "numeroExterior", "numeroInterior", "numLicencia", "fechaInicio"};
    private int choferId;

    /**
    * Constructor de la clase. Unicamente define el id del chofer
    */
    public Chofer(){
        choferCount++;
        this.choferId = choferCount;
    }

    /**
    * Metodo personalizado para toString regresa el chofer en un formato amigable para CSV
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
    * Regresa el valor de una propiedad en particular del chofer
    * @param property La propiedad que se requiere
    * @return Valor de la propiedad del chofer
    * @throws IllegalArgumentException Si la propiedad no esta definida para chofer
    */
    public String get(String property){
        switch (property){
            case "choferId":
                return this.choferId+"";
            case "nombre":
                return this.nombre;
            case "apellidoMaterno":
                return this.apellidoMaterno;
            case "apellidoPaterno":
                return this.apellidoPaterno;
            case "foto":
                return this.foto;
            case "email":
                return this.email;
            case "calle":
                return this.calle;
            case "colonia":
                return this.colonia;
            case "municipio":
                return this.municipio;
            case "estado":
                return this.estado;
            case "numeroExterior":
                return this.numeroExterior+"";
            case "numeroInterior":
                return this.numeroInterior+"";
            case "numLicencia":
                return this.numLicencia;
            case "fechaInicio":
                return this.fechaInicio.get(fechaInicio.YEAR)+"-"+this.fechaInicio.get(fechaInicio.MONTH)+"-"+this.fechaInicio.get(fechaInicio.DAY_OF_MONTH);
            default:
                throw new IllegalArgumentException(property+" is not defined in Chofer");
        }
    }

    /**
    * Define el valor de una propiedad en particular del chofer
    * @param property La propiedad que se requiere
    * @param value El valor a definir
    * @return El chofer con los datos editados
    * @throws IllegalArgumentException Si la propiedad no esta definida para chofer
    */
    public Chofer set(String property, String value){
        switch (property){
            case "nombre":
                this.nombre = value;
                break;
            case "apellidoMaterno":
                this.apellidoMaterno = value;
                break;
            case "apellidoPaterno":
                this.apellidoPaterno = value;
                break;
            case "foto":
                this.foto = value;
                break;
            case "email":
                this.email = value;
                break;
            case "calle":
                this.calle = value;
                break;
            case "colonia":
                this.colonia = value;
                break;
            case "municipio":
                this.municipio = value;
                break;
            case "estado":
                this.estado = value;
                break;
            case "numeroExterior":
                this.numeroExterior = Integer.parseInt(value);
                break;
            case "numeroInterior":
                this.numeroInterior = Integer.parseInt(value);
                break;
            case "numLicencia":
                this.numLicencia = value;
                break;
            case "fechaInicio":
                String[] dateData = value.split("-");
                Calendar newDate = Calendar.getInstance();
                newDate.clear();
                newDate.set(Integer.parseInt(dateData[0]), Integer.parseInt(dateData[1]), Integer.parseInt(dateData[2]));
                this.fechaInicio = newDate;
                break;
            default:
                throw new IllegalArgumentException(property+" is not defined in Chofer");
        }
        return this;
    }

    /**
    * Define el indice para la tabla chofer
    * @param index el nuevo indice
    */
    public static void setTableIndex(int index){
        choferCount = index;
    }

    /**
    * Regresa la cantidad de choferes creados
    * @return La cantidad de choferes creados
    */
    public static int getCount(){
        return choferCount;
    }
}
