package com.example.demo.model.dao;

import com.example.demo.model.entity.Cliente;
import org.springframework.data.repository.CrudRepository;

public interface ClienteDao  extends CrudRepository<Cliente, Integer> {
}
