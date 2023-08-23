package com.busqueda.proyecto.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.busqueda.proyecto.entidad.AsignationEntity;
import com.busqueda.proyecto.entidad.ProjectEntity;
import com.busqueda.proyecto.entidad.ScientistEntity;

@Repository
public interface AsignationRepository extends JpaRepository<AsignationEntity, Long> {

	@Query("SELECT proj FROM ProjectEntity proj "
			+ "INNER JOIN AsignationEntity asig ON asig.idProject = proj.id "
			+ "INNER JOIN ScientistEntity sc ON sc.orcid = asig.idScientist "
			+ "WHERE sc.orcid = :orcid ")
	List<ProjectEntity> findProjectsByIdScientist(@Param("orcid") String orcid);

	@Query("SELECT sc FROM ScientistEntity sc "
			+ "INNER JOIN AsignationEntity asig ON asig.idScientist = sc.orcid "
			+ "INNER JOIN ProjectEntity proj ON proj.id = asig.idProject "
			+ "WHERE proj.id = :idProject ")
	List<ScientistEntity> findScientistsByIdProject(@Param("idProject") Long idProject);
	
}