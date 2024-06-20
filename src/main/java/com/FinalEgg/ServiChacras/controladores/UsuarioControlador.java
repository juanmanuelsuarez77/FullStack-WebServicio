package com.FinalEgg.ServiChacras.controladores;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.ui.ModelMap;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;

import com.FinalEgg.ServiChacras.entidades.Pedido;
import com.FinalEgg.ServiChacras.entidades.Mensaje;
import com.FinalEgg.ServiChacras.entidades.Usuario;
import com.FinalEgg.ServiChacras.entidades.Servicio;
import com.FinalEgg.ServiChacras.entidades.Notificacion;
import com.FinalEgg.ServiChacras.excepciones.MiExcepcion;
import com.FinalEgg.ServiChacras.servicios.PedidoServicio;
import com.FinalEgg.ServiChacras.servicios.UsuarioServicio;
import com.FinalEgg.ServiChacras.servicios.ClienteServicio;
import com.FinalEgg.ServiChacras.servicios.ServicioServicio;
import com.FinalEgg.ServiChacras.servicios.ProveedorServicio;
import com.FinalEgg.ServiChacras.repositorios.UsuarioRepositorio;
import com.FinalEgg.ServiChacras.repositorios.MensajeRepositorio;
import com.FinalEgg.ServiChacras.repositorios.ServicioRepositorio;
import com.FinalEgg.ServiChacras.repositorios.NotificacionRepositorio;

@Controller
@RequestMapping("/usuario")
public class UsuarioControlador {
    @Autowired
    private ClienteServicio clienteServicio;
    @Autowired
    private PedidoServicio pedidoServicio;
    @Autowired
    private UsuarioServicio usuarioServicio;
    @Autowired
    private ServicioServicio servicioServicio;
    @Autowired
    private ProveedorServicio proveedorServicio;
    @Autowired
    private MensajeRepositorio mensajeRepositorio;
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
    @Autowired
    private ServicioRepositorio servicioRepositorio;
    @Autowired
    private NotificacionRepositorio notificacionRepositorio;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENTE', 'ROLE_PROVEEDOR', 'ROLE_MIXTO')")
    @GetMapping("/buscador")
    public String buscador(ModelMap modelo, HttpSession session, @RequestParam(value = "rolSession", required = false) String rolSession) {
        Usuario logueado = (Usuario) session.getAttribute("usuariosession");

        if (rolSession != null && rolSession.equals("CLIENTE")) {
            List<Servicio> limpieza = servicioServicio.listarPorCategoria("Servicios de limpieza");
            List<Servicio> mantenimiento = servicioServicio.listarPorCategoria("Servicios de mantenimiento y reparaciones");
            List<Servicio> seguridad = servicioServicio.listarPorCategoria("Servicios de seguridad");
            List<Servicio> tecnologia = servicioServicio.listarPorCategoria("Servicios de tecnologia y conectividad");
            List<Servicio> cuidado = servicioServicio.listarPorCategoria("Servicios de cuidado personal y bienestar");
            List<Servicio> logistica = servicioServicio.listarPorCategoria("Servicios de entrega y logistica");
    
            modelo.addAttribute("limpieza", limpieza);
            modelo.addAttribute("mantenimiento", mantenimiento);
            modelo.addAttribute("seguridad", seguridad);
            modelo.addAttribute("tecnologia", tecnologia);
            modelo.addAttribute("cuidado", cuidado);
            modelo.addAttribute("logistica", logistica);

        } else if (rolSession != null && rolSession.equals("PROVEEDOR")) {
            String idProveedor = proveedorServicio.idUsuario(logueado.getId());
            List<Pedido> pedidos = pedidoServicio.getPedidoPorProveedores(idProveedor);

            modelo.addAttribute("pedidos", pedidos);

        } else {
            modelo.addAttribute("codigo", 400);
            modelo.addAttribute("mensaje", "El recurso solicitado no existe.");

            return "error.html";
        }

        modelo.addAttribute("rolSession", rolSession);
        return "buscador.html";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENTE', 'ROLE_PROVEEDOR', 'ROLE_MIXTO')")
    @PostMapping("/expositor")
    public String expositor(ModelMap modelo, HttpSession session, @RequestParam(value = "rolSession", required = false) String rolSession, @RequestParam(required = false) Integer barrio, 
                            @RequestParam(required = false) String idServicio, @RequestParam(required = false) String idPedido, @RequestParam(required = false) String nombreUsuario) {
        
        Usuario logueado = (Usuario) session.getAttribute("usuariosession");

        if (rolSession != null && rolSession.equals("CLIENTE")) {
            
        }
        

        return "expositor.html";
    }
}