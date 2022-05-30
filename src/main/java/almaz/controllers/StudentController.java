package almaz.controllers;

import almaz.models.Student;
import almaz.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/student")
public class StudentController {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping()
    public String getAll(Model model) {
        model.addAttribute("students", studentRepository.findAll());
        return "student/getAll";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("students", studentRepository.findById(id));
        return "student/findById";
    }

    @GetMapping("/new")
    public String newStudent(@ModelAttribute("students") Student student) {
        return "student/new";
    }

    @PostMapping()
    public String createStudent(@ModelAttribute("students") Student student) {
        studentRepository.saveStudent(student);
        return "redirect:/student";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("students", studentRepository.findById(id));
        return "student/edit";
    }

    @PatchMapping("{id}")
    public String updateStudent(@ModelAttribute("students") Student student,
                               @PathVariable("id") Long id) {
        studentRepository.update(id, student);
        return "redirect:/student";
    }

    @DeleteMapping("{id}")
    public String delete(@PathVariable("id") Long id) {
        studentRepository.deleteById(id);
        return "redirect:/student";
    }
}