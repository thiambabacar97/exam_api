package sn.isi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sn.isi.doa.IAppUserRepository;
import sn.isi.entities.AppUserEntity;
import sn.isi.exception.EntityNotFoundException;
import sn.isi.mapping.AppUserMapper;

import java.util.List;
import java.util.Locale;

@Service
public class AppUserService {
    @Autowired
    private IAppUserRepository iAppUserRepository;
    @Autowired
    private AppUserMapper appUserMapper;
    @Autowired
    MessageSource messageSource;

    @Autowired
    BCryptPasswordEncoder bCrypt;
    public String hasPassword(String password) {
        return bCrypt.encode(password);
    }


    public ResponseEntity<AppUserEntity> save(AppUserEntity userReq) {
        userReq.setPassword(hasPassword(userReq.getPassword()));
        AppUserEntity  user = iAppUserRepository.save(userReq);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }


    @Transactional(readOnly = true)
    public AppUserEntity findByEmail(String email) {
        return iAppUserRepository.findByEmail(email).orElseThrow(() -> new EntityNotFoundException(messageSource.getMessage("user.notfound", new Object[]{email},
                Locale.getDefault())));
    }

    public ResponseEntity<List<AppUserEntity>>get() {
        List<AppUserEntity> user =   iAppUserRepository.findAll();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


    public ResponseEntity<AppUserEntity> get(int id) {
        AppUserEntity user = iAppUserRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(messageSource.getMessage("user.notfound", new Object[]{id},
                        Locale.getDefault())));

        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    public AppUserEntity getId(int id) {
        return  iAppUserRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Not found Comment with id = " + id));
    }
    @Transactional
    public  ResponseEntity<AppUserEntity> update(int id, AppUserEntity userReq) {
        iAppUserRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(messageSource.getMessage("user.notfound", new Object[]{id},
                Locale.getDefault())));

        userReq.setId(id);
        AppUserEntity user  = iAppUserRepository.save(userReq);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<HttpStatus> delete(int id) {
        iAppUserRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}


