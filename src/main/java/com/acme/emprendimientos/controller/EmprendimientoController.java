package com.acme.emprendimientos.controller;

import com.acme.emprendimientos.dto.OperacionEmprendimiento;
import com.acme.emprendimientos.entity.Emprendimiento;

import com.acme.emprendimientos.repository.EmprendimientoRepository;
import com.acme.emprendimientos.service.EmprendimientoService;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.HashMap;

import java.util.Map;

@RestController
@RequestMapping(value = "/emprendimiento")
public class EmprendimientoController {

    private final EmprendimientoService emprendimientoService;
    private final EmprendimientoRepository emprendimientoRepository;

    @Autowired
    public EmprendimientoController(EmprendimientoService emprendimientoService, EmprendimientoRepository emprendimientoRepository) {
        this.emprendimientoService = emprendimientoService;
        this.emprendimientoRepository = emprendimientoRepository;
    }


    @PostMapping
    public ResponseEntity<?> createEmprendimiento(@Valid @RequestBody OperacionEmprendimiento operacionEmprendimiento) {

        return new ResponseEntity<>(emprendimientoService.createEmprendimiento(operacionEmprendimiento), HttpStatus.CREATED);
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

}
