package com.faost.security.controller;

import com.faost.security.domain.Game;
import com.faost.security.domain.User;
import com.faost.security.domain.UserCreateForm;
import com.faost.security.service.game.GameService;
import com.faost.security.service.user.UserService;
import com.faost.security.validator.UserCreateFormValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.NoSuchElementException;

@Controller
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;
    private final UserCreateFormValidator userCreateFormValidator;
    private final GameService gameService;

    @Autowired
    public UserController(UserService userService, UserCreateFormValidator userCreateFormValidator,
                          GameService gameService) {
        this.userService = userService;
        this.gameService = gameService;
        this.userCreateFormValidator = userCreateFormValidator;
    }

    @InitBinder("form")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(userCreateFormValidator);
    }

    @PreAuthorize("@currentUserServiceImpl.canAccessUser(principal, #id)")
    @RequestMapping("/user/{id}")
    public ModelAndView getUserPage(@PathVariable Long id) {
        LOGGER.debug("Getting user page for user={}", id);
        return new ModelAndView("user", "user", userService.getUserById(id)
                .orElseThrow(() -> new NoSuchElementException(String.format("User=%s not found", id))));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/user/create", method = RequestMethod.GET)
    public ModelAndView getUserCreatePage() {
        LOGGER.debug("Getting user create form");
        return new ModelAndView("user_create", "form", new UserCreateForm());
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/user/create", method = RequestMethod.POST)
    public String handleUserCreateForm(@Valid @ModelAttribute("form") UserCreateForm form, BindingResult bindingResult) {
        LOGGER.debug("Processing user create form={}, bindingResult={}", form, bindingResult);
        if (bindingResult.hasErrors()) {
            // failed validation
            return "user_create";
        }
        try {
            userService.create(form);
        } catch (DataIntegrityViolationException e) {
            // probably email already exists - very rare case when multiple admins are adding same user
            // at the same time and form validation has passed for more than one of them.
            LOGGER.warn("Exception occurred when trying to save the user, assuming duplicate email", e);
            bindingResult.reject("email.exists", "Email already exists");
            return "user_create";
        }
        // ok, redirect
        return "redirect:/users";
    }

    @RequestMapping(value = "/game/current", method = RequestMethod.GET)
    public ModelAndView getUsersCurrentGame(){
        //вот эта ебатень загружает текущего юзера по его залогиненному юзернейму.
        //выглядит странно, ощущается еще хуже. быть может стоит переписать?
        User user = userService.getUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).get();

        if (gameService.letsPlay(user).equals("OK")){
            Game curentGame = user.getGame();
            return new ModelAndView("current_game", "game", curentGame);
        }
        else return new ModelAndView("user", "user", userService.getUserById(user.getId())
                .orElseThrow(() -> new NoSuchElementException(String.format("User=%s not found", user.getId()))));
    }
}
