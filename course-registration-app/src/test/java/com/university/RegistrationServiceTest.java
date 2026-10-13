package com.university;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegistrationServiceTest {

    @Test
    void shouldRegisterStudentForValidCourse() {

        Student student = new Student("S101", "Anita");

        Course course = new Course(
                "CS301",
                "Data Structures",
                4,
                30,
                true,
                null
        );

        RegistrationService service = new RegistrationService();

        boolean result = service.register(student, course);

        assertTrue(result);
        assertTrue(student.isRegistered("CS301"));
        assertEquals(4, student.getTotalCredits());
        assertEquals(1, course.getCurrentEnrollment());
    }

    @Test
    void shouldRejectInactiveCourse() {

        Student student = new Student("S101", "Anita");

        Course course = new Course(
                "CS301",
                "Data Structures",
                4,
                30,
                false,
                null
        );

        RegistrationService service = new RegistrationService();

        boolean result = service.register(student, course);

        assertFalse(result);
        assertFalse(student.isRegistered("CS301"));
        assertEquals(0, student.getTotalCredits());
        assertEquals(0, course.getCurrentEnrollment());
    }

    @Test
    void shouldRejectMissingPrerequisite() {

        Student student = new Student("S101", "Anita");

        Course course = new Course(
                "CS401",
                "Advanced Algorithms",
                4,
                30,
                true,
                "CS301"
        );

        RegistrationService service = new RegistrationService();

        boolean result = service.register(student, course);

        assertFalse(result);
        assertFalse(student.isRegistered("CS401"));
        assertEquals(0, student.getTotalCredits());
        assertEquals(0, course.getCurrentEnrollment());
    }

    @Test
    void shouldAllowRegistrationWhenPrerequisiteIsCompleted() {

        Student student = new Student("S101", "Anita");
        student.addCompletedPrerequisite("CS301");

        Course course = new Course(
                "CS401",
                "Advanced Algorithms",
                4,
                30,
                true,
                "CS301"
        );

        RegistrationService service = new RegistrationService();

        boolean result = service.register(student, course);

        assertTrue(result);
        assertTrue(student.isRegistered("CS401"));
        assertEquals(4, student.getTotalCredits());
        assertEquals(1, course.getCurrentEnrollment());
    }

    @Test
    void shouldRejectDuplicateRegistration() {

        Student student = new Student("S101", "Anita");

        Course course = new Course(
                "CS301",
                "Data Structures",
                4,
                30,
                true,
                null
        );

        RegistrationService service = new RegistrationService();

        assertTrue(service.register(student, course));

        boolean secondAttempt = service.register(student, course);

        assertFalse(secondAttempt);
        assertTrue(student.isRegistered("CS301"));
        assertEquals(4, student.getTotalCredits());
        assertEquals(1, course.getCurrentEnrollment());
    }

    @Test
    void shouldRejectRegistrationWhenCreditLimitIsExceeded() {

        Student student = new Student("S101", "Anita");

        // Simulate 20 existing credits.
        student.addRegistration("CS101", 4);
        student.addRegistration("CS102", 4);
        student.addRegistration("CS201", 4);
        student.addRegistration("CS202", 4);
        student.addRegistration("CS203", 4);

        Course course = new Course(
                "CS301",
                "Data Structures",
                4,
                30,
                true,
                null
        );

        RegistrationService service = new RegistrationService();

        boolean result = service.register(student, course);

        assertFalse(result);
        assertFalse(student.isRegistered("CS301"));
        assertEquals(20, student.getTotalCredits());
        assertEquals(0, course.getCurrentEnrollment());
    }

    @Test
    void shouldAllowRegistrationWhenExactlyOneSeatRemains() {

        Course course = new Course(
                "CS301",
                "Cool Subject",
                4,
                2,
                true,
                null
        );

        RegistrationService service = new RegistrationService();

        Student student1 = new Student("S101", "Anita");
        Student student2 = new Student("S102", "Abhi");

        // Fill the first seat.
        boolean firstResult = service.register(student1, course);

        assertTrue(firstResult);
        assertTrue(student1.isRegistered("CS301"));
        assertEquals(1, course.getCurrentEnrollment());
        assertEquals(4, student1.getTotalCredits());

        // Exactly one seat remains. The second registration must succeed.
        boolean secondResult = service.register(student2, course);

        assertTrue(secondResult);
        assertTrue(student2.isRegistered("CS301"));
        assertEquals(2, course.getCurrentEnrollment());
        assertEquals(4, student2.getTotalCredits());
    }

    @Test
    void shouldRejectRegistrationWhenCourseIsFull() {

        Course course = new Course(
                "CS301",
                "Cool Subject",
                4,
                2,
                true,
                null
        );

        RegistrationService service = new RegistrationService();

        Student student1 = new Student("S101", "Anita");
        Student student2 = new Student("S102", "Abhi");
        Student student3 = new Student("S103", "Chris");

        assertTrue(service.register(student1, course));
        assertTrue(service.register(student2, course));

        assertEquals(2, course.getCurrentEnrollment());

        int creditsBefore = student3.getTotalCredits();
        boolean registeredBefore = student3.isRegistered("CS301");
        int enrollmentBefore = course.getCurrentEnrollment();

        boolean result = service.register(student3, course);

        assertFalse(result);

        assertEquals(
                creditsBefore,
                student3.getTotalCredits()
        );

        assertEquals(
                registeredBefore,
                student3.isRegistered("CS301")
        );

        assertFalse(student3.isRegistered("CS301"));

        assertEquals(
                enrollmentBefore,
                course.getCurrentEnrollment()
        );

        assertEquals(2, course.getCurrentEnrollment());
    }
}
