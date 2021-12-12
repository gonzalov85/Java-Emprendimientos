package com.acme.emprendimientos.controller;

import com.acme.emprendimientos.entity.Usuario;
import com.acme.emprendimientos.repository.UsuarioRepository;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/usuario")
public class UsuarioController {
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping
    public List<Usuario> getUsuarios(){
        return usuarioRepository.findAll();
    }

    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteUsuario(@PathVariable(value = "id") Long Id)
            throws ResourceNotFoundException {
        Usuario usuario = usuarioRepository.findById(Id)
                .orElseThrow(() -> new ResourceNotFoundException("No existe un usuario con el id: " + Id));

        usuarioRepository.delete(usuario);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    @PostMapping
    public ResponseEntity<?> createUsuario(@Valid @RequestBody Usuario usuario) {
        return new ResponseEntity<>(usuarioRepository.save(usuario), HttpStatus.CREATED);
    }
}
