package sn.isi.doa;

import org.springframework.data.jpa.repository.JpaRepository;
import sn.isi.entities.AppUserEntity;
import sn.isi.entities.ExperienceProfessionelEntity;

import java.util.List;

public interface IExperienceProfessionelRepository  extends JpaRepository<ExperienceProfessionelEntity, Integer> {

    List<ExperienceProfessionelEntity> findByUser( AppUserEntity user );
}
