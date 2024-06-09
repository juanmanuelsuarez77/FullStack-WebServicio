package com.FinalEgg.ServiChacras.controladores;

import com.FinalEgg.ServiChacras.entidades.Servicio;
import com.FinalEgg.ServiChacras.entidades.Usuario;
import com.FinalEgg.ServiChacras.excepciones.MiExcepcion;
import com.FinalEgg.ServiChacras.repositorios.UsuarioRepositorio;
import com.FinalEgg.ServiChacras.servicios.UsuarioServicio;
import com.FinalEgg.ServiChacras.servicios.ServicioServicio;

import org.springframework.ui.ModelMap;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

@Controller
@RequestMapping("/")
public class PortalControlador {
   @Autowired
   private UsuarioServicio usuarioServicio;
   @Autowired
   private UsuarioRepositorio usuarioRepositorio;
   @Autowired
   private ServicioServicio servicioServicio;

   @GetMapping("/")
   public String index() {
      return "index.html";
   }

   @GetMapping("/registrar")
   public String registrar(ModelMap modelo) {
      List<Servicio> servicios = servicioServicio.listarServicios();
      modelo.addAttribute("servicios", servicios);

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
      if (rol == null) { rol = "";}
      
      try {
         usuarioServicio.registrar(nombre, apellido, email, password, password2,  barrio, rol, direccion, telefono);

         if (rol.equalsIgnoreCase("proveedor") || rol.equalsIgnoreCase("mixto")) {
            try {
               Usuario usuario = usuarioServicio.getPorEmail(email);
               usuarioServicio.definirMixto(usuario, archivo, descripcion, idServicio, 0);

            } catch (MiExcepcion ex) {
               Usuario usuario = usuarioServicio.getPorEmail(email);
               usuarioRepositorio.deleteById(usuario.getId());

               modelo.put("error", "Los siguientes datos no pueden estar nulos");
               modelo.put("imagen", nombre);
               modelo.put("descripcion", apellido);
               modelo.put("servicio", email);

               return "registro.html";
            }
         }
         modelo.put("exito", "Usuario registrado con exito");
      
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
            try {
               Usuario usuario = usuarioServicio.getOne(id);
               usuarioServicio.definirMixto(usuario, archivo, descripcion, idServicio, 1);

            } catch (MiExcepcion ex) {
               Usuario usuario = usuarioServicio.getPorEmail(email);
               usuarioRepositorio.deleteById(usuario.getId());

               modelo.put("error", "Los siguientes datos no pueden estar nulos");
               modelo.put("imagen", nombre);
               modelo.put("descripcion", apellido);
               modelo.put("servicio", email);

               return "usuario_modificar.html";
            }
         }
         modelo.put("exito", "Usuario actualizado corectamente");

      } catch (MiExcepcion ex) {
         modelo.put("error", ex.getMessage());
         modelo.put("nombre", nombre);
         modelo.put("email", email);

         return "usuario_modificar.html";
      }
      return "inicio.html";
   }
}