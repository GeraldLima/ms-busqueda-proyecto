package com.busqueda.proyecto.servicio.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.busqueda.proyecto.constants.Constants;
import com.busqueda.proyecto.entidad.OrganizationEntity;
import com.busqueda.proyecto.entidad.ScientistEntity;
import com.busqueda.proyecto.entidad.SearchUserEntity;
import com.busqueda.proyecto.exception.ProyectSearchException;
import com.busqueda.proyecto.repositorio.OrganizationRepository;
import com.busqueda.proyecto.repositorio.ScientistRepository;
import com.busqueda.proyecto.repositorio.UserRepository;
import com.busqueda.proyecto.servicio.BusquedaService;
import com.busqueda.proyecto.setters.ServiceSetters;

import dto.GetLoginDTO;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional
public class BusquedaServiceImpl implements BusquedaService {

	@Autowired
	private ScientistRepository scientistRepository;
	
	@Autowired
	private OrganizationRepository organizationRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ServiceSetters serviceSetter;
	
	
	@Override
	public Long postScientist(ScientistEntity sc) {
		
		log.debug("Entering postScientist [request]:{} ",  sc);
		
		ScientistEntity scientist = scientistRepository.save(sc);
		
		log.debug("Leaving postScientist [response]:{} ",  scientist);
		
		return scientist.getId();
	}

	@Override
	public ScientistEntity getScientistByOrcid(String orcid) {

		ScientistEntity scientist = scientistRepository.findByOrcid(orcid);
		
		return scientist != null? scientist : null;
	}
	
	@Override
	public ScientistEntity getScientistById(Long id) {

		Optional<ScientistEntity> scientist = scientistRepository.findById(id);
		
		return scientist.orElse(null);
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

	@Override
	public Long postUserUUID(SearchUserEntity user) {

		log.debug("Entering postUserUUID [request]:{} ",  user);
		
		user = serviceSetter.postSearchUserSetter(user);
		try {
			SearchUserEntity responseEntity = userRepository.save(user);
			log.debug("Leaving postUserUUID [response]:{} ",  responseEntity);
			
			return responseEntity.getId();
		} catch (Exception e) {
			log.error("Error with postUserUUID service ");
			throw new ProyectSearchException("Error in repository response." + e);
		}		
	}
	
	@Override
	public GetLoginDTO loginProcess(String uuidUser) {

		GetLoginDTO responseDto = new GetLoginDTO();

		OrganizationEntity organism = organizationRepository.findByUuid(uuidUser);
		if (organism != null) {
			responseDto.setIdUser(organism.getId());
			responseDto.setUserType(Constants.ORGANIZATION);
		}
		else { 
			ScientistEntity scientist = scientistRepository.findByUuid(uuidUser);
			if (scientist != null) {
				responseDto.setIdUser(scientist.getId());
				responseDto.setUserType(Constants.SCIENTIST);
			}
		}
		
		//TO FIX
		if (responseDto.getIdUser() == null){
			throw new ProyectSearchException("No user found with that id: " + uuidUser);
		}

		return responseDto;
	}
	
}
