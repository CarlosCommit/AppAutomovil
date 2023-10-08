package ar.com.automar.catalogo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.com.automar.catalogo.service.MultimediaService;

@RestController
@RequestMapping(path = "/images")
public class ImagesController {

	@Autowired
	MultimediaService multimediaService; 
	@GetMapping("/{nombre:.+}")
	public ResponseEntity<Resource> obtenerImagen(@PathVariable String nombre)
	{
		Resource resource = multimediaService.ObtenerRecurso(nombre);
	    HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG); 
        return ResponseEntity.ok()
                .headers(headers)
                .body(resource);
	}
}
