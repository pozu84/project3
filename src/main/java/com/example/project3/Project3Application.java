package com.example.project3;

import com.example.project3.entities.Medico;
import com.example.project3.repository.MedicoRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Project3Application {

	public static void main(String[] args) {

//		SpringApplication.run(Project3Application.class, args);
		ApplicationContext context = SpringApplication.run(Project3Application.class, args);
		MedicoRepository repository = context.getBean(MedicoRepository.class);

		System.out.println("Antes de la insercion " + repository.findAll());

		Medico md1 = new Medico(null, "Lorem", "Ipsum");
		repository.save(md1);

		System.out.println("Despues de insertar " + repository.findAll());
	}

}
