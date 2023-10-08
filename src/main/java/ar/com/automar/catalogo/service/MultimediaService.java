package ar.com.automar.catalogo.service;


import java.nio.file.Path;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface MultimediaService {

	public void crearDirectorio();

	public String guardarEnDirectorio(MultipartFile archivo);

	public Path obtenerPath(String nombreArchivo);

	public Resource ObtenerRecurso(String nombreArchivo);

	public void eliminarArchivo(String nombreArchivo);
}
