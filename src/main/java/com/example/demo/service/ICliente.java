package com.example.demo.service;

import com.example.demo.model.dto.ClienteDto;
import com.example.demo.model.entity.Cliente;

public interface ICliente {

    Cliente save(ClienteDto cliente);

    Cliente findById(Integer id);

    void delete(Cliente cliente);
}
