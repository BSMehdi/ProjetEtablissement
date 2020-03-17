package com.fr.adaming.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.fr.adaming.dto.MatiereDto;
import com.fr.adaming.entity.Matiere;

@Component
public class MatiereConverter implements IConverter<Matiere, MatiereDto> {

	@Override
	public Matiere dtoToEntite(MatiereDto dto) {
		if (dto != null && dto.getNom() != null) {
			Matiere entite = new Matiere(0, dto.getNom());
			return entite;

		} else {
			return null;
		}

	}

	@Override
	public List<Matiere> listDtoToEntite(List<MatiereDto> dtoliste) {
		if (dtoliste == null) {
			return null;
		}
		List<Matiere> liste = new ArrayList<Matiere>();
		for (MatiereDto dto : dtoliste) {
			if (dto.getNom() != null) {
				liste.add(new Matiere(0, dto.getNom()));
			}
		}
		return liste;
	}

	@Override
	public MatiereDto entiteToDto(Matiere entite) {
		if (entite != null && entite.getNom()!=null) {
			MatiereDto dto = new MatiereDto(entite.getNom());
			return dto;

		} else {
			return null;
		}

	}

	@Override
	public List<MatiereDto> listEntiteToDto(List<Matiere> entite) {
		if (entite == null) {
			return null;
		}
		List<MatiereDto> liste = new ArrayList<MatiereDto>();
		for (Matiere e : entite) {
			if (e.getNom() != null) {
				liste.add(new MatiereDto(e.getNom()));
			}
		}
		return liste;
	}

}
