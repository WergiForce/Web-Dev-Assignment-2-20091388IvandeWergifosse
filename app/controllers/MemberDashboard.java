package controllers;

import models.Assessment;
import models.Member;
import play.Logger;
import play.mvc.Controller;


import java.util.List;

public class MemberDashboard extends Controller{
    public static void index() {
        Logger.info("Rendering Dashboard");
        Member member=MemberAccounts.getLoggedInMember();
        List<Assessment> assessments=member.assessments;
        double BMI=Math.round((GymUtility.calculateBMI(member))*100.0)/100.0;
        String isIdealBodyWeight="No";
        if(!assessments.isEmpty()){
            Assessment assessment=assessments.get(assessments.size()-1);
            BMI=Math.round((GymUtility.calculateBMI(member,assessment))*100.0)/100.0;
            GymUtility.isIdealBodyWeight(member,assessment);
            if(GymUtility.isIdealBodyWeight(member,assessment)==true){
                isIdealBodyWeight="Yes";
            }
        }
        String BMICategory=GymUtility.determineBMICategory(BMI);
        String colour="red";
        if(isIdealBodyWeight.equals("Yes")){
            colour="green";
        }
        render ("dashboard.html", assessments,member,BMI,BMICategory,isIdealBodyWeight,colour);
    }
}
