package com.busqueda.proyecto.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.busqueda.proyecto.entidad.AssignationEntity;
import com.busqueda.proyecto.entidad.ProjectEntity;
import com.busqueda.proyecto.entidad.ScientistEntity;

import dto.GetAssignmentDTO;

@Repository
public interface AssignationRepository extends JpaRepository<AssignationEntity, Long> {

	@Query("SELECT proj FROM AssignationEntity asig "
			+ "INNER JOIN ProjectEntity proj ON asig.idProject = proj.id "
			+ "INNER JOIN ScientistEntity sc ON sc.orcid = asig.idScientist "
			+ "WHERE asig.active = TRUE AND sc.orcid = :orcid ")
	List<ProjectEntity> findProjectsByIdScientist(@Param("orcid") String orcid);

	@Query("SELECT sc FROM AssignationEntity asig "
			+ "INNER JOIN ScientistEntity sc ON asig.idScientist = sc.orcid "
			+ "INNER JOIN ProjectEntity proj ON proj.id = asig.idProject "
			+ "WHERE asig.active = TRUE AND proj.id = :idProject ")
	List<ScientistEntity> findScientistsByIdProject(@Param("idProject") Long idProject);

	@Query("SELECT NEW dto.GetAssignmentDTO (asig, sc, proj) FROM AssignationEntity asig "
			+ "INNER JOIN ScientistEntity sc ON asig.idScientist = sc.orcid "
			+ "INNER JOIN ProjectEntity proj ON proj.id = asig.idProject "
			+ "WHERE asig.active = TRUE "
			+ "AND asig.idScientist = :orcid AND asig.idProject = :idProject ")
	GetAssignmentDTO findAssignment(@Param("orcid") String orcid, @Param("idProject") Long idProject);
	
}