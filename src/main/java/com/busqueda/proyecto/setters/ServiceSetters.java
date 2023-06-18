package com.busqueda.proyecto.setters;

import org.springframework.stereotype.Component;

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
		scNew.setSpeciality(sc.getSpeciality());
		scNew.setActive(sc.getActive());
		
		return scNew;
	}

	public SearchUserEntity postSearchUserSetter(SearchUserEntity user) {

		user.setActive(true);
		user.setInitLifeDate(ProjectUtils.getLocalDateTimeNow());
		
		return user;
	}
}
