package Fer.ForoHub.api.tatamientoErrores;

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
