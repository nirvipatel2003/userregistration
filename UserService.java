package io.bytesbank.registration.service;

import io.bytesbank.registration.dto.CreateUserDto;
import io.bytesbank.registration.model.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserService {

    public User create(CreateUserDto req);
    public List<User> findByName(String firstName);
    public User findById(String id);
    public User updateMobile(String id, String mobile);
    public Optional<User> deleteId(String id);
    public Map<String,User> getUsersMappedByMobile();
}
