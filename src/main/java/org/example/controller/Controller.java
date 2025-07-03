package org.example.controller;

import org.example.dto.CourseDTO;
import org.example.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/courses")
public class Controller {

    @Autowired
    private CourseService courseService;

    @PostMapping(value = "/create")
    public ResponseEntity<String> createCourse(@RequestBody CourseDTO courseDTO) {
        try {
            courseService.addCourse(courseDTO);
            return ResponseEntity.ok("Course created successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error creating course: " + e.getMessage());
        }
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<String> updateCourse(@PathVariable int id, @RequestBody CourseDTO courseDTO) {
        try {
            var isUpdated = courseService.updateCourse(id, courseDTO);
            if (isUpdated) {
                return ResponseEntity.ok("Course updated successfully");
            }else {
                return ResponseEntity.status(404).body("Course not found with id: " + id);
            }
        }catch (Exception e) {
            return ResponseEntity.status(500).body("Error updating course: " + e.getMessage());
        }
    }

    @GetMapping(value = "/get/{id}", produces = "application/json")
    public ResponseEntity<CourseDTO> getCourseById(@PathVariable int id) {
        try {
            CourseDTO course = courseService.getCourseById(id);
            return ResponseEntity.ok(course);
        } catch (Exception e) {
            return ResponseEntity.status(404).body(null);
        }
    }
    @GetMapping(value = "/getAll", produces = "application/json")
    public ResponseEntity<?> getAllCourses() {
        try {
            var courses = courseService.getAllCourses();
            return ResponseEntity.ok(courses);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error retrieving courses: " + e.getMessage());
        }
    }
}
