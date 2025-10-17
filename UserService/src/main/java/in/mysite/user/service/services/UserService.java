package in.mysite.user.service.services;

import in.mysite.user.service.entities.User;

import java.util.List;

public interface UserService {
    //insert one user data
    User saveUser(User user);

    //get all users data
    List<User> getAllUsers();

    //get one user data by userid
    User getUserById(String id);

    //update one user data by userid
    User updateUserById(String id, User user);

    //delete one user data by userid
    void deleteUserById(String id);
}
