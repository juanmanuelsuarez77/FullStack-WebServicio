package com.FinalEgg.ServiChacras.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.FinalEgg.ServiChacras.entidades.Proveedor;

@Repository
public interface ProveedorRepositorio extends JpaRepository<Proveedor, String> {
    @Query("SELECT id FROM Proveedor p WHERE p.usuario.id = :idUsuario")
    public String idUsuario(@Param("idUsuario") String idUsuario);
}