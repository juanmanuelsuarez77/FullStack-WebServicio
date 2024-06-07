package com.FinalEgg.ServiChacras.servicios;

import com.FinalEgg.ServiChacras.entidades.*;
import com.FinalEgg.ServiChacras.enumeraciones.Estado;
import com.FinalEgg.ServiChacras.excepciones.MiExcepcion;
import com.FinalEgg.ServiChacras.repositorios.PedidoRepositorio;
import com.FinalEgg.ServiChacras.repositorios.ClienteRepositorio;
import com.FinalEgg.ServiChacras.repositorios.ServicioRepositorio;
import com.FinalEgg.ServiChacras.repositorios.ProveedorRepositorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public class PedidoServicio {
    @Autowired
    private PedidoRepositorio pedidoRepositorio;
    @Autowired
    private ClienteRepositorio clienteRepositorio;
    @Autowired
    private ServicioRepositorio servicioRepositorio;
    @Autowired
    private ProveedorRepositorio proveedorRepositorio;

    @Autowired
    ImagenServicio imagenServicio;

    @Transactional
    public void crearPedido(String idCliente, String idServicio, String idProveedor) throws MiExcepcion {
        Pedido pedido = new Pedido();

        Optional<Cliente> opcionalCliente = clienteRepositorio.findById(idCliente);
        Cliente cliente = new Cliente();

        if (opcionalCliente.isPresent()) { cliente = opcionalCliente.get(); }
        pedido.setCliente(cliente);

        Optional<Servicio> opcionalServicio = servicioRepositorio.findById(idServicio);
        Servicio servicio = new Servicio();

        if (opcionalServicio.isPresent()) { servicio = opcionalServicio.get(); }
        pedido.setServicio(servicio);

        Optional<Proveedor> opcionalProveedor = proveedorRepositorio.findById(idProveedor);
        Proveedor proveedor = new Proveedor();

        if (opcionalProveedor.isPresent()) { proveedor = opcionalProveedor.get(); }
        pedido.setProveedor(proveedor);
        pedido.setFechaPedido(new Date());
        pedido.setEstado(Estado.PENDIENTE);
    }

    @Transactional(readOnly = true)
    public List<Pedido> listarPedidos() { return pedidoRepositorio.findAll(); }

    @Transactional
    public void actualizar(String id, String estadoString, String comentario, Integer puntuacion) throws MiExcepcion {
        Optional<Pedido> optionalPedido = pedidoRepositorio.findById(id);

        optionalPedido.ifPresent(pedido -> {
            Estado estado = Estado.valueOf(estadoString.toUpperCase());
            pedido.setEstado(estado);
            pedido.setComentario(comentario);
            pedido.setPuntuacion(puntuacion);
        });
    }

    @Transactional(readOnly = true)
    public Pedido getOne(String id) { return pedidoRepositorio.getOne(id); }

    @Transactional(readOnly = true)
    public List<Object> getPedidoPorClientes(String id) { return pedidoRepositorio.getPedidoPorClientes(id); }

    @Transactional(readOnly = true)
    public List<Object> getPedidoPorProveedores(String id) { return pedidoRepositorio.getPedidoPorProveedores(id); }

    @Transactional(readOnly = true)
    public List<Object> getPedidosPendiente() { return pedidoRepositorio.getPedidosPendiente(); }
}