package controllers;

import models.Assessment;
import models.Trainer;
import models.Member;
import play.Logger;
import play.mvc.Controller;

import java.util.Collections;
import java.util.List;

/**
 * Controller class to render the trainer dashboard and display all members registered to the system and provide
 * functionality to delete a member or view individual member profiles, also provides the same assessment functionality
 * as a member (add/remove assessments) with the addition of commenting on individual assessments.
 *
 * @author Ivan de Wergifosse 20091388
 */

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
        String trend=GymUtility.trend(member);
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
        render ("member.html",member,assessments,BMI,BMICategory,isIdealBodyWeight,trend);
    }
}