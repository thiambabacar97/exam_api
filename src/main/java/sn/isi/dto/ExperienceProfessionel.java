package sn.isi.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ExperienceProfessionel {
    private int id;
    @NotBlank(message = "L'entreprise ne peut pas être vide")
    @Length(max = 100, message = "L'entreprise doit avoir une longueur maximale de 100 caractères")
    private String entreprise;

    @NotBlank(message = "Le poste ne peut pas être vide")
    @Length(max = 100, message = "Le poste doit avoir une longueur maximale de 100 caractères")
    private String poste;

    @NotNull(message = "La date de début ne peut pas être vide")
    private LocalDate dateDebut;

    private LocalDate dateFin;

    @Length(max = 500, message = "Le commentaire doit avoir une longueur maximale de 500 caractères")
    private String commentaire;
    private AppUser app_user;
}
