/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dataaccess.UserDB;
import domain.Users;
import java.util.List;

/**
 *
 * @author 810585
 */
public class UserService {

    private UserDB userDB;

    public UserService() {
        userDB = new UserDB();
    }

    public Users get(String username) throws Exception {
        return userDB.getUser(username);
    }

    public List<Users> getAll() throws Exception {
        return userDB.getAll();
    }

    public int update(String username, String password, String firstname, String lastname, String email,
            boolean active, boolean isAdmin) throws Exception {
        Users user = get(username);
        user.setPassword(password);
        user.setFirstName(firstname);
        user.setLastName(lastname);
        user.setEmail(email);
        user.setActive(active);
        user.setIsAdmin(isAdmin);
        return userDB.update(user);
    }

    public int delete(String username) throws Exception {
        Users deletedUser = userDB.getUser(username);
        // do not allow the admin to be deleted
        if (deletedUser.getUsername().equals("admin")) {
            return 0;
        }
        return userDB.delete(deletedUser);
    }

    public int insert(String username, String password, String email, String firstname, String lastname,
            boolean active, boolean isAdmin) throws Exception {
        Users user = new Users(username, password, email, firstname, lastname, true, false);
        user.setFirstName(firstname);
        user.setLastName(lastname);
        user.setEmail(email);
        user.setActive(active);
        user.setIsAdmin(isAdmin);
        return userDB.insert(user);
    }
}
