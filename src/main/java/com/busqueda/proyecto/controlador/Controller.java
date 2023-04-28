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

import com.busqueda.proyecto.entidad.OrganizationEntity;
import com.busqueda.proyecto.entidad.ScientistEntity;
import com.busqueda.proyecto.servicio.BusquedaService;

@RestController
public class Controller {

	@Autowired
	private BusquedaService service;
	
	@GetMapping(value = "/cientifico/{id}",
			produces = { MediaType.APPLICATION_JSON_VALUE })
//	@Operation(summary = "", method = "GET", 
//	security = {@SecurityRequirement(name = "user-key")})
	public ResponseEntity<ScientistEntity> getScientistById(
			@PathVariable (name="id") String idScientist) {
		ScientistEntity scientist = service.getScientistById(idScientist);
		return ResponseEntity.ok().body(scientist);
	}

	@PostMapping(value = "/cientifico", consumes = { MediaType.APPLICATION_JSON_VALUE }, 
			produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Long> postScientist(
			@RequestBody ScientistEntity scientist) {
		Long idScientist = service.postScientist(scientist);
		return ResponseEntity.ok().body(idScientist);
	}
	
	@PutMapping(value = "/cientifico/{id}", consumes = { MediaType.APPLICATION_JSON_VALUE }, 
			produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ScientistEntity> putScientist(
			@PathVariable (name="id") Long idScientist, @RequestBody ScientistEntity sc) {
		ScientistEntity scientist = service.putScientist(idScientist, sc);
		return ResponseEntity.ok().body(scientist);
	}

	@GetMapping(value = "/medico/all", 
			produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<ScientistEntity>> getScientists() {
		return ResponseEntity.ok().body(service.getScientists());
	}

	@DeleteMapping(value = "/cientifico/{orcid}")
	public ResponseEntity<Boolean> deleteScientist(@PathVariable String orcid) {
		return ResponseEntity.ok().body(service.deleteScientist(orcid));
	}
	
	@GetMapping(value = "/organismo/{id}", 
			produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<OrganizationEntity> getOrganizationById(
			@PathVariable (name="id") String id) {
		OrganizationEntity organization = service.getOrganizationById(id);
		return ResponseEntity.ok().body(organization);
	}

	@PostMapping(value = "/organismo", consumes = { MediaType.APPLICATION_JSON_VALUE }, 
			produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Long> postOrganization(
			@RequestBody OrganizationEntity organization) {
		Long idOrganization = service.postOrganization(organization);
		return ResponseEntity.ok().body(idOrganization);
	}
}
