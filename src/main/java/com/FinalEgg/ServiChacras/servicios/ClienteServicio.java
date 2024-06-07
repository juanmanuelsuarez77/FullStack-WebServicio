package com.FinalEgg.ServiChacras.servicios;

import com.FinalEgg.ServiChacras.entidades.Cliente;
import com.FinalEgg.ServiChacras.entidades.Usuario;
import com.FinalEgg.ServiChacras.excepciones.MiExcepcion;
import com.FinalEgg.ServiChacras.repositorios.ClienteRepositorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public class ClienteServicio {
    @Autowired
    private ClienteRepositorio clienteRepositorio;

    @Transactional
    public void crearCliente(Usuario usuario) throws MiExcepcion {
        Cliente cliente = new Cliente();
        cliente.setUsuario(usuario);
        clienteRepositorio.save(cliente);
    }

    @Transactional(readOnly = true)
    public List<Cliente> listarClientes() { return clienteRepositorio.findAll(); }

    public void actualizar(String id, String nombre, String apellido, String email, String password, String password2, Integer barrio, String rolString, String direccion, String telefono) throws MiExcepcion {
        Optional<Cliente> optionalCliente = clienteRepositorio.findById(id);

        optionalCliente.ifPresent(cliente -> {
            Usuario usuario = cliente.getUsuario();
            UsuarioServicio usuarioServicio = new UsuarioServicio();

            try { usuarioServicio.actualizar(usuario.getId(), nombre, apellido, email, password, password2, barrio, rolString, direccion, telefono); }
            catch (MiExcepcion e) { e.printStackTrace(); }

            cliente.setUsuario(usuario);
            clienteRepositorio.save(cliente);
        });
    }

    public Cliente getOne(String id) { return clienteRepositorio.getOne(id); }
}