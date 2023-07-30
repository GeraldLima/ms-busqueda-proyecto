package com.busqueda.proyecto.servicio;

import java.util.List;

import com.busqueda.proyecto.entidad.ProjectEntity;
import com.busqueda.proyecto.entidad.PublicationEntity;

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

}