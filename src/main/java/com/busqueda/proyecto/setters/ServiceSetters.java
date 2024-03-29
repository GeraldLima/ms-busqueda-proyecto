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
	
	public ScientistEntity insertScientistSetter(ScientistEntity sc) {
		
		sc.setAvailable(true);
		sc.setActive(true);
		
		return sc;
	}
			
	public ScientistEntity updateScientistSetter(ScientistEntity old, ScientistEntity sc) {
		
		old.setName(sc.getName());
		old.setEmail(sc.getEmail());
		old.setProfession(sc.getProfession());
		
		return old;
	}

	public SearchUserEntity postSearchUserSetter(SearchUserEntity user) {

		user.setActive(true);
		user.setInitLifeDate(ProjectUtils.getLocalDateTimeNow());
		
		return user;
	}

	public PublicationEntity postPublicationSetter(PublicationEntity publication) {
		
		publication.setActive(true);
		publication.setInitLifeDate(ProjectUtils.getLocalDateTimeNow());
		
		
		return publication;
	}

	public PublicationEntity updatePublicationSetter(PublicationEntity old, PublicationEntity pub) {

		old.setTitle(pub.getTitle());
		old.setExpertise(pub.getExpertise());
		old.setProfExperience(pub.getProfExperience());
		old.setDescription(pub.getDescription());
		old.setUpdateLife(ProjectUtils.getLocalDateTimeNow());
		
		return old;
	}

	public OrganizationEntity updateOrganizationSetter(OrganizationEntity old, OrganizationEntity org) {

		old.setName(org.getName());
		old.setEmail(org.getEmail());
		old.setLocation(org.getLocation());
		old.setArea(org.getArea());
		
		return old;
	}

	public ProjectEntity postProjectSetter(ProjectEntity project) {

		project.setActive(true);
		project.setSize(0);
		project.setFull(false);
		project.setInitLifeDate(ProjectUtils.getLocalDateTimeNow());
		
		return project;
	}

	public ProjectEntity updateProjectSetter(ProjectEntity old, ProjectEntity proj) {

		old.setTitle(proj.getTitle());
		old.setDescription(proj.getDescription());
		old.setCapacity(proj.getCapacity());
		old.setDuration(proj.getDuration());
		old.setScope(proj.getScope());
		old.setSubscope(proj.getSubscope());
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
