package com.busqueda.proyecto.repositorio;

import org.springframework.data.domain.Page;

import com.busqueda.proyecto.entidad.ProjectEntity;

import dto.ProjectMetrics;

public interface DynamicRepository {

	Page<ProjectEntity> findRecomendedProjects(ProjectMetrics metrics);
}
