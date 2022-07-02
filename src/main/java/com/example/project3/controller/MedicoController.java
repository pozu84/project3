package com.example.project3.controller;

import com.example.project3.entities.Medico;
import com.example.project3.repository.MedicoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class MedicoController {

    /*Como tenemos que acceder al repository, tenemos que crearlo como un atributo para poder accederlo desde
    * esta clase, al igual que crear su constructor*/
    private final MedicoRepository medicoRepository;

    public MedicoController(MedicoRepository medicoRepository) {
        this.medicoRepository = medicoRepository;
    }

    /*BUSCAR TODOS LOS MEDICOS*/
    @GetMapping("/medicos")
    public List<Medico> findAll() {
        return medicoRepository.findAll();
    }

    /*BUSCAR MEDICO SEGUN SU id. Como en este caso buscamos por un parametro, debemos de indicar @PathVariable.
    * Lo envolvemos en un OPTIONAL porque queremos comprobar si el parametro que nos estan pasando se encuentra en
    * nuestra BBDD, asi lo podemos utilizar con el if para comprobar su existencia. Utilizamos tambien
    * ResponseEntity para que nos pueda devolver una respuesta HTTP, como un error 404 en caso de no encontrarlo*/
    @GetMapping("/medicos/{id}")
    public ResponseEntity<Medico> findById(@PathVariable Long id) {

        Optional<Medico> medicoOpt = medicoRepository.findById(id);

        if (medicoOpt.isPresent()) {
            return ResponseEntity.ok(medicoOpt.get());
        }
        return ResponseEntity.notFound().build(); //con build() indicamos que construya una respuesta notFound
    }

    /*CREAR UN NUEVO MEDICO*/
    @PostMapping("/medicos")
    public ResponseEntity<Medico> create(@RequestBody Medico medico) {
        if (medico.getId() != null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(medicoRepository.save(medico));
    }

    /*ACTUALIZAR MEDICOS QUE YA ESTAN EN BBDD. Comprobamos si el medico existe por el id, y en el caso que no
    * exista devolvemos un not found*/
    @PutMapping("/medicos")
    public ResponseEntity<Medico> update(@RequestBody Medico medico) {
        if (!medicoRepository.existsById(medico.getId())) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(medicoRepository.save(medico));
    }

    /*BORRAR UN MEDICO, cuando borramos de nuestra BBDD devolvemos un estado no content ya que al borrar no nos
    * devuelve nada, es decir, al borrarlo es lo unico que hacemos, borrar*/
    @DeleteMapping("/medicos/{id}")
    public ResponseEntity<Medico> delete(@PathVariable Long id) {
        if (!medicoRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        medicoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    /*BORRAR TODOS LOS MEDICOS, y una vez realizado devolvemos la respuesta no content*/
    @DeleteMapping("/medicos")
    public ResponseEntity<Medico> deleteAll() {
        medicoRepository.deleteAll();
        return ResponseEntity.noContent().build();
    }

}
