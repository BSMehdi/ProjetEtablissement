package com.fr.adaming.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import com.fr.adaming.entity.Classe;
import com.fr.adaming.entity.Niveau;

@SpringBootTest
public class NiveauServiceTest {

	@Autowired
	private INiveauService service;

	// Tests create
	// Valide !
	@Test
	@DisplayName("Création d'un niveau null")
	public void testCreatingNiveauNull_shouldReturnNull() {
		Niveau niv = null;
		assertNull(service.create(niv));
	}

	// Valide !
	@Test
	@DisplayName("Création d'un niveau avec param null")
	public void testCreatingNiveauWithNullName_shouldReturnNiveau() {
		Niveau niv = new Niveau(null, 0, null);
		assertThat(service.create(niv)).isEqualTo(niv);
	}

	// Valide !
	@Test
	@DisplayName("Création d'un niveau avec correct")
	public void testCreatingCorrectNiveau_shouldReturnNiveau() {
		Classe term1 = new Classe();
		Classe term2 = new Classe();
		List<Classe> list = new ArrayList<Classe>();
		list.add(term1);
		list.add(term2);
		Niveau niv = new Niveau(list, 0, "Bof");
		assertThat(service.create(niv)).isEqualTo(niv);
	}

	// Test findAll
	// Valide !
	@Test
	@Sql(statements = "DELETE FROM Niveau", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@DisplayName("Demande de la liste vide")
	public void testGetList_shouldReturnEmptyList() {
		assertThat(service.findAll()).isEmpty();
	}

	// Valide !
	@Sql(statements = "INSERT INTO Niveau (id, nom) VALUES (19, 'Maternel')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Niveau (id, nom) VALUES (29, 'Primaire')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Niveau", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Niveau", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@DisplayName("Demande de la liste de 2 niveaux")
	public void testGetList_shouldReturnList() {
		List<Niveau> retour = service.findAll();
		assertThat(retour).hasSize(2);
		assertThat(retour.get(0).getId()).isEqualTo(1);
		assertThat(retour.get(0).getNom()).isEqualTo("Maternel");
	}

	// Test findById
	// Valide !
	@Test
	@DisplayName("Recherche de niveau par id non existant")
	public void testFindByIdWithInexistantId_shouldReturnNull() {
		assertThat(service.findById(1)).isNull();
	}

	// Valide ! (pb list)
	@Sql(statements = "INSERT INTO Niveau (id, nom) VALUES (1, 'Primaire')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Niveau", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@DisplayName("Recherche de niveau par id")
	public void testFindById_shouldReturnNiveau() {
		Niveau retour = service.findById(1);
		assertThat(retour.getId()).isEqualTo(1);
		assertThat(retour.getNom()).isEqualTo("Primaire");
		//assertThat(retour.getClasses()).isEmpty();
	}

	// Test update
	// Valide !
	@Test
	@DisplayName("Update d'un niveau null")
	public void testUpdateNullNiveau_shouldReturnFalse() {
		Niveau niv = null;
		assertThat(service.update(niv)).isFalse();
	}

	// Valide !
	@Test
	@DisplayName("Update d'un niveau inexistant dans la bd")
	public void testUpdateInexistantNiveau_shouldReturnFalse() {
		Classe term1 = new Classe();
		Classe term2 = new Classe();
		List<Classe> list = new ArrayList<Classe>();
		list.add(term1);
		list.add(term2);
		Niveau niv = new Niveau(list, 0, "Bof");
		assertThat(service.update(niv)).isFalse();
	}

	// Valide !
	@Sql(statements = "INSERT INTO Niveau (id, nom) VALUES (2878, 'Primaire')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Niveau", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@DisplayName("Update d'un niveau enregistrer dans la BD")
	public void testUpdateNiveauWithId_shouldReturnTrue() {
		Niveau niv = new Niveau(null, 2878, "Bof");
		assertThat(service.update(niv)).isTrue();
	}

	// Test deleteById
	// Valide !
	@Sql(statements = "INSERT INTO Niveau (id, nom) VALUES (19, 'Primaire')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Niveau", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@DisplayName("Delete avec id = 0")
	public void testDeleteByIdWithIdEqualsZero_shouldReturnFalse() {
		Integer id = 0;
		assertThat(service.deleteById(id)).isFalse();
	}

	// VAlide !
	@Sql(statements = "INSERT INTO Niveau (id, nom) VALUES (17, 'Primaire')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Niveau", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@DisplayName("Delete avec id valid")
	public void testDeleteByIdWithValidId_shouldReturnTrue() {
		Integer id = 17;
		assertThat(service.deleteById(id)).isTrue();
	}
	
	//Valide !
	@Test
	@DisplayName("Liste des classes avec id 0")
	public void testFindListClasseByIdNiveauWithId0_ShouldReturnEmptyList() {
		assertThat(service.findListClasseByIdNiveau(0)).isEmpty();
	}
	
	// Valide !
	@Test
	@DisplayName("Liste des classes avec id valide mais avec BD vite")
	public void testFindListClasseByIdNiveauWithEmptyDB_ShouldReturnEmptyList() {
		List<Classe> retour = service.findListClasseByIdNiveau(1);
		assertThat(retour).isEmpty();
	}
	
	// Valide !
	@Sql(statements = "INSERT INTO Niveau (id, nom) VALUES (1, 'TopOfTheTop')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Classe (id, nom, id_niveau) VALUES (2, 'Session2020', 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Etudiant (id, nom, prenom, adresse, ville, email, code_postale, cni, telephone, sexe, en_etude, etudiants_id) "
			+ "VALUES (1,'Bob', 'Marley', '3eme nuage a gauche', 'paradis', 'jamin@with.you', 0, 0, 0, true, true, 2)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Etudiant", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Classe", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Niveau", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@DisplayName("Liste des classes avec id valide et classes existantes")
	public void testFindListClasseByIdNiveauValid_ShouldReturnNull() {
		List<Classe> retour = service.findListClasseByIdNiveau(1);
		
		assertNotNull(retour);
		assertThat(retour).hasSize(1);
		assertThat(retour.get(0)).hasFieldOrPropertyWithValue("id", 2);
		assertThat(retour.get(0)).hasFieldOrPropertyWithValue("nom", "Session2020");
		assertThat(retour.get(0).getEtudiants()).hasSize(1);
		assertThat(retour.get(0).getEtudiants().get(0)).hasFieldOrPropertyWithValue("id", 1);
		assertThat(retour.get(0).getEtudiants().get(0)).hasFieldOrPropertyWithValue("nom", "Bob");
		assertThat(retour.get(0).getEtudiants().get(0)).hasFieldOrPropertyWithValue("prenom", "Marley");
		assertThat(retour.get(0).getEtudiants().get(0)).hasFieldOrPropertyWithValue("adresse", "3eme nuage a gauche");		
	}	
}
