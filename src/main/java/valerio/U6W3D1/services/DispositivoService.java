package valerio.U6W3D1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import valerio.U6W3D1.entity.Dipendente;
import valerio.U6W3D1.entity.Dispositivo;
import valerio.U6W3D1.exceptions.NotFoundException;
import valerio.U6W3D1.payloads.DispositivoDTO;
import valerio.U6W3D1.repositories.DispositivoDAO;

import java.util.Objects;

@Service
public class DispositivoService {

    @Autowired
    private DispositivoDAO dispositivoDAO;

    @Autowired
    private DipendenteService dipendenteService;


    public Page<Dispositivo> getDispositivi(int page, int size, String sortBy) {
        if(size < 100) size = 100;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return dispositivoDAO.findAll(pageable);
    }

    public DispositivoDTO save(DispositivoDTO newDispositivoDTO) {
        Dispositivo dispositivo = new Dispositivo(newDispositivoDTO.tipologia(), newDispositivoDTO.stato());
        dispositivoDAO.save(dispositivo);


        return newDispositivoDTO;
    }

    public Dispositivo findById(int dispositivoId) {
        return this.dispositivoDAO.findById(dispositivoId).orElseThrow(() -> new NotFoundException(dispositivoId));
    }


    public void findByIdAndDelete(int id) {
        Dispositivo found = this.findById(id);
        this.dispositivoDAO.delete(found);
    }

    public DispositivoDTO findByIdAndUpdate(int id, DispositivoDTO modifiedDispositivo) {
        Dispositivo found = this.findById(id);
        found.setTipologia(modifiedDispositivo.tipologia());
        found.setStato(modifiedDispositivo.stato());

        return modifiedDispositivo;

    }

    public Dispositivo findAndAssociate(int idDispositivo, int idDipendente) {
        Dispositivo found = this.findById(idDispositivo);
        Dipendente foundDipendente = dipendenteService.findById(idDipendente);

        if(found.getDipendente() != null || Objects.equals(found.getStato(), "in manutenzione") || Objects.equals(found.getStato(), "dismesso")){
        throw new NotFoundException("non è possibile assegnare questo dispositivo");

        }else if(Objects.equals(found.getStato(), "da assegnare")){
            found.setStato("assegnato");
        }
        found.setDipendente(foundDipendente);
        dispositivoDAO.save(found);
        return found;
    }


}
