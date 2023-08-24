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
@Table(name="ORGANISMO", schema="busqueda")
public class OrganizationEntity implements Serializable {

	private static final long serialVersionUID = -8351928181537467051L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	@Column(name="ID")
	private Long id;
	
	@Column(name="ID_ORGANISMO")
	private String idOrganization;
	
	@Column(name="UID_USUARIO")
	private String userUuid;
	
	@Column(name="NOMBRE")
	private String name;
	
	@Column(name="EMAIL")
	private String email;
	
	@Column(name="LOCALIDAD")
	private String location;
	
	@Column(name="AMBITO")
	private String area;
	
	@Column(name="ACTIVO")
	private Boolean active;

}
