package com.busqueda.proyecto.servicio.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.busqueda.proyecto.entidad.ProjectEntity;
import com.busqueda.proyecto.entidad.PublicationEntity;
import com.busqueda.proyecto.exception.ProyectSearchException;
import com.busqueda.proyecto.repositorio.OrganizationRepository;
import com.busqueda.proyecto.repositorio.ProjectRepository;
import com.busqueda.proyecto.repositorio.PublicationRepository;
import com.busqueda.proyecto.repositorio.ScientistRepository;
import com.busqueda.proyecto.servicio.PublicationService;
import com.busqueda.proyecto.setters.ServiceSetters;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional
public class PublicationServiceImpl implements PublicationService {

	@Autowired
	private PublicationRepository publicationRepository;

	@Autowired
	private ScientistRepository scientistRepository;
	
	@Autowired
	private ProjectRepository projectRepository;
	
	@Autowired
	private OrganizationRepository organizationRepository;
	
	@Autowired
	private ServiceSetters serviceSetter;
	
	@Override
	public PublicationEntity getPublicationById(Long id) {

		Optional<PublicationEntity> publication = publicationRepository.findPublicationById(id);
		
		return publication.orElse(null);
	}

	@Override
	public Long postPublication(PublicationEntity publication) {
		
		log.debug("Entering postPublication [request]:{} ",  publication);
		
		publication = serviceSetter.postPublicationSetter(publication);
		//TODO id of scientist
//		Optional<ScientistEntity> scientist = scientistRepository.findById(publication.getIdScientist());
//		publication.setScientist(scientist.get());
		
		try {
			PublicationEntity responseEntity = publicationRepository.save(publication);
			log.debug("Leaving postPublication [response]:{} ",  responseEntity);
			
			return responseEntity.getId();
		} catch (Exception e) {
			log.error("Error with postPublication service ");
			throw new ProyectSearchException("Error in repository response." + e);
		}
	}

	@Override
	public PublicationEntity putPublication(Long idPublication, PublicationEntity pub) {

		if (publicationRepository.findById(idPublication).isEmpty()) {
			throw new ProyectSearchException("No Publication was found with that id");
		}
		
		PublicationEntity newPub = serviceSetter.updatePublicationSetter(pub);
		
		PublicationEntity publication = publicationRepository.save(newPub);
		
		return publication;
	}

	@Override
	public List<PublicationEntity> getPublications(String idScientist) {

		List<PublicationEntity> publications = publicationRepository.findPublicationsByIdScientist(idScientist);
		
		return publications;
	}

	@Override
	public Boolean deletePublication(Long id) {

		Boolean deleted = false;
		
		Optional<PublicationEntity> publication = publicationRepository.findPublicationById(id);
		
		if (publication.get().getId() != null) {
			publication.get().setActive(false);
			publicationRepository.save(publication.get());
			deleted = true;
		}
		//return (scientistRepository.deleteByOrcid(orcid))? true : false;
		
		return deleted;
	}

	@Override
	public ProjectEntity getProjectById(Long id) {

		Optional<ProjectEntity> project = projectRepository.findProjectById(id);
		
		return project.orElse(null);
	}

	@Override
	public Long postProject(ProjectEntity project) {

		log.debug("Entering postProject [request]:{} ",  project);
		
		project = serviceSetter.postProjectSetter(project);
		//TODO id of organization
//		Optional<OrganizationEntity> org = organizationRepository.findById(project.getIdOrganization());
//		project.setOrganization(org.get());
		
		try {
			ProjectEntity responseEntity = projectRepository.save(project);
			log.debug("Leaving postProject [response]:{} ",  responseEntity);
			
			return responseEntity.getId();
		} catch (Exception e) {
			log.error("Error with postProject service ");
			throw new ProyectSearchException("Error in repository response." + e);
		}
	}

	@Override
	public ProjectEntity putProject(Long idProject, ProjectEntity proj) {
		
		if (projectRepository.findById(idProject).isEmpty()) {
			throw new ProyectSearchException("No Project was found with that id");
		}
		
		ProjectEntity newProj = serviceSetter.updateProjectSetter(proj);
		
		ProjectEntity project = projectRepository.save(newProj);
		
		return project;
	}

	@Override
	public List<ProjectEntity> getProjects(String idOrganization) {

		List<ProjectEntity> projects = projectRepository.findProjectsByIdOrganization(idOrganization);
		
		return projects;
	}

	@Override
	public Boolean deleteProject(Long id) {

		Boolean deleted = false;
		
		Optional<ProjectEntity> project = projectRepository.findProjectById(id);
		
		if (project.get().getId() != null) {
			project.get().setActive(false);
			projectRepository.save(project.get());
			deleted = true;
		}
		//return (organizationRepository.deleteByIdOrganization(idOrg))? true : false;
		
		return deleted;
	}

	
}
