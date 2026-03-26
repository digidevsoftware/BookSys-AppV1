package digidevsoftware.shiftmanagementsystem.controller;

import digidevsoftware.shiftmanagementsystem.service.EmployeeService;
import digidevsoftware.shiftmanagementsystem.service.ShiftService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final EmployeeService employeeService;
    private final ShiftService shiftService;

    public HomeController(EmployeeService employeeService, ShiftService shiftService) {
        this.employeeService = employeeService;
        this.shiftService = shiftService;
    }

    @GetMapping("/")
    public String dashboard(Model model) {
        model.addAttribute("employeeCount", employeeService.findAll().size());
        model.addAttribute("shiftCount", shiftService.findAll().size());
        model.addAttribute("recentShifts", shiftService.findAll());
        return "dashboard";
    }
}
