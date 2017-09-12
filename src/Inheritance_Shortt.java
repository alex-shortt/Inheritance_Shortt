import java.util.ArrayList;
import java.util.UUID;

public class Inheritance_Shortt {
    public static void main(String args[]){
        ArrayList<Person> people = new ArrayList<Person>();

        Person student1 = new BCPStudent("Man", "Manlyman");
        people.add(student1);
        Person student2 = new NDStudent("Girl", "Girlygril");
        people.add(student2);
        Person teacher1 = new BCPTeacher("Teacher", "McTeacherFace");
        people.add(teacher1);
        Person teacher2 = new NDTeacher("TeacherAt", "GirlSchool");
        people.add(teacher2);

        for(Person person : people){
            person.greet();
        }
    }
}

abstract class Person{
    protected String id;
    protected String fName;
    protected String lName;
    protected String greeting;

    public Person(String firstName, String lastName){
        id = UUID.randomUUID().toString();
        fName = firstName;
        lName = lastName;
    }

    public void setGreeting(String greet){
        greeting = greet;
    };

    abstract void greet();
}

abstract class Student extends Person{
    public Student(String firstName, String lastName){
        super(firstName, lastName);
        greeting = "Hi, I'm " + fName + " " + lName + ". ";

    }

    protected void greet(){
        System.out.print(greeting);
    }
}

class BCPStudent extends Student{
    public BCPStudent(String firstName, String lastName){
        super(firstName, lastName);
        System.out.println("BCP Student Born.");
    }

    public void greet(){
        super.greet();
        System.out.println("I'm a student at BCP.");
    }
}

class NDStudent extends Student{
    public NDStudent(String firstName, String lastName){
        super(firstName, lastName);
        System.out.println("ND Student Born.");
    }

    public void greet(){
        super.greet();
        System.out.println("I'm a student at ND.");
    }
}

abstract class Teacher extends Person{
    public Teacher(String firstName, String lastName){
        super(firstName, lastName);
        greeting = "Hi, I'm " + fName + " " + lName + ". ";
    }

    protected void greet(){
        System.out.print(greeting);
    }
}

class BCPTeacher extends Teacher{
    public BCPTeacher(String firstName, String lastName){
        super(firstName, lastName);
        System.out.println("BCP Teacher Born.");
    }

    public void greet(){
        super.greet();
        System.out.println("I'm a teacher at BCP.");
    }
}

class NDTeacher extends Teacher{
    public NDTeacher(String firstName, String lastName){
        super(firstName, lastName);
        System.out.println("ND Teacher Born.");
    }

    public void greet(){
        super.greet();
        System.out.println("I'm a teacher at ND.");
    }
}