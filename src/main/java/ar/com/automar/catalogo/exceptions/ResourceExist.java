package ar.com.automar.catalogo.exceptions;


public class ResourceExist extends RuntimeException{

    private String mensaje; 
    
    public ResourceExist(String mensaje)
    {
        this.mensaje = mensaje; 
    }

    public String getMensaje()
    {
        return this.mensaje; 
    }

    public void SetMensaje(String mensaje)
    {
        this.mensaje = mensaje; 
    }

}
