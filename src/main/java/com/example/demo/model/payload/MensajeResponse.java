package com.example.demo.model.payload;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@NoArgsConstructor
@ToString
@Builder
public class MensajeResponse implements Serializable {

    private String mensaje;
    private Object object;

    // Constructor privado con todos los argumentos para que @Builder funcione
    private MensajeResponse(String mensaje, Object object) {
        this.mensaje = mensaje;
        this.object = object;
    }
}
