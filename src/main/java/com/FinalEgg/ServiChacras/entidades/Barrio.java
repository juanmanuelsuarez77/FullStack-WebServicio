package com.FinalEgg.ServiChacras.entidades;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Barrio {
    @Id
    @Column(unique = true)
    private String nombre;
    
    @Column(unique = true)
    private String direccion;
}