package valerio.U6W3D1.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import valerio.U6W3D1.exceptions.BadRequestException;
import valerio.U6W3D1.payloads.DipendenteDTO;
import valerio.U6W3D1.payloads.NewUserRespDTO;
import valerio.U6W3D1.payloads.UserLoginDTO;
import valerio.U6W3D1.payloads.UserLoginResponseDTO;
import valerio.U6W3D1.services.AuthService;
import valerio.U6W3D1.services.DipendenteService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private DipendenteService dipendenteService;

    @PostMapping("/login")
    public UserLoginResponseDTO login(@RequestBody UserLoginDTO userLoginDTO) {
        return new UserLoginResponseDTO(this.authService.authenticateDipendenteAndGenerateToken(userLoginDTO));
    }

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public NewUserRespDTO saveUser(@RequestBody @Validated DipendenteDTO body, BindingResult validation){
        if(validation.hasErrors()){
            throw new BadRequestException(validation.getAllErrors());
        }
        return new NewUserRespDTO(this.dipendenteService.save(body))
    }
}
