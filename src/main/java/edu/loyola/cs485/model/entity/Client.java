package edu.loyola.cs485.model.entity;
import java.sql.Date;

public class Client extends AbstractEntity {
    private Integer ID = null;
    private String Name;
    private String Email;
    private Date Dob;

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public Date getDob() {
        return Dob;
    }

    public void setDob(Date dob) {
        Dob = dob;
    }

    @Override
    public String toString(){
        return getID().toString()+": "+getName()+" <"+getEmail()+">";
    }
}