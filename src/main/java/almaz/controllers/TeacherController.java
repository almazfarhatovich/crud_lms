package almaz.controllers;

import almaz.models.Teacher;
import almaz.repositories.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/teacher")
public class TeacherController {

    private final TeacherRepository teacherRepository;

    @Autowired
    public TeacherController(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @GetMapping()
    public String getAll(Model model) {
        model.addAttribute("teachers", teacherRepository.findAll());
        return "teacher/getAll";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("teachers", teacherRepository.findById(id));
        return "teacher/findById";
    }

    @GetMapping("/new")
    public String newTeacher(@ModelAttribute("teachers") Teacher teacher) {
        return "teacher/new";
    }

    @PostMapping()
    public String createTeacher(@ModelAttribute("teachers") Teacher teacher) {
        teacherRepository.saveTeacher(teacher);
        return "redirect:/teacher";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("teachers", teacherRepository.findById(id));
        return "teacher/edit";
    }

    @PatchMapping("{id}")
    public String updateTeacher(@ModelAttribute("teachers") Teacher teacher,
                               @PathVariable("id") Long id) {
        teacherRepository.update(id, teacher);
        return "redirect:/teacher";
    }

    @DeleteMapping("{id}")
    public String delete(@PathVariable("id") Long id) {
        teacherRepository.deleteById(id);
        return "redirect:/teacher";
    }
}
