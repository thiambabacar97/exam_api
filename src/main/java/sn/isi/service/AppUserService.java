package sn.isi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sn.isi.doa.IAppUserRepository;
import sn.isi.dto.AppUser;
import sn.isi.entities.AppUserEntity;
import sn.isi.entities.ExperienceProfessionelEntity;
import sn.isi.exception.EntityNotFoundException;
import sn.isi.exception.RequestException;
import sn.isi.mapping.AppUserMapper;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class AppUserService {
    @Autowired
    private IAppUserRepository iAppUserRepository;
    @Autowired
    private AppUserMapper appUserMapper;
    @Autowired
    MessageSource messageSource;

    public ResponseEntity<AppUserEntity> save(AppUserEntity userReq) {
        AppUserEntity  user = iAppUserRepository.save(userReq);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
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


