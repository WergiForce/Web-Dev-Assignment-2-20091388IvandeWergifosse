package controllers;

import models.Assessment;
import models.Trainer;
import models.Member;
import play.Logger;
import play.mvc.Controller;

import java.util.List;

public class TrainerDashboard extends Controller{
    public static void index() {
        Logger.info("Rendering Trainer Dashboard");
        Trainer trainer=TrainerAccounts.getLoggedInTrainer();
        List<Member> members=Member.findAll();
        render ("trainerdashboard.html",trainer,members);
    }

    public static void memberView(Long id) {
        Logger.info("Rendering member view. Member id = "+id);
        Member member=Member.findById(id);
        List<Assessment> assessments=member.assessments;
        double BMI=Math.round((GymUtility.calculateBMI(member))*100.0)/100.0;
        if(!assessments.isEmpty()){
            Assessment assessment=assessments.get(assessments.size()-1);
            BMI=Math.round((GymUtility.calculateBMI(member,assessment))*100.0)/100.0;
        }
        String BMICategory=GymUtility.determineBMICategory(BMI);
        render ("member.html",member,assessments,BMI,BMICategory);
    }
}