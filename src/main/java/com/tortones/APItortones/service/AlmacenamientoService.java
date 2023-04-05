package com.tortones.APItortones.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface AlmacenamientoService {
    void init() throws IOException;
    String almacenar(MultipartFile archivo);
    Resource cargarArchivo(String nombreArchivo);
}