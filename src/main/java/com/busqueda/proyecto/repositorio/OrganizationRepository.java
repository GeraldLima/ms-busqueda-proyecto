package com.busqueda.proyecto.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.busqueda.proyecto.entidad.OrganizationEntity;

@Repository
public interface OrganizationRepository extends JpaRepository<OrganizationEntity, Long> {

	@Query("SELECT org FROM OrganizationEntity org WHERE org.idOrganization = :id ")
	OrganizationEntity findByIdOrganization(@Param("id") String id);

	@Query("SELECT org.idOrganization FROM OrganizationEntity org "
			+ "JOIN org.userUuid user ON user.id = :idUser "
			+ "WHERE org.active = TRUE AND user.active = TRUE "
			+ "AND user.endLifeDate IS NOT NULL ")
	String findByUuid(@Param("idUser") String idUser);
}
