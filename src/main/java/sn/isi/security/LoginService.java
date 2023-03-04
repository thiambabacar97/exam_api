package sn.isi.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import sn.isi.dto.LoginRequest;
import sn.isi.dto.LoginResponse;
import sn.isi.entities.AppUserEntity;
import sn.isi.service.AppUserService;

import javax.validation.Valid;
import java.util.Date;

@Service
@AllArgsConstructor
public class LoginService {
    private final AuthenticationManager authenticationManager;
    private final AppUserService appUserService;

   // private final JwtEnvironment environment;

    public LoginResponse login(@Valid LoginRequest loginRequest) {
        Authentication authentication = authenticationManager
            .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        AppUserEntity appUserEntity = appUserService.findByEmail(loginRequest.getEmail());

        String jwt = Jwts.builder()
                .setSubject(authentication.getName())
                .setExpiration(new Date(System.currentTimeMillis()+ SecurityConstant.EXPIRATION_TIME))
                .setId(appUserEntity.getId()+"")
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS512,  SecurityConstant.getSecret())
                .claim("roles", authentication.getAuthorities().isEmpty())
                .compact();
            return new LoginResponse(jwt, appUserEntity);
    }
}
