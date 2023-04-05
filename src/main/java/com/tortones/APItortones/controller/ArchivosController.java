package com.tortones.APItortones.controller;

import com.tortones.APItortones.service.AlmacenamientoService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Map;

@RestController
@RequestMapping("/api/archivos")
@AllArgsConstructor
public class ArchivosController {

    private final AlmacenamientoService almacenamientoService;
    private final HttpServletRequest request;


    @PostMapping("/cargar")
    public Map<String, String> cargarArchivo(@RequestParam("archivo") MultipartFile archivo){
        String ruta = almacenamientoService.almacenar(archivo);
        String destino = request.getRequestURL().toString().replace(request.getRequestURI(), "");
        String url = ServletUriComponentsBuilder
                .fromHttpUrl(destino)
                .path("/api/archivos/")
                .path(ruta)
                .toUriString();
        return Map.of("url", url);
    }

    @GetMapping("{nombreArchivo:.+}")
    public ResponseEntity<Resource> obtenerArchivo(@PathVariable String nombreArchivo) throws IOException {
        Resource archivo = almacenamientoService.cargarArchivo(nombreArchivo);
        String tipoContenido = Files.probeContentType(archivo.getFile().toPath());
        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_TYPE, tipoContenido)
                .body(archivo);
    }
}
