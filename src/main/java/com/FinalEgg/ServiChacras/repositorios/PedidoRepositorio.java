package com.FinalEgg.ServiChacras.repositorios;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;

import com.FinalEgg.ServiChacras.entidades.Pedido;

@Repository
public interface PedidoRepositorio extends JpaRepository<Pedido, String> {
    @Query("SELECT p.id AS pedido, CONCAT(p.cliente.usuario.nombre, ' ', p.cliente.usuario.apellido) AS cliente,"+
           " p.estado FROM Pedido p WHERE p.cliente.id = :idCliente")
    public List<Object> getPedidoPorClientes(@Param("idCliente") String idCliente);

    @Query("SELECT p.id AS pedido, CONCAT(p.proveedor.usuario.nombre, ' ', p.proveedor.usuario.apellido) AS proveedor,"+
           " p.estado FROM Pedido p WHERE p.proveedor.id = :idProveedor")
    public List<Object> getPedidoPorProveedores(@Param("idProveedor") String idProveedor);

    @Query("SELECT p.id AS pedido, CONCAT(p.proveedor.usuario.nombre, ' ', p.proveedor.usuario.apellido) AS proveedor,"+
           " CONCAT(p.cliente.usuario.nombre, ' ', p.cliente.usuario.apellido) AS cliente,"+
           " CONCAT(p.cliente.usuario.barrio, ' ', p.cliente.usuario.direccion) AS direccion FROM Pedido p WHERE p.estado = 'PENDIENTE'")
    public List<Object> getPedidosPendiente();

     @Query("SELECT p FROM Pedido p WHERE p.fechaPedido = :fecha")
    public List<Pedido> getPedidosPorFecha(@Param("fecha") Date fecha);

    @Query("SELECT p FROM Pedido p WHERE p.fechaPedido BETWEEN :inicio AND :fin")
    public List<Pedido> getPedidosEntreFechas(@Param("inicio") Date fechaInicio, @Param("fin") Date fechaFin);
}
