package controllers;

import models.Member;
import models.Trainer;
import play.Logger;
import play.mvc.Controller;

public class TrainerAccounts extends Controller {
    public static void signup(){
        render("trainersignup.html");
    }

    public static void login(){
        render("trainerlogin.html");
    }

    public static void register(String firstname,String lastname,String email,String password){
        Logger.info("Registering new Trainer "+email);
        Trainer trainer=new Trainer(firstname,lastname,email,password);
        trainer.save();
        redirect("/dashboard");
    }

    public static void tAuthenticate(String email,String password) {
        Logger.info("Attempting to authenticate with " + email + ": " + password);

        Trainer trainer = Trainer.findByEmail(email);
        if ((trainer != null) && (trainer.checkPassword(password) == true)) {
            Logger.info("Authentication successful");
            session.put("logged_in_Trainerid", trainer.id);
            redirect("/trainerdashboard");
        } else {
            Logger.info("Authentication failed");
            redirect("/trainerlogin");
        }
    }

    public static void logout(){
        session.clear();
        redirect("/");
    }

    public static Trainer getLoggedInTrainer() {
        Trainer trainer = null;
        if (session.contains("logged_in_Trainerid")) {
            String trainerId = session.get("logged_in_Trainerid");
            trainer = Member.findById(Long.parseLong(trainerId));
        } else {
            login();
        }
        return trainer;
    }
}
