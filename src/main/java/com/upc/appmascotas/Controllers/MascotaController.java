package com.upc.appmascotas.Controllers;

import com.upc.appmascotas.Dtos.MascotaDTO;
import com.upc.appmascotas.Entities.Mascota;
import com.upc.appmascotas.Services.MascotaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
@CrossOrigin(origins = {"http://localhost:4200"})

@RestController
@RequestMapping("/api")
public class MascotaController {
    @Autowired
    private MascotaService mascotaService;
    @PostMapping("/mascota")
    public ResponseEntity<?> create(@RequestBody MascotaDTO mascotaDTO){
        try{
            ModelMapper modelMapper = new ModelMapper();
            Mascota m = modelMapper.map(mascotaDTO, Mascota.class);

            m = mascotaService.create(m);

            mascotaDTO = modelMapper.map(m, MascotaDTO.class);
            return new ResponseEntity<>(mascotaDTO, HttpStatus.OK);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PutMapping("/mascota")
    public ResponseEntity<?> edit(@RequestBody MascotaDTO mascotaDTO){
        try{
            ModelMapper modelMapper = new ModelMapper();
            Mascota m = modelMapper.map(mascotaDTO, Mascota.class);

            m = mascotaService.edit(m);

            mascotaDTO = modelMapper.map(m, MascotaDTO.class);
            return new ResponseEntity<>(mascotaDTO, HttpStatus.OK);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/mascota/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id){
        try{
            ModelMapper modelMapper = new ModelMapper();

            Mascota m = mascotaService.delete(id);

            MascotaDTO mascotaDTO = modelMapper.map(m, MascotaDTO.class);
            return new ResponseEntity<>(mascotaDTO, HttpStatus.OK);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    private MascotaDTO convertToListDto(Mascota mascota){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(mascota, MascotaDTO.class);
    }

    @GetMapping("/mascotas")
    public ResponseEntity<?> list(){
        try {
            List<Mascota> l;
            l = mascotaService.list();
            List<MascotaDTO> listDto = l.stream().map(this::convertToListDto).toList();
            return new ResponseEntity<>(listDto, HttpStatus.OK);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/mascota/{id}")
    public ResponseEntity<?> searchById(@PathVariable(value = "id") Long id){
        try{
            ModelMapper modelMapper = new ModelMapper();

            Mascota m = mascotaService.searchById(id);

            MascotaDTO mascotaDTO = modelMapper.map(m, MascotaDTO.class);
            return new ResponseEntity<>(mascotaDTO, HttpStatus.OK);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
