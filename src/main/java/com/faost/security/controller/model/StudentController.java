package com.faost.security.controller.model;

import com.faost.security.service.model.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.NoSuchElementException;

/**
 * Created by re4 on 12.10.16.
 */

@Controller
public class StudentController {
    private static final Logger LOGGER = LoggerFactory.getLogger(StudentController.class);

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @RequestMapping("/student/{id}")
    public ModelAndView getStudentPage(@PathVariable Integer id) {
        LOGGER.debug("Getting student page for student={}", id);
        return new ModelAndView("student", "student", studentService.getStudentById(id)
                .orElseThrow(() -> new NoSuchElementException(String.format("Student=%s not found", id))));
    }

    @RequestMapping("/students")
    public ModelAndView getStudentsPage() {
        LOGGER.debug("Getting students page");
        return new ModelAndView("students", "students", studentService.getAllStudents());
    }
}
