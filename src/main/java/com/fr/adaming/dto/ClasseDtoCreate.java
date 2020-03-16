package com.fr.adaming.dto;

import java.util.List;

import com.fr.adaming.entity.Etudiant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class ClasseDtoCreate {
	
	private int id;
	
	private String nom;
	
	private List<Etudiant> liste;

}
