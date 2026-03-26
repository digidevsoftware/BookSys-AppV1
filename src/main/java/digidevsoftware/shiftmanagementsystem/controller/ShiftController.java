package digidevsoftware.shiftmanagementsystem.controller;

import digidevsoftware.shiftmanagementsystem.model.Shift;
import digidevsoftware.shiftmanagementsystem.model.ShiftType;
import digidevsoftware.shiftmanagementsystem.service.EmployeeService;
import digidevsoftware.shiftmanagementsystem.service.ShiftService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/shifts")
public class ShiftController {

    private final ShiftService shiftService;
    private final EmployeeService employeeService;

    public ShiftController(ShiftService shiftService, EmployeeService employeeService) {
        this.shiftService = shiftService;
        this.employeeService = employeeService;
    }

    @GetMapping
    public String listShifts(Model model) {
        model.addAttribute("shifts", shiftService.findAll());
        return "shift/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        populateForm(model, new Shift(), "Add Shift");
        return "shift/form";
    }

    @PostMapping
    public String saveShift(@Valid @ModelAttribute("shift") Shift shift,
                            BindingResult bindingResult,
                            Model model,
                            RedirectAttributes redirectAttributes) {
        if (!bindingResult.hasFieldErrors("startTime") && !bindingResult.hasFieldErrors("endTime") && !shift.hasValidTimeRange()) {
            bindingResult.rejectValue("endTime", "invalid.endTime", "End time must be later than start time.");
        }

        if (bindingResult.hasErrors()) {
            populateForm(model, shift, shift.getId() == null ? "Add Shift" : "Edit Shift");
            return "shift/form";
        }

        shiftService.save(shift);
        redirectAttributes.addFlashAttribute("successMessage", "Shift saved successfully.");
        return "redirect:/shifts";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        return shiftService.findById(id)
                .map(shift -> {
                    populateForm(model, shift, "Edit Shift");
                    return "shift/form";
                })
                .orElseGet(() -> {
                    redirectAttributes.addFlashAttribute("errorMessage", "Shift not found.");
                    return "redirect:/shifts";
                });
    }

    @PostMapping("/delete/{id}")
    public String deleteShift(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        shiftService.deleteById(id);
        redirectAttributes.addFlashAttribute("successMessage", "Shift deleted successfully.");
        return "redirect:/shifts";
    }

    @GetMapping("/schedule")
    public String schedule(Model model) {
        model.addAttribute("shifts", shiftService.findAll());
        return "shift/schedule";
    }

    private void populateForm(Model model, Shift shift, String pageTitle) {
        model.addAttribute("shift", shift);
        model.addAttribute("employees", employeeService.findAll());
        model.addAttribute("shiftTypes", ShiftType.values());
        model.addAttribute("pageTitle", pageTitle);
    }
}
