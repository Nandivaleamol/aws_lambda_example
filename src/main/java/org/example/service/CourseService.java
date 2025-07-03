package org.example.service;

import org.example.dto.CourseDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseService {

    private final List<CourseDTO> courses = new ArrayList<>();

    // Create a new course
    public void addCourse(CourseDTO courseDTO) {
        courses.add(courseDTO);
    }

    // Get all courses
    public List<CourseDTO> getAllCourses() {
        return new ArrayList<>(courses);
    }

    // Get a course by ID
    public CourseDTO getCourseById(int id) {
        return courses.stream()
                .filter(course -> course.getId() == id)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Course not found with id: " + id));
    }

    public boolean updateCourse(int id, CourseDTO updatedCourse) {

        if (updatedCourse == null || updatedCourse.getId() != id || id==0) {
            throw new IllegalArgumentException("Invalid course data or ID mismatch");
        }

        var courseById = getCourseById(id);
        if (courseById != null) {
            courses.remove(courseById);
            courses.add(updatedCourse);
            return true;
        }
        return false;
    }

    public boolean deleteCourse(int id) {
        CourseDTO course = getCourseById(id);
        if (course != null) {
            courses.remove(course);
            return true;
        }
        return false;
    }


}
