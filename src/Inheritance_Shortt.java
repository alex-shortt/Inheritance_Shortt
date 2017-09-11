import java.util.ArrayList;
import java.util.UUID;

public class Inheritance_Shortt {
    public static void main(String args[]){
        ArrayList<Person> people = new ArrayList<Person>();

        Person student1 = new Student("Alex", "Shortt", "BCP");
        people.add(student1);
        Person student2 = new Student("Alex", "Shortt", "BCP");
        people.add(student2);
        Person teacher1 = new Teacher("Alex", "Shortt", "BCP");
        people.add(teacher1);

        Person student3 = new Student("Alex", "Shortt", "BCP");
        people.add(student3);
        Person student4 = new Student("Alex", "Shortt", "BCP");
        people.add(student4);
        Person teacher2 = new Teacher("Alex", "Shortt", "BCP");
        people.add(teacher2);

        for(Person person : people){
            person.greet();
        }
    }
}

abstract class Person{
    private String id;
    private String fName;
    private String lName;
    private String greeting;

    public Person(String firstName, String lastName){
        id = UUID.randomUUID().toString();
        fName = firstName;
        lName = lastName;
    }

    public void setGreeting(String greet){
        greeting = greet;
    }

    public void greet(){
        System.out.println(greeting);
    }
}

class Student extends Person{
    private String school;

    public Student(String firstName, String lastName, String thisSchool){
        super(firstName, lastName);
        school = thisSchool;
        System.out.println(school + " Student born.");
    }
}

class Teacher extends Person{
    private String school;

    public Teacher(String firstName, String lastName, String thisSchool){
        super(firstName, lastName);
        school = thisSchool;
        System.out.println(school + " Teacher born.");
    }

    @Override
    public void greet() {
        super.greet();
    }
}