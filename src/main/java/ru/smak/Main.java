package ru.smak;

import ru.smak.model.Grade;
import ru.smak.model.Student;
import ru.smak.service.GradeService;
import ru.smak.service.StudentService;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static final StudentService studentService = new StudentService();
    private static final GradeService gradeService = new GradeService();

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n1. Создать студента");
            System.out.println("2. Показать всех студентов");
            System.out.println("3. Добавить оценку");
            System.out.println("4. Обновить оценку");
            System.out.println("5. Удалить студента");
            System.out.println("6. Поиск студента");
            System.out.println("0. Выход");
            System.out.print("Выберите операцию: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    createStudent(scanner);
                    break;
                case 2:
                    showAllStudents();
                    break;
                case 3:
                    addGrade(scanner);
                    break;
                case 4:
                    updateGrade(scanner);
                    break;
                case 5:
                    deleteStudent(scanner);
                    break;
                case 6:
                    findStudent(scanner);
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Неверный выбор");
            }
        }
    }

    private static void createStudent(Scanner scanner) {
        System.out.print("Введите имя студента: ");
        String name = scanner.nextLine();
        System.out.print("Введите возраст: ");
        int age = scanner.nextInt();
        Student student = new Student(name, age);
        studentService.createStudent(student);
        System.out.println("Студент создан с ID: " + student.getId());
    }

    private static void showAllStudents() {
        List<Student> students = studentService.getAllStudents();
        for (Student student : students) {
            System.out.println("ID: " + student.getId() + ", Имя: " + student.getName() + ", Возраст: " + student.getAge());
            student.getGrades().forEach(grade ->
                    System.out.println("  Оценка: " + grade.getSubject() + " - " + grade.getScore())
            );
        }
    }

    private static void findStudent(Scanner scanner) {
        System.out.print("Введите имя студента: ");
        String name = scanner.nextLine();
        List<Student> students = studentService.getStudentsByName(name);
        for (Student student : students) {
            System.out.println("ID: " + student.getId() + ", Имя: " + student.getName() + ", Возраст: " + student.getAge());
            student.getGrades().forEach(grade ->
                    System.out.println("  Оценка: " + grade.getSubject() + " - " + grade.getScore())
            );
        }
    }

    private static void addGrade(Scanner scanner) {
        System.out.print("Введите ID студента: ");
        Long studentId = scanner.nextLong();
        scanner.nextLine();
        System.out.print("Введите предмет: ");
        String subject = scanner.nextLine();
        System.out.print("Введите оценку: ");
        int score = scanner.nextInt();

        Student student = studentService.getAllStudents().stream()
                .filter(s -> s.getId().equals(studentId))
                .findFirst()
                .orElse(null);

        if (student != null) {
            Grade grade = new Grade(subject, score, student);
            gradeService.addGrade(grade);
            System.out.println("Оценка добавлена");
        } else {
            System.out.println("Студент не найден");
        }
    }

    private static void updateGrade(Scanner scanner) {
        System.out.print("Введите ID оценки: ");
        Long gradeId = scanner.nextLong();
        System.out.print("Введите новую оценку: ");
        int newScore = scanner.nextInt();
        gradeService.updateGrade(gradeId, newScore);
        System.out.println("Оценка обновлена");
    }

    private static void deleteStudent(Scanner scanner) {
        System.out.print("Введите ID студента для удаления: ");
        Long studentId = scanner.nextLong();
        studentService.deleteStudent(studentId);
        System.out.println("Студент удален");
    }


}