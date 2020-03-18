package com.fr.adaming.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fr.adaming.converter.IConverter;
import com.fr.adaming.dto.AbsenceDto;
import com.fr.adaming.dto.AbsenceDtoCreate;
import com.fr.adaming.dto.ResponseDto;
import com.fr.adaming.entity.Absence;
import com.fr.adaming.service.IAbsenceService;

@RestController
public class AbsenceController implements IAbsenceController {

	@Autowired
	private IConverter<Absence, AbsenceDto> convert;
	
	@Autowired
	private IConverter<Absence, AbsenceDtoCreate> convertCreate;

	@Autowired
	@Qualifier("absenceservice")
	private IAbsenceService service;

	@Override
	public ResponseEntity<ResponseDto> create(@Valid @RequestBody AbsenceDtoCreate dto) {
		AbsenceDtoCreate abs = convertCreate.entiteToDto(service.create(convertCreate.dtoToEntite(dto)));

		ResponseDto resp = null;

		if (abs != null) {
			resp = new ResponseDto(false, "SUCCESS", abs);
			return ResponseEntity.status(HttpStatus.OK).body(resp);
		}
		resp = new ResponseDto(true, "FAIL", abs);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
	}

	@Override
	public ResponseEntity<ResponseDto> update(@Valid @RequestBody AbsenceDtoCreate dto) {
		boolean result = service.update(convertCreate.dtoToEntite(dto));
		ResponseDto resp = null;

		if (result) {
			resp = new ResponseDto(true, "SUCCESS", null);
			return ResponseEntity.status(HttpStatus.OK).body(resp);
		}
		resp = new ResponseDto(false, "FAIL", null);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
	}

	@Override
	public ResponseEntity<ResponseDto> findById(@PathVariable(name = "id") int id) {
		AbsenceDto dto = convert.entiteToDto(service.findById(id));
		ResponseDto resp = null;

		if (dto != null) {
			resp = new ResponseDto(false, "SUCCESS", dto);
			return ResponseEntity.status(HttpStatus.OK).body(resp);
		}
		resp = new ResponseDto(true, "FAIL", dto);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
	}

	@Override
	public ResponseEntity<ResponseDto> findAll() {
		List<AbsenceDto> list = convert.listEntiteToDto(service.findAll());

		ResponseDto resp = new ResponseDto(false, "SUCCESS", list);
		return ResponseEntity.status(HttpStatus.OK).body(resp);
	}

	@Override
	public ResponseEntity<ResponseDto> delete(@PathVariable(name = "id") int id) {
		boolean result = service.deleteById(id);
		ResponseDto resp = null;

		if (result) {
			resp = new ResponseDto(true, "SUCCESS", null);
			return ResponseEntity.status(HttpStatus.OK).body(resp);
		}
		resp = new ResponseDto(false, "FAIL", null);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
	}
}
