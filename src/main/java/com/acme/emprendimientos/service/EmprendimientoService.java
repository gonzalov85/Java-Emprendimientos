package com.acme.emprendimientos.service;


import com.acme.emprendimientos.dto.OperacionEmprendimiento;
import com.acme.emprendimientos.entity.Emprendimiento;
import com.acme.emprendimientos.entity.Tag;
import com.acme.emprendimientos.entity.Usuario;
import com.acme.emprendimientos.repository.EmprendimientoRepository;
import com.acme.emprendimientos.repository.TagRepository;
import com.acme.emprendimientos.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class EmprendimientoService {

    private final EmprendimientoRepository emprendimientoRepository;
    private final UsuarioRepository usuarioRepository;
    private final TagRepository tagRepository;
    private final CapturaRepository capturaRepository;

    @Autowired
    public EmprendimientoService(EmprendimientoRepository emprendimientoRepository,
                                 UsuarioRepository usuarioRepository,
                                 TagRepository tagRepository,
                                 CapturaRepository capturaRepository) {
        this.emprendimientoRepository = emprendimientoRepository;
        this.usuarioRepository = usuarioRepository;
        this.tagRepository = tagRepository;
        this.capturaRepository = capturaRepository;
    }

    public Emprendimiento createEmprendimiento(OperacionEmprendimiento operacionEmprendimiento) {
        Usuario usuario = usuarioRepository.findById(operacionEmprendimiento.getIdUsuario())
                .orElseThrow(() -> new EntityNotFoundException("Usuario No Encontrado"));
        List<Tag> tags = tagRepository.findAllById(operacionEmprendimiento.getTags());
        Emprendimiento emprendimiento = new Emprendimiento();
        emprendimiento.setNombre(operacionEmprendimiento.getNombre());
        emprendimiento.setDescripcion(operacionEmprendimiento.getDescripcion());
        emprendimiento.setOwner(usuario);
        emprendimiento.setContenido(operacionEmprendimiento.getDescripcion());
        emprendimiento.setFechaDeCreacion(operacionEmprendimiento.getFechaDeCreacion());
        emprendimiento.setObjetivo(operacionEmprendimiento.getObjetivo());
        emprendimiento.setPublicado(operacionEmprendimiento.isPublicado());
        emprendimiento.getTags().addAll(tags);

        return emprendimientoRepository.save(emprendimiento);
    }

}

