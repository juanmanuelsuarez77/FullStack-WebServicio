package com.FinalEgg.ServiChacras.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.FinalEgg.ServiChacras.entidades.Servicio;

@Repository
public interface ServicioRepositorio extends JpaRepository<Servicio, String> {}