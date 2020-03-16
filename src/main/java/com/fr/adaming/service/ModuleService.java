package com.fr.adaming.service;

import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.InvalidDataAccessApiUsageException;

import com.fr.adaming.Dao.IModuleDao;
import com.fr.adaming.entity.Module;

public class ModuleService implements IModuleService{
	
	@Autowired
	private IModuleDao dao;

	@Override
	public Module create(Module module) {
		try {
			if (module == null || dao.existsById(module.getId())) {
				return null;
			}
			return dao.save(module);
		} catch (DataIntegrityViolationException e) {
			e.printStackTrace();
			return null;
		}

		catch (ConstraintViolationException er) {
			er.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Module> findAll() {
		if (dao.findAll().isEmpty()) {
			return null;
		}
		return dao.findAll();
	}

	@Override
	public Module findById(int id) {
		try {
			if (id != 0) {
				return dao.findById(id).orElse(null);
			} else {
				return null;
			}
		} catch (InvalidDataAccessApiUsageException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Module findByNom(String nom) {
		if (nom != null) {
			return dao.findByNom(nom);
		} else {
			return null;
		}
	}

	@Override
	public boolean update(Module module) {
		try {
			if (dao.existsById(module.getId())) {
				dao.save(module);
				return true;
			} else {
				return false;
			}
		} catch (InvalidDataAccessApiUsageException er) {
			er.printStackTrace();
			return false;
		} catch (NullPointerException ec) {
			ec.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean deleteById(int id) {
		try {
			if (dao.findById(id) != null && id != 0) {
				dao.deleteById(id);
				return true;
			} else {
				return false;
			}
		} catch (InvalidDataAccessApiUsageException e) {
			e.printStackTrace();
			return false;
		} catch (EmptyResultDataAccessException er) {
			er.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean deleteByNom(String nom) {
		try {
			if (dao.findByNom(nom) != null && nom != null) {
				dao.deleteByNom(nom);
				return true;
			} else {
				return false;
			}
		} catch (InvalidDataAccessApiUsageException e) {
			e.printStackTrace();
			return false;
		} catch (EmptyResultDataAccessException er) {
			er.printStackTrace();
			return false;
		}
	}

}
