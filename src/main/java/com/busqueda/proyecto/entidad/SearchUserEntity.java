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
@Table(name="USUARIO", schema="busqueda")
public class SearchUserEntity implements Serializable {

	private static final long serialVersionUID = -4504824026750390724L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	@Column(name="UUID_USUARIO")
	private Long id;
	
	@Column(name="UUID_CORREO")
	private String uuidEmail;
	
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
 	@Column(name = "FEC_BAJA")
 	private LocalDateTime endLifeDate;

}
