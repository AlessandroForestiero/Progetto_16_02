package com.example.progetto_16_02.controller;

import com.example.progetto_16_02.model.Utente;
import com.example.progetto_16_02.model.UtenteRequest;
import com.example.progetto_16_02.service.UtenteService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    @Autowired private UtenteService utenteService;
    @PostMapping("auth/register")
    public Utente register(@RequestBody @Validated UtenteRequest utenteRequest, BindingResult bindingResult){
if (bindingResult.hasErrors()){
    throw new BadRequestException(bindingResult.getAllErrors().toString());
}
return utenteService.save(utenteRequest)

}
