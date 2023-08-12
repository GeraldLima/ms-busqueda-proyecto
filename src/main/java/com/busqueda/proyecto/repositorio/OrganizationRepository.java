package com.busqueda.proyecto.repositorio;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.busqueda.proyecto.entidad.OrganizationEntity;

@Repository
public interface OrganizationRepository extends JpaRepository<OrganizationEntity, Long> {

	@Query("SELECT org FROM OrganizationEntity org WHERE org.id = :id "
			+ "AND org.active = TRUE ")
	Optional<OrganizationEntity> findById(@Param("id") Long id);
	
	@Query("SELECT org FROM OrganizationEntity org WHERE org.idOrganization = :id "
			+ "AND org.active = TRUE ")
	OrganizationEntity findByIdOrganization(@Param("id") String id);

	@Query("SELECT org FROM OrganizationEntity org "
			+ "WHERE org.userUuid = :idUser AND org.active = TRUE ")
	OrganizationEntity findByUuid(@Param("idUser") String idUser);
	
	@Query("SELECT org FROM OrganizationEntity org "
			+ "WHERE UPPER(org.name) LIKE CONCAT('%', UPPER(:nameOrg), '%') "
			+ "AND org.active = TRUE ")
	List<OrganizationEntity> findOrganizationsByName(@Param("nameOrg") String nameOrg);
}
