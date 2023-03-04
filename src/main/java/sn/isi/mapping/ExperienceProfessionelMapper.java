package sn.isi.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import sn.isi.dto.ExperienceProfessionel;
import sn.isi.entities.ExperienceProfessionelEntity;

@Mapper
public interface ExperienceProfessionelMapper {
    ExperienceProfessionel toExperienceProfessionel(ExperienceProfessionelEntity experienceProfessionelEntity);
    ExperienceProfessionelEntity fromExperienceProfessionel(ExperienceProfessionel experienceProfessionel);
}
