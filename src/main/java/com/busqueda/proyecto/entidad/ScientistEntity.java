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
@Table(name="CIENTIFICO", schema="busqueda")
public class ScientistEntity implements Serializable {

	private static final long serialVersionUID = -4504854026750310724L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	@Column(name="ID")
	private Long id;
	
	@Column(name="ORCID")
	private String orcid;
	
	@Column(name="NOMBRE")
	private String name;
	
	@Column(name="EMAIL")
	private String email;
	
	@Column(name="ESPECIALIDAD")
	private String speciality;
	
	@Column(name="ACTIVO")
	private Boolean active;

}
