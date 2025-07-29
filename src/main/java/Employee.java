import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Employee {
    @Id
    int Emp_ID;
    @Column
    String name;
    @Column
    String department;
    @Column
    int age ;
    @Column
    int salary;

    public int getEmp_ID() {
        return Emp_ID;
    }

    public void setEmp_ID(int emp_ID) {
        Emp_ID = emp_ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }




}
