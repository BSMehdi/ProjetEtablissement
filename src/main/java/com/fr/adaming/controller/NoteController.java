package com.fr.adaming.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fr.adaming.converter.NoteConverter;
import com.fr.adaming.dto.NoteDto;
import com.fr.adaming.dto.NoteDtoCreate;
import com.fr.adaming.dto.ResponseDto;
import com.fr.adaming.entity.Note;
import com.fr.adaming.service.INoteService;

public class NoteController implements INoteController {

	@Autowired
	@Qualifier("servicenote")
	private INoteService service;

	// create
	@Override
	public ResponseEntity<ResponseDto> create(@Valid NoteDtoCreate dto) {
		NoteDto note = NoteConverter.convertNoteToNoteDtoCreate().service.create(NoteConverter.convertNoteDtoCreateToNote(dto)));
		
		//initialisation de la reponse
		ResponseDto resp = null;
		
		//Attribution de la réponse en fonction du retour DB et de la conposition de l'objet
		if (note != null) {
			resp = new ResponseDto(false, "SUCCESS", note);
			return ResponseEntity.status(HttpStatus.OK).body(resp);
		}
		resp = new ResponseDto(true, "FAIL", note);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
}

	// find By Id
	@Override
	public ResponseEntity<ResponseDto> findById(int id) {
		Note note = service.findById(id);

		// initialisation de la reponse
		ResponseDto resp = null;

		if (note != null) {
			resp = new ResponseDto(false, "SUCCESS", note);
			return ResponseEntity.status(HttpStatus.OK).body(resp);
		} else {
			resp = new ResponseDto(true, "FAIL", note);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
		}
	}

	// fidn By Etudiant
	@Override
	public ResponseEntity<ResponseDto> findByEtudiant(int etudiant) {
		Note note = service.findByEtudiant(etudiant);

		// initialisation de la reponse
		ResponseDto resp = null;

		if (note != null) {
			resp = new ResponseDto(false, "SUCCESS", note);
			return ResponseEntity.status(HttpStatus.OK).body(resp);
		} else {
			resp = new ResponseDto(true, "FAIL", note);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
		}
	}

	// find All
	@Override
	public ResponseEntity<ResponseDto> findAll() {
		List<Note> list = service.findAll();
		ResponseDto resp = null;
		if (list != null) {
			resp = new ResponseDto(false, "SUCCESS", list);
			return ResponseEntity.status(HttpStatus.OK).body(resp);
		}
		resp = new ResponseDto(false, "FAIL", null);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);

	}

	// Update
	@Override
	public ResponseEntity<ResponseDto> update(@Valid NoteDto dto) {
		boolean result = service.update(NoteConverter.convertNoteDtoToNote(dto));
		ResponseDto resp = null;

		if (!result) {
			resp = new ResponseDto(true, "SUCCESS", null);
			return ResponseEntity.status(HttpStatus.OK).body(resp);
		}
		resp = new ResponseDto(false, "FAIL", null);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
	}

	// delete By Id
	@Override
	public ResponseEntity<ResponseDto> deleteById(int id) {
		boolean result = service.deleteById(id);
		ResponseDto resp = null;

		if (result) {
			resp = new ResponseDto(true, "SUCCESS", null);
			return ResponseEntity.status(HttpStatus.OK).body(resp);
		}
		resp = new ResponseDto(false, "FAIL", null);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
	}

	// Delete By Etudiant
	@Override
	public ResponseEntity<ResponseDto> deleteByEtudiant(int etudiant) {
		boolean result = service.deleteByEtudiant(etudiant);
		ResponseDto resp = null;

		if (result) {
			resp = new ResponseDto(true, "SUCCESS", null);
			return ResponseEntity.status(HttpStatus.OK).body(resp);
		}
		resp = new ResponseDto(false, "FAIL", null);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
	}

}
