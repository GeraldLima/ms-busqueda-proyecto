package com.busqueda.proyecto.repositorio;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.busqueda.proyecto.entidad.ScientistEntity;

@Repository
public interface ScientistRepository extends JpaRepository<ScientistEntity, Long> {

	@Query("SELECT sc FROM ScientistEntity sc WHERE sc.id = :id "
			+ "AND sc.active = TRUE")
	Optional<ScientistEntity> findById(@Param("id") Long id);
	
	@Query("SELECT sc FROM ScientistEntity sc WHERE sc.orcid = :orcid "
			+ "AND sc.active = TRUE")
	ScientistEntity findByOrcid(@Param("orcid") String orcid);

	@Modifying
	@Query("SELECT sc FROM ScientistEntity sc WHERE sc.orcid = :orcid "
			+ "AND sc.active = TRUE ")
	boolean deleteByOrcid(@Param("orcid") String orcid);

	@Query("SELECT sc FROM ScientistEntity sc "
			+ "WHERE sc.userUuid = :idUser AND sc.active = TRUE ")
	ScientistEntity findByUuid(@Param("idUser") String idUser);

	@Query("SELECT CASE WHEN COUNT (sc) > 0 THEN false ELSE true END FROM ScientistEntity sc "
			+ "WHERE sc.orcid = :orcidIn AND sc.active = TRUE ")
	Boolean existsByOrcid(@Param("orcidIn") String orcidIn);

	@Query("SELECT sc FROM ScientistEntity sc WHERE sc.orcid = :orcid "
			+ "AND sc.active = FALSE")
	ScientistEntity findDeactivatedScientist(@Param("orcid") String orcid);
}
