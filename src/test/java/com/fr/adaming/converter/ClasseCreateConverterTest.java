package com.fr.adaming.converter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fr.adaming.dto.ClasseDtoCreate;
import com.fr.adaming.entity.Classe;
import com.fr.adaming.entity.Etudiant;

@SpringBootTest
public class ClasseCreateConverterTest {
	
	@Autowired
	public IConverter<Classe, ClasseDtoCreate> convert;

	// Valide !
	@Test
	@DisplayName("Convertion DtoToEntite correct")
	public void testDtoToEntiteValid_shouldReturnEntite() {
		Etudiant e1 = new Etudiant();
		Etudiant e2 = new Etudiant();
		List<Etudiant> listEtudiant = new ArrayList<Etudiant>();
		listEtudiant.add(e1);
		listEtudiant.add(e2);
		ClasseDtoCreate dto = new ClasseDtoCreate(1, "TopOfTheTop", listEtudiant);

		Classe retour = convert.dtoToEntite(dto);

		assertNotNull(retour);
		assertEquals(listEtudiant, retour.getEtudiants());
		assertEquals(1, retour.getId());
		assertEquals("TopOfTheTop", retour.getNom());
	}

	// Valide !
	@Test
	@DisplayName("Convertion DtoToEntite avec listNull")
	public void testDtoToEntiteWithNullClasseList_shouldReturnEntite() {
		ClasseDtoCreate dto = new ClasseDtoCreate(1, "TopOfTheTop", null);

		Classe retour = convert.dtoToEntite(dto);

		assertNotNull(retour);
		assertEquals(null, retour.getEtudiants());
		assertEquals(1, retour.getId());
		assertEquals("TopOfTheTop", retour.getNom());
	}

	// Valide !
	@Test
	@DisplayName("Convertion DtoToEntite avec nomNull")
	public void testDtoToEntiteWithNullNom_shouldReturnEntite() {
		Etudiant e1 = new Etudiant();
		Etudiant e2 = new Etudiant();
		List<Etudiant> listEtudiant = new ArrayList<Etudiant>();
		listEtudiant.add(e1);
		listEtudiant.add(e2);
		ClasseDtoCreate dto = new ClasseDtoCreate(1, null, listEtudiant);

		Classe retour = convert.dtoToEntite(dto);

		assertNotNull(retour);
		assertEquals(listEtudiant, retour.getEtudiants());
		assertEquals(1, retour.getId());
		assertEquals(null, retour.getNom());
	}

	// Valide !
	@Test
	@DisplayName("Convertion DtoToEntite dtoNull")
	public void testDtoToEntiteWithNullEntite_shouldReturnNull() {
		ClasseDtoCreate dto = null;

		Classe retour = convert.dtoToEntite(dto);

		assertNull(retour);
	}

	// Valide !
	@Test
	@DisplayName("Convertion EntiteToDto correct")
	public void testEntiteToDtoValid_shouldReturnDto() {
		Etudiant e1 = new Etudiant();
		Etudiant e2 = new Etudiant();
		List<Etudiant> listEtudiant = new ArrayList<Etudiant>();
		listEtudiant.add(e1);
		listEtudiant.add(e2);
		Classe entite = new Classe(1, "TopOfTheTop",listEtudiant);

		ClasseDtoCreate retour = convert.entiteToDto(entite);

		assertNotNull(retour);
		assertEquals(listEtudiant, retour.getListe());
		assertEquals(1, retour.getId());
		assertEquals("TopOfTheTop", retour.getNom());
	}

	// Valide !
	@Test
	@DisplayName("Convertion EntiteToDto avec listNull")
	public void testEntiteToDtoWithNullClasseList_shouldReturnDto() {
		Classe entite = new Classe(1, "TopOfTheTop", null);

		ClasseDtoCreate retour = convert.entiteToDto(entite);

		assertNotNull(retour);
		assertEquals(null, retour.getListe());
		assertEquals(1, retour.getId());
		assertEquals("TopOfTheTop", retour.getNom());
	}

