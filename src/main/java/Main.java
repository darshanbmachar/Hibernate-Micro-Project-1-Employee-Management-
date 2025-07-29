import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    private static Configuration configuration = null;
    private static SessionFactory factory = null;
    private static Session session = null;
    private Transaction transaction = null;

    public static void main(String[] args) {
        configuration = new Configuration().configure("hibernate.cfg.xml");
        factory = configuration.buildSessionFactory();

        Main main = new Main();

        while (true) {
            System.out.println("\n------------------Employee Management-------------");
            System.out.println("1. Add Employee");
            System.out.println("2. Search Employee");
            System.out.println("3. Update Employee details");
            System.out.println("4. Delete Employee");
            System.out.println("5. Exit");
            System.out.print("Choose your option: ");
            int option = sc.nextInt();

            switch (option) {
                case 1 -> main.addEmployee();
                case 2 -> main.viewEmployee();
                case 3 -> main.update();
                case 4 -> main.removeEmp();
                case 5 -> {
                    System.out.println("Exiting... Thank you!");
                    factory.close();
                    System.exit(0);
                }
                default -> System.out.println("Invalid input. Please choose a valid option.");
            }
        }
    }

    public void addEmployee() {
        Employee emp = new Employee(); // create a new object each time

        System.out.print("Enter Employee ID: ");
        int id = sc.nextInt();
        emp.setEmp_ID(id);

        System.out.print("Enter Employee Name: ");
        String name = sc.next();
        emp.setName(name);

        System.out.print("Enter Department: ");
        String dept = sc.next();
        emp.setDepartment(dept);

        System.out.print("Enter Age: ");
        int age = sc.nextInt();
        emp.setAge(age);

        System.out.print("Enter Salary: ");
        int salary = sc.nextInt();
        emp.setSalary(salary);

        session = factory.openSession();
        transaction = session.beginTransaction();
        session.persist(emp);
        transaction.commit();
        session.close();

        System.out.println("Employee added successfully.");
    }

    public void viewEmployee() {
        System.out.print("Enter Employee ID: ");
        int id = sc.nextInt();

        session = factory.openSession();
        transaction = session.beginTransaction();
        Employee obj = session.get(Employee.class, id);

        if (obj != null) {
            System.out.println("ID: " + obj.getEmp_ID());
            System.out.println("Name: " + obj.getName());
            System.out.println("Department: " + obj.getDepartment());
            System.out.println("Age: " + obj.getAge());
            System.out.println("Salary: " + obj.getSalary());
        } else {
            System.out.println("Employee with ID " + id + " not found.");
        }

        transaction.commit();
        session.close();
    }

    public void update() {
        System.out.print("Enter Employee ID to update: ");
        int id = sc.nextInt();

        session = factory.openSession();
        transaction = session.beginTransaction();
        Employee obj = session.get(Employee.class, id);

        if (obj == null) {
            System.out.println("Employee with ID " + id + " not found.");
            transaction.commit();
            session.close();
            return;
        }

        System.out.println("Choose detail to update:");
        System.out.println("1. Name");
        System.out.println("2. Department");
        System.out.println("3. Age");
        System.out.println("4. Salary");
        System.out.print("Enter option: ");
        int option = sc.nextInt();

        switch (option) {
            case 1 -> {
                System.out.print("Enter new Name: ");
                obj.setName(sc.next());
            }
            case 2 -> {
                System.out.print("Enter new Department: ");
                obj.setDepartment(sc.next());
            }
            case 3 -> {
                System.out.print("Enter new Age: ");
                obj.setAge(sc.nextInt());
            }
            case 4 -> {
                System.out.print("Enter new Salary: ");
                obj.setSalary(sc.nextInt());
            }
            default -> System.out.println("Invalid option.");
        }

        transaction.commit();
        session.close();
        System.out.println("Employee updated successfully.");
    }

    public void removeEmp() {
        System.out.print("Enter Employee ID to remove: ");
        int id = sc.nextInt();

        session = factory.openSession();
        transaction = session.beginTransaction();
        Employee obj = session.get(Employee.class, id);

        if (obj != null) {
            session.remove(obj);
            System.out.println("Employee removed successfully.");
        } else {
            System.out.println("Employee with ID " + id + " not found.");
        }

        transaction.commit();
        session.close();
    }
}
