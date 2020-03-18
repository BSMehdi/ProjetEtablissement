package com.fr.adaming.converter;

import java.util.List;

import org.springframework.stereotype.Component;


public interface IConverter<Entite, Dto> {
	
	public Entite dtoToEntite(Dto dto);
	public List<Entite> listDtoToEntite(List<Dto> liste);
	public Dto entiteToDto(Entite entite);
	public List<Dto> listEntiteToDto(List<Entite> liste);
}
