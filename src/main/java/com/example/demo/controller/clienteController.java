package com.example.demo.controller;

import com.example.demo.model.dto.ClienteDto;
import com.example.demo.model.entity.Cliente;
import com.example.demo.service.ICliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class clienteController {

    @Autowired
    private ICliente clienteService;

    @PostMapping("/cliente")
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteDto create(@RequestBody ClienteDto ClienteDto){
      Cliente clienteSave =  clienteService.save(ClienteDto);
        return ClienteDto.builder()
                .idCliente(clienteSave.getIdCliente())
                .nombre(clienteSave.getNombre())
                .apellido(clienteSave.getApellido())
                .correo(clienteSave.getCorreo())
                .fechaRegistro(clienteSave.getFechaRegistro())
                .build();


    }

    @PutMapping("/cliente")
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteDto update(@RequestBody ClienteDto ClienteDto){
        Cliente clienteUpdate =  clienteService.save(ClienteDto);
        return ClienteDto.builder()
                .idCliente(clienteUpdate.getIdCliente())
                .nombre(clienteUpdate.getNombre())
                .apellido(clienteUpdate.getApellido())
                .correo(clienteUpdate.getCorreo())
                .fechaRegistro(clienteUpdate.getFechaRegistro())
                .build();

    }

    @DeleteMapping("/cliente/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        Map<String, Object> response = new HashMap<>();
        try {
            Cliente clienteDelete = clienteService.findById(id);
            clienteService.delete(clienteDelete);
            return new ResponseEntity<>(clienteDelete, HttpStatus.NO_CONTENT);
        } catch (DataAccessException exDt) {
            // Manejo de excepciones
            response.put("mensaje", exDt.getMessage());
            response.put("cliente", null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/cliente/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ClienteDto showById(@PathVariable Integer id){
        Cliente cliente =  clienteService.findById(id);
        return ClienteDto.builder()
                .idCliente(cliente.getIdCliente())
                .nombre(cliente.getNombre())
                .apellido(cliente.getApellido())
                .correo(cliente.getCorreo())
                .fechaRegistro(cliente.getFechaRegistro())
                .build();
    }

}
