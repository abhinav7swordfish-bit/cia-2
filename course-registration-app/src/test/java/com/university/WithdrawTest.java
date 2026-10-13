package com.university;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WithdrawTest {

@Test
void shouldWithdrawStudentSuccessfully() {

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

    assertTrue(student.isRegistered("CS301"));
    assertEquals(4, student.getTotalCredits());
    assertEquals(1, course.getCurrentEnrollment());

    boolean result = service.withdraw(student, course);

    assertTrue(result);
    assertFalse(student.isRegistered("CS301"));
    assertEquals(0, student.getTotalCredits());
    assertEquals(0, course.getCurrentEnrollment());
}

@Test
void shouldRejectWithdrawalWhenStudentIsNotRegistered() {

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

    int creditsBefore = student.getTotalCredits();
    int enrollmentBefore = course.getCurrentEnrollment();

    boolean result = service.withdraw(student, course);

    assertFalse(result);

    assertFalse(student.isRegistered("CS301"));
    assertEquals(creditsBefore, student.getTotalCredits());

    // Verify course enrollment is unchanged.
    assertEquals(enrollmentBefore, course.getCurrentEnrollment());
}

@Test
void shouldAllowAnotherStudentToRegisterAfterWithdrawal() {

    Student student1 = new Student("S101", "e");
    Student student2 = new Student("S102", "Abhi");

    Course course = new Course(
            "CS301",
            "Data Structures",
            4,
            1,
            true,
            null
    );

    RegistrationService service = new RegistrationService();

    assertTrue(service.register(student1, course));

    assertTrue(student1.isRegistered("CS301"));
    assertEquals(4, student1.getTotalCredits());
    assertEquals(1, course.getCurrentEnrollment());

    // Second student cannot register while the course is full.
    assertFalse(service.register(student2, course));

    assertFalse(student2.isRegistered("CS301"));
    assertEquals(0, student2.getTotalCredits());
    assertEquals(1, course.getCurrentEnrollment());

    // First student withdraws and releases the seat.
    assertTrue(service.withdraw(student1, course));

    assertFalse(student1.isRegistered("CS301"));
    assertEquals(0, student1.getTotalCredits());
    assertEquals(0, course.getCurrentEnrollment());

    // Second student can now register.
    assertTrue(service.register(student2, course));

    assertTrue(student2.isRegistered("CS301"));
    assertEquals(4, student2.getTotalCredits());
    assertEquals(1, course.getCurrentEnrollment());
}
}
