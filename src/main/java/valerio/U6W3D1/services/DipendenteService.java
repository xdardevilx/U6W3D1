package valerio.U6W3D1.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import valerio.U6W3D1.entity.Dipendente;
import valerio.U6W3D1.exceptions.NotFoundException;
import valerio.U6W3D1.payloads.DipendenteDTO;
import valerio.U6W3D1.repositories.DipendenteDAO;

import java.io.IOException;

@Service
public class DipendenteService {

    @Autowired
    private DipendenteDAO dipendenteDAO;

    @Autowired
    private Cloudinary progileImg;

    public DipendenteDTO save (DipendenteDTO newDipendenteDTO){
        Dipendente newDipendente = new Dipendente(newDipendenteDTO.name(),
                newDipendenteDTO.surname(),
                newDipendenteDTO.username(),
                newDipendenteDTO.email(),
                newDipendenteDTO.profileImg());
        dipendenteDAO.save(newDipendente);
        return newDipendenteDTO;

    }

    public Page<Dipendente> getAllDipendenti(int page, int size, String sortBy){
        if(size > 100) size = 100;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return this.dipendenteDAO.findAll(pageable);
    }

    public Dipendente findById(int postId) {
        return this.dipendenteDAO.findById(postId).orElseThrow(() -> new NotFoundException(postId));
    }

    public void findByIdAndDelete(int id) {

        Dipendente found = this.findById(id);
        this.dipendenteDAO.delete(found);
    }

    public DipendenteDTO findByIdAndUpdate(int id, DipendenteDTO body) {
        Dipendente found = this.findById(id);
        found.setName(body.name());
        found.setSurname(body.surname());
        found.setUsername(body.username());
        found.setEmail(body.email());
        found.setProfileImage(body.profileImg());
        this.dipendenteDAO.save(found);
        return body;

    }

    public Dipendente findByIdAndUploadImg(int dipendentiId, MultipartFile img) throws IOException {
        Dipendente found= this.findById(dipendentiId);
        String url = (String) progileImg.uploader().upload(img.getBytes(), ObjectUtils.emptyMap()).get("url");
        found.setProfileImage(url);
        this.dipendenteDAO.save(found);
        return found;
    }


}
