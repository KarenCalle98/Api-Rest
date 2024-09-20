package com.example.demo.controller;

import com.example.demo.model.dto.ClienteDto;
import com.example.demo.model.entity.Cliente;
import com.example.demo.model.payload.MensajeResponse;
import com.example.demo.service.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1")
public class clienteController {

    @Autowired
    private IClienteService clienteService;

    @PostMapping("/cliente")
    public ResponseEntity<?>  create(@RequestBody ClienteDto ClienteDto){
        Cliente clienteSave = null;
        try {
            clienteSave = clienteService.save(ClienteDto);
            return new ResponseEntity<>( MensajeResponse.builder()
                            .mensaje("Guardado Correctamente")
                            .object(ClienteDto.builder()
                                    .idCliente(clienteSave.getIdCliente())
                                    .nombre(clienteSave.getNombre())
                                    .apellido(clienteSave.getApellido())
                                    .correo(clienteSave.getCorreo())
                                    .fechaRegistro(clienteSave.getFechaRegistro())
                                    .build())
                            .build()
            , HttpStatus.CREATED);

        } catch (DataAccessException exDt) {
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje(exDt.getMessage())
                            .object(null)
                            .build(),
                    HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    @PutMapping("/cliente")
    public ResponseEntity<?>  update(@RequestBody ClienteDto ClienteDto, @PathVariable Integer id){
        Cliente clienteUpdate = null;

        try {
            if (clienteService.existsById(id)) {
                ClienteDto.setIdCliente(id);
                clienteUpdate = clienteService.save(ClienteDto);
                return new ResponseEntity<>( MensajeResponse.builder()
                        .mensaje("Guardado Correctamente")
                        .object(ClienteDto.builder()
                                .idCliente(clienteUpdate.getIdCliente())
                                .nombre(clienteUpdate.getNombre())
                                .apellido(clienteUpdate.getApellido())
                                .correo(clienteUpdate.getCorreo())
                                .fechaRegistro(clienteUpdate.getFechaRegistro())
                                .build())
                        .build()
                        , HttpStatus.CREATED);
            }else {
                return new ResponseEntity<>(
                        MensajeResponse.builder()
                                .mensaje("El registro que intenta actualizar no se encuentra en la base de datos")
                                .object(null)
                                .build(),
                        HttpStatus.NOT_FOUND);
            }
        } catch (DataAccessException exDt) {

        }

        return null;
    }

    @DeleteMapping("/cliente/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        try {
            Cliente clienteDelete = clienteService.findById(id);
            clienteService.delete(clienteDelete);
            return new ResponseEntity<>(clienteDelete, HttpStatus.NO_CONTENT);
        } catch (DataAccessException exDt) {

            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje(exDt.getMessage())
                            .object(null)
                            .build(),
                    HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    @GetMapping("/cliente/{id}")
    public ResponseEntity<?>  showById(@PathVariable Integer id){
        Cliente cliente =  clienteService.findById(id);

        if (cliente == null) {
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje("El registro que intenta buscar, no existe!!")
                            .object(null)
                            .build()
                    , HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(
                MensajeResponse.builder()
                        .mensaje("")
                        .object(ClienteDto.builder()
                                .idCliente(cliente.getIdCliente())
                                .nombre(cliente.getNombre())
                                .apellido(cliente.getApellido())
                                .correo(cliente.getCorreo())
                                .fechaRegistro(cliente.getFechaRegistro())
                                .build())
                        .build()
                , HttpStatus.OK);
    }

}
