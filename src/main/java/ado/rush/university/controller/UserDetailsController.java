package ado.rush.university.controller;

import ado.rush.university.dto.CreateNewsDto;
import ado.rush.university.dto.ImageProjection;
import ado.rush.university.dto.NewsDto;
import ado.rush.university.dto.UserBaseEntityDto;
import ado.rush.university.dto.validgroups.StudentInfo;
import ado.rush.university.dto.validgroups.BasicInfo;
import ado.rush.university.dto.validgroups.TrainerInfo;
import ado.rush.university.entity.Image;
import ado.rush.university.entity.UserType;
import ado.rush.university.service.IDepartmentService;
import ado.rush.university.service.INewsService;
import ado.rush.university.service.IUserService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class UserDetailsController {
    private final IUserService userService;

    private final INewsService newsService;
    private final IDepartmentService departmentService;
    private final Validator validator;

    public UserDetailsController(IUserService userService, INewsService newsService, IDepartmentService departmentService, Validator validator) {
        this.userService = userService;
        this.newsService = newsService;
        this.departmentService = departmentService;
        this.validator = validator;
    }

    @GetMapping
    public String showInfoPage(Model model, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {

        Page<NewsDto> pageReq = newsService.getLatestNews(PageRequest.of(page.orElse(1) - 1, size.orElse(5)));
        model.addAttribute("news", pageReq);

        if (pageReq.getTotalPages() > 0) {
            List<Integer> pages = IntStream.rangeClosed(1, pageReq.getTotalPages()).boxed().collect(Collectors.toList());
            model.addAttribute("pages", pages);
        }

        return "info-page";
    }

    @GetMapping("{UserId}")
    public String showUser(@PathVariable("UserId") Long id, Model model) {
        model.addAttribute("user", userService.findById(id));
        return "user-page";
    }

    @GetMapping("/registerUser")
    public String createUser(Model model) {
        model.addAttribute("user", new UserBaseEntityDto());
        return "usertype-select";
    }

    @PostMapping("/usertypeSelect")
    public String selectRole(@ModelAttribute("user") UserBaseEntityDto userDetailsDto,
                             BindingResult result,
                             Model model) {

        if (userDetailsDto.getUserType() == UserType.STUDENT) {
            List<String> roles = Arrays.stream(UserType.values()).map(Enum::name).collect(Collectors.toList());
            userDetailsDto.setRoles(roles);
        }

        if (userDetailsDto.getUserType() == UserType.TRAINER) {
            List<String> departments = departmentService.getAllDepartments();
            userDetailsDto.setDepartments(departments);
        }
        model.addAttribute("user", userDetailsDto);
        return "user-create";
    }

    @PostMapping("/registerUser")
    public String create(@Validated(BasicInfo.class) @ModelAttribute("user") UserBaseEntityDto userDetailsDto,
                         BindingResult result, Model model) {
        if (userDetailsDto.getUserType() == UserType.STUDENT) {
            Set<ConstraintViolation<UserBaseEntityDto>> validationResult = validator.validate(userDetailsDto, StudentInfo.class);
            if (!validationResult.isEmpty()) {
                for (ConstraintViolation<UserBaseEntityDto> v : validationResult) {
                    result.addError(new ObjectError(v.toString(), v.getMessage()));
                }
            }
        }

        if (userDetailsDto.getUserType().name().equals("TRAINER")) {
            Set<ConstraintViolation<UserBaseEntityDto>> validationResult = validator.validate(userDetailsDto, TrainerInfo.class);
            if (!validationResult.isEmpty()) {
                for (ConstraintViolation<UserBaseEntityDto> v : validationResult) {
                    result.addError(new ObjectError(v.toString(), v.getMessage()));
                }
            }
        }


        if (userService.checkUserByLogin(userDetailsDto.getLogin())) {
            ObjectError CheckLoginError = new ObjectError("login", "login is not unique");
            result.addError(CheckLoginError);
        }
        if (result.hasErrors()) {
            model.addAttribute(userDetailsDto);
            return "user-create";
        }
        userService.save(userDetailsDto);

        return "redirect:/";
    }


    @PreAuthorize(value = "@RoleService.hasAnySiteRole(T(ado.rush.university.entity.SiteRole).ADMIN)")
    @GetMapping("/createNews")
    public String createNews(Model model) {
        model.addAttribute("news", new CreateNewsDto());
        return "news-create";
    }


    @PreAuthorize(value = "@RoleService.hasAnySiteRole(@SiteRole.ADMIN)")
    @PostMapping("/createNews")
    public String createNews(@ModelAttribute("news") CreateNewsDto newsDto,
                             @RequestParam(value = "image", required = false) MultipartFile[] files) {

        List<Image> images = Arrays.stream(files).map(f -> {
            Image img = new Image();
            try {
                img.setImage(f.getBytes());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            img.setName(f.getName());
            return img;
        }).collect(Collectors.toList());

        newsDto.setImageList(images);
        newsService.save(newsDto);


        return "redirect: /";
    }

    @GetMapping("/news/{newsId}/images/{imageId}")
    public ResponseEntity<byte[]> getImage(@PathVariable("newsId") Long newsId, @PathVariable("imageId") Long imageId) {
        ImageProjection resource = newsService.getNewsImages(newsId, imageId);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=\"" + resource.getName() + "\"").body(resource.getImage());
    }


}
