package HealthCareManagment.model;
public abstract class  Person{

    protected int id;
    protected String name;
    protected String phone;
    protected String email;

    public Person(int id, String name, String phone, String email) {
        this.id = id;
        this.name = name;
        this.phone = phone;
    }

    public abstract String getDetails();


}