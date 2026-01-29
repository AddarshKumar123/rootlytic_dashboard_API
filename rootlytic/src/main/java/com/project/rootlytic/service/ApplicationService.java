package com.project.rootlytic.service;

import com.project.rootlytic.DTO.ApplicationDTO;
import com.project.rootlytic.model.ApplicationModel;
import com.project.rootlytic.repository.ApplicationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ApplicationService {
    @Autowired
    ApplicationRepository applicationRepository;

    @Autowired
    ModelMapper modelMapper;

    public ResponseEntity<String> createApplication(ApplicationDTO applicationDTO){
        try{
            String rawUuid = UUID.randomUUID().toString();

            ApplicationModel applicationModel=new ApplicationModel();
            applicationModel.setApplicationName(applicationDTO.getApplicationName());
            applicationModel.setType(applicationDTO.getType());
            applicationModel.setStatus(applicationDTO.getStatus());
            applicationModel.setApi_key(rawUuid);
            applicationRepository.save(applicationModel);
            return new ResponseEntity<>(rawUuid,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<ApplicationDTO>> fetchServices(){
        try{
            List<ApplicationDTO> services = applicationRepository.findAll()
                    .stream().map(service->modelMapper.map(service,ApplicationDTO.class))
                    .collect(Collectors.toList());
            return new ResponseEntity<>(services,HttpStatus.OK);
        }catch(Exception e){
            throw e;
        }
    }
}
