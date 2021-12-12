package com.acme.emprendimientos.controller;

import com.acme.emprendimientos.entity.Emprendimiento;
import com.acme.emprendimientos.entity.Usuario;
import com.acme.emprendimientos.repository.EmprendimientoRepository;
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
@RequestMapping(value = "/emprendimiento")
public class EmprendimientoController {
    private final EmprendimientoRepository emprendimientoRepository;

    @Autowired
    public EmprendimientoController(EmprendimientoRepository emprendimientoRepository){
        this.emprendimientoRepository = emprendimientoRepository;
    }

    @GetMapping
    public List<Emprendimiento> getEmprendimientos(){
        return emprendimientoRepository.findAll();
    }

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

    @PostMapping
    public ResponseEntity<?> createEmprendimiento (@Valid @RequestBody Emprendimiento emprendimiento){
        return new ResponseEntity<>(emprendimientoRepository.save(emprendimiento), HttpStatus.CREATED);
    }

}
