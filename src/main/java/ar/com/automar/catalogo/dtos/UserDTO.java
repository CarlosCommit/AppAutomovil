package ar.com.automar.catalogo.dtos;

import java.io.Serializable;

import ar.com.automar.catalogo.validations.StringField;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;


@Data
public class UserDTO implements Serializable {

    @NotBlank
    @StringField
    private String username;
    @StringField
    @NotBlank
    private String rol;
    @NotBlank
    private String password;
    @NotBlank
    @StringField
    private String nombre;
    @NotBlank
     @StringField
    private String apellido;

}
