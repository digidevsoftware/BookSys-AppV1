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

    public Optional<Shift> findById(Long id) {
        return shiftRepository.findById(id);
    }

    public Shift save(Shift shift) {
        return shiftRepository.save(shift);
    }

    public void deleteById(Long id) {
        shiftRepository.deleteById(id);
    }
}
