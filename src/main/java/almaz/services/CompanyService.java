package almaz.services;

import almaz.models.Company;
import almaz.repositories.CompanyRepository;
import almaz.repositories.StudentRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CompanyService {
    private final CompanyRepository companyRepository;
    private final StudentRepository studentRepository;

    public CompanyService(CompanyRepository companyRepository, StudentRepository studentRepository) {
        this.companyRepository = companyRepository;
        this.studentRepository = studentRepository;
    }

    public void saveCompany(Company company) {
        companyRepository.saveCompany(company);
    }

    public Company findById(Long companyId) {
        return companyRepository.findById(companyId);
    }

    public List<Company> findAll() {
        return companyRepository.findAll();
    }

    public void update(Long id, Company company) {
        companyRepository.update(id, company);
    }

    public void deleteById(Long companyId) {
        companyRepository.deleteById(companyId);
    }

}
