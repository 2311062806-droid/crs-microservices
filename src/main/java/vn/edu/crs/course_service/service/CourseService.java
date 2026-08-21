package vn.edu.crs.course_service.service;

import vn.edu.crs.course_service.entity.Course;
import vn.edu.crs.course_service.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Course getCourseById(Long id) {
        return courseRepository.findById(id).orElse(null);
    }

    public Course createCourse(Course course) {
        if (course.getSoChoConLai() == null) {
            course.setSoChoConLai(course.getSoChoToiDa());
        }

        return courseRepository.save(course);
    }

    public Course updateCourse(Long id, Course course) {
        Course existing = courseRepository.findById(id).orElse(null);

        if (existing == null) {
            return null;
        }

        existing.setTenMonHoc(course.getTenMonHoc());
        existing.setSoTinChi(course.getSoTinChi());
        existing.setSoChoToiDa(course.getSoChoToiDa());
        existing.setSoChoConLai(course.getSoChoConLai());

        return courseRepository.save(existing);
    }

    public boolean deleteCourse(Long id) {
        if (!courseRepository.existsById(id)) {
            return false;
        }

        courseRepository.deleteById(id);
        return true;
    }
}