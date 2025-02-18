package io.bytesbank.registration.model;

import io.bytesbank.registration.options.Gender;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document("users")
public class User {

    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String mcc;
    private String email;
    private String mobile;
    private Date birthDate;
    private Gender gender;
    private Date createdOn;
    private Boolean inactive;
}
