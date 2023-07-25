package com.busqueda.proyecto.servicio;

import java.util.List;

import com.busqueda.proyecto.entidad.PublicationEntity;

public interface PublicationService {

	PublicationEntity getPublicationById(Long id);

	Long postPublication(PublicationEntity publication);

	PublicationEntity putPublication(Long idPublication, PublicationEntity pub);

	List<PublicationEntity> getPublications(String idScientist);

	Boolean deletePublication(Long id);

}
