package com.busqueda.proyecto.servicio;

import java.util.List;

import com.busqueda.proyecto.entidad.OrganizationEntity;
import com.busqueda.proyecto.entidad.ScientistEntity;

public interface BusquedaService {

	Long postScientist(ScientistEntity m);
	
	ScientistEntity getScientistById(String id);
	
	Long postOrganization(OrganizationEntity org);
	
	OrganizationEntity getOrganizationById(String id);

	ScientistEntity putScientist(Long idScientist, ScientistEntity sc);

	List<ScientistEntity> getScientists();

	Boolean deleteScientist(String orcid);
}
