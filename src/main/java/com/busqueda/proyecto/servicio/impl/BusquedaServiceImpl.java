package com.busqueda.proyecto.servicio.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.busqueda.proyecto.entidad.OrganizationEntity;
import com.busqueda.proyecto.entidad.ScientistEntity;
import com.busqueda.proyecto.exception.ProyectSearchException;
import com.busqueda.proyecto.repositorio.OrganizationRepository;
import com.busqueda.proyecto.repositorio.ScientistRepository;
import com.busqueda.proyecto.servicio.BusquedaService;
import com.busqueda.proyecto.setters.ServiceSetters;

@Service
public class BusquedaServiceImpl implements BusquedaService {

	@Autowired
	private ScientistRepository scientistRepository;
	
	@Autowired
	private OrganizationRepository organizationRepository;
	
	@Autowired
	private ServiceSetters serviceSetter;
	
	@Override
	public Long postScientist(ScientistEntity sc) {
		
		ScientistEntity scientist = scientistRepository.save(sc);
		
		return scientist.getId();
	}

	@Override
	public ScientistEntity getScientistById(String id) {

		ScientistEntity scientist = scientistRepository.findByOrcid(id);
		
		return scientist != null? scientist : null;
	}	

	@Override
	public ScientistEntity putScientist(Long idScientist, ScientistEntity sc) {
		
		if (scientistRepository.findById(idScientist).isEmpty()) {
			throw new ProyectSearchException("No Scientist found with that id");
		}
		
		ScientistEntity newSc = this.serviceSetter.updateScientistSetter(sc);
		
		ScientistEntity scientist = scientistRepository.save(newSc);
		
		return scientist;
	}

	@Override
	public List<ScientistEntity> getScientists() {
		List<ScientistEntity> Scientists = scientistRepository.findAll();
		
		return Scientists;
	}

	@Override
	public Boolean deleteScientist(String orcid) {
		return (scientistRepository.deleteByOrcid(orcid))? true : false;
	}
	
	@Override
	public Long postOrganization(OrganizationEntity org) {
		
		OrganizationEntity organization = organizationRepository.save(org);
		
		return organization.getId();
	}

	@Override
	public OrganizationEntity getOrganizationById(String id) {

		OrganizationEntity organization = organizationRepository.findByIdOrganization(id);
		
		return organization != null? organization : null;
	}
	
}
