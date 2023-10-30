package cl.Inacap.inacap_aplicacionesmoviles.modelo;

import android.widget.EditText;

public class Usuario {
    private String id;
    private String nombre;
    private String apellido;
    private String rut;
    private String correo;

    public Usuario(String nombre, String apellido, String rut, String correo) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.rut = rut;
        this.correo = correo;
    }

    public Usuario() {
    }

    public Usuario(String id, String nombre, String apellido, String rut, String correo) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.rut = rut;
        this.correo = correo;
    }

    public Usuario(EditText nombre, EditText apellido, EditText rut, EditText correo) {
        this.nombre = nombre.getText().toString();
        this.apellido = apellido.getText().toString();
        this.rut = rut.getText().toString();
        this.correo = correo.getText().toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}
