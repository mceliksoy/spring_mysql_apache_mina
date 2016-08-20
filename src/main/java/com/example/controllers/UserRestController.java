package com.example.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.models.User;
import com.example.repositories.UserRepository;

@Controller
@RequestMapping("/users")
public class UserRestController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@RequestBody User user) {
        userRepository.save(user);
        return "User succesfully created! (id = " + user.getId() + ")";
    }

    /**
     * Delete the user having the passed id.
     *
     * @param id
     *            The id of the user to delete
     * @return
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable Long id) {
        User user = new User(id);
        userRepository.delete(user);
        return "User succesfully deleted!";
    }

    // /**
    // * Return the id for the user having the passed email.
    // *
    // * @param email
    // * The email to search in the database.
    // * @return
    // */
    // @RequestMapping("/get-by-email")
    // @ResponseBody
    // public String getByEmail(String email) {
    // String userId;
    // User user = userRepository.findByEmail(email);
    // userId = String.valueOf(user.getId());
    // return "The user id is: " + userId;
    // }
    //
    // /**
    // * Update the email and the name for the user in the database having the passed id.
    // *
    // * @param id
    // * The id for the user to update.
    // * @param email
    // * The new email.
    // * @param name
    // * The new name.
    // * @return
    // */
    // @RequestMapping("/update")
    // @ResponseBody
    // public String updateUser(long id, String email, String name) {
    // User user = userRepository.findOne(id);
    // user.setEmail(email);
    // user.setName(name);
    // userRepository.save(user);
    // return "User succesfully updated!";
    // }

}
