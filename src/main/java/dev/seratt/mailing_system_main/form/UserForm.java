package dev.seratt.mailing_system_main.form;


import dev.seratt.mailing_system_main.entity.City;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.sql.Date;
import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
public class UserForm {
    private int id;

    @NotBlank(message = "Name can not be empty")
    @Size(min = 2, max = 25, message = "min 2 and max 25 characters")
    private String name;

    @NotBlank(message = "Surname can not be empty")
    @Size(min = 2, max = 25, message = "min 2 and max 25 characters")
    private String surname;

    @NotBlank(message = "Otchestvo can not be empty")
    @Size(min = 2, max = 25, message = "min 2 and max 25 characters")
    private String otchestvo;

    private int countryId;

    private int cityId;

    @Size(max = 100, message = "Email max 100 characters")
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}",
            flags = Pattern.Flag.CASE_INSENSITIVE,
            message = "Invalid email: does not match the pattern")
    private String email;

    private Timestamp dateOfCreation;

    private City city;

}
