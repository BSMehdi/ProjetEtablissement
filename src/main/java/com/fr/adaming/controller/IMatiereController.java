package com.fr.adaming.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fr.adaming.dto.MatiereDto;
import com.fr.adaming.dto.MatiereDtoCreate;
import com.fr.adaming.dto.ResponseDto;

@RequestMapping(path = "matiere/")
public interface IMatiereController {

	
	// create matiere
	@PostMapping
	public ResponseEntity<ResponseDto> create(@RequestBody @Valid MatiereDtoCreate dto);

	// read matiere by id
	@GetMapping (path = "id")
	public ResponseEntity<ResponseDto> findById(@RequestParam (name = "id", required = false) int id);

	// read matiere by nom
	@GetMapping (path = "nom")
	public ResponseEntity<ResponseDto> findByNom(@RequestParam (name = "nom", required = false) String nom);
	
	// read all
	@GetMapping (path = "all")
	public ResponseEntity<ResponseDto> findAll(@RequestBody @Valid List<Matiere> listeMatiere);
	
	// update matiere by id
	@PutMapping
	public ResponseEntity<ResponseDto> update(@RequestBody MatiereDto dto);
	
	// delete matiere by id
	@DeleteMapping (path = "{id}")
	public ResponseEntity<ResponseDto> deleteById (@PathVariable(name = "id") int id);
	
	//delete matiere by nom
	@DeleteMapping (path = "{nom}")
	public ResponseEntity<ResponseDto> deleteByNom (@PathVariable(name = "nom") String nom);
}