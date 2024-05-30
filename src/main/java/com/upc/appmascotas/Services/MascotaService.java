package com.upc.appmascotas.Services;

import com.upc.appmascotas.Entities.Mascota;
import com.upc.appmascotas.Repositories.MascotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MascotaService {
    @Autowired
    private MascotaRepository mascotaRepository;

    @Transactional(rollbackFor = Exception.class)
    public Mascota create(Mascota mascota){
        return mascotaRepository.save(mascota);
    }

    @Transactional(rollbackFor = Exception.class)
    public Mascota edit(Mascota mascota) throws Exception{
        mascotaRepository.findById(mascota.getId()).orElseThrow(() -> new Exception("No se encontró la mascota"));
        return mascotaRepository.save(mascota);
    }

    @Transactional(rollbackFor = Exception.class)
    public Mascota delete(Long id) throws Exception{
        Mascota mascota = mascotaRepository.findById(id).orElseThrow(() -> new Exception("No se encontró la mascota"));
        mascotaRepository.delete(mascota);
        return mascota;
    }

    public List<Mascota> list(){
        return mascotaRepository.findAll();
    }

    public Mascota searchById(Long id) throws Exception{
        return mascotaRepository.findById(id).orElseThrow(() -> new Exception("No se encontró la mascota"));
    }
}
