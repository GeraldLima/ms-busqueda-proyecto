package com.busqueda.proyecto.entidad;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="ASIGNACION", schema="busqueda")
public class AssignationEntity implements Serializable {

	private static final long serialVersionUID = -2573139114013513407L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	@Column(name="ID_ASIGNACION")
	private Long id;
	
	@Column(name="ID_CIENTIFICO")
	private String idScientist;
	
	@Column(name="ID_PROYECTO")
	private Long idProject;
	
	@Column(name="DESCRIPCION")
	private String description;
	
	@Column(name="ACTIVO")
	private Boolean active;

}
