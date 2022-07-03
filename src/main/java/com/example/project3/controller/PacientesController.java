package com.example.project3.controller;

import com.example.project3.entities.Pacientes;
import com.example.project3.repository.PacientesRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class PacientesController {

    private final PacientesRepository pacientesRepository;

    public PacientesController(PacientesRepository pacientesRepository) {
        this.pacientesRepository = pacientesRepository;
    }

    /*BUSCAR TODOS LOS PACIENTES*/
    @GetMapping("/pacientes")
    public List<Pacientes> findAll() {
        return pacientesRepository.findAll();
    }

    /*BUSCAR PACIENTE POR id*/
    @GetMapping("/pacientes/{id}")
    public ResponseEntity<Pacientes> findById(@PathVariable Long id) {

        Optional<Pacientes> pacienteOpt = pacientesRepository.findById(id);

        if (pacienteOpt.isPresent()) {
            return ResponseEntity.ok(pacienteOpt.get());
        }
        return ResponseEntity.notFound().build();
    }

    /*CREAR NUEVO PACIENTE*/
    @PostMapping("/pacientes")
    public ResponseEntity<Pacientes> create(@RequestBody Pacientes paciente) {
        if (paciente.getId() != null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(pacientesRepository.save(paciente));
    }

    /*ACTUALIZAR PACIENTE*/
    @PutMapping("/pacientes/{id}")
    public ResponseEntity<Pacientes> update(@RequestBody Pacientes paciente) {
        if (!pacientesRepository.existsById(paciente.getId())) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pacientesRepository.save(paciente));
    }

    /*BORRAR UN PACIENTE*/
    @DeleteMapping("/pacientes/{id}")
    public ResponseEntity<Pacientes> delete(@PathVariable Long id) {
        if (!pacientesRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        pacientesRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    /*BORRAR TODOS LOS PACIENTES*/
    @DeleteMapping("/pacientes")
    public ResponseEntity<Pacientes> deleteAll() {
        pacientesRepository.deleteAll();
        return ResponseEntity.noContent().build();
    }

}
