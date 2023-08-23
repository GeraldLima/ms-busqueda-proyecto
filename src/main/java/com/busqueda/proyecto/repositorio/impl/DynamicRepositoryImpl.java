package com.busqueda.proyecto.repositorio.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.busqueda.proyecto.entidad.ProjectEntity;
import com.busqueda.proyecto.entidad.ScientistEntity;
import com.busqueda.proyecto.repositorio.DynamicRepository;

import dto.ProjectMetrics;
import dto.ScientistMetrics;
import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class DynamicRepositoryImpl implements DynamicRepository {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public Page<ProjectEntity> findRecomendedProjects(ProjectMetrics metrics) {

		log.debug("Entering into findRecomendedProjects dynamic-repository");
		
		StringBuilder queryBuilder = new StringBuilder();
		
		queryBuilder.append("SELECT proj FROM ProjectEntity proj "
				+ "WHERE proj.active = TRUE AND proj.full = FALSE ");
		
		if (metrics.getProfession() != null && 
				StringUtils.isNotBlank(metrics.getProfession())) {
			queryBuilder.append("AND ((UPPER(proj.scope) LIKE CONCAT('%', UPPER(:profession), '%')) "
					+ "OR (UPPER(proj.subscope) LIKE CONCAT('%', UPPER(:profession), '%'))) ");
		}
		
		if (!CollectionUtils.isEmpty(metrics.getListExpertise())) {
			queryBuilder.append(" OR ((UPPER(proj.scope) IN (:listExpertise)) "
					+ "OR (UPPER(proj.subscope) IN (:listExpertise))) ");
		}
		
		TypedQuery<ProjectEntity> query = entityManager.
				createQuery(queryBuilder.toString(), ProjectEntity.class);

		if (metrics.getProfession() != null && 
				StringUtils.isNotBlank(metrics.getProfession())) {
			query.setParameter("profession", metrics.getProfession());
		}

		if (!CollectionUtils.isEmpty(metrics.getListExpertise())) {
			query.setParameter("listExpertise", metrics.getListExpertise());
		}

		Pageable pageableRequest = Pageable.unpaged();
		Long count = 5L;
		Page<ProjectEntity> responseCriteria = new PageImpl<ProjectEntity>
		(query.getResultList(), pageableRequest, count);
		
		log.debug("Leaving findRecomendedProjects dynamic-repository, [result]:", responseCriteria);

		return responseCriteria;
	}

	@Override
	public Page<ScientistEntity> findRecomendedScientists(ScientistMetrics metrics) {
		
		log.debug("Entering into findRecomendedScientists dynamic-repository");
		
		StringBuilder queryBuilder = new StringBuilder();
		
		queryBuilder.append("SELECT sc FROM ScientistEntity sc "
//				+ "INNER JOIN PublicationEntity pub ON pub.idScientist = sc.orcid "
				+ "WHERE sc.active = TRUE ");
		
		if (metrics.getScope() != null && 
				StringUtils.isNotBlank(metrics.getScope())) {
			queryBuilder.append("AND (UPPER(sc.profession) LIKE CONCAT('%', UPPER(:scope), '%')) ");
		}
		
		if (metrics.getScope() != null && 
				StringUtils.isNotBlank(metrics.getScope())) {
			queryBuilder.append("OR (UPPER(sc.profession) LIKE CONCAT('%', UPPER(:subscope), '%')) ");
		}
		
		TypedQuery<ScientistEntity> query = entityManager.
				createQuery(queryBuilder.toString(), ScientistEntity.class);

		if (metrics.getScope() != null && 
				StringUtils.isNotBlank(metrics.getScope())) {
			query.setParameter("scope", metrics.getScope());
		}
		
		if (metrics.getSubscope() != null && 
				StringUtils.isNotBlank(metrics.getSubscope())) {
			query.setParameter("subscope", metrics.getSubscope());
		}

		System.out.println(query.getResultList());

		Pageable pageableRequest = Pageable.unpaged();
		Long count = 5L;
		Page<ScientistEntity> responseCriteria = new PageImpl<ScientistEntity>
		(query.getResultList(), pageableRequest, count);
		
		log.debug("Leaving findRecomendedScientists dynamic-repository, [result]:", responseCriteria);

		return responseCriteria;
	}

}
