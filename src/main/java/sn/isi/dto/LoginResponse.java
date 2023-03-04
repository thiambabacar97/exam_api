package sn.isi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sn.isi.entities.AppUserEntity;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse implements Serializable {
    private String token;
    private AppUserEntity appUser;

    public LoginResponse(String token) { this.token = token; }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
