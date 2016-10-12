package com.faost.security.controller.model;

import com.faost.security.domain.model.create.GroupCreateForm;
import com.faost.security.service.model.GroupService;
import com.faost.security.validator.GroupCreateFormValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.NoSuchElementException;

/**
 * Created by faos7 on 09.10.16.
 */
@Controller
public class GroupController {

    private static final Logger LOGGER = LoggerFactory.getLogger(GroupController.class);

    private final GroupService groupService;
    private final GroupCreateFormValidator groupCreateFormValidator;

    @Autowired
    public GroupController(GroupService groupService, GroupCreateFormValidator groupCreateFormValidator) {
        this.groupService = groupService;
        this.groupCreateFormValidator = groupCreateFormValidator;
    }


    @InitBinder("form")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(groupCreateFormValidator);
    }

    @RequestMapping("/group/{id}")
    public ModelAndView getGroupPage(@PathVariable Integer id) {
        LOGGER.debug("Getting group page for group={}", id);
        return new ModelAndView("group", "group", groupService.getGroupById(id)
                .orElseThrow(() -> new NoSuchElementException(String.format("Group=%s not found", id))));
    }

    @RequestMapping("/groups")
    public ModelAndView getGroupsPage() {
        LOGGER.debug("Getting groups page");
        return new ModelAndView("groups", "groups", groupService.getAllGroups());
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/group/create", method = RequestMethod.GET)
    public ModelAndView getGroupCreatePage() {
        LOGGER.debug("Getting group create form");
        return new ModelAndView("group_create", "form", new GroupCreateForm());
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/group/create", method = RequestMethod.POST)
    public String handleGroupCreateForm(@Valid @ModelAttribute("form") GroupCreateForm form, BindingResult bindingResult) {
        LOGGER.debug("Processing group create form={}, bindingResult={}", form, bindingResult);
        if (bindingResult.hasErrors()) {
            // failed validation
            return "group_create";
        }
        try {
            groupService.create(form);
        } catch (DataIntegrityViolationException e) {
            LOGGER.warn("Exception occurred when trying to save the group, assuming duplicate name", e);
            bindingResult.reject("group.exists", "Such group already exists");
            return "group_create";
        }
        // ok, redirect
        return "redirect:/groups";
    }
}
