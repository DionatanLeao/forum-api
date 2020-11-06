package br.com.forum.api.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorValidationHandler {
	
	@Autowired
	private MessageSource messageSource;
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<ErrorFormDto> handle(MethodArgumentNotValidException exception) {
		
		List<ErrorFormDto> dto = new ArrayList<>();
		
		List<FieldError> fieldError = exception.getBindingResult().getFieldErrors();
		fieldError.forEach(e -> {
			String mensagem = messageSource.getMessage(e, LocaleContextHolder.getLocale());
			ErrorFormDto erro = new ErrorFormDto(e.getField(), mensagem);
			dto.add(erro);
		});
		
		return dto;
	}

}
