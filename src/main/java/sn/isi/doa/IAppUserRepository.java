package sn.isi.doa;

import org.springframework.data.jpa.repository.JpaRepository;
import sn.isi.entities.AppUserEntity;

import java.util.Optional;

public interface IAppUserRepository extends JpaRepository<AppUserEntity, Integer> {
    Optional<AppUserEntity> findByEmail(String email);
}
