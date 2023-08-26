package dto;

import com.busqueda.proyecto.entidad.AssignationEntity;
import com.busqueda.proyecto.entidad.ProjectEntity;
import com.busqueda.proyecto.entidad.ScientistEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetAssignmentDTO {

	private AssignationEntity assignation;
	
	private ScientistEntity scientist;
	
	private ProjectEntity project;
	
}
