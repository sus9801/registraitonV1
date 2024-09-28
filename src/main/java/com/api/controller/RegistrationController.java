package com.api.controller;

import com.api.entity.Registration;
import com.api.payload.RegistrationDto;
import com.api.service.RegistrationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/registration")
public class RegistrationController {

    private RegistrationService service;
     public RegistrationController(RegistrationService service){
         this.service=service;
    }


    @GetMapping
    public ResponseEntity<List<RegistrationDto>>getAllRegistration(){
        List<RegistrationDto> regDto=service.getAllRegistrations();
        return new ResponseEntity<>(regDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity <?> saveRegistration
            (@Valid @RequestBody RegistrationDto registrationDto , BindingResult result){
         if(result.hasErrors()){
             return new ResponseEntity<>(result.getFieldError().getDefaultMessage(),HttpStatus.BAD_REQUEST);
         }
        RegistrationDto regDto=service.createRegistration(registrationDto);
        return new ResponseEntity<>(regDto,HttpStatus.CREATED);

    }
//@RequestParam :-in Spring MVC used to bind HTTP request
// parameters to method parameters in a controller.
// in RESTful APIs to retrieve query parameters from a URL.


    @DeleteMapping
    public ResponseEntity<String> DeleteRegistrationById(@RequestParam Long id) {
        service.deleteRegById(id);
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }

    //@RequestBoddy:-it is used to copy the data from json the given object

    @PutMapping("/{id}")
    public ResponseEntity<RegistrationDto> updateRegById(@PathVariable Long id,@RequestBody Registration registration){
              RegistrationDto updateRegisDto=service.updateRegById(id,registration);
               return new ResponseEntity<>(updateRegisDto,HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<RegistrationDto>findRegById(@PathVariable long id){
      RegistrationDto dto=service.findRegsById(id);

         return new ResponseEntity<>(dto,HttpStatus.OK);
    }

}
