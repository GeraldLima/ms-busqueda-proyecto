package com.busqueda.proyecto.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.busqueda.proyecto.entidad.OrganizationEntity;
import com.busqueda.proyecto.entidad.ScientistEntity;
import com.busqueda.proyecto.entidad.SearchUserEntity;
import com.busqueda.proyecto.servicio.BusquedaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
//@RequestMapping("api/project")
public class ProjectController {

	@Autowired
	private BusquedaService service;
	
	@GetMapping(value = "/cientifico/{id}",
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@Operation(summary = "Find a Scientist by its id", method = "GET")
	public ResponseEntity<ScientistEntity> getScientistById(
			@Parameter(description = "ORCID of a Scientist to be searched") 
			@PathVariable(name = "id") String idScientist) {
		ScientistEntity scientist = service.getScientistById(idScientist);
		
		return ResponseEntity.ok().body(scientist);
	}
	
	@PostMapping(value = "/cientifico", consumes = { MediaType.APPLICATION_JSON_VALUE }, 
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@Operation(summary = "Insert a new Scientist with a body request", method = "POST")
	public ResponseEntity<Long> postScientist(
			@RequestBody ScientistEntity scientist) {
		Long idScientist = service.postScientist(scientist);
		
		return ResponseEntity.ok().body(idScientist);
	}
	
	@PutMapping(value = "/cientifico/{id}", consumes = { MediaType.APPLICATION_JSON_VALUE }, 
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@Operation(summary = "Update a Scientist by its id and a body request", method = "PUT")
	public ResponseEntity<ScientistEntity> putScientist(
			@Parameter(description = "orcid of a Scientist to be updated") 
			@PathVariable (name="id") Long idScientist, @RequestBody ScientistEntity sc) {
		ScientistEntity scientist = service.putScientist(idScientist, sc);
		
		return ResponseEntity.ok().body(scientist);
	}

	@GetMapping(value = "/cientifico/all", 
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@Operation(summary = "Find all Scientists", method = "GET")
	public ResponseEntity<List<ScientistEntity>> getScientists() {
		
		return ResponseEntity.ok().body(service.getScientists());
	}

	@DeleteMapping(value = "/cientifico/{orcid}")
	@Operation(summary = "Delete a Scientist by its id", method = "DELETE")
	public ResponseEntity<Boolean> deleteScientist(
			@Parameter(description = "orcid of a Scientist to be deleted") 
			@PathVariable String orcid) {
		
		return ResponseEntity.ok().body(service.deleteScientist(orcid));
	}
	
	@GetMapping(value = "/organismo/{id}", 
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@Operation(summary = "Find an Organization by its id", method = "GET")
	public ResponseEntity<OrganizationEntity> getOrganizationById(
			@Parameter(description = "ID of an Organization to be searched") 
			@PathVariable(name = "id") String id) {
		OrganizationEntity organization = service.getOrganizationById(id);
		
		return ResponseEntity.ok().body(organization);
	}

	@PostMapping(value = "/organismo", consumes = { MediaType.APPLICATION_JSON_VALUE }, 
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@Operation(summary = "Insert a new Organization with a body request", method = "POST")
	public ResponseEntity<Long> postOrganization(
			@RequestBody OrganizationEntity organization) {
		Long idOrganization = service.postOrganization(organization);
		
		return ResponseEntity.ok().body(idOrganization);
	}
	
	@PostMapping(value = "/usuario", consumes = { MediaType.APPLICATION_JSON_VALUE }, 
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@Operation(summary = "Insert a new SearchUser with a body request", method = "POST")
	public ResponseEntity<Long> postUser(
			@RequestBody SearchUserEntity user) {
		Long idUser = service.postUserUUID(user);
		
		return ResponseEntity.ok().body(idUser);
	}
	
	@GetMapping(value = "/login/{uuidUser}", 
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@Operation(summary = "Find an SearchUser by its id and if it exists return type of user", 
			method = "GET")
	public ResponseEntity<String> loginProcess(
			@Parameter(description = "uuidUser of a SearchUser to be searched") 
			@RequestParam (name="uuidUser", required = true) String uuidUser) {
		String idUser = service.loginProcess(uuidUser);
		
		return ResponseEntity.ok().body(idUser);
	}
	
	
	
}
