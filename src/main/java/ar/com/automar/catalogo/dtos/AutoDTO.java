package ar.com.automar.catalogo.dtos;

import java.io.Serializable;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AutoDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id; 
	@NotBlank
	private String marca;
	@NotBlank
	private String modelo;
	@NotNull
	private Double precio;
	@NotNull
	private Double anticipo;
	
	private List<String> imagenesURI;
}
