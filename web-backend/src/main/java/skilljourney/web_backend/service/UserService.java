package skilljourney.web_backend.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import skilljourney.web_backend.dao.UserDao;
import skilljourney.web_backend.models.User;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public List<User> getAllUsers() {
        return userDao.findAll();
    }

    public User getUserById(Long id) {
        return userDao.findById(id).orElse(null);
    }

    @Transactional
    public User createUser(User user) {
        return userDao.save(user);
    }

    @Transactional
    public User updateUser(Long id, User updatedUser) {
        User existingUser = userDao.findById(id).orElseThrow();
        existingUser.setUsername(updatedUser.getUsername());
        existingUser.setPassword(updatedUser.getPassword());
        existingUser.setEmail(updatedUser.getEmail());
        return userDao.save(existingUser);
    }

    @Transactional
    public void deleteUser(Long id) {
        userDao.deleteById(id);
    }
}
