package com.busqueda.proyecto.entidad;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.busqueda.proyecto.utils.ProjectUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="PUBLICACION", schema="busqueda")
public class PublicationEntity implements Serializable {

	private static final long serialVersionUID = -4504954926750910724L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	@Column(name="ID_PUBLICACION")
	private Long id;
	
	@Column(name="ID_CIENTIFICO")
	private String idScientist;
	
	@Column(name="TITULO")
	private String title;
	
	@Column(name="ESPECIALIDAD")
	private String expertise;
	
	@Column(name="EXPERIENCIA")
	private String profExperience;
	
	@Column(name="DESCRIPCION")
	private String description;
	
	@Column(name="ACTIVO")
	private Boolean active;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = ProjectUtils.LOCAL_DATE_TIME_PATTERN)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
 	@Column(name = "FEC_CREACION")
 	private LocalDateTime initLifeDate; 	
 	
 	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = ProjectUtils.LOCAL_DATE_TIME_PATTERN)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
 	@Column(name = "FEC_ACTUALIZACION")
 	private LocalDateTime updateLife;
 	
}
