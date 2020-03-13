package com.fr.adaming.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Etudiant {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(length = 30)
	private String nom;
	@Column(length = 20)
	private String prenom;
	@Column(length = 40)
	private String adresse;
	@Column(length = 20)
	private String ville;
	@Column(length = 25)
	private String email;
	@Column
	private int codePostale;
	@Column
	private int cni;
	@Column
	private int telephone;
	@Column
	private boolean sexe;
	@Column
	private boolean enEtude;

}
