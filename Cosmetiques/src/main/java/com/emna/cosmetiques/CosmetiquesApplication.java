package com.emna.cosmetiques;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.emna.cosmetiques.entities.Cosmetique;
import com.emna.cosmetiques.service.CosmetiqueService;

@SpringBootApplication
public class CosmetiquesApplication implements CommandLineRunner {

	@Autowired
	CosmetiqueService cosmetiqueService;
	
	public static void main(String[] args) {
		SpringApplication.run(CosmetiquesApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		cosmetiqueService.saveCosmetique(new Cosmetique("c2", 2600.0, new Date()));
		cosmetiqueService.saveCosmetique(new Cosmetique("cos3333", 20.0, new Date()));
		cosmetiqueService.saveCosmetique(new Cosmetique("COSS5555", 220.0, new Date()));
	}

}
