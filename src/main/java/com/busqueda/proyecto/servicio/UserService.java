package com.busqueda.proyecto.servicio;

import java.util.List;

import com.busqueda.proyecto.entidad.OrganizationEntity;
import com.busqueda.proyecto.entidad.ScientistEntity;
import com.busqueda.proyecto.entidad.SearchUserEntity;

import dto.GetLoginDTO;

public interface UserService {

	Long postScientist(ScientistEntity m);
	
	ScientistEntity getScientistByOrcid(String orcid);
	
	ScientistEntity getScientistById(Long id);
	
	ScientistEntity putScientist(Long idScientist, ScientistEntity sc);

	List<ScientistEntity> getScientists();

	Boolean deleteScientist(String orcid);
	
	Long postOrganization(OrganizationEntity org);
	
	OrganizationEntity getOrganizationById(Long id);
	
	OrganizationEntity getOrganizationByIdOrg(String id);

	OrganizationEntity putOrganization(Long idOrganization, OrganizationEntity org);

	List<OrganizationEntity> getOrganizations();

	Boolean deleteOrganization(String idOrganization);

	Long postUserUUID(SearchUserEntity user);
	
	GetLoginDTO loginProcess(String uuidUser);

	List<OrganizationEntity> getOrganizationsByName(String nameOrg);
	
}
