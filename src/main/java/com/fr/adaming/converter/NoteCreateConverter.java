package com.fr.adaming.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.fr.adaming.dto.NoteDtoCreate;
import com.fr.adaming.entity.Note;
@Component
public class NoteCreateConverter implements IConverter<Note, NoteDtoCreate> {
	

	@Override
	public Note dtoToEntite(NoteDtoCreate dto) {
		if(dto==null) {
			return null;
		}
		return new Note(dto.getId(), dto.getModule(),dto.getValeur(),dto.getEtudiant(),dto.getExamen());
	}

	@Override
	public List<Note> listDtoToEntite(List<NoteDtoCreate> dtoliste) {
		List<Note> liste = new ArrayList<>();
		if(dtoliste!=null) {
			for (NoteDtoCreate dto : dtoliste) {
				liste.add(new Note(dto.getId(), dto.getModule(),dto.getValeur(),dto.getEtudiant(),dto.getExamen()));
			}
		}
		return liste;
	}

	@Override
	public NoteDtoCreate entiteToDto(Note entite) {
		if(entite==null) {
			return null;
		}
		return new NoteDtoCreate(entite.getId(), entite.getModule(), entite.getValeur(), entite.getEtudiant(), entite.getExamen());
	}

	@Override
	public List<NoteDtoCreate> listEntiteToDto(List<Note> entite) {
		List<NoteDtoCreate> liste = new ArrayList<>();
		if(entite!=null) {
			for (Note e : entite) {
				liste.add(new NoteDtoCreate(e.getId(),e.getModule(), e.getValeur(), e.getEtudiant(), e.getExamen()));
			}
		}
		return liste;
	}
	

}
