package com.FinalEgg.ServiChacras.entidades;

import com.FinalEgg.ServiChacras.enumeraciones.Estado;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Pago {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name ="uuid", strategy = "uuid2")
    private String id;

    @OneToOne
    @JoinColumn(name ="id_pedido")
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name ="id_cliente")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name ="id_proveedor")
    private Proveedor proveedor;

    private Integer valor;
    
    @Enumerated(EnumType.STRING)
    private Estado estado;
}
