package almaz.controllers;


import almaz.models.Company;
import almaz.models.Student;
import almaz.repositories.CompanyRepository;
import almaz.repositories.StudentRepository;
import almaz.services.CompanyService;
import almaz.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/company")
public class CompanyController {

    private final CompanyService companyService;
    private final StudentService studentService;

    @Autowired
    public CompanyController(CompanyService companyService, StudentService studentService) {
        this.companyService = companyService;
        this.studentService = studentService;
    }
    @GetMapping()
    public String getAll(Model model) {
        model.addAttribute("companies",companyService.findAll());
        return "company/getAll";
    }
    @ModelAttribute("studentList")
    public List<Student> findAllStudents(){
        return studentService.findAll();
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("companies", companyService.findById(id));
        return "company/findById";
    }

    @GetMapping("/new")
    public String newCompany(@ModelAttribute("companies") Company company)  {
        return "company/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("companies")Company company) {
        companyService.saveCompany(company);
        return "redirect:/company";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("companies", companyService.findById(id));
        return "company/edit";
    }

    @PatchMapping("{id}")
    public String update(@ModelAttribute("companies")Company company,
                         @PathVariable("id") Long id) {
        companyService.update(id, company);
        return "redirect:/company";
    }

    @DeleteMapping("{id}")
    public String delete(@PathVariable("id") Long id) {
        companyService.deleteById(id);
        return "redirect:/company";
    }
}
