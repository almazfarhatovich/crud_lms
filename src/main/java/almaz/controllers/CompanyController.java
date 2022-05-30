package almaz.controllers;


import almaz.models.Company;
import almaz.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/company")
public class CompanyController {

    private final CompanyRepository companyRepository;

    @Autowired
    public CompanyController(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }
    @GetMapping()
    public String getAll(Model model) {
        model.addAttribute("companies",companyRepository.findAll());
        return "company/getAll";
    }
    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("companies", companyRepository.findById(id));
        return "company/findById";
    }

    @GetMapping("/new")
    public String newCompany(@ModelAttribute("companies") Company company)  {
        return "company/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("companies")Company company) {
        companyRepository.saveCompany(company);
        return "redirect:/company";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("companies", companyRepository.findById(id));
        return "company/edit";
    }

    @PatchMapping("{id}")
    public String update(@ModelAttribute("companies")Company company,
                         @PathVariable("id") Long id) {
        companyRepository.update(id, company);
        return "redirect:/company";
    }

    @DeleteMapping("{id}")
    public String delete(@PathVariable("id") Long id) {
        companyRepository.deleteById(id);
        return "redirect:/company";
    }
}
