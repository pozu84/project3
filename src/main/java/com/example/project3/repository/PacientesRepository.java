package com.example.project3.repository;

import com.example.project3.entities.Pacientes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacientesRepository extends JpaRepository<Pacientes, Long> {
}
