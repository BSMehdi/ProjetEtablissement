package com.fr.adaming.dao;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

@SpringBootTest
public class ExamenDaoTest {
	
	@Autowired
	private IExamenDao dao;
	
	@Sql(statements = "INSERT INTO Examen (id, type) VALUES (1, 'exam1')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Examen (id, type) VALUES (2, 'exam2')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Module (id, nom) VALUES (1, 'module1')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Matiere (id, nom,module_id) VALUES (1, 'matiere1',1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Note (id, module_id,valeur,examen_id) VALUES (1, 1,12,1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Note (id, module_id,valeur,examen_id) VALUES (2, 1,13,2)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Note", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Matiere", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Examen", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Module", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testListByMatiere_shouldReturnListOfExamen() {
		assertThat(dao.listByMatiere(1)).hasSize(2);
		
	}

}
