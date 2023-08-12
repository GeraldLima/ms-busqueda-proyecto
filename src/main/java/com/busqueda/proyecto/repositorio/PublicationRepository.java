package com.busqueda.proyecto.repositorio;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.busqueda.proyecto.entidad.PublicationEntity;

@Repository
public interface PublicationRepository extends JpaRepository<PublicationEntity, Long> {

	@Query("SELECT pub FROM PublicationEntity pub "
			+ "WHERE pub.id = :idPublication AND pub.active = TRUE ")
	Optional<PublicationEntity> findPublicationById(@Param("idPublication") Long idPublication);

	@Query("SELECT pub FROM PublicationEntity pub "
			+ "INNER JOIN ScientistEntity sc ON sc.orcid = pub.idScientist AND sc.active = TRUE "
			+ "WHERE pub.idScientist = :idScientist ")
	List<PublicationEntity> findPublicationsByIdScientist(@Param("idScientist") String idScientist);
}
