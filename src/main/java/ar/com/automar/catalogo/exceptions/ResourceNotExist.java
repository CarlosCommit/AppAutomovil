package ar.com.automar.catalogo.exceptions;

public class ResourceNotExist extends RuntimeException {

    private String mensaje;

    public ResourceNotExist(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getMensaje() {
        return this.mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
