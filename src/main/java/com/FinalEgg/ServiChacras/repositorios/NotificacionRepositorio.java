package com.FinalEgg.ServiChacras.repositorios;

import org.springframework.stereotype.Repository;
import com.FinalEgg.ServiChacras.entidades.Notificacion;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface NotificacionRepositorio extends JpaRepository<Notificacion, String> {}