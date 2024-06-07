package com.FinalEgg.ServiChacras.servicios;

import com.FinalEgg.ServiChacras.entidades.Imagen;
import com.FinalEgg.ServiChacras.entidades.Usuario;
import com.FinalEgg.ServiChacras.entidades.Servicio;
import com.FinalEgg.ServiChacras.entidades.Proveedor;
import com.FinalEgg.ServiChacras.excepciones.MiExcepcion;
import com.FinalEgg.ServiChacras.repositorios.ServicioRepositorio;
import com.FinalEgg.ServiChacras.repositorios.ProveedorRepositorio;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public class ProveedorServicio {
    @Autowired
    private ProveedorRepositorio proveedorRepositorio;
    @Autowired
    private ServicioRepositorio servicioRepositorio;

    @Autowired
    ImagenServicio imagenServicio;

    @Transactional
    public void crearProveedor(MultipartFile archivo, Usuario usuario, String descripcion, String idServicio) throws MiExcepcion {
        Proveedor proveedor = new Proveedor();

        proveedor.setUsuario(usuario);

        Imagen imagen = imagenServicio.guardar(archivo);
        proveedor.setImagen(imagen);

        proveedor.setDescripcion(descripcion);

        Optional<Servicio> opcionalServicio = servicioRepositorio.findById(idServicio);
        Servicio servicio = new Servicio();

        if (opcionalServicio.isPresent()) { 
            servicio = opcionalServicio.get(); 
        }
        proveedor.setServicio(servicio);

        proveedorRepositorio.save(proveedor);
    }

    @Transactional(readOnly = true)
    public List<Proveedor> listarProveedors() { return proveedorRepositorio.findAll(); }

    public void actualizar(String id, MultipartFile archivo, Usuario usuario, String descripcion, String idServicio) throws MiExcepcion {
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

            if (opcionalServicio.isPresent()) {
                servicio = opcionalServicio.get();
            }
            proveedor.setServicio(servicio);

            proveedorRepositorio.save(proveedor);
        });
    }

    public Proveedor getOne(String id) { return proveedorRepositorio.getOne(id); }
}