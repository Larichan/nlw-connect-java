package net.larichan.nlw_connect.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.larichan.nlw_connect.model.User;
import net.larichan.nlw_connect.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserByUserEmail(String userEmail) {
        return userRepository.findByUserEmail(userEmail);
    }

    public User updateUser(Long id, User userDetails) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setUserName(userDetails.getUserName());
            user.setUserEmail(userDetails.getUserEmail());
            return userRepository.save(user);
        } else {
            return null; // or throw an exception
        }
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
