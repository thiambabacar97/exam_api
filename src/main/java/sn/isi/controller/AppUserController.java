package sn.isi.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.isi.entities.AppUserEntity;
import sn.isi.service.AppUserService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class AppUserController {
    private AppUserService appUserService;
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping
    public ResponseEntity<List<AppUserEntity>> getAppUsers() {
        return appUserService.get();
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/{id}")
    public  ResponseEntity<AppUserEntity>  getAppUser(@PathVariable("id") int id) {
        return appUserService.get(id);
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<AppUserEntity> createUser(@Valid @RequestBody AppUserEntity appUser) {
        return appUserService.save(appUser);
    }

   @PutMapping("/{id}")
    public  ResponseEntity<AppUserEntity> update(@PathVariable("id") int id, @Valid @RequestBody AppUserEntity appUser) {
        return appUserService.update(id, appUser);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        appUserService.delete(id);
    }

}
