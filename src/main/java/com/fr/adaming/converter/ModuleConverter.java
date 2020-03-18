package com.fr.adaming.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.fr.adaming.dto.ModuleDto;
import com.fr.adaming.entity.Module;

@Component
public class ModuleConverter implements IConverter<Module, ModuleDto> {

	@Override
	public Module dtoToEntite(ModuleDto dto) {
		if (dto != null && dto.getNom() != null) {
			Module entite = new Module(0, dto.getNom(), dto.getMatiere());
			return entite;
		}
		return null;
	}

	@Override
	public List<Module> listDtoToEntite(List<ModuleDto> dtoliste) {
		if (dtoliste == null) {
			return null;
		}
		List<Module> liste = new ArrayList<Module>();
		for (ModuleDto dto : dtoliste) {
			if (dto.getNom() != null) {
				liste.add(new Module(0, dto.getNom(), dto.getMatiere()));
			}
		}
		return liste;
	}

	@Override
	public ModuleDto entiteToDto(Module entite) {
		if (entite != null && entite.getNom() != null) {
			ModuleDto dto = new ModuleDto(entite.getNom(), entite.getMatieres());
			return dto;

		}
		return null;
	}

	@Override
	public List<ModuleDto> listEntiteToDto(List<Module> entite) {
		if (entite == null) {
			return null;
		}
		List<ModuleDto> liste = new ArrayList<ModuleDto>();
		for (Module e : entite) {
			if (e.getNom() != null) {
				liste.add(new ModuleDto(e.getNom(), e.getMatieres()));
			}

		}
		return liste;
	}

}
