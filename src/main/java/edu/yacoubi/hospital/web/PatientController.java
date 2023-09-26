package edu.yacoubi.hospital.web;

import edu.yacoubi.hospital.entities.Patient;
import edu.yacoubi.hospital.repository.PatientRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class PatientController {

    private PatientRepository patientRepository;

//    @GetMapping("/index")
//    public String index(Model model, HttpServletRequest request) {
//        servlet
//        int page = Integer.parseInt(request.getParameter("page"));
//        int size = Integer.parseInt(request.getParameter("size"));
//        Page<Patient> pagePatients = patientRepository
//                .findAll(PageRequest.of(page, size));
//        model.addAttribute("patients", pagePatients.getContent());
//        return "patients";
//    }

//    @GetMapping("/index")
//    public String index(Model model,int page, int size) {
//        // http://localhost:9090/index?page=0&size=4
//        Page<Patient> pagePatients = patientRepository
//                .findAll(PageRequest.of(page, size));
//        model.addAttribute("patients", pagePatients.getContent());
//        return "patients";
//    }

//    @GetMapping("/index")
//    public String index(Model model,
//                        // assign page parameter coming from http request to variable p
//                        @RequestParam(name = "page", defaultValue="0") int p,
//                        @RequestParam(name = "size", defaultValue="3") int s) {
//        // http://localhost:9090/index?page=0&size=4
//        // http://localhost:9090/index?size=3
//        Page<Patient> pagePatients = patientRepository
//                .findAll(PageRequest.of(p, s));
//        model.addAttribute(
//                "patients",
//                pagePatients.getContent()
//        );
//        model.addAttribute(
//                "pages",
//                new int[pagePatients.getTotalPages()]
//        );
//        model.addAttribute("currentPage", p);
//        return "patients";
//    }

    @GetMapping("/index")
    public String index(Model model,
                        @RequestParam(name = "page", defaultValue="0") int p,
                        @RequestParam(name = "size", defaultValue="3") int s,
                        @RequestParam(name = "keyword", defaultValue = "") String kW) {
        Page<Patient> pagePatients = patientRepository
                .findByNameContains(kW, PageRequest.of(p, s));

        model.addAttribute(
                "patients",
                pagePatients.getContent()
        );

        model.addAttribute(
                "pages",
                new int[pagePatients.getTotalPages()]
        );

        model.addAttribute("currentPage", p);

        model.addAttribute("keyword", kW);
        return "patients";
    }

    @GetMapping("/delete")
    public String delete(Long id) {
        patientRepository.deleteById(id);
        return "redirect:/index";
    }
}
