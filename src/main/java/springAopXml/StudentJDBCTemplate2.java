package springAopXml;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import springAopXml.Student2DAO;
import springAopXml.StudentMarks;
import springAopXml.StudentMarksMapper;

import javax.sql.DataSource;
import java.util.List;

public class StudentJDBCTemplate2 implements Student2DAO {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplateObject;
    private PlatformTransactionManager transactionManager;
    @Override
    public void setDataSource(DataSource ds) {
            this.dataSource = ds;
            this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }

    public void setTransactionManager(PlatformTransactionManager transactionManager){
        this.transactionManager = transactionManager;
    }

    @Override
    public void create(String name, Integer age, Integer marks, Integer year) {
        TransactionDefinition def = new DefaultTransactionDefinition();
        TransactionStatus status = transactionManager.getTransaction(def);
        try{
            String SQL1 = "insert into Student (name,age) values (?,?)";
            jdbcTemplateObject.update(SQL1,name,age);
            //Get the latest student id to be used in Marks table;
            String SQL2 = "select max(id) from Student ";
            int sid = jdbcTemplateObject.queryForObject(SQL2,Integer.class);

            String SQL3 = "insert into Marks (sid,marks,year) values(?,?,?)";
            jdbcTemplateObject.update(SQL3,sid,marks,year);
            System.out.println("Created Name = " + name + " , Age = " +age);

            transactionManager.commit(status);
        }catch(DataAccessException e){
            System.out.println("Error in creating record,rolling back");
            transactionManager.rollback(status);
        }

        return ;
    }

    @Override
    public List<StudentMarks> listStudents() {
        String SQL = "Select * from Student,Marks where Student.id = Marks.sid";
        List<StudentMarks> studentMarks = jdbcTemplateObject.query(SQL,new StudentMarksMapper());

        return studentMarks;
    }
}
