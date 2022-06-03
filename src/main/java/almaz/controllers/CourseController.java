package almaz.controllers;

import almaz.models.Company;
import almaz.models.Course;
import almaz.services.CompanyService;
import almaz.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/course")
public class CourseController {

    private final CourseService courseService;
    private final CompanyService companyService;

    @Autowired
    public CourseController(CourseService courseService, CompanyService companyService) {
        this.courseService = courseService;
        this.companyService = companyService;
    }

    @GetMapping()
    public String getAll(Model model) {
        model.addAttribute("courses", courseService.findAll());
        return "course/getAll";
    }
    @ModelAttribute("companyList")
    public List<Company> findAllCompanies(){
        return companyService.findAll();
    }


    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("courses", courseService.findById(id));
        return "course/findById";
    }

    @GetMapping("/new")
    public String showStudentSavePage(@PathVariable Long courseId, Model model) {

        model.addAttribute("courseId", courseId);
        model.addAttribute("emptyStudent", new Course());

        return "redirect:/course";
    }

    @PostMapping()
    public String saveStudent(Course course,
                              @PathVariable Long courseId) {

        courseService.saveCourse(courseId, course);

        return "redirect:/course" + courseId;
    }

//    @GetMapping("/new")
//    public String newCourse(Model model) {
//        model.addAttribute("courses",new Course());
//        return "course/new";
//    }
//
//    @PostMapping()
//    public String createCourse(Course course) {
//        courseService.saveCourse(course,course.getCompanyId());
//        return "redirect:/course";
    //}

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("courses", courseService.findById(id));
        return "course/edit";
    }

    @PatchMapping("{id}")
    public String updateCourse(@ModelAttribute("courses") Course course,
                         @PathVariable("id") Long id) {
        courseService.update(id, course);
        return "redirect:/course";
    }

    @DeleteMapping("{id}")
    public String delete(@PathVariable("id") Long id) {
        courseService.deleteById(id);
        return "redirect:/course";
    }
}
