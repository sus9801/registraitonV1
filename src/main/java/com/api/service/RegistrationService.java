package com.api.service;

import com.api.entity.Registration;
import com.api.execption.ResourceNotFoundException;
import com.api.payload.RegistrationDto;
import com.api.repository.RegistrationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static javax.swing.UIManager.get;

@Service
public class RegistrationService {

    private RegistrationRepository registrationRepository;
    private ModelMapper modelMapper;
    public RegistrationService(RegistrationRepository registrationRepository, ModelMapper model){
        this.registrationRepository=registrationRepository;
        this.modelMapper=model;
    }
    public List<RegistrationDto> getAllRegistrations() {
        List<Registration> registrations = registrationRepository.findAll();
        List<RegistrationDto> regDto=registrations.stream().map(x->mapToDto(x)).collect(Collectors.toList());
        return regDto;
    }

    public RegistrationDto createRegistration(RegistrationDto registrationDto){
        //copy dto to entity
        Registration reg=mapToEntity(registrationDto);
        Registration saveEntity=registrationRepository.save(mapToEntity(registrationDto));
        //copy entity to dto
        RegistrationDto dtoo=mapToDto(saveEntity);
        return dtoo;
    }

    public void deleteRegById(Long id){
        registrationRepository.deleteById(id);
    }

    public RegistrationDto updateRegById(Long id,Registration reg){
      Registration regs=registrationRepository.findById(id).get();
           //.get method extract the entity object from optional that is stored in reg.
          regs.setName(reg.getName());
        regs.setEmail(reg.getEmail());
        regs.setMobile(reg.getMobile());
        Registration regis=registrationRepository.save(regs);
        RegistrationDto dto=mapToDto(regis);
        return dto;
    }

    //Copy data Dto to Entity
    Registration mapToEntity(RegistrationDto regDto){
        Registration reg=modelMapper.map(regDto,Registration.class);
        return reg;
    }
    RegistrationDto mapToDto(Registration registration){
        RegistrationDto dto=modelMapper.map(registration,RegistrationDto.class);
        return dto;
    }


    public RegistrationDto findRegsById(long id) {
        Registration reg=registrationRepository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException("RecordNotFound"));

        return mapToDto(reg);
    }
}
