package com.busqueda.proyecto.repositorio;

import org.springframework.data.domain.Page;

import com.busqueda.proyecto.entidad.ProjectEntity;
import com.busqueda.proyecto.entidad.ScientistEntity;

import dto.ProjectMetrics;
import dto.ScientistMetrics;

public interface DynamicRepository {

	Page<ProjectEntity> findRecomendedProjects(ProjectMetrics metrics);
	
	Page<ScientistEntity> findRecomendedScientists(ScientistMetrics metrics);
}
