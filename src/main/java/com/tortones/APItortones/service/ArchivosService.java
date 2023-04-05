package com.tortones.APItortones.service;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class ArchivosService implements AlmacenamientoService{

    @Value("${media.location}")
    private String rutaAlmacenamiento;

    private Path raizAlmacenamiento;

    @Override
    @PostConstruct
    public void init() throws IOException {
        raizAlmacenamiento = Paths.get(rutaAlmacenamiento);
        Files.createDirectories(raizAlmacenamiento);
    }

    @Override
    public String almacenar(MultipartFile archivo){
        try{
            if(archivo.isEmpty()){
                throw new RuntimeException("Archivo vacío");
            }
            String nombreArchivo = archivo.getOriginalFilename();
            Path rutaDestino = raizAlmacenamiento.resolve(Paths.get(nombreArchivo))
                    .normalize().toAbsolutePath();
            try (InputStream inputStream = archivo.getInputStream()){
                Files.copy(inputStream, rutaDestino, StandardCopyOption.REPLACE_EXISTING);
            }
            return nombreArchivo;
        } catch (IOException e){
            throw new RuntimeException("Error al guardar archivo");
        }
    }

    @Override
    public Resource cargarArchivo(String nombreArchivo) {
        try{
            Path archivo = raizAlmacenamiento.resolve(nombreArchivo);
            Resource recurso = new UrlResource(archivo.toUri());

            if(recurso.exists() || recurso.isReadable()){
                return recurso;
            } else {
                throw new RuntimeException("No se pudo leer el archivo: " + nombreArchivo);
            }
        } catch (MalformedURLException e){
            throw new RuntimeException("No se pudo leer el archivo: " + nombreArchivo);
        }
    }
}
