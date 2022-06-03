package almaz.controllers;

import almaz.models.Group;
import almaz.models.Student;
import almaz.repositories.GroupRepository;
import almaz.repositories.StudentRepository;
import almaz.services.GroupService;
import almaz.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;
    private final GroupService groupService;

    @Autowired
    public StudentController(StudentService studentService, GroupService groupService) {
        this.studentService = studentService;
        this.groupService = groupService;
    }

    @GetMapping()
    public String getAll(Model model) {
        model.addAttribute("students", studentService.findAll());
        return "student/getAll";
    }

    @ModelAttribute("groupList")
    public List<Group> findAllGroups() {
        return groupService.findAll();
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("students", studentService.findById(id));
        return "student/findById";
    }

    @GetMapping("/new")
    public String newStudent(@ModelAttribute("students") Student student) {
        return "student/new";
    }

    @PostMapping()
    public String createStudent(@ModelAttribute("students") Student student) {
        studentService.saveStudent(student,student.getId());
        return "redirect:/student";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("students", studentService.findById(id));
        return "student/edit";
    }

    @PatchMapping("{id}")
    public String updateStudent(@ModelAttribute("students") Student student,
                                @PathVariable("id") Long id) {
        studentService.update(id, student);
        return "redirect:/student";
    }

    @DeleteMapping("{id}")
    public String delete(@PathVariable("id") Long id) {
        studentService.deleteById(id);
        return "redirect:/student";
    }
}