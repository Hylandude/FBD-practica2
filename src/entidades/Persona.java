package entidades;


/**
* Clase que define los campos de una persona
* @see Asociado
*/
public abstract class Persona{
    protected String nombre;
    protected String apellidoMaterno;
    protected String apellidoPaterno;
    protected String foto; //se almacena un url a la foto
    protected String email;
    protected String calle;
    protected String colonia;
    protected String municipio;
    protected String estado;
    protected int numeroExterior;
    protected int numeroInterior;
}
