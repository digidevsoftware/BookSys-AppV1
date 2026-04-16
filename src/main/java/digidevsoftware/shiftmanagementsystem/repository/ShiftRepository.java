package digidevsoftware.shiftmanagementsystem.repository;

import digidevsoftware.shiftmanagementsystem.model.Shift;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface ShiftRepository extends JpaRepository<Shift, Long> {
    List<Shift> findAllByOrderByDateAscStartTimeAsc();
    List<Shift> findByDateOrderByStartTimeAsc(LocalDate date);
    List<Shift> findByEmployeeIdAndDateOrderByStartTimeAsc(Long employeeId, LocalDate date);
    List<Shift> findByEmployeeIdAndDateAndIdNotOrderByStartTimeAsc(Long employeeId, LocalDate date, Long id);
}
