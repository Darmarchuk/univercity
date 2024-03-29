package ado.rush.university.controller;


import ado.rush.university.dto.course.CourseCreateDto;
import ado.rush.university.entity.Trainer;
import ado.rush.university.security.RoleService;
import ado.rush.university.service.impl.CourseSevice;
import ado.rush.university.service.impl.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.BindParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CourseController {
    private final RoleService roleService;
    private final TrainerService trainerService;
    private final CourseSevice courseSevice;


    @Autowired
    public CourseController(RoleService roleService, TrainerService trainerService, CourseSevice courseSevice) {
        this.roleService = roleService;
        this.trainerService = trainerService;
        this.courseSevice = courseSevice;
    }

    @GetMapping(value = "/createCourse")
    public String createCourse(Model model) {


        CourseCreateDto dto = new CourseCreateDto();
        Trainer user = trainerService.findTrinerByLogin(roleService.getCurrentUser());
        dto.setTrainer(user);

        model.addAttribute("course", dto);
        return "create-course";
    }

    @PostMapping(value = "/createCourse")
    public String create(@Validated @ModelAttribute("course") CourseCreateDto dto, BindingResult result, Model model) {
        courseSevice.create(dto);
        if(result.hasErrors()){
            model.addAttribute("course",dto);
            return "create-course";
        }

        return "redirect:/";
    }

}
