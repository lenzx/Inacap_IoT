package cl.Inacap.inacap_aplicacionesmoviles.modelo;

public class Categoria {
    private String nombre;
    private String descripcion;
    private int imgCategoria;

    public Categoria(String nombre, String descripcion, int imgCategoria){
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imgCategoria = imgCategoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getImgCategoria() {
        return imgCategoria;
    }

    public void setImgCategoria(int imgCategoria) {
        this.imgCategoria = imgCategoria;
    }
}
