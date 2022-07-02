package com.example.project3;

import com.example.project3.entities.Citas;
import com.example.project3.entities.Medico;
import com.example.project3.repository.CitasRepository;
import com.example.project3.repository.MedicoRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootApplication
public class Project3Application {

	public static void main(String[] args) {

//		SpringApplication.run(Project3Application.class, args);

		ApplicationContext context = SpringApplication.run(Project3Application.class, args);

		/*CREAR MEDICO*/
		MedicoRepository repository = context.getBean(MedicoRepository.class);

		System.out.println("Antes de la insercion " + repository.findAll());

		Medico md1 = new Medico(null, "Lorem", "Ipsum");
		repository.save(md1);

		System.out.println("Despues de insertar " + repository.findAll());

		/*repository.delete(md1);

		System.out.println("Despues del borrado" + repository.findAll());*/

		/*CREAR CITA*/
		CitasRepository citasRepository = context.getBean(CitasRepository.class);

		Citas cita1 = new Citas(null,
				LocalDateTime.of(2022, 5, 12, 13, 45),
				"Lorem Ipsum");

		citasRepository.save(cita1);

		System.out.println("Citas " + citasRepository.findAll());
	}

}
