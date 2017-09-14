import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

/*
-Write a clone method (if needed) that will create a clone of a person. The counts will go up but the clone will have same id and characteristics of the clonee. Note that the hash code will be different, however.
-Be sure to test out your hierarchy and all methods. Your grade will partially be based on how effectively you test your program.
 */

public class Inheritance_Shortt {
    public static void main(String args[]) {
        Random r = new Random();
        ArrayList<Person> people = new ArrayList<>();

        Person student1 = new BCPStudent("Man", "Manlyman");
        people.add(student1);
        Person student2 = new BCPStudent("Boy", "ThatGoesToBell");
        people.add(student2);
        Person student3 = new NDStudent("Girl", "Girlygril");
        people.add(student3);
        Person teacher1 = new BCPTeacher("Teacher", "McTeacherFace");
        people.add(teacher1);
        Person teacher2 = new NDTeacher("TeacherAt", "GirlSchool");
        people.add(teacher2);
        Person teacher3 = new NDTeacher("TeacherAt", "GirlSchool");
        people.add(teacher3);

        System.out.println("Greet");
        for (Person person : people) {
            person.greet();
        }

        System.out.println("Versus/toString");
        for (Person person : people) {
            int ind = r.nextInt(people.size());
            System.out.println(person.toString() + " vs. " + people.get(ind).toString());
            System.out.println(person.compareTo(people.get(ind)));
        }

        System.out.println("Specific Count");
        System.out.println(BCPStudent.class.toString() + ": " + Person.specificCount(BCPStudent.class));
        System.out.println(NDTeacher.class.toString() + ": " + Person.specificCount(NDTeacher.class));
        System.out.println(BCPTeacher.class.toString() + ": " + Person.specificCount(BCPTeacher.class));
        System.out.println(NDStudent.class.toString() + ": " + Person.specificCount(NDStudent.class));

        System.out.println("Family Count");
        System.out.println(Teacher.class.toString() + ": " + Teacher.familyCount());
        System.out.println(Student.class.toString() + ": " + Student.familyCount());
        System.out.println(Person.class.toString() + ": " + Person.familyCount());

        System.out.println("ID");
        for (Person person : people) {
            System.out.println(person.toString());
            System.out.println(person.getFQN());
        }
    }
}

abstract class Person {
    protected String id;
    protected String fName;
    protected String lName;
    protected String greeting;

    protected static int personCount;
    protected static int teacherCount;
    protected static int studentCount;
    protected static int bcpStudentCount;
    protected static int ndStudentCount;
    protected static int bcpTeacherCount;
    protected static int ndTeacherCount;

    public Person(String firstName, String lastName) {
        id = UUID.randomUUID().toString();
        fName = firstName;
        lName = lastName;
        increaseClassCount(this);
    }

    private void increaseClassCount(Object obj) {
        if (obj.getClass() == BCPStudent.class) {
            bcpStudentCount++;
        } else if (obj.getClass() == NDStudent.class) {
            ndStudentCount++;
        } else if (obj.getClass() == BCPTeacher.class) {
            bcpTeacherCount++;
        } else if (obj.getClass() == NDTeacher.class) {
            ndTeacherCount++;
        } else if (obj.getClass() == Person.class) {
            personCount++;
        } else if (obj.getClass() == Teacher.class) {
            teacherCount++;
        } else if (obj.getClass() == Student.class) {
            studentCount++;
        }

    }

    public static int specificCount(Class giveClass) {
        if (giveClass == BCPStudent.class) {
            return bcpStudentCount;
        } else if (giveClass == NDStudent.class) {
            return ndStudentCount;
        } else if (giveClass == BCPTeacher.class) {
            return bcpTeacherCount;
        } else if (giveClass == NDTeacher.class) {
            return ndTeacherCount;
        } else if (giveClass == Person.class) {
            return personCount;
        } else if (giveClass == Teacher.class) {
            return teacherCount;
        } else if (giveClass == Student.class) {
            return studentCount;
        }
        return -1;
    }

    public static  int familyCount() {
        return personCount + teacherCount + studentCount + bcpStudentCount + ndStudentCount + bcpTeacherCount + ndTeacherCount;
    }

    public String getFQN(){
        return this.getClass().toString() + "." + this.hashCode() + "." + id;
    }

    public void setGreeting(String greet) {
        greeting = greet;
    }

    public String toString() {
        return fName + " " + lName;
    }

    public int compareTo(Person person) {
        if (id == person.id) return 0;
        return id.compareTo(person.id);
    }

    abstract void greet();
}

abstract class Student extends Person {
    public Student(String firstName, String lastName) {
        super(firstName, lastName);
        greeting = "Hi, I'm " + fName + " " + lName + ". ";

    }

    public static int familyCount() {
        return studentCount + bcpStudentCount + ndStudentCount;
    }

    @Override
    public int compareTo(Person person) {
        if (Teacher.class.isAssignableFrom(person.getClass())) {
            return 1;
        }
        if (lName.compareTo(person.lName) != 0) {
            return lName.compareTo(person.lName);
        }
        if (fName.compareTo(person.fName) != 0) {
            return fName.compareTo(person.fName);
        }
        return 0;
    }

    public String toString(){
        return "id# " + id + ", size: " + specificCount(this.getClass()) + ": " + fName + " " + lName;
    }

    protected void greet() {
        System.out.print(greeting);
    }
}

class BCPStudent extends Student {
    public BCPStudent(String firstName, String lastName) {
        super(firstName, lastName);
        System.out.println("BCP Student Born.");
    }

    public static  int familyCount() {
        return bcpStudentCount;
    }

    public void greet() {
        super.greet();
        System.out.println("I'm a student at BCP.");
    }
}

class NDStudent extends Student {
    public NDStudent(String firstName, String lastName) {
        super(firstName, lastName);
        System.out.println("ND Student Born.");
    }

    public static  int familyCount() {
        return ndStudentCount;
    }

    public void greet() {
        super.greet();
        System.out.println("I'm a student at ND.");
    }
}

abstract class Teacher extends Person {
    public Teacher(String firstName, String lastName) {
        super(firstName, lastName);
        greeting = "Hi, I'm " + fName + " " + lName + ". ";
    }

    @Override
    public int compareTo(Person person) {
        if (Student.class.isAssignableFrom(person.getClass())) {
            return -1;
        }
        if (lName.compareTo(person.lName) != 0) {
            return lName.compareTo(person.lName);
        }
        if (fName.compareTo(person.fName) != 0) {
            return fName.compareTo(person.fName);
        }
        return 0;
    }

    public static  int familyCount() {
        return teacherCount + bcpTeacherCount + ndTeacherCount;
    }

    public String toString(){
        return "id# " + id + ", size: " + specificCount(this.getClass()) + ": " + fName + " " + lName;
    }

    protected void greet() {
        System.out.print(greeting);
    }
}

class BCPTeacher extends Teacher {
    public BCPTeacher(String firstName, String lastName) {
        super(firstName, lastName);
        System.out.println("BCP Teacher Born.");
    }

    public static  int familyCount() {
        return bcpTeacherCount;
    }

    public void greet() {
        super.greet();
        System.out.println("I'm a teacher at BCP.");
    }
}

class NDTeacher extends Teacher {
    public NDTeacher(String firstName, String lastName) {
        super(firstName, lastName);
        System.out.println("ND Teacher Born.");
    }

    public static  int familyCount() {
        return ndTeacherCount;
    }

    public void greet() {
        super.greet();
        System.out.println("I'm a teacher at ND.");
    }
}
