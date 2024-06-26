package com.FinalEgg.ServiChacras.controladores;

import com.FinalEgg.ServiChacras.entidades.Usuario;
import com.FinalEgg.ServiChacras.entidades.Servicio;
import com.FinalEgg.ServiChacras.excepciones.MiExcepcion;
import com.FinalEgg.ServiChacras.servicios.UsuarioServicio;
import com.FinalEgg.ServiChacras.servicios.ServicioServicio;
import com.FinalEgg.ServiChacras.repositorios.UsuarioRepositorio;
import com.FinalEgg.ServiChacras.repositorios.ServicioRepositorio;
import com.FinalEgg.ServiChacras.repositorios.ProveedorRepositorio;

import org.springframework.ui.ModelMap;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class PortalControlador {
   @Autowired
   private UsuarioServicio usuarioServicio;
   @Autowired
   private UsuarioRepositorio usuarioRepositorio;
   @Autowired
   private ServicioServicio servicioServicio;
   @Autowired
   private ServicioRepositorio servicioRepositorio;
   @Autowired
   private ProveedorRepositorio proveedorRepositorio;

   @GetMapping("/")
   public String index() {
      return "index.html";
   }

   @GetMapping("/registrar")
   public String registrar(ModelMap modelo) {
      List<Servicio> servicios1 = servicioServicio.listarPorCategoria("Servicios de limpieza");
      List<Servicio> servicios2 = servicioServicio.listarPorCategoria("Servicios de mantenimiento y reparaciones");
      List<Servicio> servicios3 = servicioServicio.listarPorCategoria("Servicios de seguridad");
      List<Servicio> servicios4 = servicioServicio.listarPorCategoria("Servicios de tecnologia y conectividad");
      List<Servicio> servicios5 = servicioServicio.listarPorCategoria("Servicios de cuidado personal y bienestar");
      List<Servicio> servicios6 = servicioServicio.listarPorCategoria("Servicios de entrega y logistica");

      modelo.addAttribute("servicios1", servicios1);
      modelo.addAttribute("servicios2", servicios2);
      modelo.addAttribute("servicios3", servicios3);
      modelo.addAttribute("servicios4", servicios4);
      modelo.addAttribute("servicios5", servicios5);
      modelo.addAttribute("servicios6", servicios6);

      return "registro.html";
   }

   @GetMapping("/login")
   public String login( @RequestParam(required = false) String error, ModelMap modelo ) {
      if ( error != null ) { modelo.put("error", "Usuario o Contraseña inválidos!"); }
      return "login.html";
   }

   @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENTE', 'ROLE_PROVEEDOR', 'ROLE_MIXTO')")
   @GetMapping("/inicio")
   public String inicio( HttpSession session ) {
      Usuario logueado = (Usuario) session.getAttribute("usuariosession");

      if ( logueado.getRol().toString().equalsIgnoreCase("ADMIN") ) { return "redirect:/admin/dashboard"; }
      return "inicio.html";
   }

   @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENTE', 'ROLE_PROVEEDOR', 'ROLE_MIXTO')")
   @GetMapping("/perfil")
   public String perfil( ModelMap modelo, HttpSession session ) {
      Usuario usuario = (Usuario) session.getAttribute("usuariosession");
      modelo.put("usuario", usuario);
      return "usuario_modificar.html";
   }

   @PostMapping("/registro")
   public String registro(@RequestParam String nombre, @RequestParam String apellido, @RequestParam String email, @RequestParam String password,
                          @RequestParam String password2, @RequestParam(required = false) Integer barrio, @RequestParam(required = false) String rol,
                          @RequestParam String direccion, @RequestParam String telefono, @RequestParam(required = false) MultipartFile archivo,
                          @RequestParam(required = false) String descripcion, @RequestParam(required = false) String idServicio, ModelMap modelo ) {

      if (barrio == null) { barrio = 0;}
      if (direccion.trim().isEmpty()) { direccion = "No especificada";}
      if (telefono.trim().isEmpty()) { telefono = "No especificada";}
      if (rol == null || rol.trim().isEmpty()) { rol = "USER"; }
      
      try {
         usuarioServicio.registrar(nombre, apellido, email, password, password2,  barrio, rol, direccion, telefono);

         if (rol.equalsIgnoreCase("proveedor") || rol.equalsIgnoreCase("mixto")) {
            Usuario usuario = usuarioServicio.getPorEmail(email);

            if ((descripcion.equals("")) || (descripcion == null) || (idServicio == null)) {
               usuario = usuarioServicio.getPorEmail(email);
               usuarioRepositorio.deleteById(usuario.getId());

               String concat = ".";
               String servicioNombre = "servicio";

               if (idServicio != null) {
                  Optional<Servicio> optionalServicio = servicioRepositorio.findById(idServicio);
                  if (optionalServicio.isPresent()) {
                     servicioNombre = "";
                     concat = "";
                  }
               }

               String descrip = "";
               if ((descripcion.equals("")) || (descripcion == null)) {
                  descrip = "descripción.";
                  if(!servicioNombre.equals("")) { concat = ", "; }
               }

               List<Servicio> servicios1 = servicioServicio.listarPorCategoria("Servicios de limpieza");
               List<Servicio> servicios2 = servicioServicio.listarPorCategoria("Servicios de mantenimiento y reparaciones");
               List<Servicio> servicios3 = servicioServicio.listarPorCategoria("Servicios de seguridad");
               List<Servicio> servicios4 = servicioServicio.listarPorCategoria("Servicios de tecnologia y conectividad");
               List<Servicio> servicios5 = servicioServicio.listarPorCategoria("Servicios de cuidado personal y bienestar");
               List<Servicio> servicios6 = servicioServicio.listarPorCategoria("Servicios de entrega y logistica");

               modelo.addAttribute("servicios1", servicios1);
               modelo.addAttribute("servicios2", servicios2);
               modelo.addAttribute("servicios3", servicios3);
               modelo.addAttribute("servicios4", servicios4);
               modelo.addAttribute("servicios5", servicios5);
               modelo.addAttribute("servicios6", servicios6);

               modelo.put("error", "Los siguientes datos no pueden estar nulos: "+servicioNombre+concat+descrip);
               return "registro.html";
            }
            usuarioServicio.definirMixto(usuario, archivo, descripcion, idServicio, 0);
         }
      
      } catch (MiExcepcion ex) {
         modelo.put("error", "El usuario no pudo ser registrado");
         modelo.put("nombre", nombre);
         modelo.put("apellido", apellido);
         modelo.put("email", email);

         List<Servicio> servicios = servicioServicio.listarServicios();
         modelo.addAttribute("servicios", servicios);
         return "registro.html";
      }
      return "index.html";
   }

   @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENTE', 'ROLE_PROVEEDOR', 'ROLE_MIXTO')")
   @PostMapping("/perfil/{id}")
   public String actualizar(@PathVariable String id, @RequestParam(required = false) String nombre, @RequestParam(required = false) String apellido, @RequestParam(required = false) String email,
                            @RequestParam(required = false) String password, @RequestParam(required = false) String password2, @RequestParam(required = false) Integer barrio,
                            @RequestParam(required = false) String rol, @RequestParam(required = false) String direccion, @RequestParam(required = false) String telefono, 
                            @RequestParam(required = false) MultipartFile archivo, @RequestParam(required = false) String descripcion, @RequestParam(required = false) String idServicio, ModelMap modelo ) {
      try {
         usuarioServicio.actualizar(id, nombre, apellido, email, password, password2, barrio, rol, direccion, telefono);
         
         if (rol.equalsIgnoreCase("proveedor") || rol.equalsIgnoreCase("mixto")) {
            Usuario usuario = usuarioServicio.getOne(id);
            usuarioServicio.definirMixto(usuario, archivo, descripcion, idServicio, 1);

            if ((descripcion.equals("")) || (descripcion == null) || (idServicio == null)) {
               usuario = usuarioServicio.getPorEmail(email);
               usuarioRepositorio.deleteById(usuario.getId());

               String concat = ".";
               String servicioNombre = "servicio";

               if (idServicio != null) {
                  Optional<Servicio> optionalServicio = servicioRepositorio.findById(idServicio);
                  if (optionalServicio.isPresent()) {
                     servicioNombre = "";
                     concat = "";
                  }
               }

               String descrip = "";
               if ((descripcion.equals("")) || (descripcion == null)) {
                  descrip = "descripción.";
                  if(!servicioNombre.equals("")) { concat = ", "; }
               }

               List<Servicio> servicios1 = servicioServicio.listarPorCategoria("Servicios de limpieza");
               List<Servicio> servicios2 = servicioServicio.listarPorCategoria("Servicios de mantenimiento y reparaciones");
               List<Servicio> servicios3 = servicioServicio.listarPorCategoria("Servicios de seguridad");
               List<Servicio> servicios4 = servicioServicio.listarPorCategoria("Servicios de tecnologia y conectividad");
               List<Servicio> servicios5 = servicioServicio.listarPorCategoria("Servicios de cuidado personal y bienestar");
               List<Servicio> servicios6 = servicioServicio.listarPorCategoria("Servicios de entrega y logistica");

               modelo.addAttribute("servicios1", servicios1);
               modelo.addAttribute("servicios2", servicios2);
               modelo.addAttribute("servicios3", servicios3);
               modelo.addAttribute("servicios4", servicios4);
               modelo.addAttribute("servicios5", servicios5);
               modelo.addAttribute("servicios6", servicios6);

               modelo.put("error", "Los siguientes datos no pueden estar nulos: "+servicioNombre+concat+descrip);
               return "registro.html";
            }
            usuarioServicio.definirMixto(usuario, archivo, descripcion, idServicio, 0);
         }
         modelo.put("exito", "Usuario actualizado correctamente, ¿desea modificar algo más?");

      } catch (MiExcepcion ex) {
         modelo.put("error", ex.getMessage());
         modelo.put("nombre", nombre);
         modelo.put("email", email);

         return "actualizarUsuario.html";
      }
      return "actualizarUsuario.html";
   }
}