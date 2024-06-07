package com.FinalEgg.ServiChacras.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.FinalEgg.ServiChacras.entidades.Cliente;

@Repository
public interface ClienteRepositorio extends JpaRepository<Cliente, String> {
    @Query("SELECT id FROM Cliente c WHERE c.usuario.id = :idUsuario")
    public String idUsuario(@Param("idUsuario") String idUsuario);
}