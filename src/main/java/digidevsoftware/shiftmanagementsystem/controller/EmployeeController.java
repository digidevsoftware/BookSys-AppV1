package digidevsoftware.shiftmanagementsystem.controller;

import digidevsoftware.shiftmanagementsystem.model.Employee;
import digidevsoftware.shiftmanagementsystem.model.EmployeeStatus;
import digidevsoftware.shiftmanagementsystem.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public String listEmployees(Model model) {
        model.addAttribute("employees", employeeService.findAll());
        return "employee/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("employee", new Employee());
        model.addAttribute("statuses", EmployeeStatus.values());
        model.addAttribute("pageTitle", "Add Employee");
        return "employee/form";
    }

    @PostMapping
    public String saveEmployee(@Valid @ModelAttribute("employee") Employee employee,
                               BindingResult bindingResult,
                               Model model,
                               RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("statuses", EmployeeStatus.values());
            model.addAttribute("pageTitle", employee.getId() == null ? "Add Employee" : "Edit Employee");
            return "employee/form";
        }

        employeeService.save(employee);
        redirectAttributes.addFlashAttribute("successMessage", "Employee saved successfully.");
        return "redirect:/employees";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        return employeeService.findById(id)
                .map(employee -> {
                    model.addAttribute("employee", employee);
                    model.addAttribute("statuses", EmployeeStatus.values());
                    model.addAttribute("pageTitle", "Edit Employee");
                    return "employee/form";
                })
                .orElseGet(() -> {
                    redirectAttributes.addFlashAttribute("errorMessage", "Employee not found.");
                    return "redirect:/employees";
                });
    }

    @PostMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        employeeService.deleteById(id);
        redirectAttributes.addFlashAttribute("successMessage", "Employee deleted successfully.");
        return "redirect:/employees";
    }
}
