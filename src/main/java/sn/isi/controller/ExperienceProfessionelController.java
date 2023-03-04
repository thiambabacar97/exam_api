package sn.isi.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.isi.dto.ExperienceProfessionel;
import sn.isi.entities.ExperienceProfessionelEntity;
import sn.isi.service.AppUserService;
import sn.isi.service.ExperienceProfessionelService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/experiencepros")
@AllArgsConstructor
public class ExperienceProfessionelController {
    private ExperienceProfessionelService experienceProfessionelService;
    private AppUserService appUserService;

    @GetMapping
    public List<ExperienceProfessionelEntity> getExperiencePros() {
        return experienceProfessionelService.get();
    }
    @GetMapping("/{id}")
    public ResponseEntity<ExperienceProfessionelEntity> getExperiencePro(@PathVariable("id") int id) {
        return experienceProfessionelService.get(id);
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/{userId}/user")
    public ResponseEntity<List<ExperienceProfessionelEntity>> getExpProByUserId(@PathVariable("userId") int id) {

        return experienceProfessionelService.getExpProByUserId(appUserService.getId(id));
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<ExperienceProfessionelEntity> createAppRoles(@Valid @RequestBody ExperienceProfessionelEntity ExperiencePro) {
        return experienceProfessionelService.save(ExperiencePro);
    }

    @PutMapping("/{id}")
    public  ResponseEntity<ExperienceProfessionelEntity> updateAppRoles(@PathVariable("id") int id, @Valid @RequestBody ExperienceProfessionelEntity ExperiencePro) {
        return experienceProfessionelService.update(id, ExperiencePro);
    }

    @DeleteMapping("/{id}")
    public void deleteAppRoles(@PathVariable("id") int id) {
        experienceProfessionelService.delete(id);
    }
}
