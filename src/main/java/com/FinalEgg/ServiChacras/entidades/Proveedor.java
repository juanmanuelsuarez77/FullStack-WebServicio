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
public class Proveedor {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name ="uuid", strategy = "uuid2")
    private String id;

    @OneToOne
    @JoinColumn(name ="id_usuario")
    private Usuario usuario;

    @OneToOne
    @JoinColumn(name ="id_imagen")
    private Imagen imagen;
    private String descripcion;

    @ManyToOne
    @JoinColumn(name ="id_servicio")
    private Servicio servicio;

    @Column(name ="id_pedidos")
    private List<Pedido> pedidos;

    @Column(name = "cantidad_pedidos")
    private Integer cantPedido;

    @Column(name = "promedio_puntuación")
    private Integer promPuntuacion;
}
