package com.emna.cosmetiques.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.emna.cosmetiques.entities.Cosmetique;
import com.emna.cosmetiques.service.CosmetiqueService;

@Controller
public class CosmetiqueController {
	
	@Autowired
	CosmetiqueService cosmetiqueService;
	
	@RequestMapping("/showCreate")
	public String showCreate()
	{
	return "createCosmetique";
	}
	
	@RequestMapping("/saveCosmetique")
	public String saveCosmetique(@ModelAttribute("cosmetique") Cosmetique cosmetique,
	 @RequestParam("date") String date,
	 ModelMap modelMap) throws
	ParseException
	{
	
	 SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
	 Date dateCreation = dateformat.parse(String.valueOf(date));
	 cosmetique.setDateCreation(dateCreation);

	Cosmetique saveCosmetique = cosmetiqueService.saveCosmetique(cosmetique);
	String msg ="cosmetique enregistré avec Id "+saveCosmetique.getIdCosmetique();
	modelMap.addAttribute("msg", msg);
	return "createCosmetique";
	}
	
	@RequestMapping("/ListeCosmetiques")
	public String listeCosmetiques(ModelMap modelMap,
			@RequestParam (name="page",defaultValue = "0") int page,
			@RequestParam (name="size", defaultValue = "2") int size)
	{
		Page<Cosmetique> coss = cosmetiqueService.getAllCosmetiquesParPage(page, size);
		modelMap.addAttribute("cosmetiques", coss);
		 modelMap.addAttribute("pages", new int[coss.getTotalPages()]);
		modelMap.addAttribute("currentPage", page);
		return "listeCosmetiques";
	}

	@RequestMapping("/supprimerCosmetique")
	public String supprimerCosmetique(@RequestParam("id") Long id,
	 ModelMap modelMap,
	 @RequestParam (name="page",defaultValue = "0") int page,
	 @RequestParam (name="size", defaultValue = "2") int size)
	{
	cosmetiqueService.deleteCosmetiqueById(id);
	Page<Cosmetique> coss = cosmetiqueService.getAllCosmetiquesParPage(page,
			size);
			modelMap.addAttribute("cosmetiques", coss);
			modelMap.addAttribute("pages", new int[coss.getTotalPages()]);
			modelMap.addAttribute("currentPage", page);
			modelMap.addAttribute("size", size);
	return "listeCosmetiques";
	}
	
	@RequestMapping("/modifierCosmetique")
	public String editerCosmetique(@RequestParam("id") Long id,ModelMap modelMap)
	{
		Cosmetique p= cosmetiqueService.getCosmetique(id);
	modelMap.addAttribute("cosmetique", p);
	return "editerCosmetique";
	}
	@RequestMapping("/updateCosmetique")
	public String updateCosmetique(@ModelAttribute("cosmetique") Cosmetique cosmetique,
	@RequestParam("date") String date,ModelMap modelMap) throws ParseException
	{
		//conversion de la date
		 SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
		 Date dateCreation = dateformat.parse(String.valueOf(date));
		 cosmetique.setDateCreation(dateCreation);

		 cosmetiqueService.updateCosmetique(cosmetique);
		 List<Cosmetique> coss = cosmetiqueService.getAllCosmetiques();
		 modelMap.addAttribute("cosmetiques", coss);
		return "listeCosmetiques";
		}


}