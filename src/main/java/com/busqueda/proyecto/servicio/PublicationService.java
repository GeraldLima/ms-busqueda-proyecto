package com.busqueda.proyecto.servicio;

import java.util.List;

import org.springframework.data.domain.Page;

import com.busqueda.proyecto.entidad.ProjectEntity;
import com.busqueda.proyecto.entidad.PublicationEntity;
import com.busqueda.proyecto.entidad.ScientistEntity;

public interface PublicationService {

	PublicationEntity getPublicationById(Long id);

	Long postPublication(PublicationEntity publication);

	PublicationEntity putPublication(Long idPublication, PublicationEntity pub);

	List<PublicationEntity> getPublications(String idScientist);

	Boolean deletePublication(Long id);

	ProjectEntity getProjectById(Long id);

	Long postProject(ProjectEntity project);

	ProjectEntity putProject(Long idProject, ProjectEntity proj);

	List<ProjectEntity> getProjects(String idOrganization);

	Boolean deleteProject(Long id);
	
	Boolean reactivateProject(Long idProject);

	Boolean reactivatePublication(Long idPublication);

	Page<PublicationEntity> getAllPublications(Integer page, Integer size);

	Page<ProjectEntity> getAllProjects(Integer page, Integer size);

	Page<ScientistEntity> getRecommendedScientists(Long idProject);

}
