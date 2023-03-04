package sn.isi.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import sn.isi.entities.AppUserEntity;
import sn.isi.service.AppUserService;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class UserDetailsServiceImp implements UserDetailsService {

    private final AppUserService userService;

    public UserDetailsServiceImp(AppUserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {

        if (usernameOrEmail.trim().isEmpty()) {
            throw new UsernameNotFoundException("username is empty");
        }

       // System.out.println(usernameOrEmail);

        AppUserEntity appUserEntity = userService.findByEmail(usernameOrEmail.trim());

        System.out.println(appUserEntity);

        if(appUserEntity == null) throw new UsernameNotFoundException(usernameOrEmail+" incorrecte");

        Collection<GrantedAuthority> authorities = new ArrayList<>();

        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));


       /* appUser.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getCode()));
        });

        */

        return new User(appUserEntity.getEmail(), appUserEntity.getPassword(), authorities);
    }
}
