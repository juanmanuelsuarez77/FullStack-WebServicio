package com.FinalEgg.ServiChacras.servicios;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.FinalEgg.ServiChacras.entidades.*;
import com.FinalEgg.ServiChacras.enumeraciones.*;
import com.FinalEgg.ServiChacras.excepciones.MiExcepcion;
import com.FinalEgg.ServiChacras.repositorios.UsuarioRepositorio;
import com.FinalEgg.ServiChacras.repositorios.NotificacionRepositorio;

public class NotificacionServicio {
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private NotificacionRepositorio notificacionRepositorio;

    @Transactional
    public void crearNotificacion(String notaString, String idUsuario, String idCliente, String idProveedor) throws MiExcepcion {
        TipoDeNota nota = TipoDeNota.valueOf(notaString.toUpperCase());
        Notificacion notificacion = new Notificacion();
        notificacion.setNota(nota);

        String nombre = "";

        switch (notaString) {
            case "PEDIDOSolicitud" -> { nombre = "Solicitud de Pedido"; }
            case "PEDIDOAceptado"-> { nombre = "Se ha aceptado el Pedido"; }
            case "PEDIDORechazado" -> { nombre = "Se ha rechazado el Pedido"; }
            case "PEDIDOCancelado" -> { nombre = "Se ha cancelado el Pedido"; }
            case "PAGODemorado"-> { nombre = "Pago Pendiente"; }
            case "PAGODemandado" -> { nombre = "Pago Exigido"; }
            case "PAGOEfectuado" -> { nombre = "Ha recibido el Pago"; }
            case "DENUNCIA"-> { nombre = "Tiene una Denuncia"; }
            case "DENUNCIARechazada" -> { nombre = "Denuncia desestimada"; }
            case "PUNTUACION" -> { nombre = "Fué puntuado"; }
        }
        notificacion.setNombre(nombre);

        Optional<Usuario> optionalUsuario = usuarioRepositorio.findById(idUsuario);
        if (optionalUsuario.isPresent()) {
            Usuario usuario = optionalUsuario.get();
            notificacion.setUsuario(usuario);

            notificacionRepositorio.save(notificacion);
            destinarPedido(notificacion, idCliente, idProveedor);

        } else { throw new MiExcepcion("El usuario seleccionado no existe."); }
    }

    public void destinarPedido(Notificacion notificacion, String idCliente, String idProveedor) throws MiExcepcion {
        Usuario usuario = notificacion.getUsuario();
    }
}
