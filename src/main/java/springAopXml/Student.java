package springAopXml;

public class Student {

    private Integer age;
    private String name;
    private Integer id;

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getAge() {
//        System.out.println("Age : " + age);
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
//        System.out.println("Name : " + name);
        return name;
    }
    public void setId(Integer id){
        this.id = id;
    }

    public Integer getId(){
        return id;
    }

    public void printThrowException() {
//        System.out.println("Exception raised");
        throw new IllegalArgumentException();
    }
}