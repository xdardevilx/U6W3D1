package valerio.U6W3D1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import valerio.U6W3D1.entity.Dispositivo;
import valerio.U6W3D1.payloads.DispositivoDTO;
import valerio.U6W3D1.services.DispositivoService;

@RestController
@RequestMapping("dispositivi")
public class DispositivoController {
    @Autowired
    DispositivoService dispositivoService;


    // 1. - POST http://localhost:3001/dispositivi (+ req.body)

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED) // <-- 201
    public DispositivoDTO saveDispositivo(@RequestBody @Validated DispositivoDTO dispositivoDTO) throws Exception {
        System.out.println(dispositivoDTO);
        return dispositivoService.save(dispositivoDTO);
    }

    // 2. - GET http://localhost:3001/dispositivi

    @GetMapping("")
    public Page<Dispositivo> getAllDispositivi(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String sort) {
        return dispositivoService.getDispositivi(page, size, sort);
    }

    // 3. - GET http://localhost:3001/dispositivi/{id}
    @GetMapping("/{dispositivoId}")
    public Dispositivo findById(@PathVariable int dispositivoId) throws Exception {
        return dispositivoService.findById(dispositivoId);
    }

    // http://localhost:3001/dispositivi//{dispositivoId}/assign/{dipendenteId}
    @PostMapping("/{dispositivoId}/assign/{dipendenteId}")
    public Dispositivo findAndAssociate(@PathVariable int dispositivoId, @PathVariable int dipendenteId) throws Exception {
            Dispositivo dispositivo = dispositivoService.findById(dispositivoId);
            return dispositivoService.findAndAssociate(dispositivoId, dipendenteId);

    }

}
