package uniandes.edu.co.demo.modelo;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.ToString;

@Document(collection="bares")
@ToString
public class Bar {
    @Id
    private int id;
    private String nombre;
    private String ciudad;
    private String presupuesto;
    private int cant_sedes;
    private List<Bebida> oferta_bebidas;
    public Bar(int id, String nombre, String ciudad, String presupuesto, int cant_sedes) {
        this.id = id;
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.cant_sedes = cant_sedes;
        this.presupuesto = presupuesto;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getCiudad() {
        return ciudad;
    }
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
    public String getPresupuesto() {
        return presupuesto;
    }
    public void setPresupuesto(String presupuesto) {
        this.presupuesto = presupuesto;
    }
    public int getCant_sedes() {
        return cant_sedes;
    }
    public void setCant_sedes(int cant_sedes) {
        this.cant_sedes = cant_sedes;
    }
    public List<Bebida> getOferta_bebidas() {
        return oferta_bebidas;
    }
    public void setOferta_bebidas(List<Bebida> oferta_bebidas) {
        this.oferta_bebidas = oferta_bebidas;
    }
}
