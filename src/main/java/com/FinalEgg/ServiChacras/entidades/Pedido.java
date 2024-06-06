package com.FinalEgg.ServiChacras.entidades;

import com.FinalEgg.ServiChacras.enumeraciones.Estado;

import java.util.Date;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Pedido {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name ="uuid", strategy = "uuid2")
    private String id;

    @ManyToOne
    @JoinColumn(name ="id_cliente")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name ="id_servicio")
    private Servicio servicio;

    @ManyToOne
    @JoinColumn(name ="id_proveedor")
    private Proveedor proveedor;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_pedido")
    private Date fechaPedido;

    @Enumerated(EnumType.STRING)
    private Estado estado;

    private String comentario;
    private Integer puntuacion;
}
