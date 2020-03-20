package com.fr.adaming.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.UnsupportedEncodingException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fr.adaming.dto.MatiereDtoCreate;
import com.fr.adaming.dto.ResponseDto;

@SpringBootTest
@AutoConfigureMockMvc
public class MatiereControllerTest {

	@Autowired
	private MockMvc mockMvc;

	private ObjectMapper mapper = new ObjectMapper();

	@Test
	@DisplayName("Creation matiere")
	public void testCreatingMatiereWithController_shouldWork() throws UnsupportedEncodingException, Exception {

		// preparer le DTO
		MatiereDtoCreate requestDto = new MatiereDtoCreate();
		requestDto.setNom("francais");
		requestDto.setId(20);

		// convrtir le DTO en Json
		String dtoAsJson = mapper.writeValueAsString(requestDto);

		// test requete
		String responseAsStrig = mockMvc
				.perform(post("http://localhost:8080/matiere/create").contentType(MediaType.APPLICATION_JSON_VALUE)
						.content(dtoAsJson))
				.andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		// convertir la reponse JSON en DTO
		ResponseDto responseDto = mapper.readValue(responseAsStrig, ResponseDto.class);

		assertNotNull(responseDto);

		String matString = mapper.writeValueAsString(responseDto.getObject());
		MatiereDtoCreate respMatiere = mapper.readValue(matString, MatiereDtoCreate.class);

		assertThat(respMatiere).isNotNull().hasFieldOrPropertyWithValue("nom", requestDto.getNom());

		assertThat(responseDto).isNotNull().hasFieldOrPropertyWithValue("message", "SUCCESS");
		assertFalse(responseDto.isError());

	}

	@Test
	@DisplayName("Creation matiere avec champ null - retour FAIL")
	public void testCreatingMatiereWithNullAttribute_shouldReturnFail() throws UnsupportedEncodingException, Exception {

		// preparer le DTO
		MatiereDtoCreate requestDto = new MatiereDtoCreate();

		// convrtir le DTO en Json
		String dtoAsJson = mapper.writeValueAsString(requestDto);

		// test requete
		String responseAsStrig = mockMvc
				.perform(post("http://localhost:8080/matiere/create").contentType(MediaType.APPLICATION_JSON_VALUE)
						.content(dtoAsJson))
				.andDo(print()).andExpect(status().isBadRequest()).andReturn().getResponse().getContentAsString();
		// convertir la reponse JSON en DTO
		ResponseDto responseDto = mapper.readValue(responseAsStrig, ResponseDto.class);

		assertNotNull(responseDto);
		assertTrue(responseDto.isError());
		assertThat(responseDto).isNotNull().hasFieldOrPropertyWithValue("message", "FAIL");

	}

