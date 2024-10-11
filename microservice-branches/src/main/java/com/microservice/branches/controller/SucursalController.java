package com.microservice.branches.controller;

import com.microservice.branches.entities.Sucursal;
import com.microservice.branches.service.ISucursalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/sucursales")
public class SucursalController {

    @Autowired
    private ISucursalService sucursalService;

    @GetMapping("/all")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(sucursalService.findAll());
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<?> findById(@PathVariable Short id) {
        return ResponseEntity.ok(sucursalService.findById(id));
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody Sucursal sucursal) {
        sucursalService.save(sucursal);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Short id) {
        sucursalService.delete(id);
    }


}
