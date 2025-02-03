package com.example.employeeattendance;

import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface EmployeeAttendanceRepository extends JpaRepository<EmployeeAttendance, Long> {
    List<EmployeeAttendance> findByEmployeeIdAndDateBetween(Long employeeId, LocalDate startDate, LocalDate endDate);
}
