package uniandes.edu.co.demo.controller;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import uniandes.edu.co.demo.modelo.Bar;
import uniandes.edu.co.demo.repository.BarRepository;

@RestController
public class BaresController {

    @Autowired
    private BarRepository barRepository;

    // Obtener todos los bares
    @GetMapping("/bares")
    public ResponseEntity<Collection<Bar>> obtenerTodosLosBares() {
        try {
            // Llamar al método definido en el repositorio para obtener todos los registros excluyendo la lista de bebidas
            List<Bar> bares = barRepository.buscarTodosLosBares();

            return ResponseEntity.ok(bares);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Obtener bar por ID
    @GetMapping("/bares/{id}")
    public ResponseEntity<Bar> obtenerBarPorId(@PathVariable("id") int id) {
        try {
            List<Bar> bares = barRepository.buscarPorId(id);
            if (!bares.isEmpty()) {
                return ResponseEntity.ok(bares.get(0)); // Retorna el primer bar con ese ID
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Crear un nuevo bar
    @PostMapping("/bares/new/save")
    public ResponseEntity<String> guardarBar(@RequestBody Bar bar) {
        try {
            barRepository.save(bar);
            return new ResponseEntity<>("Bar creado exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear el bar", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Actualizar un bar existente
    @PostMapping("/bares/{id}/edit/save")
    public ResponseEntity<String> editarGuardarBar(@PathVariable("id") int id, @RequestBody Bar bar) {
        try {
            bar.setId(id);
            barRepository.save(bar);
            return new ResponseEntity<>("Bar actualizado exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al actualizar el bar", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Eliminar un bar por su ID
    @DeleteMapping("/bares/{id}/delete")
    public ResponseEntity<String> eliminarBar(@PathVariable("id") int id) {
        try {
            barRepository.eliminarBarPorId(id);
            return new ResponseEntity<>("Bar eliminado exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar el bar", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
