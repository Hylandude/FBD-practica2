package entidades;
import java.util.Calendar;

/**
* Clase abstracta que define los campos de un Asociado
* @see Chofer
* @see Dueño
*/
public abstract class Asociado extends Persona{
    protected String numLicencia;
    protected Calendar fechaInicio;
}
