package HealthCareManagment.model;

public class Patient extends Person{
    private int age;

    public Patient(int id, String name, String phone, String email, int age) {
        super(id, name, phone, email);
        this.age = age;
    }
    @Override
    public String getDetails() {
        return String.format(
                "Pacienti: %s, ID: %d, Moshë: %d, Tel: %s, Email: %s",
                name, id, age, phone, email
        );
    }

    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

}