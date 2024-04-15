package valerio.U6W3D1.services;

import org.springframework.beans.factory.annotation.Autowired;
import valerio.U6W3D1.entity.Dipendente;
import valerio.U6W3D1.exceptions.UnauthorizedEx;
import valerio.U6W3D1.payloads.UserLoginDTO;
import valerio.U6W3D1.security.JWTTools;

public class AuthService {
    @Autowired
    private DipendenteService dipendenteService;

    @Autowired
    private JWTTools jwtTools;

    public String authenticateDipendenteAndGenerateToken(UserLoginDTO payload){
        Dipendente dipendente = this.dipendenteService.findByEmail(payload.email());
        if(dipendente.getPassword().equals(payload.password())){
            return jwtTools.createToken(dipendente);
        }else{
            throw  new UnauthorizedEx("credenziali non valide nome utente o password non corretti");
        }
    }

}
