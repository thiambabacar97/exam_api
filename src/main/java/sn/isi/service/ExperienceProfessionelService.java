package sn.isi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sn.isi.doa.IExperienceProfessionelRepository;
import sn.isi.entities.AppUserEntity;
import sn.isi.entities.ExperienceProfessionelEntity;
import sn.isi.exception.EntityNotFoundException;

import java.util.List;
import java.util.Locale;

@Service
public class ExperienceProfessionelService {
    @Autowired
    private IExperienceProfessionelRepository iExperienceProRepository;
    @Autowired
    MessageSource messageSource;


    public ResponseEntity<ExperienceProfessionelEntity> save(ExperienceProfessionelEntity expReq) {
        ExperienceProfessionelEntity  experiencePro = iExperienceProRepository.save(expReq);
        return new ResponseEntity<>(experiencePro, HttpStatus.CREATED);
    }


    public List<ExperienceProfessionelEntity>get() {
        //List<ExperienceProfessionelEntity> experiencePro =   iExperienceProRepository.findAll();
        return iExperienceProRepository.findAll();
    }


    public ResponseEntity<ExperienceProfessionelEntity> get(int id) {
        ExperienceProfessionelEntity experiencePro = iExperienceProRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Not found Comment with id = " + id));

        return new ResponseEntity<>(experiencePro, HttpStatus.OK);
    }
    public ResponseEntity<List<ExperienceProfessionelEntity>> getExpProByUserId(AppUserEntity userReq) {
        List<ExperienceProfessionelEntity> expPro = iExperienceProRepository.findByUser(userReq);
        return new ResponseEntity<>(expPro, HttpStatus.OK);
    }


    @Transactional
    public  ResponseEntity<ExperienceProfessionelEntity> update(int id, ExperienceProfessionelEntity expReq) {
        iExperienceProRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(messageSource.getMessage("experiencePro.notfound", new Object[]{id},
                        Locale.getDefault())));

        expReq.setId(id);
        ExperienceProfessionelEntity experiencePro  = iExperienceProRepository.save(expReq);
        return new ResponseEntity<>(experiencePro, HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<HttpStatus> delete(int id) {
        iExperienceProRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
