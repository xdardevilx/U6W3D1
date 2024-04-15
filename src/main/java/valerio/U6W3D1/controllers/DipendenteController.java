package valerio.U6W3D1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import valerio.U6W3D1.entity.Dipendente;
import valerio.U6W3D1.payloads.DipendenteDTO;
import valerio.U6W3D1.services.DipendenteService;

@RestController
@RequestMapping("/dipendenti")
public class DipendenteController {
    @Autowired
    private DipendenteService dipendenteService;

   //  1. - POST http://localhost:3001/dipendenti (+ req.body)
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)

    public DipendenteDTO save (@RequestBody @Validated DipendenteDTO newDipendenteDTO){
        return dipendenteService.save(newDipendenteDTO);
    }

    // 2. - GET http://localhost:3001/dipendenti
    @GetMapping("")
    public Page<Dipendente> getAllDipendenti(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String sort) {
        return dipendenteService.getAllDipendenti(page, size, sort);
    }

    // 3. - GET http://localhost:3001/dipendente/{id}
    @GetMapping("/{dipendenteId}")
    public Dipendente findById(@PathVariable int dipendenteId) throws Exception {
        return dipendenteService.findById(dipendenteId);
    }


    // 4. - PUT http://localhost:3001/dipendente/{id} (+ req.body)
    @PutMapping("/{dipendenteId}")
    public DipendenteDTO findAndUpdate(@PathVariable int dipendenteId, @RequestBody DipendenteDTO body) throws Exception {
        return dipendenteService.findByIdAndUpdate(dipendenteId, body);
    }


    // 5. - DELETE http://localhost:3001/dipendenti/{id}

    @DeleteMapping("/{dipendenteId}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // <-- 204 NO CONTENT
    public ResponseEntity<String> findAndDelete(@PathVariable int dipendenteId) {

        dipendenteService.findByIdAndDelete(dipendenteId);
        return ResponseEntity.ok("Dispositivo eliminato con successo");
    }


    // 6. - POST http://localhost:3001/dipendenti/uploadImg/dipendenteId (+ req.body)

    @PostMapping("/uploadImg/{dipendenteId}")
    public Dipendente uploadImg(@RequestParam("pImg") MultipartFile img, @PathVariable int dipendenteId) throws Exception {
        return dipendenteService.findByIdAndUploadImg(dipendenteId, img );

    }

}
