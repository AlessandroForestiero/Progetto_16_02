package com.example.progetto_16_02.controller;


import com.example.progetto_16_02.exception.NotFoundException;
import com.example.progetto_16_02.model.CustomResponse;
import com.example.progetto_16_02.model.Dipendente;
import com.example.progetto_16_02.model.DipendenteRequest;
import com.example.progetto_16_02.service.DipendenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.cloudinary.Cloudinary;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;

@RestController
public class DipendenteController {
    @Autowired
    private DipendenteService dipendenteService;
    @Autowired
    private Cloudinary cloudinary;

    @GetMapping("/dipendenti")
    public ResponseEntity<CustomResponse> getAll(Pageable pageable) {

        try {
            return CustomResponse.success(HttpStatus.OK.toString(), dipendenteService.getAll(pageable), HttpStatus.OK);
        } catch (Exception e) {
            return CustomResponse.error(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/dipendenti/{id}")
    public ResponseEntity<CustomResponse> getDipendenteById(@PathVariable int id) {

        try {
            return CustomResponse.success(HttpStatus.OK.toString(), dipendenteService.getDipendenteById(id), HttpStatus.OK);
        } catch (NotFoundException e) {
            return CustomResponse.error(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return CustomResponse.error(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping("/dipendenti")
    public ResponseEntity<CustomResponse> saveDipendente(@RequestBody @Validated DipendenteRequest dipendenteRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return CustomResponse.error(bindingResult.getAllErrors().toString(), HttpStatus.BAD_REQUEST);

        }

        try {
            return CustomResponse.success(HttpStatus.OK.toString(), dipendenteService.saveDipendente(dipendenteRequest), HttpStatus.OK);
        } catch (Exception e) {
            return CustomResponse.error(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/dipendenti/{id}")
    public ResponseEntity<CustomResponse> updateDipendente(@PathVariable int id, @RequestBody @Validated DipendenteRequest dipendenteRequest, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return CustomResponse.error(bindingResult.getAllErrors().toString(), HttpStatus.BAD_REQUEST);
        }

        try {
            return CustomResponse.success(HttpStatus.OK.toString(), dipendenteService.updateDipendente(id, dipendenteRequest), HttpStatus.OK);
        } catch (NotFoundException e) {
            return CustomResponse.error(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return CustomResponse.error(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/dipendenti/{id}")
    public ResponseEntity<CustomResponse> deleteDipendente(@PathVariable int id) {

        try {
            dipendenteService.deleteDipendente(id);
            return CustomResponse.emptyResponse("dipendente con id=" + id + " cancellato", HttpStatus.OK);
        } catch (NotFoundException e) {
            return CustomResponse.error(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return CustomResponse.error(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }
    @PatchMapping("/dispositivo/{id}/upload")
    public ResponseEntity<CustomResponse> uploadImmagineProfilo(@PathVariable int id,@RequestParam("upload") MultipartFile file){
        try {
            Dipendente dipendente = dipendenteService.uploadImmagineProfilo(id, (String)cloudinary.uploader().upload(file.getBytes(), new HashMap()).get("url"));
            return CustomResponse.success(HttpStatus.OK.toString(), dipendente, HttpStatus.OK);
        }
        catch (IOException e){
            return CustomResponse.error(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
