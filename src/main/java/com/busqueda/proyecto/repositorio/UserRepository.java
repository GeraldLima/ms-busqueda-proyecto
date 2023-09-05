package com.busqueda.proyecto.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.busqueda.proyecto.entidad.SearchUserEntity;

@Repository
public interface UserRepository extends JpaRepository<SearchUserEntity, Long> {

	
}
