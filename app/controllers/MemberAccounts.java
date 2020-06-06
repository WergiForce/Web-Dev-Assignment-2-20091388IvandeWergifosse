package controllers;

import models.Member;
import play.Logger;
import play.mvc.Controller;

/**
 * Member Account controller class to control various aspects of a member account, such as rendering the member signup page, registering a new member,
 * rendering the member login page, authenticating sign in, logging out, providing the logged in member for a session and rendering the edit member details
 * pages and providing functionality.
 *
 * @author Ivan de Wergifosse 20091388
 */

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
        member.save();
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

    public static void editMemberDetails(){
        Member member=getLoggedInMember();
        render("editmemberdetails.html",member);
    }

    public static void editMember(String firstname,String lastname,String email,String password,String gender,int height,double startingweight){
        Logger.info("Editing existing member "+email);
        Member member=getLoggedInMember();
        member.setFirstname(firstname);
        member.setLastname(lastname);
        member.setEmail(email);
        member.setPassword(password);
        member.setGender(gender);
        member.setHeight(height);
        member.setStartingweight(startingweight);
        member.save();
        redirect("/dashboard");
    }
}
