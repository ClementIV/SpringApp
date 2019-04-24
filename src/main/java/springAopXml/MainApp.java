package springAopXml;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class MainApp {
    public static void main(String[] args){
        ApplicationContext context = new ClassPathXmlApplicationContext("TransactionBeans.xml");

        StudentJDBCTemplate2 studentJDBCTemplate2 =(StudentJDBCTemplate2)context.getBean("studentJDBCTemplate2");

        System.out.println("-------- Records creation---------");
        studentJDBCTemplate2.create("Zara",11,99,2010);
        studentJDBCTemplate2.create("Nuha",20,97,2010);
        studentJDBCTemplate2.create("Ayan",25,100,2011);

        System.out.println("-------- Listing all the records -------------");
        List<StudentMarks> studentMarks = studentJDBCTemplate2.listStudents();
        for(StudentMarks records : studentMarks){
            System.out.print("ID : " + records.getId());
            System.out.print(", Name : " + records.getName());
            System.out.print(", Marks : " + records.getMarks());
            System.out.print(", Year : "+ records.getYear());
            System.out.println(", Age : " + records.getAge());
        }
    }
}
