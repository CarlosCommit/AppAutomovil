package ar.com.automar.catalogo.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ar.com.automar.catalogo.model.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import ar.com.automar.catalogo.dtos.AutoDTO;
import ar.com.automar.catalogo.service.AutoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping(path="/auto")
public class AutoController {

	@Autowired
	AutoService autoService;

	@PostMapping(path = "/subir", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Object> upLoadAuto(@RequestPart @Valid AutoDTO formulario, @RequestPart(required = false) List<MultipartFile> imagenes) {
		Map<String, Object> response = new HashMap<String, Object>();
		AutoDTO autoDTO = autoService.guardarAuto(formulario, imagenes);
		response.put("autoGuardado", autoDTO);
		response.put("mensaje", "Creado correctamente");
		return new ResponseEntity<Object>(response, HttpStatus.CREATED);

	}

	@GetMapping(path = "/get/all/{numero}")
	public ResponseEntity<Map<String, Object>> getAll(@PathVariable(name = "numero") int numero)
	{
		 Map<String,Object> response = new HashMap<String,Object>();
		 //Pageable necesita (el numero de pagina, elementos por pagina, sort"ordenacion)
		 numero = (numero == 0)? numero: numero-1;
		 Pageable pageable = PageRequest.of(numero,10);
		 response.put("paginas" , autoService.getAutoPages(pageable));
		 return new ResponseEntity<>(response, HttpStatus.OK);
	}
}