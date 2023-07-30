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
	
	public ScientistEntity updateScientistSetter(ScientistEntity sc) {

		ScientistEntity scNew = new ScientistEntity();
		
		scNew.setId(sc.getId());
//		scNew.setOrcid(sc.getOrcid());
		scNew.setName(sc.getName());
		scNew.setEmail(sc.getEmail());
		scNew.setProfession(sc.getProfession());
		scNew.setActive(sc.getActive());
		
		return scNew;
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

	public PublicationEntity updatePublicationSetter(PublicationEntity pub) {

		PublicationEntity newPub = new PublicationEntity();
		
		newPub.setId(pub.getId());
		newPub.setTitle(pub.getTitle());
		newPub.setExpertise(pub.getExpertise());
		newPub.setProfExperience(pub.getProfExperience());
		newPub.setDescription(pub.getDescription());
		newPub.setActive(pub.getActive());
		newPub.setUpdateLife(ProjectUtils.getLocalDateTimeNow());
		
		return newPub;
	}

	public OrganizationEntity updateOrganizationSetter(OrganizationEntity org) {

		OrganizationEntity newOrg = new OrganizationEntity();
		
		newOrg.setId(org.getId());
//		newOrg.setIdOrganization(org.getIdOrganization());
//		newOrg.setUserUuid(org.getUserUuid());
		newOrg.setName(org.getName());
		newOrg.setEmail(org.getEmail());
		newOrg.setLocation(org.getLocation());
		newOrg.setArea(org.getArea());
		newOrg.setActive(org.getActive());
		
		return newOrg;
	}

	public ProjectEntity postProjectSetter(ProjectEntity project) {

		project.setActive(true);
//		project.setId(id);
		project.setInitLifeDate(ProjectUtils.getLocalDateTimeNow());
		
		return project;
	}

	public ProjectEntity updateProjectSetter(ProjectEntity proj) {

		ProjectEntity newProj = new ProjectEntity();
		newProj.setId(proj.getId());
		newProj.setTitle(proj.getTitle());
		newProj.setDescription(proj.getDescription());
		newProj.setCapacity(proj.getCapacity());
		newProj.setDuration(proj.getDuration());
		newProj.setActive(proj.getActive());
		newProj.setUpdateLife(ProjectUtils.getLocalDateTimeNow());
		
		return newProj;
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
