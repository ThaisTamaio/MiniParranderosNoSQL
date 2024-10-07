package uniandes.edu.co.demo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;

import uniandes.edu.co.demo.modelo.Bar;
import uniandes.edu.co.demo.modelo.Bebida;

public interface BarRepository extends MongoRepository<Bar, Integer> {

    // Consultar todos los bares excluyendo la lista de bebidas para mejorar el rendimiento
    @Query(value = "{}", fields = "{ 'oferta_bebidas': 0 }")
    List<Bar> buscarTodosLosBares();

    // Consultar bar por su ID
    @Query("{_id: ?0}")
    List<Bar> buscarPorId(int id);

    // Crear un nuevo bar
    @Query("{ $insert: { _id: ?0, nombre: ?1, ciudad: ?2, presupuesto: ?3, cant_sedes: ?4, oferta_bebidas: ?5 } }")
    void insertarBar(int id, String nombre, String ciudad, String presupuesto, int cant_sedes, List<Bebida> oferta_bebidas);

    // Actualizar un bar por su ID
    @Query("{ _id: ?0 }")
    @Update("{ $set: { nombre: ?1, ciudad: ?2, presupuesto: ?3, cant_sedes: ?4, oferta_bebidas: ?5 }}")
    void actualizarBar(int id, String nombre, String ciudad, String presupuesto, int cant_sedes, List<Bebida> oferta_bebidas);

    // Eliminar un bar por su ID
    @Query(value = "{_id: ?0}", delete = true)
    void eliminarBarPorId(int id);

    // Obtener todas las bebidas de un bar por su ID
    @Query(value = "{_id: ?0}", fields = "{ 'oferta_bebidas': 1 }")
    List<Bebida> obtenerBebidasPorBar(int id);
}
