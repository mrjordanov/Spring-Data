package example.services;

import example.models.Account;
import example.models.User;
import example.repositories.AccountRepository;
import example.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public void registerUser(User user) {

      /*  User found = userRepository.findByUserName(user.getUserName());
        if(found==null){
            this.userRepository.save(user);
        }*/

        if (userRepository.getByUserName(user.getUserName()) == null) {
            this.userRepository.save(user);

        }
    }
}
