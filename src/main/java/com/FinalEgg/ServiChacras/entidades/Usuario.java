package com.FinalEgg.ServiChacras.entidades;

import com.FinalEgg.ServiChacras.enumeraciones.*;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Usuario {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String nombre;
    private String apellido;

    @Column(unique = true)
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private Rol rol;

    // @ManyToOne
    // @JoinColumn(name ="id_barrio")
    // private Barrio barrio;

    @Enumerated(EnumType.STRING)
    private Barrio barrio;
    
    private String direccion;
    private String telefono;
}