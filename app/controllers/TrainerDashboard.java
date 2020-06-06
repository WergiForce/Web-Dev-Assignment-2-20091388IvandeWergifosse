package controllers;

import models.Assessment;
import models.Trainer;
import models.Member;
import play.Logger;
import play.mvc.Controller;

import java.util.Collections;
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
        Collections.reverse(assessments);
        double BMI=Math.round((GymUtility.calculateBMI(member))*100.0)/100.0;
        String isIdealBodyWeight="No";
        if(!assessments.isEmpty()){
            Assessment assessment=assessments.get(0);
            BMI=Math.round((GymUtility.calculateBMI(member,assessment))*100.0)/100.0;
            GymUtility.isIdealBodyWeight(member,assessment);
            if(GymUtility.isIdealBodyWeight(member,assessment)==true){
                isIdealBodyWeight="Yes";
            }
        }
        String BMICategory=GymUtility.determineBMICategory(BMI);
        render ("member.html",member,assessments,BMI,BMICategory,isIdealBodyWeight);
    }
}