package com.busqueda.proyecto.setters;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.busqueda.proyecto.entidad.OrganizationEntity;
import com.busqueda.proyecto.entidad.ProjectEntity;
import com.busqueda.proyecto.entidad.PublicationEntity;
import com.busqueda.proyecto.entidad.ScientistEntity;
import com.busqueda.proyecto.entidad.SearchUserEntity;
import com.busqueda.proyecto.utils.ProjectUtils;

@Component
public class ServiceSetters {

	private Random random;
	
	public ScientistEntity updateScientistSetter(ScientistEntity old, ScientistEntity sc) {
		
//		old.setId(sc.getId());
//		old.setOrcid(sc.getOrcid());
		old.setName(sc.getName());
		old.setEmail(sc.getEmail());
		old.setProfession(sc.getProfession());
//		old.setActive(sc.getActive());
		
		return old;
	}

	public SearchUserEntity postSearchUserSetter(SearchUserEntity user) {

		user.setActive(true);
		user.setInitLifeDate(ProjectUtils.getLocalDateTimeNow());
		
		return user;
	}

	public PublicationEntity postPublicationSetter(PublicationEntity publication) {
		
		publication.setActive(true);
//		publication.setId(id);
		publication.setInitLifeDate(ProjectUtils.getLocalDateTimeNow());
		
		
		return publication;
	}

	public PublicationEntity updatePublicationSetter(PublicationEntity old, PublicationEntity pub) {

//		old.setId(pub.getId());
		old.setTitle(pub.getTitle());
		old.setExpertise(pub.getExpertise());
		old.setProfExperience(pub.getProfExperience());
		old.setDescription(pub.getDescription());
//		old.setActive(pub.getActive());
		old.setUpdateLife(ProjectUtils.getLocalDateTimeNow());
		
		return old;
	}

	public OrganizationEntity updateOrganizationSetter(OrganizationEntity old, OrganizationEntity org) {

		
//		old.setId(org.getId());
//		old.setIdOrganization(org.getIdOrganization());
//		old.setUserUuid(org.getUserUuid());
		old.setName(org.getName());
		old.setEmail(org.getEmail());
		old.setLocation(org.getLocation());
		old.setArea(org.getArea());
//		old.setActive(org.getActive());
		
		return old;
	}

	public ProjectEntity postProjectSetter(ProjectEntity project) {

		project.setActive(true);
//		project.setId(id);
		project.setInitLifeDate(ProjectUtils.getLocalDateTimeNow());
		
		return project;
	}

	public ProjectEntity updateProjectSetter(ProjectEntity old, ProjectEntity proj) {

//		old.setId(proj.getId());
		old.setTitle(proj.getTitle());
		old.setDescription(proj.getDescription());
		old.setCapacity(proj.getCapacity());
		old.setDuration(proj.getDuration());
//		old.setActive(proj.getActive());
		old.setUpdateLife(ProjectUtils.getLocalDateTimeNow());
		
		return old;
	}
	
	protected Integer getRandomInteger() {
		Set<Integer> usedNumbers = new HashSet<Integer>();
		
		while (usedNumbers.size() < 100) {
			int randomN = random.nextInt(100);
			
			if (!usedNumbers.contains(randomN)) {
				usedNumbers.add(randomN);
			}
		}	
	
		return null;
	}
}
