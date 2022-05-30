package almaz.controllers;

import almaz.models.Course;
import almaz.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/course")
public class CourseController {

    private final CourseRepository courseRepository;

    @Autowired
    public CourseController(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @GetMapping()
    public String getAll(Model model) {
        model.addAttribute("courses", courseRepository.findAll());
        return "course/getAll";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("courses", courseRepository.findById(id));
        return "course/findById";
    }

    @GetMapping("/new")
    public String newCourse(@ModelAttribute("courses") Course course) {
        return "course/new";
    }

    @PostMapping()
    public String createCourse(@ModelAttribute("courses") Course course) {
        courseRepository.saveCourse(course);
        return "redirect:/course";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("courses", courseRepository.findById(id));
        return "course/edit";
    }

    @PatchMapping("{id}")
    public String updateCourse(@ModelAttribute("courses") Course course,
                         @PathVariable("id") Long id) {
        courseRepository.update(id, course);
        return "redirect:/course";
    }

    @DeleteMapping("{id}")
    public String delete(@PathVariable("id") Long id) {
        courseRepository.deleteById(id);
        return "redirect:/course";
    }
}
