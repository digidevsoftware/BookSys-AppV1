package digidevsoftware.shiftmanagementsystem.repository;

import digidevsoftware.shiftmanagementsystem.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
