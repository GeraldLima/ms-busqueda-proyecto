package com.busqueda.proyecto.repositorio;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.busqueda.proyecto.entidad.ProjectEntity;

@Repository
public interface ProjectRepository extends JpaRepository<ProjectEntity, Long> {

	@Query("SELECT proj FROM ProjectEntity proj "
			+ "WHERE proj.id = :idProject AND proj.active = TRUE ")
	Optional<ProjectEntity> findProjectById(@Param("idProject") Long idProject);

	@Query("SELECT proj FROM ProjectEntity proj "
			+ "INNER JOIN OrganizationEntity org ON org.orcid = proj.idOrganization AND org.active = TRUE "
			+ "WHERE proj.idOrganization = :idOrganization AND proj.active = TRUE ")
	List<ProjectEntity> findProjectsByIdOrganization(@Param("idOrganization") String idOrganization);
}
