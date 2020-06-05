package controllers;

import models.Member;
import models.Assessment;
import play.Logger;
import play.mvc.Controller;

public class MemberAccounts extends Controller{
    public static void signup(){
        render("signup.html");
    }

    public static void login(){
        render("login.html");
    }

    public static void register(String firstname,String lastname,String email,String password,String gender,int height,double startingweight){
        Logger.info("Registering new user "+email);
        Member member=new Member(firstname,lastname,email,password,gender.toLowerCase(),height,startingweight);
        Assessment assessment=new Assessment(startingweight,0.0,0.0,0.0,0.0,0.0," ");
        member.save();
        assessment.save();
        redirect("/dashboard");
    }

    public static void authenticate(String email,String password) {
        Logger.info("Attempting to authenticate with " + email + ": " + password);

        Member member = Member.findByEmail(email);
        if ((member != null) && (member.checkPassword(password) == true)) {
            Logger.info("Authentication successful");
            session.put("logged_in_Memberid", member.id);
            redirect("/dashboard");
        } else {
            Logger.info("Authentication failed");
            redirect("/login");
        }
    }

    public static void logout(){
        session.clear();
        redirect("/");
    }

    public static Member getLoggedInMember(){
        Member member=null;
        if(session.contains("logged_in_Memberid")){
            String memberId=session.get("logged_in_Memberid");
            member=Member.findById(Long.parseLong(memberId));
        } else {
            login();
        }
        return member;
    }
}
