package com.FinalEgg.ServiChacras.entidades;

import jakarta.persistence.*;

import lombok.Setter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import com.FinalEgg.ServiChacras.enumeraciones.*;

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
    private Barrio barrio;
    private String direccion;
    private String telefono;

    @Enumerated(EnumType.STRING)
    private Rol rol;    

    private boolean alta;
}