package controllers;

import models.Assessment;
import models.Member;
import play.Logger;
import play.mvc.Controller;

import java.util.Collections;
import java.util.List;

/**
 * Controller class to render the member dashboard and provide relevant information for the display such as assessment lists,
 * BMI, weight trend and ideal weight indicators. Also inverts the assessment list to display newest assessments first.
 *
 * @author Ivan de Wergifosse 20091388
 */

public class MemberDashboard extends Controller{
    public static void index() {
        Logger.info("Rendering Dashboard");
        Member member=MemberAccounts.getLoggedInMember();
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
        render ("dashboard.html", assessments,member,BMI,BMICategory,isIdealBodyWeight,trend);
    }
}
