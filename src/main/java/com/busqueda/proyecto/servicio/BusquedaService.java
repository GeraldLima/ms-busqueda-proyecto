package com.busqueda.proyecto.servicio;

import java.util.List;

import com.busqueda.proyecto.entidad.OrganizationEntity;
import com.busqueda.proyecto.entidad.ScientistEntity;
import com.busqueda.proyecto.entidad.SearchUserEntity;

import dto.GetLoginDTO;

public interface BusquedaService {

	Long postScientist(ScientistEntity m);
	
	ScientistEntity getScientistByOrcid(String orcid);
	
	ScientistEntity getScientistById(Long id);
	
	Long postOrganization(OrganizationEntity org);
	
	OrganizationEntity getOrganizationById(String id);

	ScientistEntity putScientist(Long idScientist, ScientistEntity sc);

	List<ScientistEntity> getScientists();

	Boolean deleteScientist(String orcid);

	Long postUserUUID(SearchUserEntity user);
	
	GetLoginDTO loginProcess(String uuidUser);
	
}
