package com.busqueda.proyecto.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.busqueda.proyecto.entidad.PublicationEntity;
import com.busqueda.proyecto.servicio.PublicationService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

@RestController
public class PublicationController {

	@Autowired
	private PublicationService service;
	
	@GetMapping(value = "/publicacion/{id}",
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@Operation(summary = "Find a Publication by its id", method = "GET")
	public ResponseEntity<PublicationEntity> getPublicationById(
			@Parameter(description = "ID of a Publication to be searched") 
			@PathVariable(name = "id") Long id) {
		PublicationEntity publication = service.getPublicationById(id);
		
		return ResponseEntity.ok().body(publication);
	}	
	
	@PostMapping(value = "/publicacion", consumes = { MediaType.APPLICATION_JSON_VALUE }, 
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@Operation(summary = "Insert a new Publication with a body request", method = "POST")
	public ResponseEntity<Long> postPublication(
			@RequestBody PublicationEntity publication) {
		Long idPublication = service.postPublication(publication);
		
		return ResponseEntity.ok().body(idPublication);
	}
	
	@PutMapping(value = "/publicacion/{id}", consumes = { MediaType.APPLICATION_JSON_VALUE }, 
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@Operation(summary = "Update a Publication by its id and a body request", method = "PUT")
	public ResponseEntity<PublicationEntity> putPublication(
			@Parameter(description = "orcid of a Publication to be updated") 
			@PathVariable (name="id") Long idPublication, @RequestBody PublicationEntity sc) {
		PublicationEntity publication = service.putPublication(idPublication, sc);
		
		return ResponseEntity.ok().body(publication);
	}

	@GetMapping(value = "/publicacion/all", 
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@Operation(summary = "Find all Publications", method = "GET")
	public ResponseEntity<List<PublicationEntity>> getPublications() {
		
		return ResponseEntity.ok().body(service.getPublications());
	}

	@DeleteMapping(value = "/publicacion/{id}")
	@Operation(summary = "Delete a Publication by its id", method = "DELETE")
	public ResponseEntity<Boolean> deletePublication(
			@Parameter(description = "orcid of a Publication to be deleted") 
			@PathVariable String id) {
		
		return ResponseEntity.ok().body(service.deletePublication(id));
	}
	
}
