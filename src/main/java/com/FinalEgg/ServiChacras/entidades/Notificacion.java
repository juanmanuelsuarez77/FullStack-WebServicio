package com.FinalEgg.ServiChacras.entidades;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import com.FinalEgg.ServiChacras.enumeraciones.TipoDeNota;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Notificacion {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String nombre;
    
   @Enumerated(EnumType.STRING)
    private TipoDeNota nota;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;
}