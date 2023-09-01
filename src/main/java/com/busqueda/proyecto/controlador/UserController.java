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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.busqueda.proyecto.entidad.OrganizationEntity;
import com.busqueda.proyecto.entidad.ProjectEntity;
import com.busqueda.proyecto.entidad.ScientistEntity;
import com.busqueda.proyecto.entidad.SearchUserEntity;
import com.busqueda.proyecto.servicio.UserService;

import dto.GetLoginDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/project")
public class UserController {

	@Autowired
	private UserService service;
	
	@GetMapping(value = "/cientifico/findBy/{orcid}",
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@Operation(summary = "Find a Scientist by its id", method = "GET")
	public ResponseEntity<ScientistEntity> getScientistByOrcid(
			@Parameter(description = "ORCID of a Scientist to be searched") 
			@PathVariable(name = "orcid") String orcid) {
		ScientistEntity scientist = service.getScientistByOrcid(orcid);
		
		return ResponseEntity.ok().body(scientist);
	}
	
	@GetMapping(value = "/cientifico/{id}",
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@Operation(summary = "Find a Scientist by its id", method = "GET")
	public ResponseEntity<ScientistEntity> getScientistById(
			@Parameter(description = "ID of a Scientist to be searched") 
			@PathVariable(name = "id") Long id) {
		ScientistEntity scientist = service.getScientistById(id);
		
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
	
	@GetMapping(value = "/cientifico/isExists/{orcid}",
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@Operation(summary = "Find out if a Scientist exists by its id", method = "GET")
	public ResponseEntity<Boolean> getScientistIsExistsByOrcid(
			@Parameter(description = "ORCID of a Scientist to be searched") 
			@PathVariable(name = "orcid") String orcid) {
		
		return ResponseEntity.ok().body(service.getScientistIsExistsByOrcid(orcid));
	}
	
	@GetMapping(value = "/cientifico/reactivate/{idScientist}")
	@Operation(summary = "Reactivate an Scientist by its id", method = "GET")
	public ResponseEntity<Boolean> reactivateScientist(
			@Parameter(description = "Id of a Scientist to be reactivated") 
			@PathVariable String idScientist) {
		
		return ResponseEntity.ok().body(service.reactivateScientist(idScientist));
	}
	
	@GetMapping(value = "/organismo/{id}", 
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@Operation(summary = "Find an Organization by its id", method = "GET")
	public ResponseEntity<OrganizationEntity> getOrganizationById(
			@Parameter(description = "ID of an Organization to be searched") 
			@PathVariable(name = "id") Long id) {
		OrganizationEntity organization = service.getOrganizationById(id);
		
		return ResponseEntity.ok().body(organization);
	}
	
	@GetMapping(value = "/organismo/findBy/{idOrganization}", 
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@Operation(summary = "Find an Organization by idOrganization", method = "GET")
	public ResponseEntity<OrganizationEntity> getOrganizationById(
			@Parameter(description = "Identification of an Organization to be searched") 
			@PathVariable(name = "idOrganization") String idOrganization) {
		OrganizationEntity organization = service.getOrganizationByIdOrg(idOrganization);
		
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
	
	@PutMapping(value = "/organismo/{id}", consumes = { MediaType.APPLICATION_JSON_VALUE }, 
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@Operation(summary = "Update a Organization by its id and a body request", method = "PUT")
	public ResponseEntity<OrganizationEntity> putOrganization(
			@Parameter(description = "Identification of an Organization to be updated") 
			@PathVariable (name="id") Long idOrganization, 
			@RequestBody OrganizationEntity org) {
		OrganizationEntity organization = service.putOrganization(idOrganization, org);
		
		return ResponseEntity.ok().body(organization);
	}

	@GetMapping(value = "/organismo/all", 
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@Operation(summary = "Find all Organizations", method = "GET")
	public ResponseEntity<List<OrganizationEntity>> getOrganizations() {
		
		return ResponseEntity.ok().body(service.getOrganizations());
	}
	
	@GetMapping(value = "/organismo/findByName/{nameOrg}",
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@Operation(summary = "Find Organizations by name", method = "GET")
	public ResponseEntity<List<OrganizationEntity>> getOrganizationsByName(
			@Parameter(description = "Full or partial name of an Organization to be searched") 
			@PathVariable(name = "nameOrg") String nameOrg) {
		List<OrganizationEntity> listOrgs = service.getOrganizationsByName(nameOrg);
		
		return ResponseEntity.ok().body(listOrgs);
	}

	@DeleteMapping(value = "/organismo/{idOrganization}")
	@Operation(summary = "Delete an Organization by its id", method = "DELETE")
	public ResponseEntity<Boolean> deleteOrganization(
			@Parameter(description = "Id of an Organization to be deleted") 
			@PathVariable String idOrganization) {
		
		return ResponseEntity.ok().body(service.deleteOrganization(idOrganization));
	}
	
	@GetMapping(value = "/organismo/isExists/{idOrganization}",
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@Operation(summary = "Find out if an Organization exists by its id", method = "GET")
	public ResponseEntity<Boolean> getOrganizationIsExistsById(
			@Parameter(description = "Id of an Organization to be searched") 
			@PathVariable(name = "idOrganization") String idOrganization) {
		
		return ResponseEntity.ok().body(service.getOrganizationIsExistsById(idOrganization));
	}
	
	@GetMapping(value = "/organismo/reactivate/{idOrganization}")
	@Operation(summary = "Reactivate an Organization by its id", method = "GET")
	public ResponseEntity<Boolean> reactivateOrganization(
			@Parameter(description = "Id of an Organization to be reactivated") 
			@PathVariable String idOrganization) {
		
		return ResponseEntity.ok().body(service.reactivateOrganization(idOrganization));
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
	public ResponseEntity<GetLoginDTO> loginProcess(
			@Parameter(description = "uuidUser of a SearchUser to be searched") 
			@PathVariable (name="uuidUser") String uuidUser) {
		GetLoginDTO responseDto = service.loginProcess(uuidUser);
		
		return ResponseEntity.ok().body(responseDto);
	}
	
	@GetMapping(value = "/cientifico/assignment/{orcid}",
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@Operation(summary = "Assign an available Scientist by an orcid to a not full Project", 
			method = "GET")
	public ResponseEntity<Boolean> assignmentProcess(
			@Parameter(description = "orcid of a Scientist to be assigned") 
			@PathVariable (name="orcid") String orcid, 
			@RequestParam (required = true) Long idProject) {
		
		return ResponseEntity.ok().body(service.assignmentProcess(idProject, orcid));
	}
	
	@GetMapping(value = "/cientifico/recommendation/{orcid}",
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@Operation(summary = "Find a list of Projets recommended by the application", method = "GET")
	public ResponseEntity<Page<ProjectEntity>> getRecomendedProjects(
			@Parameter(description = "Id of a Scientist to be recommended") 
			@PathVariable (name="orcid") String orcid) {
		Page<ProjectEntity> projectList = service.getRecommendedProjects(orcid);
		
		return ResponseEntity.ok().body(projectList);
	}
	
	@GetMapping(value = "/cientifico/project/{orcid}",
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@Operation(summary = "Find the Project which a Scientist is assigned", method = "GET")
	public ResponseEntity<ProjectEntity> getProjectOfScientist(
			@Parameter(description = "orcid of a Scientist to be assigned") 
			@PathVariable (name="orcid") String orcid) {
		
		ProjectEntity proj = service.getProjectOfScientist(orcid);
		
		return ResponseEntity.ok().body(proj);
	}
	
	@GetMapping(value = "/cientifico/assignment/getOut/{orcid}",
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@Operation(summary = "Remove a Scientist from an assigned-Project", 
			method = "GET")
	public ResponseEntity<Boolean> assignmentGeOut(
			@Parameter(description = "orcid of a Scientist to be assigned") 
			@PathVariable (name="orcid") String orcid, 
			@RequestParam (required = true) Long idProject) {
		
		return ResponseEntity.ok().body(service.assignmentGetOut(idProject, orcid));
	}
}
