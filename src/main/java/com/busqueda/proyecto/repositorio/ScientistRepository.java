package com.busqueda.proyecto.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.busqueda.proyecto.entidad.ScientistEntity;

@Repository
public interface ScientistRepository extends JpaRepository<ScientistEntity, Long> {

	@Query("SELECT sc FROM ScientistEntity sc WHERE sc.orcid = :orcid ")
	ScientistEntity findByOrcid(@Param("orcid") String orcid);
}
