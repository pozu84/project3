package com.example.project3.repository;

import com.example.project3.entities.Citas;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CitasRepository extends JpaRepository<Citas, Long> {
}
