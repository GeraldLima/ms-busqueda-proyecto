package com.busqueda.proyecto.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.busqueda.proyecto.entidad.ProjectEntity;
import com.busqueda.proyecto.entidad.PublicationEntity;
import com.busqueda.proyecto.servicio.PublicationService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
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

	@GetMapping(value = "/publicacion/all/{idCientifico}", 
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@Operation(summary = "Find all Publications by idScientist", method = "GET")
	public ResponseEntity<List<PublicationEntity>> getPublications(
			@Parameter(description = "id of a Scientist to be searched") 
			@PathVariable (name="idCientifico") String idScientist) {
		
		return ResponseEntity.ok().body(service.getPublications(idScientist));
	}
	
	@GetMapping(value = "/publicacion/all", 
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@Operation(summary = "Find all Publications", method = "GET")
	public ResponseEntity<Page<PublicationEntity>> getAllPublications(
			@RequestParam(name="page", required=false) Integer page, 
			@RequestParam(name="size", required=false) Integer size) {
		
		return ResponseEntity.ok().body(service.getAllPublications(page, size));
	}

	@DeleteMapping(value = "/publicacion/{id}")
	@Operation(summary = "Delete a Publication by its id", method = "DELETE")
	public ResponseEntity<Boolean> deletePublication(
			@Parameter(description = "id of a Publication to be deleted") 
			@PathVariable Long id) {
		
		return ResponseEntity.ok().body(service.deletePublication(id));
	}
	
	@GetMapping(value = "/proyecto/{id}",
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@Operation(summary = "Find a Project by its id", method = "GET")
	public ResponseEntity<ProjectEntity> getProjectById(
			@Parameter(description = "ID of a Project to be searched") 
			@PathVariable(name = "id") Long id) {
		ProjectEntity project = service.getProjectById(id);
		
		return ResponseEntity.ok().body(project);
	}	
	
	@PostMapping(value = "/proyecto", consumes = { MediaType.APPLICATION_JSON_VALUE }, 
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@Operation(summary = "Insert a new Project with a body request", method = "POST")
	public ResponseEntity<Long> postProject(
			@RequestBody ProjectEntity project) {
		Long idProject = service.postProject(project);
		
		return ResponseEntity.ok().body(idProject);
	}
	
	@PutMapping(value = "/proyecto/{id}", consumes = { MediaType.APPLICATION_JSON_VALUE }, 
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@Operation(summary = "Update a Project by its id and a body request", method = "PUT")
	public ResponseEntity<ProjectEntity> putProject(
			@Parameter(description = "orcid of a Project to be updated") 
			@PathVariable (name="id") Long idProject, 
			@RequestBody ProjectEntity proj) {
		ProjectEntity project = service.putProject(idProject, proj);
		
		return ResponseEntity.ok().body(project);
	}

	@GetMapping(value = "/proyecto/all/{idOrganismo}", 
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@Operation(summary = "Find all Projects by idOrganization", method = "GET")
	public ResponseEntity<List<ProjectEntity>> getProjects(
			@Parameter(description = "id of a Organization to be searched") 
			@PathVariable (name="idOrganismo") String idOrganization) {
		
		return ResponseEntity.ok().body(service.getProjects(idOrganization));
	}
	
	@GetMapping(value = "/proyecto/all", 
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@Operation(summary = "Find all Projects", method = "GET")
	public ResponseEntity<Page<ProjectEntity>> getAllProjects(
		@RequestParam(name="page", required=false) Integer page, 
		@RequestParam(name="size", required=false) Integer size) {
		
		return ResponseEntity.ok().body(service.getAllProjects(page, size));
	}

	@DeleteMapping(value = "/proyecto/{id}")
	@Operation(summary = "Delete a Project by its id", method = "DELETE")
	public ResponseEntity<Boolean> deleteProject(
			@Parameter(description = "id of a Project to be deleted") 
			@PathVariable Long id) {
		
		return ResponseEntity.ok().body(service.deleteProject(id));
	}
	
}