	// Valide !
	@Test
	@DisplayName("Convertion EntiteToDto avec nomNull")
	public void testEntiteToDtoWithNullNom_shouldReturnDto() {
		Etudiant e1 = new Etudiant();
		Etudiant e2 = new Etudiant();
		List<Etudiant> listEtudiant = new ArrayList<Etudiant>();
		listEtudiant.add(e1);
		listEtudiant.add(e2);
		Classe entite = new Classe(1, "TopOfTheTop", null);

		ClasseDtoCreate retour = convert.entiteToDto(entite);

		assertNotNull(retour);
		assertEquals(null, retour.getListe());
		assertEquals(1, retour.getId());
		assertEquals("TopOfTheTop", retour.getNom());
	}

	// Valide !
	@Test
	@DisplayName("Convertion EntiteToDto dtoNull")
	public void testEntiteToDtoWithNullEntite_shouldReturnNull() {
		Classe entite = null;

		ClasseDtoCreate retour = convert.entiteToDto(entite);

		assertNull(retour);
	}

	// Valide !
	@Test
	@DisplayName("Convertion ListDtoToListEntite correct")
	public void testListDtoToListEntiteValid_shouldReturnListEntite() {
		Etudiant e1 = new Etudiant();
		Etudiant e2 = new Etudiant();
		List<Etudiant> listEtudiant = new ArrayList<Etudiant>();
		listEtudiant.add(e1);
		listEtudiant.add(e2);
		ClasseDtoCreate dto1 = new ClasseDtoCreate(1, "TopOfTheTop", listEtudiant);
		ClasseDtoCreate dto2 = new ClasseDtoCreate(2, "Moins Top", listEtudiant);
		List<ClasseDtoCreate> listEtudiantDtoCreate = new ArrayList<ClasseDtoCreate>();
		listEtudiantDtoCreate.add(dto1);
		listEtudiantDtoCreate.add(dto2);

		List<Classe> listRetour = convert.listDtoToEntite(listEtudiantDtoCreate);

		assertNotNull(listRetour);
		assertThat(listRetour).hasSize(2);
		assertThat(listRetour.get(0)).hasFieldOrPropertyWithValue("etudiants", listEtudiant);
		assertThat(listRetour.get(0)).hasFieldOrPropertyWithValue("id", 1);
		assertThat(listRetour.get(0)).hasFieldOrPropertyWithValue("nom", "TopOfTheTop");
	}

	// Valide !
	@Test
	@DisplayName("Convertion ListDtoToListEntite correct")
	public void testListDtoToListEntiteWithNullList_shouldReturnNull() {
		List<ClasseDtoCreate> listEtudiantDtoCreate = null;

		List<Classe> listRetour = convert.listDtoToEntite(listEtudiantDtoCreate);

		assertNull(listRetour);
	}

	// Valide !
	@Test
	@DisplayName("Convertion listEntiteToListDto correct")
	public void testListEntiteToListDtoValid_shouldReturnListEntite() {
		Etudiant e1 = new Etudiant();
		Etudiant e2 = new Etudiant();
		List<Etudiant> listEtudiant = new ArrayList<Etudiant>();
		listEtudiant.add(e1);
		listEtudiant.add(e2);
		Classe entite1 = new Classe(1, "TopOfTheTop", listEtudiant);
		Classe entite2 = new Classe(2, "Moins Top", listEtudiant);
		List<Classe> listClasse = new ArrayList<Classe>();
		listClasse.add(entite1);
		listClasse.add(entite2);

		List<ClasseDtoCreate> listRetour = convert.listEntiteToDto(listClasse);

		assertNotNull(listRetour);
		assertThat(listRetour).hasSize(2);
		assertThat(listRetour.get(0)).hasFieldOrPropertyWithValue("liste", listEtudiant);
		assertThat(listRetour.get(0)).hasFieldOrPropertyWithValue("id", 1);
		assertThat(listRetour.get(0)).hasFieldOrPropertyWithValue("nom", "TopOfTheTop");
	}

	// Valide !
	@Test
	@DisplayName("Convertion ListDtoToListEntite null")
	public void testListEntiteToListDtoWithNullList_shouldReturnNull() {
		List<Classe> listClasse = null;

		List<ClasseDtoCreate> listRetour = convert.listEntiteToDto(listClasse);

		assertNull(listRetour);
	}

}