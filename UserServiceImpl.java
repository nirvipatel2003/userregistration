package io.bytesbank.registration.service.impl;

import io.bytesbank.registration.dto.CreateUserDto;
import io.bytesbank.registration.model.User;
import io.bytesbank.registration.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public User create(CreateUserDto req) {
        User newUser = mongoTemplate.insert(req.transform());
        return newUser;
    }

    @Override
    public List<User> findByName(String firstName) {
        Query query = new Query(Criteria.where("firstName").is(firstName));
        log.trace("findByName:{}",query);
        List<User> u = mongoTemplate.find(query,User.class);
        return u;
    }

    @Override
    public User findById(String id) {
        Query q = new Query();
        Criteria cId = Criteria.where("_id").is(id);
        q.addCriteria(cId);
        return mongoTemplate.findOne(q,User.class);
    }

    public User updateMobile(String id,String mobile){
        User u = findById(id);
        u.setMobile(mobile);
        User updated = mongoTemplate.save(u);
        return updated;
    }

    @Override
    public Optional<User> deleteId(String id) {
        Query q=new Query();
        Criteria did=Criteria.where("_id").is(id);
        q.addCriteria(did);
        User result = mongoTemplate.findOne(q, User.class);
        if(result == null){
            return Optional.empty();
        }else{

            mongoTemplate.remove(result);
            return Optional.of(result);
        }

    }

    @Override
    public Map<String, User> getUsersMappedByMobile() {
        List<User> userList=mongoTemplate.findAll(User.class);
        Map<String,User> userMap = new HashMap<>();
        for (User user:userList){
            if (user.getMobile() != null && !user.getMobile().isEmpty()) {
                userMap.put(user.getMobile(),user);
            }
        }
    return userMap;

    }

}
