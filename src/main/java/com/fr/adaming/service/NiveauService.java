package com.fr.adaming.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fr.adaming.dao.INiveauDao;
import com.fr.adaming.entity.Niveau;

public class NiveauService implements INiveauService {
	
	@Autowired
	private INiveauDao dao;

	@Override
	public Niveau create(Niveau niveau) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Niveau> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Niveau findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Niveau findByNom(String nom) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(Niveau niveau) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteById(int id) {
		// TODO Auto-generated method stub
		return false;
	}

}
