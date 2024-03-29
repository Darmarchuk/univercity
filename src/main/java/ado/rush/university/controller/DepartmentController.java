package ado.rush.university.controller;

import ado.rush.university.dto.department.DepartmentDto;
import ado.rush.university.entity.Department;
import ado.rush.university.service.IDepartmentService;
import ado.rush.university.service.impl.DepartmentService;
import ado.rush.university.service.impl.TrainerService;
import jakarta.persistence.criteria.CriteriaBuilder;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller

public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {

        this.departmentService = departmentService;
    }

    @GetMapping(path = "/department/show")
    @PreAuthorize("@RoleService.hasAnySiteRole(@UserType.DEAN)")
    public String show(@RequestParam("page") Optional<Integer> page,
                       @RequestParam("size") Optional<Integer> size,
                       Model model) {
        Page<Department> departments = departmentService.getAllDepartments(PageRequest.of(page.orElse(1) - 1, size.orElse(10)));
        model.addAttribute("departments", departments);

        return "department-list";


    }


    @GetMapping(path = "/department/create")
    @PreAuthorize("@RoleService.hasAnySiteRole(@UserType.DEAN)")
    public String createDep(Model model) {
        DepartmentDto dto = new DepartmentDto();
        model.addAttribute("department", dto);
        return "department-create";

    }

    @PostMapping(path = "/department/create")
    public String create(@Validated @ModelAttribute("department") DepartmentDto dto,
                         Model model, BindingResult result) {
        if (result.hasErrors()) {
            model.addAttribute("department", dto);
            return "department-create";
        }
        departmentService.create(dto);
        return "redirect:/";
    }

//    @PostMapping("/department/depUpdateToEdit")
//    public String updateFromList(@ModelAttribute("department") DepartmentDto dto
//            ,Model model ){
//        model.addAttribute("department",dto);
//        return "department-edit";
//
//    }


    @GetMapping("/department/update/{id}")
    public String getUpdate(@PathVariable("id") Long depId, Model model) {
        model.addAttribute("department", departmentService.getDepartmentById(depId));
        return "department-edit";
    }


    @GetMapping("/department/delete/{id}")
    public String delete(@PathVariable("id") Long depId) {
        departmentService.delete(depId);
        return "redirect:/department/show";
    }

    @PostMapping("/department/update/{id}")
    public String update(@RequestParam("id") Long depId, @Validated @ModelAttribute("department") DepartmentDto dto
            , Model model, BindingResult result) {

        if (result.hasErrors()) {
            model.addAttribute("department", dto);
            return "department-edit";
        }
        departmentService.update(depId, dto);
        return "redirect:/department/show";
    }

    @GetMapping("/department/findById")
    @ResponseBody
    public DepartmentDto findById(@RequestParam("id") Long id) {
        return departmentService.getDepartmentById(id);
    }

    @PostMapping("/department/save")
    public String save(@Validated @ModelAttribute("dep") DepartmentDto dto
            , Model model
            , BindingResult result) {
        if (result.hasErrors()) {
            model.addAttribute("department", dto);
            return "department-edit";
        }
        if (dto.getId() == null) {
            departmentService.create(dto);
        } else {
            departmentService.update(dto.getId(), dto);
        }
        return "redirect:/department/show";
    }

}
