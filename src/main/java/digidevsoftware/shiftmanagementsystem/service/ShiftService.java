package digidevsoftware.shiftmanagementsystem.service;

import digidevsoftware.shiftmanagementsystem.model.Shift;
import digidevsoftware.shiftmanagementsystem.repository.ShiftRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ShiftService {

    private final ShiftRepository shiftRepository;

    public ShiftService(ShiftRepository shiftRepository) {
        this.shiftRepository = shiftRepository;
    }

    public List<Shift> findAll() {
        return shiftRepository.findAllByOrderByDateAscStartTimeAsc();
    }

    public List<Shift> findByDate(LocalDate date) {
        return shiftRepository.findByDateOrderByStartTimeAsc(date);
    }

    public List<Shift> findByFilters(LocalDate date, Long employeeId) {
        if (date != null && employeeId != null) {
            return shiftRepository.findByEmployeeIdAndDateOrderByStartTimeAsc(employeeId, date);
        }
        if (date != null) {
            return shiftRepository.findByDateOrderByStartTimeAsc(date);
        }
        if (employeeId != null) {
            return shiftRepository.findAllByOrderByDateAscStartTimeAsc().stream()
                    .filter(shift -> shift.getEmployee() != null && employeeId.equals(shift.getEmployee().getId()))
                    .toList();
        }
        return shiftRepository.findAllByOrderByDateAscStartTimeAsc();
    }

    public Optional<Shift> findById(Long id) {
        return shiftRepository.findById(id);
    }

    public Shift save(Shift shift) {
        return shiftRepository.save(shift);
    }

    public boolean hasShiftConflict(Shift shift) {
        if (shift.getEmployee() == null || shift.getEmployee().getId() == null || shift.getDate() == null || shift.getStartTime() == null || shift.getEndTime() == null) {
            return false;
        }

        List<Shift> sameDayShifts = shift.getId() == null
                ? shiftRepository.findByEmployeeIdAndDateOrderByStartTimeAsc(shift.getEmployee().getId(), shift.getDate())
                : shiftRepository.findByEmployeeIdAndDateAndIdNotOrderByStartTimeAsc(shift.getEmployee().getId(), shift.getDate(), shift.getId());

        return sameDayShifts.stream().anyMatch(existing ->
                shift.getStartTime().isBefore(existing.getEndTime()) && existing.getStartTime().isBefore(shift.getEndTime())
        );
    }

    public void deleteById(Long id) {
        shiftRepository.deleteById(id);
    }
}
