package com.busqueda.proyecto.entidad;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import org.springframework.data.annotation.Id;

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

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long id;
	
	@Column(name="NIF")
	private String nif;
	
	@Column(name="NOMBRE")
	private String name;
	
	@Column(name="EMAIL")
	private String email;
	
	@Column(name="LOCALIDAD")
	private String location;
	
	@Column(name="TIPO")
	private String type;
	
	@Column(name="ACTIVO")
	private Boolean active;

}
