package dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponseDTO {

	private int httpCode;
	
	private String httpMessage;
	
	private List<ErrorInformationDTO> moreInformation;
	
}
