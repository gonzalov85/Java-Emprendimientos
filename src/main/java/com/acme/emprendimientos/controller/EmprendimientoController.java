package com.acme.emprendimientos.controller;

import com.acme.emprendimientos.dto.OperacionEmprendimiento;
import com.acme.emprendimientos.entity.Emprendimiento;
import com.acme.emprendimientos.entity.Usuario;
import com.acme.emprendimientos.repository.EmprendimientoRepository;
import com.acme.emprendimientos.repository.UsuarioRepository;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/emprendimiento")
public class EmprendimientoController {
    private final EmprendimientoRepository emprendimientoRepository;
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public EmprendimientoController(EmprendimientoRepository emprendimientoRepository,
                                    UsuarioRepository usuarioRepository){
        this.emprendimientoRepository = emprendimientoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    //GET All Emprendimientos
    @GetMapping
    public ResponseEntity<?> getEmprendimientos() {
        return new ResponseEntity<>(emprendimientoRepository.findAll(), HttpStatus.OK);
    }

    //Delete Emprendimiento by id
    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteEmprendimiento(@PathVariable(value = "id") Long Id)
            throws ResourceNotFoundException {
        Emprendimiento emprendimiento = emprendimientoRepository.findById(Id)
                .orElseThrow(() -> new ResourceNotFoundException("No existe un Emprendimiento con el id: " + Id));

        emprendimientoRepository.delete(emprendimiento);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    //Crear Emprendimiento
    @PostMapping
    public ResponseEntity<?> createEmprendimiento(@Valid @RequestBody OperacionEmprendimiento operacionEmprendimiento) {
        Usuario usuario = usuarioRepository.findById(operacionEmprendimiento.getIdUsuario())
                .orElseThrow(() -> new EntityNotFoundException("Usuario No Encontrado"));
        Emprendimiento emprendimiento = new Emprendimiento();
        emprendimiento.setNombre(operacionEmprendimiento.getNombre());
        emprendimiento.setDescripcion(operacionEmprendimiento.getDescripcion());
        emprendimiento.setContenido(operacionEmprendimiento.getContenido());
        emprendimiento.setFechaDeCreacion(operacionEmprendimiento.getFechaDeCreacion());
        emprendimiento.setObjetivo(operacionEmprendimiento.getObjetivo());
        emprendimiento.setPublicado(operacionEmprendimiento.isPublicado());
        emprendimiento.setOwner(usuario);
        return new ResponseEntity<>(emprendimientoRepository.save(emprendimiento), HttpStatus.CREATED);
    }

}
