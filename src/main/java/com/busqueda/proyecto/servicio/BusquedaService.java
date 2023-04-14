package com.busqueda.proyecto.servicio;

import com.busqueda.proyecto.entidad.OrganizationEntity;
import com.busqueda.proyecto.entidad.ScientistEntity;

public interface BusquedaService {

	Long postScientist(ScientistEntity m);
	
	ScientistEntity getScientistById(String id);
	
	Long postOrganization(OrganizationEntity org);
	
	OrganizationEntity getOrganizationById(String id);
}
