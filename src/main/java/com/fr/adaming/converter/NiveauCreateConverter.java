package com.fr.adaming.converter;

import java.util.ArrayList;
import java.util.List;

import com.fr.adaming.dto.NiveauDtoCreate;
import com.fr.adaming.entity.Niveau;

public class NiveauCreateConverter implements IConverter<Niveau, NiveauDtoCreate> {

	@Override
	public Niveau dtoToEntite(NiveauDtoCreate dto) {
		Niveau entite = new Niveau(dto.getClasses(),dto.getId() , dto.getNom());
		return entite;
	}

	@Override
	public List<Niveau> listDtoToEntite(List<NiveauDtoCreate> dtoliste) {
		List<Niveau> liste = new ArrayList<Niveau>();
		for (NiveauDtoCreate dto : dtoliste) {
			liste.add(new Niveau(dto.getClasses(),dto.getId() , dto.getNom()));
		}
		return liste;
	}

	@Override
	public NiveauDtoCreate entiteToDto(Niveau entite) {
		NiveauDtoCreate dto = new NiveauDtoCreate(entite.getClasses(),entite.getId(), entite.getNom());
		return dto;
	}

	@Override
	public List<NiveauDtoCreate> listEntiteToDto(List<Niveau> entite) {
		List<NiveauDtoCreate> liste = new ArrayList<NiveauDtoCreate>();
		for (Niveau e : entite) {
			liste.add(new NiveauDtoCreate(e.getClasses(),e.getId(), e.getNom()));
		}
		return liste;
	}
	

}
