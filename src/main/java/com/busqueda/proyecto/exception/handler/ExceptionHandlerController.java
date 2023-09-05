package com.busqueda.proyecto.exception.handler;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.busqueda.proyecto.exception.ProyectSearchException;

import dto.ErrorInformationDTO;
import dto.ErrorResponseDTO;
import dto.ErrorValueDTO;

public class ExceptionHandlerController extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = RuntimeException.class)
	protected ResponseEntity<?> handleRuntimeException(RuntimeException ex) {
		logger.error("Handled RuntimeException by ExceptionHandlerController.handleRuntimeException", ex);
		return getErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex, 1);
	}

	@ExceptionHandler(value= ProyectSearchException.class)
	protected ResponseEntity<?> handleRuntimeException(ProyectSearchException ex) {
		logger.error("Handled ProyectSearchException by ExceptionHandlerController.ProyectSearchException", ex);
		return getErrorResponse(HttpStatus.BAD_REQUEST, ex, 2);
	}
	
	private ResponseEntity<?> getErrorResponse(HttpStatus httpCode, Exception ex, long errorCode) {
		ErrorResponseDTO errorResponse = new ErrorResponseDTO();
		errorResponse.setHttpCode(httpCode.value());
		errorResponse.setHttpMessage(httpCode.getReasonPhrase());

		ErrorValueDTO value = new ErrorValueDTO(errorCode, ex.getMessage(), new Object[] { "" }, LocaleContextHolder.getLocale());
		List<ErrorInformationDTO> moreInfomation = Arrays.asList(new ErrorInformationDTO("API", value));
		errorResponse.setMoreInformation(moreInfomation);

		return new ResponseEntity<Object>(errorResponse, httpCode);
	}
}
