package com.example.progetto_16_02.controller;
import com.example.progetto_16_02.exception.NotFoundException;
import com.example.progetto_16_02.model.DispositivoRequest;
import com.example.progetto_16_02.model.CustomResponse;
import com.example.progetto_16_02.service.DispositivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
public class DispositivoController {
    @Autowired
    private DispositivoService dispositivoService;


    @GetMapping("/dispositivo")
    public ResponseEntity<CustomResponse> getAll(Pageable pageable){
            try{
                return CustomResponse.success(HttpStatus.OK.toString(), dispositivoService.getAll(pageable), HttpStatus.OK);
            }
            catch (Exception e){
                return CustomResponse.error(HttpStatus.INTERNAL_SERVER_ERROR);
            }

    }
    @GetMapping("/dispositivo/{id}")
    public ResponseEntity<CustomResponse> getDispositivoById(@PathVariable int id){
        try{
            return CustomResponse.success(HttpStatus.OK.toString(), dispositivoService.getDispsotivoById(id), HttpStatus.OK);
        }
        catch (NotFoundException e){
            return CustomResponse.error(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            return CustomResponse.error(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/dispositivo")
    public ResponseEntity<CustomResponse> saveDispositivo(@RequestBody @Validated DispositivoRequest dispositivoRequest, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return CustomResponse.error(bindingResult.getAllErrors().stream().map(objectError -> objectError.getDefaultMessage()).toList().toString(), HttpStatus.BAD_REQUEST);
        }

        try{
            return CustomResponse.success(HttpStatus.OK.toString(), dispositivoService.saveDispositivo(dispositivoRequest), HttpStatus.OK);
        }
        catch (NotFoundException e){
            return CustomResponse.error(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            return CustomResponse.error(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/dispositivo/{id}")
    public ResponseEntity<CustomResponse> updateDispositivo(@PathVariable int id, @RequestBody @Validated DispositivoRequest dispositivoRequest, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return CustomResponse.error(bindingResult.getAllErrors().toString(), HttpStatus.BAD_REQUEST);
        }
        try{
            return CustomResponse.success(HttpStatus.OK.toString(), dispositivoService.updateDispositivo(id, dispositivoRequest), HttpStatus.OK);
        }
            catch (NotFoundException e){
            return CustomResponse.error(e.getMessage(), HttpStatus.NOT_FOUND);
        }
            catch (Exception e){
            return CustomResponse.error(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/dispositivo/{id}")
    public ResponseEntity<CustomResponse> deleteDispositivo(@PathVariable int id){

            dispositivoService.deleteDispositivo(id);
            try{
                return CustomResponse.emptyResponse("Dispositivo con id=" + id + " cancellato", HttpStatus.OK);
            }
            catch (NotFoundException e){
                return CustomResponse.error(e.getMessage(), HttpStatus.NOT_FOUND);
            }
            catch (Exception e){
                return CustomResponse.error(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
            }

    }





}
