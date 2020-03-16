package com.fr.adaming.converter;

import java.util.ArrayList;
import java.util.List;

import com.fr.adaming.dto.MatiereDtoCreate;
import com.fr.adaming.entity.Matiere;

public class MatiereCreateConverter implements IConverter<Matiere, MatiereDtoCreate> {

	@Override
	public Matiere dtoToEntite(MatiereDtoCreate dto) {
		Matiere entite = new Matiere(dto.getId(), dto.getNom());
		return entite;
	}

	@Override
	public List<Matiere> listDtoToEntite(List<MatiereDtoCreate> dtoliste) {
		List<Matiere> liste = new ArrayList<Matiere>();
		for (MatiereDtoCreate dto : dtoliste) {
			liste.add(new Matiere(dto.getId(), dto.getNom()));
		}
		return liste;
	}

	@Override
	public MatiereDtoCreate entiteToDto(Matiere entite) {
		MatiereDtoCreate dto = new MatiereDtoCreate(entite.getId(), entite.getNom());
		return dto;
	}

	@Override
	public List<MatiereDtoCreate> listEntiteToDto(List<Matiere> entite) {
		List<MatiereDtoCreate> liste = new ArrayList<MatiereDtoCreate>();
		for (Matiere e : entite) {
			liste.add(new MatiereDtoCreate(e.getId(), e.getNom()));
		}
		return liste;
	}
	

}
