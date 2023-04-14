package com.busqueda.proyecto.servicio.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.busqueda.proyecto.entidad.OrganizationEntity;
import com.busqueda.proyecto.entidad.ScientistEntity;
import com.busqueda.proyecto.repositorio.OrganizationRepository;
import com.busqueda.proyecto.repositorio.ScientistRepository;
import com.busqueda.proyecto.servicio.BusquedaService;

@Service
public class BusquedaServiceImpl implements BusquedaService {

	@Autowired
	private ScientistRepository scientistRepository;
	
	@Autowired
	private OrganizationRepository organizationRepository;
	
	@Override
	public Long postScientist(ScientistEntity sc) {
		
		ScientistEntity scientist = scientistRepository.save(sc);
		
		return scientist.getId();
	}

	@Override
	public ScientistEntity getScientistById(Long id) {

		ScientistEntity scientist = scientistRepository.findByOrcid(id);
		
		return scientist != null? scientist : null;
	}
	
	@Override
	public Long postOrganization(OrganizationEntity org) {
		
		OrganizationEntity organization = organizationRepository.save(org);
		
		return organization.getId();
	}

	@Override
	public OrganizationEntity getOrganizationById(Long id) {

		OrganizationEntity organization = organizationRepository.findByNif(id);
		
		return organization != null? organization : null;
	}
	
	
}
