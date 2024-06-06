package com.FinalEgg.ServiChacras.entidades;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import org.hibernate.annotations.GenericGenerator;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Cliente {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name ="uuid", strategy = "uuid2")
    private String id;

    @OneToOne
    @JoinColumn(name ="id_usuario")
    private Usuario usuario;

    @Column(name ="id_pedidos")
    private List<Pedido> pedidos;

    private boolean alta;
}
