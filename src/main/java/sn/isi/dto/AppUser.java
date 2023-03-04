package sn.isi.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AppUser {
    private int id;
    @NotNull(message = "Le nom ne doit pas etre null")
    private String nom;
    @NotNull(message = "Le prenom ne doit pas etre null")
    private String prenom;
    @NotNull(message = "Le age ne doit pas etre null")
    private int age;
    @NotNull(message = "L'adresse ne doit pas etre null")
    private String adresse;
    @NotNull(message = "L'email ne doit pas etre null")
    private String email;
    @NotNull(message = "Le telephone ne doit pas etre null")
    private String telephone;
    @NotNull(message = "Le specialite ne doit pas etre null")
    private String specialite;
    @NotNull(message = "Le niveau d'etude ne doit pas etre null")
    private String niveauEtude;
    @NotNull(message = "Le mot de passe ne doit pas etre null")
    private String password;

    private List<ExperienceProfessionel> experienceProfessionnelleList = new ArrayList<>();

}
