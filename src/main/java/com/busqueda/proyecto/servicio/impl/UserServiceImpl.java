package com.busqueda.proyecto.servicio.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.busqueda.proyecto.constants.Constants;
import com.busqueda.proyecto.entidad.OrganizationEntity;
import com.busqueda.proyecto.entidad.ProjectEntity;
import com.busqueda.proyecto.entidad.PublicationEntity;
import com.busqueda.proyecto.entidad.ScientistEntity;
import com.busqueda.proyecto.entidad.SearchUserEntity;
import com.busqueda.proyecto.exception.ProyectSearchException;
import com.busqueda.proyecto.repositorio.DynamicRepository;
import com.busqueda.proyecto.repositorio.OrganizationRepository;
import com.busqueda.proyecto.repositorio.ProjectRepository;
import com.busqueda.proyecto.repositorio.PublicationRepository;
import com.busqueda.proyecto.repositorio.ScientistRepository;
import com.busqueda.proyecto.repositorio.UserRepository;
import com.busqueda.proyecto.servicio.UserService;
import com.busqueda.proyecto.setters.ServiceSetters;
import com.busqueda.proyecto.utils.ProjectUtils;

import dto.GetLoginDTO;
import dto.ProjectMetrics;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private ScientistRepository scientistRepository;
	
	@Autowired
	private OrganizationRepository organizationRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PublicationRepository publicationRepository;
	
	@Autowired
	private ProjectRepository projectRepository;
	
	@Autowired
	private DynamicRepository dynamicRepository;
	
	@Autowired
	private ServiceSetters serviceSetter;
	
	
	@Override
	public Long postScientist(ScientistEntity sc) {
		
		log.debug("Entering postScientist [request]:{} ",  sc);
		
		
		ScientistEntity scientist = serviceSetter.insertScientistSetter(sc);
				
		scientist = scientistRepository.save(scientist);
		
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
		
		Optional<ScientistEntity> oldScientist = Optional.of(scientistRepository.findById(idScientist)
				.orElse(null));
		
		if (oldScientist.isEmpty()) {
			throw new ProyectSearchException("No Scientist found with that id");
		}
		
		ScientistEntity newSc = this.serviceSetter.updateScientistSetter(oldScientist.get(), sc);
		
		ScientistEntity scientist = scientistRepository.save(newSc);
		
		return scientist;
	}

	@Override
	public List<ScientistEntity> getScientists() {
		
		List<ScientistEntity> scientists = scientistRepository.findAll();
		
		return scientists;
	}

	@Override
	public Boolean deleteScientist(String orcid) {
		
		Boolean deleted = false;
		
		try {
			ScientistEntity scientist = scientistRepository.findByOrcid(orcid);
			if (scientist == null) {
				throw new ProyectSearchException("No Scientist found with that id");
			}
			this.deleteAllPublicationsByScientist(orcid);
			scientist.setActive(false);
			scientistRepository.save(scientist);
			deleted = true;
		} catch (Exception e) {
			log.error("Error with deleteScientist service ");
			throw new ProyectSearchException("Error in delete Scientist-Publications cascade" + e);
		}		
		//return (scientistRepository.deleteByOrcid(orcid))? true : false;
		
		return deleted;
	}
		
	protected void deleteAllPublicationsByScientist(String idScientist) {

		List<PublicationEntity> listPubli = publicationRepository.findPublicationsByIdScientist(idScientist);
		listPubli.forEach(pub -> {
			pub.setActive(false);
			publicationRepository.save(pub);
		});
	}
	
	@Override
	public Boolean getScientistIsExistsByOrcid(String orcid) {
		
		return scientistRepository.existsByOrcid(orcid);
	}
	
	@Override
	public Long postOrganization(OrganizationEntity org) {
		
		OrganizationEntity organization = organizationRepository.save(org);
		
		return organization.getId();
	}

	@Override
	public OrganizationEntity getOrganizationById(Long id) {

		Optional<OrganizationEntity> organization = organizationRepository.findById(id);
		
		return organization != null? organization.get() : null;
	}
	
	@Override
	public OrganizationEntity getOrganizationByIdOrg(String idOrganization) {

		OrganizationEntity organization = organizationRepository.findByIdOrganization(idOrganization);
		
		return organization != null? organization : null;
	}
	
	@Override
	public OrganizationEntity putOrganization(Long idOrganization, OrganizationEntity org) {
		
		Optional<OrganizationEntity> oldOrganization = Optional.of(organizationRepository.findById(idOrganization)
				.orElse(null));
		
		if (oldOrganization.isEmpty()) {
			throw new ProyectSearchException("No Organization found with that id");
		}
		
		OrganizationEntity newOrg = this.serviceSetter.updateOrganizationSetter(oldOrganization.get(), org);
		
		OrganizationEntity organization = organizationRepository.save(newOrg);
		
		return organization;
	}

	@Override
	public List<OrganizationEntity> getOrganizations() {
		
		List<OrganizationEntity> orgs = organizationRepository.findAll();
		
		return orgs;
	}

	@Override
	public List<OrganizationEntity> getOrganizationsByName(String nameOrg) {
	
		log.debug("Entering getOrganizationsByName [request]:{} ",  nameOrg);
		
		List<OrganizationEntity> listOrgs = new ArrayList<>();
		try {
			listOrgs = organizationRepository.findOrganizationsByName(nameOrg);
			log.debug("Leaving getOrganizationsByName [response]:{} ");
			
			return listOrgs;
		} catch (Exception e) {
			log.error("Error with findOrganizationsByName service ");
			throw new ProyectSearchException("Error in repository response." + e);
		}
	}
	
	@Override
	public Boolean deleteOrganization(String idOrganization) {
		
		Boolean deleted = false;
		
		try {
			OrganizationEntity org = organizationRepository.findByIdOrganization(idOrganization);
			
			if (org == null) {
				throw new ProyectSearchException("No Organization found with that id");
			}
			this.deleteAllProjectsByOrganization(idOrganization);
			org.setActive(false);
			organizationRepository.save(org);
			deleted = true;
		} catch (Exception e) {
			log.error("Error with deleteOrganization service ");
			throw new ProyectSearchException("Error in delete Organization-Project cascade" + e);
		}		
		//return (organizaionRepository.deleteByOrcid(orcid))? true : false;
		
		return deleted;
	}
	
	protected void deleteAllProjectsByOrganization(String idOrganization) {

		List<ProjectEntity> listProj = projectRepository.findProjectsByIdOrganization(idOrganization);
		listProj.forEach(proj -> {
			proj.setActive(false);
			projectRepository.save(proj);
		});
	}
	
	@Override
	public Boolean getOrganizationIsExistsById(String idOrganization) {
		
		return organizationRepository.existsByIdOrganization(idOrganization);
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
	
	@Override
	public Boolean assignmentProcess(ProjectEntity project, String orcid) {
		
		log.debug("Entering assignmentProcess [request]:{}, [orcid]:{} ",  project, orcid);
		
		Boolean assigned = false;
		Integer capacity = project.getCapacity();
		Integer size = project.getSize();
		
		if (size >= capacity || project.getFull()) {
			throw new ProyectSearchException("The curent project is already full ");
		}
		try {
			ScientistEntity sc = scientistRepository.findByOrcid(orcid);
			
			if (sc == null || !sc.getAvailable()) {
				throw new ProyectSearchException("Scientist not found or avaible with that id: " + orcid);
			}
			
			sc.setIdProject(project.getId());
			sc.setAvailable(false);		
			scientistRepository.save(sc);
			
			size +=size;
			if (capacity == size) {
				project.setFull(true);
			}
			project.setSize(size);
			project.setUpdateLife(ProjectUtils.getLocalDateTimeNow());
			projectRepository.save(project);
			
			assigned = true;
		} catch (Exception e) {
			log.error("Error with postUserUUID service ");
			throw new ProyectSearchException("Error in repository response." + e);
		}
		
		return assigned;
	}

	@Override
	public Page<ProjectEntity> getRecommendedProjects(Long idScientist, ScientistEntity request) {

		Page<ProjectEntity> responseCriteria = null;
		
		ProjectMetrics projMetrics = new ProjectMetrics();
		projMetrics.setListExpertise(new ArrayList<>());
		try {
			
			List<PublicationEntity> publications = publicationRepository
					.findPublicationsByIdScientist(request.getOrcid());
			
			for (PublicationEntity pub : publications) {
				projMetrics.getListExpertise().add(pub.getExpertise().toUpperCase());
			}
			
			projMetrics.setProfession(request.getProfession());
		
			responseCriteria = dynamicRepository.findRecomendedProjects(projMetrics);			
			
			if (responseCriteria.isEmpty()) {
				throw new ProyectSearchException(
						"Sorry, there are not recomended projects for the scientitst" + request.getName());
			}
			
		} catch (Exception e) {
			log.error("Error with getRecommendedProjects service ");
			throw new ProyectSearchException("Error in repository response." + e);
		}
		
		return responseCriteria;
	}
	
	
}
