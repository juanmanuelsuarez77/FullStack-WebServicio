package com.FinalEgg.ServiChacras.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.FinalEgg.ServiChacras.entidades.Proveedor;

import java.util.List;

@Repository
public interface ProveedorRepositorio extends JpaRepository<Proveedor, String> {
    @Query("SELECT p.id FROM Proveedor p WHERE p.usuario.id = :idUsuario")
    public String idUsuario(@Param("idUsuario") String idUsuario);

    @Query("SELECT p FROM Proveedor p WHERE p.usuario.email = :email")
    public Proveedor getPorEmail(@Param("email") String email);

    @Query("SELECT p FROM Proveedor p WHERE p.usuario.barrio = :barrio AND p.usuario.direccion = :direccion")
    public List<Proveedor> getPorDireccion(@Param("barrio") String barrio, @Param("direccion") String direccion);

    @Query("SELECT p FROM Pedido p WHERE p.id = :idPedido")
    public Proveedor getPorPedido(@Param("idPedido") String idPedido);

    @Query("SELECT p.id AS pedido, CONCAT(p.cliente.usuario.nombre, ' ', p.cliente.usuario.apellido) AS cliente, p.comentario FROM Pedido p WHERE p.cliente.id = :idCliente")
    public List<Object> getComentarios(@Param("idCliente") String idCliente);

    @Query("SELECT CONCAT(p.proveedor.usuario.nombre, ' ', p.proveedor.usuario.apellido) AS proveedor, p.puntuacion FROM Pedido p WHERE p.proveedor.id = :idProveedor")
    public List<Object> getPuntuaciones(@Param("idProveedor") String idProveedor);
}