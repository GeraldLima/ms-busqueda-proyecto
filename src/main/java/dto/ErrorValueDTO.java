package dto;

import java.util.Locale;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorValueDTO {

	private long errorCode;
	
	private String errorMessage;
	
	private Object[] objects;
	
	private Locale locale;
}
