package com.fr.adaming.dto;

import javax.validation.constraints.NotNull;

import com.fr.adaming.entity.Etudiant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
@NoArgsConstructor @AllArgsConstructor
public class AbsenceDto {
	
	@NotNull
	private String debut;

	@NotNull
	private String fin;

	private String justification;

	private String description;
	
	private Etudiant etudiant;

	public AbsenceDto(@NotNull String debut, @NotNull String fin, String justification, String description) {
		super();
		this.debut = debut;
		this.fin = fin;
		this.justification = justification;
		this.description = description;
	}

}
