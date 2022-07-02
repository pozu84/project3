package com.example.project3.controller;

import com.example.project3.entities.Citas;
import com.example.project3.repository.CitasRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CitasController {

    private final CitasRepository citasRepository;

    public CitasController(CitasRepository citasRepository) {
        this.citasRepository = citasRepository;
    }

    /*BUSCAR TODAS LAS CITAS*/
    @GetMapping("/citas")
    public List<Citas> findAll() {
        return citasRepository.findAll();
    }

    /*BUSCAR CITA POR id*/
    @GetMapping("/citas/{id}")
    public ResponseEntity<Citas> findById(@PathVariable Long id) {

        Optional<Citas> citasOpt = citasRepository.findById(id);

        if (citasOpt.isPresent()) {
            return ResponseEntity.ok(citasOpt.get());
        }
        return ResponseEntity.notFound().build();
    }

    /*CREAR UNA CITA*/
    @PostMapping("/citas")
    public ResponseEntity<Citas> create(@RequestBody Citas cita) {
        if (cita.getId() != null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(citasRepository.save(cita));
    }

    /*ACTUALIZAR CITAS*/
    @PutMapping("/citas")
    public ResponseEntity<Citas> update(@RequestBody Citas cita) {
        if (!citasRepository.existsById(cita.getId())) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(citasRepository.save(cita));
    }

    /*BORRAR UNA CITA POR id*/
    @DeleteMapping("/citas/{id}")
    public ResponseEntity<Citas> delete(@PathVariable Long id) {
        if(!citasRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        citasRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    /*BORRAR TODAS LAS CITAS*/
    @DeleteMapping("/citas")
    public ResponseEntity<Citas> deleteAll() {
        citasRepository.deleteAll();
        return ResponseEntity.noContent().build();
    }





}
