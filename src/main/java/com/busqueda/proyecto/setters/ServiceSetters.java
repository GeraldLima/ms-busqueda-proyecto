package com.busqueda.proyecto.setters;

import org.springframework.stereotype.Component;

import com.busqueda.proyecto.entidad.PublicationEntity;
import com.busqueda.proyecto.entidad.ScientistEntity;
import com.busqueda.proyecto.entidad.SearchUserEntity;
import com.busqueda.proyecto.utils.ProjectUtils;

@Component
public class ServiceSetters {

	public ScientistEntity updateScientistSetter(ScientistEntity sc) {

		ScientistEntity scNew = new ScientistEntity();
		
		scNew.setId(sc.getId());
		scNew.setOrcid(sc.getOrcid());
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
}
