package com.FinalEgg.ServiChacras.repositorios;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;

import com.FinalEgg.ServiChacras.entidades.Cliente;

@Repository
public interface ClienteRepositorio extends JpaRepository<Cliente, String> {
    @Query("SELECT id FROM Cliente c WHERE c.usuario.id = :idUsuario")
    public String idUsuario(@Param("idUsuario") String idUsuario);

    @Query("SELECT c FROM Cliente c WHERE c.usuario.email = :email")
    public Cliente getPorEmail(@Param("email") String email);

    @Query("SELECT c FROM Cliente c WHERE c.usuario.barrio = :barrio AND c.usuario.direccion = :direccion")
    public List<Cliente> getPorDireccion(@Param("barrio") String barrio, @Param("direccion") String direccion);

    @Query("SELECT cliente FROM Pedido p WHERE p.id = :idPedido")
    public Cliente getPorPedido(@Param("idPedido") String idPedido);

    @Query("SELECT CONCAT(p.cliente.usuario.nombre, ' ', p.cliente.usuario.apellido) AS cliente,"+
           " p.cliente.usuario.email AS correo, p.comentario FROM Pedido p WHERE p.cliente.id = :idUsuario")
    public List<Object> getComentarios(@Param("idUsuario") String idUsuario);
}