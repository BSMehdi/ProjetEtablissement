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

import lombok.extern.slf4j.Slf4j;

@Service ("examenservice")
@Slf4j
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
			log.info("Creation d'un examen");
			return dao.save(exam);
		} catch (DataIntegrityViolationException e) {
			log.error("DataIntegrityViolationException");
			return null;
		}

		catch (ConstraintViolationException er) {
			log.error("ConstraintViolationException");
			return null;
		}
	}

	@Override
	public List<Examen> findAll() {
		log.info("Recuperation de la liste des examens");
		return dao.findAll();
	}

	@Override
	public Examen findById(int id) {
		try {
			if (id != 0) {
				log.info("Recuperation d'un Examen");
				return dao.findById(id).orElse(null);
			} else {
				log.warn("Tentative de recuperation d'un examen par son id");
				return null;
			}
		} catch (InvalidDataAccessApiUsageException e) {
			log.error("InvalidDataAccessApiUsageException");
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
			if (dao.findById(id).isPresent()) {
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
			if (matiereDao.findById(idMatiere).isPresent()) {
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
