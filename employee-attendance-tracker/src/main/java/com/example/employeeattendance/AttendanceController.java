package com.example.employeeattendance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/attendance")
@CrossOrigin(origins = "*")  // Allows API calls from frontend (remove in production)
public class AttendanceController {

    @Autowired
    private EmployeeAttendanceRepository repository;

    // ✅ POST: Record attendance
    @PostMapping
    @Transactional
    public EmployeeAttendance recordAttendance(@RequestBody EmployeeAttendance attendance) {
        return repository.save(attendance);
    }

    // ✅ GET: Fetch attendance by employeeId and date range
    @GetMapping("/{employeeId}")
    public List<EmployeeAttendance> getAttendance(
            @PathVariable Long employeeId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {

        // 🔹 Validation: Ensure startDate & endDate are not null
        if (startDate == null || endDate == null) {
            throw new IllegalArgumentException("startDate and endDate are required");
        }

        return repository.findByEmployeeIdAndDateBetween(employeeId, startDate, endDate);
    }
}
