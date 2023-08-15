package com.busqueda.proyecto.servicio.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.busqueda.proyecto.entidad.OrganizationEntity;
import com.busqueda.proyecto.entidad.ProjectEntity;
import com.busqueda.proyecto.entidad.PublicationEntity;
import com.busqueda.proyecto.entidad.ScientistEntity;
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

		log.debug("Entering getPublicationById [id]:{} ",  id);
		
		Optional<PublicationEntity> publication = Optional.of(publicationRepository.findPublicationById(id)
				.orElseThrow());
		
		log.debug("Leaving getPublicationById [response]:{} ",  publication);
		
		return publication.get();
	}

	@Override
	public Long postPublication(PublicationEntity publication) {
		
		log.debug("Entering postPublication [request]:{} ",  publication);

		ScientistEntity scientist = scientistRepository.findByOrcid(publication.getIdScientist());
		if (scientist == null) {
			throw new ProyectSearchException("The current user is deactivated");
		}
		
		try {
			publication = serviceSetter.postPublicationSetter(publication);
			PublicationEntity responseEntity = publicationRepository.save(publication);
			log.debug("Leaving postPublication [response]:{} ",  responseEntity);
			
			return responseEntity.getId();
		} catch (Exception e) {
			log.error("Error with postPublication service ");
			throw new ProyectSearchException("Error in repository response. " + e);
		}
	}

	@Override
	public PublicationEntity putPublication(Long idPublication, PublicationEntity pub) {

		Optional<PublicationEntity> oldPubl = Optional.of(publicationRepository.findPublicationById(idPublication)
				.orElse(null));
		
		if (oldPubl.isEmpty()) {
			throw new ProyectSearchException("No Publication was found with that id");
		}
		
		PublicationEntity newPub = serviceSetter.updatePublicationSetter(oldPubl.get(), pub);
		
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

		log.debug("Entering getProjectById [id]:{} ",  id);
		
		Optional<ProjectEntity> project = Optional.of(projectRepository.findProjectById(id)
				.orElseThrow());
		
		log.debug("Leaving getProjectById [response]:{} ",  project);
		
		return project.get();
	}

	@Override
	public Long postProject(ProjectEntity project) {

		log.debug("Entering postProject [request]:{} ",  project);

		OrganizationEntity org = organizationRepository.findByIdOrganization(project.getIdOrganization());

		if (org == null) {
			throw new ProyectSearchException("The current user is deactivated");
		}
		try {
			project = serviceSetter.postProjectSetter(project);
			ProjectEntity responseEntity = projectRepository.save(project);
			log.debug("Leaving postProject [response]:{} ",  responseEntity);
			
			return responseEntity.getId();
		} catch (Exception e) {
			log.error("Error with postProject service ");
			throw new ProyectSearchException("Error in repository response. " + e);
		}
	}

	@Override
	public ProjectEntity putProject(Long idProject, ProjectEntity proj) {
		
		Optional<ProjectEntity> oldProj = Optional.of(projectRepository.findProjectById(idProject)
				.orElse(null));
		
		if (oldProj.isEmpty()) {
			throw new ProyectSearchException("No Project was found with that id");
		}
		
		ProjectEntity newProj = serviceSetter.updateProjectSetter(oldProj.get(), proj);
		
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
	
	@Override
	public Page<PublicationEntity> getAllPublications(Integer page, Integer size) {
		
		Page<PublicationEntity> publications = null;
		Pageable pubPageable = Pageable.unpaged();
		
		try {
			if (page != null && size != null) {
				pubPageable = PageRequest.of(page, size);
			}
			publications = publicationRepository.findAll(pubPageable);			
			
			if (publications.isEmpty()) {
				throw new ProyectSearchException("No Publications were found");
			}
		} catch (Exception e) {
			log.error("Error in getAllPublications service ");
			throw new ProyectSearchException("Error in repository response. " + e);
		}
		return publications;
	}

	@Override
	public Page<ProjectEntity> getAllProjects(Integer page, Integer size) {
		
		Page<ProjectEntity> projects = null;
		Pageable projPageable = Pageable.unpaged();
		
		try {
			if (page != null && size != null) {
				projPageable = PageRequest.of(page, size);
			}
			projects = projectRepository.findAll(projPageable);			
			
			if (projects.isEmpty()) {
				throw new ProyectSearchException("No Projects were found");
			}
		} catch (Exception e) {
			log.error("Error in getAllProjects service ");
			throw new ProyectSearchException("Error in repository response. " + e);
		}
		return projects;
	}
	
}
