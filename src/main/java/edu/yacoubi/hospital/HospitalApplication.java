package edu.yacoubi.hospital;

import edu.yacoubi.hospital.entities.Patient;
import edu.yacoubi.hospital.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class HospitalApplication implements CommandLineRunner {

	@Autowired
	private PatientRepository patientRepository;

	public static void main(String[] args) {
		SpringApplication.run(HospitalApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		patientRepository.save(new Patient(null, "joe", new Date(), false, 33));
		patientRepository.save(new Patient(null, "myriam", new Date(), true, 4));
		patientRepository.save(new Patient(null, "mathias", new Date(), true, 3));
	}
}
