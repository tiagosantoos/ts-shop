package com.shopping.ts_shop.service;

import com.shopping.ts_shop.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    @Autowired
    private ClienteRepository clienteRepository;

}
