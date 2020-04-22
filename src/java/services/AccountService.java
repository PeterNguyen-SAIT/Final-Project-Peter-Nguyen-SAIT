/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dataaccess.UserDB;
import domain.Users;

/**
 *
 * @author Clara Lee 810783
 */
public class AccountService {

    public Users login(String username, String password) {
        try {
            UserDB userDB = new UserDB();
            Users user = userDB.getUser(username);

            if (user.getPassword().equals(password)) {
                return user;
            }
        } catch (Exception e) {

        }

        return null;
    }
}
