package com.FinalEgg.ServiChacras.servicios;

import com.FinalEgg.ServiChacras.entidades.*;
import com.FinalEgg.ServiChacras.excepciones.MiExcepcion;
import com.FinalEgg.ServiChacras.repositorios.ServicioRepositorio;
import com.FinalEgg.ServiChacras.repositorios.ProveedorRepositorio;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProveedorServicio {
    @Autowired
    private ProveedorRepositorio proveedorRepositorio;
    @Autowired
    private ServicioRepositorio servicioRepositorio;
    @Autowired
    private ImagenServicio imagenServicio;

    @Transactional
    public void crearProveedor(Usuario usuario, MultipartFile archivo, String descripcion, String idServicio) throws MiExcepcion {
        Proveedor proveedor = new Proveedor();

        proveedor.setUsuario(usuario);

        Imagen imagen = imagenServicio.guardar(archivo);
        proveedor.setImagen(imagen);

        proveedor.setDescripcion(descripcion);

        Optional<Servicio> opcionalServicio = servicioRepositorio.findById(idServicio);
        Servicio servicio = new Servicio();

        if (opcionalServicio.isPresent()) { servicio = opcionalServicio.get(); }
        proveedor.setServicio(servicio);

        proveedorRepositorio.save(proveedor);
    }

    @Transactional(readOnly = true)
    public List<Proveedor> listarProveedors() { return proveedorRepositorio.findAll(); }

    @Transactional
    public void actualizar(String id, Usuario usuario, MultipartFile archivo, String descripcion, String idServicio) throws MiExcepcion {
        Optional<Proveedor> optionalProveedor = proveedorRepositorio.findById(id);

        optionalProveedor.ifPresent(proveedor -> {
            proveedor.setUsuario(usuario);

            Imagen imagen = null;
            try { imagen = imagenServicio.guardar(archivo); }
            catch (MiExcepcion e) { throw new RuntimeException(e); }
            proveedor.setImagen(imagen);

            proveedor.setDescripcion(descripcion);

            Optional<Servicio> opcionalServicio = servicioRepositorio.findById(idServicio);
            Servicio servicio = new Servicio();

            if (opcionalServicio.isPresent()) { servicio = opcionalServicio.get(); }
            proveedor.setServicio(servicio);

            proveedorRepositorio.save(proveedor);
        });
    }

    @Transactional(readOnly = true)
    public Proveedor getOne(String id) { return proveedorRepositorio.getOne(id); }

     @Transactional(readOnly = true)
    public String idUsuario(String idUsuario) { return proveedorRepositorio.idUsuario(idUsuario); }

    @Transactional(readOnly = true)
    public Proveedor getPorEmail(String email) { return proveedorRepositorio.getPorEmail(email); }

    @Transactional(readOnly = true)
    public List<Proveedor> getPorDireccion(String barrio, String direccion) { return proveedorRepositorio.getPorDireccion(barrio, direccion); }

    @Transactional(readOnly = true)
    public Proveedor getPorPedido(String id) { return proveedorRepositorio.getPorPedido(id); }

    @Transactional(readOnly = true)
    public List<Object> getComentarios(String id) { return proveedorRepositorio.getComentarios(id); }

    @Transactional(readOnly = true)
    public List<Object> getPuntuaciones(String id) { return proveedorRepositorio.getPuntuaciones(id); }
}