	@Sql(statements = "INSERT INTO Matiere (id,nom) VALUES (5,'math')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Matiere where id=5", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@DisplayName("Find with Invalid Id")
	public void testFindByIdInvalid_shouldNotWork() throws UnsupportedEncodingException, Exception {
		Integer id = 10;
		// convrtir le DTO en Json

		// test requete
		String responseAsStrig = mockMvc
				.perform(get("http://localhost:8080/matiere/" + id).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andDo(print()).andExpect(status().isBadRequest()).andReturn().getResponse().getContentAsString();
		// convertir la reponse JSON en DTO
		ResponseDto responseDto = mapper.readValue(responseAsStrig, ResponseDto.class);

		assertThat(responseDto).isNotNull().hasFieldOrPropertyWithValue("message", "FAIL");
	}

	@Sql(statements = "INSERT INTO Matiere  VALUES (14,'mathematique',null)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "Delete from Matiere", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@DisplayName("Find with OK ID")
	public void testFindByIdOk_shouldWork() throws UnsupportedEncodingException, Exception {
		Integer id = 14;
		// convrtir le DTO en Json

		// test requete
		String responseAsStrig = mockMvc
				.perform(get("http://localhost:8080/matiere/" + id).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		// convertir la reponse JSON en DTO
		ResponseDto responseDto = mapper.readValue(responseAsStrig, ResponseDto.class);

		assertThat(responseDto).isNotNull().hasFieldOrPropertyWithValue("message", "SUCCESS");
	}

	@Sql(statements = "INSERT INTO Matiere  VALUES (14,'mathematique',null)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Matiere  VALUES (5,'physique',null)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "Delete from Matiere", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@DisplayName("Find all matiere")
	public void testFindAllWithController_shouldWork() throws UnsupportedEncodingException, Exception {
		// test requete
		String responseAsStrig = mockMvc
				.perform(get("http://localhost:8080/matiere/all").contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		// convertir la reponse JSON en DTO
		ResponseDto responseDto = mapper.readValue(responseAsStrig, ResponseDto.class);

		assertThat(responseDto).isNotNull().hasFieldOrPropertyWithValue("message", "SUCCESS");
	}

	// ********************************************************************************
	// TEST FIND MATIERES BY MODULE

	@Sql(statements = "INSERT INTO Module  VALUES (1,'sixieme')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Matiere  VALUES (14,'mathematique',1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Matiere  VALUES (20,'physique',1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "Delete from Matiere", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "Delete from Module", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@DisplayName("test find list matiere by module")
	public void testFindMatiereByModule_shouldBeOk() throws UnsupportedEncodingException, Exception {
		// test requete
		String responseAsStrig = mockMvc
				.perform(get("http://localhost:8080/matiere/all").contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		// convertir la reponse JSON en DTO
		ResponseDto responseDto = mapper.readValue(responseAsStrig, ResponseDto.class);

		assertThat(responseDto).isNotNull().hasFieldOrPropertyWithValue("message", "SUCCESS");
		
		String listeMatString = mapper.writeValueAsString(responseDto.getObject());
		assertThat(listeMatString).isNotNull();

	}
	
	
	@Sql(statements = "INSERT INTO Module  VALUES (1,'sixieme')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "Delete from Module", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@DisplayName("test find list matiere by module WITH NO MATIERE")
	public void testFindMatiereByModuleWithNoMatiere_shouldBeOk() throws UnsupportedEncodingException, Exception {
		// test requete
		String responseAsStrig = mockMvc
				.perform(get("http://localhost:8080/matiere/all").contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		// convertir la reponse JSON en DTO
		ResponseDto responseDto = mapper.readValue(responseAsStrig, ResponseDto.class);

		assertThat(responseDto).isNotNull().hasFieldOrPropertyWithValue("message", "SUCCESS");
		
	}

	// ********************************************************************************
	// TEST UPDATE

	@Sql(statements = "INSERT INTO Matiere (id,nom) VALUES (5,'physique')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "Delete from Matiere", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@DisplayName("Update matiere")
	public void testUpdateMatiereWithController_shouldWork() throws UnsupportedEncodingException, Exception {
		MatiereDtoCreate requestDto = new MatiereDtoCreate();
		requestDto.setNom("math");
		requestDto.setId(5);

		// convrtir le DTO en Json
		String dtoAsJson = mapper.writeValueAsString(requestDto);

		// test requete
		String responseAsStrig = mockMvc
				.perform(put("http://localhost:8080/matiere/update").contentType(MediaType.APPLICATION_JSON_VALUE)
						.content(dtoAsJson))
				.andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		// convertir la reponse JSON en DTO
		ResponseDto responseDto = mapper.readValue(responseAsStrig, ResponseDto.class);

		assertNotNull(responseDto);
		assertThat(responseDto).isNotNull().hasFieldOrPropertyWithValue("message", "SUCCESS");
	}

	// NE MARCHE PAAAAAAAAAAAAAAAAAAAAAAAAAAAS
	// STATUS 404 AU LIEU DE 400 (bad request)
	@Test
	@DisplayName("Update with no existing matiere")
	public void testUpdateWithNoexistingMAtiereController_shouldNotWork()
			throws UnsupportedEncodingException, Exception {
		// preparer le DTO
		MatiereDtoCreate requestDto = new MatiereDtoCreate();
		requestDto.setNom("math");
		requestDto.setId(10);

		// convrtir le DTO en Json
		String dtoAsJson = mapper.writeValueAsString(requestDto);

		// test requete
		String responseAsStrig = mockMvc
				.perform(put("http://localhost:8080/matiere/update").contentType(MediaType.APPLICATION_JSON_VALUE)
						.content(dtoAsJson))
				.andExpect(status().isBadRequest()).andReturn().getResponse().getContentAsString();
		// convertir la reponse JSON en DTO
		ResponseDto responseDto = mapper.readValue(responseAsStrig, ResponseDto.class);

		assertNotNull(responseDto);
		assertThat(responseDto).isNotNull().hasFieldOrPropertyWithValue("message", "FAIL");

	}

	// ********************************************************************************
	// TEST DELETE

	@Sql(statements = "INSERT INTO Matiere (id, nom) VALUES (5,'physique')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "Delete from Matiere", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@DisplayName("Delete matiere")
	public void testDeleteByIdWithController_shouldWork() throws UnsupportedEncodingException, Exception {
		int id = 5;
		// convrtir le DTO en Json
		String dtoAsJson = mapper.writeValueAsString(id);

		// test requete
		String responseAsStrig = mockMvc
				.perform(delete("http://localhost:8080/matiere/" + id).contentType(MediaType.APPLICATION_JSON_VALUE)
						.content(dtoAsJson))
				.andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		// convertir la reponse JSON en DTO
		ResponseDto responseDto = mapper.readValue(responseAsStrig, ResponseDto.class);

		assertThat(responseDto).isNotNull().hasFieldOrPropertyWithValue("message", "SUCCESS");
	}

	@Test
	@DisplayName("Delete no existing matiere")
	public void testDeleteByNotExistingIdWithController_shouldNotWork() throws UnsupportedEncodingException, Exception {
		int id = 28;
		// convrtir le DTO en Json
		String dtoAsJson = mapper.writeValueAsString(id);

		// test requete
		String responseAsStrig = mockMvc
				.perform(delete("http://localhost:8080/matiere/" + id).contentType(MediaType.APPLICATION_JSON_VALUE)
						.content(dtoAsJson))
				.andDo(print()).andExpect(status().isBadRequest()).andReturn().getResponse().getContentAsString();
		// convertir la reponse JSON en DTO
		ResponseDto responseDto = mapper.readValue(responseAsStrig, ResponseDto.class);

		assertThat(responseDto).isNotNull().hasFieldOrPropertyWithValue("message", "FAIL");
	}

}
