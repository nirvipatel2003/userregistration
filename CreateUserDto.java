package io.bytesbank.registration.dto;

import io.bytesbank.registration.model.User;
import io.bytesbank.registration.options.Gender;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class CreateUserDto {
    private String firstName;
    private String lastName;
    private String mcc;
    private String email;
    private String mobile;
    private Date birthDate;
    private Gender gender;

    public User transform(){
        User u = new User();


        u.setFirstName(firstName);
        u.setLastName(this.lastName);
        u.setMcc(this.mcc);
        u.setMobile(this.mobile);
        u.setEmail(this.email);
        u.setGender(this.gender);
        u.setBirthDate(this.birthDate);
        u.setCreatedOn(new Date());
        return u;
    }
}
