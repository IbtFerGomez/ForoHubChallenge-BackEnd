package Fer.ForoHub.api.infra.tratamientoErrores;

public class RespuestaTopicoError {
    private String mensaje;

    public RespuestaTopicoError(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
