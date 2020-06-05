package models;

import play.db.jpa.Model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

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
        this.firstname=firstname;
        this.lastname=lastname;
        this.email=email;
        this.password=password;
        setGender(gender);
        this.height=height;
        this.startingweight=startingweight;
    }

    public double getHeight(){
        return height;
    }

    public double getStartingWeight(){
        return startingweight;
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

    public String getGender(){
        return gender;
    }

    public static Member findByEmail(String email){
        return find("email",email).first();
    }

    public boolean checkPassword(String password){
        return this.password.equals(password);
    }
}
