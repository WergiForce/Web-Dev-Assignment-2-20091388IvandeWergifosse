package models;

import play.db.jpa.Model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

/**
 * Member model class, creating the model for members to be used throughout the application
 *
 * @author Ivan de Wergifosse 20091388
 */

@Entity
public class Member extends Model{

    public String firstname;
    public String lastname;
    public String email;
    public String password;
    public String gender;
    public double height;
    public double startingweight;

    @OneToMany(cascade=CascadeType.ALL)
    public List<Assessment> assessments=new ArrayList<Assessment>();

    public Member(String firstname,String lastname,String email,String password,String gender,int height, double startingweight){
        setFirstname(firstname);
        setLastname(lastname);
        setEmail(email);
        setPassword(password);
        setGender(gender);
        setHeight(height);
        setStartingweight(startingweight);
    }

    public String getFirstname(){
        return firstname;
    }

    public String getLastname(){
        return lastname;
    }

    public String getEmail(){
        return email;
    }

    public String getPassword(){
        return password;
    }

    public double getHeight(){
        return height;
    }

    public double getStartingWeight(){
        return startingweight;
    }

    public String getGender(){
        return gender;
    }

    public void setGender(String gender) {
        if(gender.equals("male")) {
            this.gender = "Male";
        } else if(gender.equals("female")){
            this.gender = "Female";
        } else {
            this.gender="Unspecified";
        }
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setStartingweight(double startingweight) {
        this.startingweight = startingweight;
    }

    public static Member findByEmail(String email){
        return find("email",email).first();
    }

    public boolean checkPassword(String password){
        return this.password.equals(password);
    }
}
