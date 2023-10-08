package ar.com.automar.catalogo.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import ar.com.automar.catalogo.service.MultimediaService;
import jakarta.annotation.PostConstruct;

@Service
public class MultimediaServiceImpl implements MultimediaService {

	@Value("${storage.location}")
	private String directorio; 
	
	@PostConstruct
	@Override
	public void crearDirectorio()  {
	try {
			Files.createDirectories(Paths.get(directorio)); 
	}catch(IOException e)
	{
	    System.out.println("No se pudo crear directorio");	
	}	
	
	}

	
	//MultipartFile tiene muchos datos sobre el archivo, nombre, bytes etc
	@Override
	public String guardarEnDirectorio(MultipartFile archivo) throws RuntimeException{
    String nombre=""; 
		if(archivo.isEmpty())
		System.out.println("Archivo nulo");
		else
		{
			try {
			nombre = archivo.getOriginalFilename(); 
			//Obtener la secuencia de bytes pero en un flujo de lectura para seguidamente almacenarlo
			//se lee de manera incremental a diferencia de getBytes que entrega todo de una en un arreglo
			InputStream inputstream = archivo.getInputStream(); 
			Files.copy(inputstream, Paths.get(directorio).resolve(nombre) , StandardCopyOption.REPLACE_EXISTING);
			}catch(IOException ex)
			{
				 throw new RuntimeException("Imposible guardar el archivo en el directorio", ex);
			}
		}
	return nombre;
	}

	@Override
	public Path obtenerPath(String nombreArchivo) {
		return Paths.get(directorio).resolve(nombreArchivo);
	}

	@Override
	public Resource ObtenerRecurso(String nombreArchivo) {
		try {
			/*Usar Uri para crear un URLRESOURCE O UN STRING para el uri necesitamos un path primero */
			Path archivo = obtenerPath(nombreArchivo);
			Resource recurso = new UrlResource(archivo.toUri());
			//mejor usar path
		
		//	System.out.println(directorio+nombreArchivo);
			//Resource recurso = new UrlResource(directorio+nombreArchivo);
			
			if(recurso.exists() || recurso.isReadable()) {
				return recurso;
			}else {
				  System.out.println("No se encontro el archivo");	
			}
		
		}catch (MalformedURLException excepcion) {
			 System.out.println("No se encontro el archivo");	
		}
		 return null; 
	}

	@Override
	public void eliminarArchivo(String nombreArchivo) {
		// TODO Auto-generated method stub
		
	}

}
