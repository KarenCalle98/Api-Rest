package com.example.demo.model.dto;

import jakarta.persistence.*;
import lombok.*;


import java.io.Serializable;
import java.util.Date;


@Data
@NoArgsConstructor
@ToString
@Builder
public class ClienteDto implements Serializable {

    private Integer idCliente;
    private String nombre;
    private String apellido;
    private String correo;
    private Date fechaRegistro;

    @Builder
    private ClienteDto(Integer idCliente, String nombre, String apellido, String correo, Date fechaRegistro) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.fechaRegistro = fechaRegistro;
    }

}
