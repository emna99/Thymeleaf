package com.emna.cosmetiques;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import com.emna.cosmetiques.entities.Cosmetique;
import com.emna.cosmetiques.repos.CosmetiqueRepository;
import com.emna.cosmetiques.service.CosmetiqueService;

@SpringBootTest
class CosmetiquesApplicationTests {

	@Autowired
	private CosmetiqueRepository cosmetiqueRepository;
	
	@Autowired
	private CosmetiqueService cosmetiqueService;
	
	@Test
	public void testCreateCosmetique() {
	Cosmetique cos = new Cosmetique("Mascara MF",50.500,new Date());
	cosmetiqueRepository.save(cos);
	}
	
	@Test
	public void testFindCosmetique()
	{
		Cosmetique c = cosmetiqueRepository.findById(1L).get();
	System.out.println(c);
	}
	
	@Test
	public void testUpdateCosmetique()
	{
	Cosmetique c = cosmetiqueRepository.findById(1L).get();
	c.setPrixCosmetique(20.0);
	cosmetiqueRepository.save(c);
	
	System.out.println(c);
	}
	
	@Test
	public void testDeleteCosmetique()
	{
		cosmetiqueRepository.deleteById(1L);;
		}
	
	@Test
	public void testFindAllCosmetiques()
	{
	List<Cosmetique> coss = cosmetiqueRepository.findAll();
	for (Cosmetique c : coss)
	{
	System.out.println(c);
	}
	}
	
	@Test
	public void testFindByNomCosmetiqueContains()
	{
	Page<Cosmetique> coss = cosmetiqueService.getAllCosmetiquesParPage(0,2);
	System.out.println(coss.getSize());
	System.out.println(coss.getTotalElements());
	System.out.println(coss.getTotalPages());
	coss.getContent().forEach(p -> {System.out.println(p.toString());
	 });
	
	}

}
