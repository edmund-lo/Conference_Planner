package controllers;

import usecases.*;

/**
 * A Controller class representing an AdminController which inherits from UserController.
 *
 * @author Edmund Lo
 *
 */
public class AdminController extends UserController{

    /**
     * Constructor for AdminController object. Uses constructor from UserController.
     *
     * @param em  current session's EventManager class.
     * @param um  current session's UserManager class.
     * @param rm  current session's RoomManager class.
     * @param mm  current session's MessageManager class.
     * @param username current logged in user's username.
     */
    public AdminController(EventManager em, UserManager um, RoomManager rm, MessageManager mm, String username) {
        super(em, um, rm, mm, username);
    }
}
