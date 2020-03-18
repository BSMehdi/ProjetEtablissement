package com.fr.adaming.service;

import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Service;

import com.fr.adaming.dao.IExamenDao;
import com.fr.adaming.dao.IMatiereDao;
import com.fr.adaming.entity.Examen;

@Service ("examenservice")
public class ExamenService implements IExamenService {
	
	@Autowired
	private IExamenDao dao;
	
	@Autowired
	private IMatiereDao matiereDao;

	@Override
	public Examen create(Examen exam) {
		try {
			if (exam == null || dao.existsById(exam.getId())) {
				return null;
			}
			return dao.save(exam);
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
	public List<Examen> findAll() {
		return dao.findAll();
	}

	@Override
	public Examen findById(int id) {
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
	public boolean update(Examen exam) {
		try {
			if (dao.existsById(exam.getId())) {
				dao.save(exam);
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
	public List<Examen> listByMatiere(int idMatiere){
		List<Examen> list =null;
		try {
			if (matiereDao.findById(idMatiere) != null && idMatiere != 0) {
				 list = dao.listByMatiere(idMatiere);
			}
		}catch (InvalidDataAccessApiUsageException e) {
			e.printStackTrace();
		} catch (EmptyResultDataAccessException er) {
			er.printStackTrace();
		}
		return list;
	}

}
