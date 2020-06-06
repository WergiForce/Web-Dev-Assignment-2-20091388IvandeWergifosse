package models;

import play.db.jpa.Model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

/**
 * Trainer model class, creating the model for trainers to be used throughout the application
 *
 * @author Ivan de Wergifosse 20091388
 */

@Entity
public class Trainer extends Model{
    public String firstName;
    public String lastName;
    public String email;
    public String password;

    @OneToMany(cascade=CascadeType.ALL)
    public List<Member> members=new ArrayList<Member>();

    public Trainer(String firstName,String lastName,String email,String password){
        this.firstName=firstName;
        this.lastName=lastName;
        this.email=email;
        this.password=password;
    }

    public static Trainer findByEmail(String email){
        return find("email",email).first();
    }

    public boolean checkPassword(String password){
        return this.password.equals(password);
    }
}
