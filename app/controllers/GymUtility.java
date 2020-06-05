package controllers;

import models.Member;
import models.Assessment;
import play.data.binding.As;
import play.mvc.Controller;

import java.util.List;

public class GymUtility {
    private static double BMI;

    public static double calculateBMI(Member member,Assessment assessment){
        BMI=assessment.weight/((member.height/100)*(member.height/100));

        return BMI;
    }

    public static double calculateBMI(Member member){
        BMI=member.startingweight/((member.height/100)*(member.height/100));

        return BMI;
    }

    public static String determineBMICategory(double bmiValue){
        bmiValue=BMI;
        String BMICategory="UNDETERMINED";
        if(bmiValue<16){
            BMICategory="SEVERELY UNDERWEIGHT";
        } else if((bmiValue>=16)&&(bmiValue<18.5)){
            BMICategory="UNDERWEIGHT";
        } else if((bmiValue>=18.5)&&(bmiValue<25)){
            BMICategory="NORMAL";
        } else if((bmiValue>=25)&&(bmiValue<30)){
            BMICategory="OVERWEIGHT";
        } else if((bmiValue>=30)&&(bmiValue<35)){
            BMICategory="MODERATELY OBESE";
        } else if(bmiValue>=35){
            BMICategory="SEVERELY OBESE";
        }
        return BMICategory;
    }

    public static boolean isIdealBodyWeight(Member member,Assessment assessment){
        double height= member.getHeight();
        double weight;
        double idealBodyWeight= 50;
        boolean isIdealBodyWeight = false;
        if(assessment.getWeight()<=0){
            weight=member.getStartingWeight();
        } else {
            weight=assessment.getWeight();
        }

        if(member.getGender().equals("M")){
            idealBodyWeight=50.0+2.3*((height*39.3701)-60);
        }
        if(member.getGender().equals("F")||member.getGender().equals("Unspecified")){
            idealBodyWeight=45.5+2.3*((height*39.3701)-60);
        }

        if((idealBodyWeight<=(weight+0.2))&&(idealBodyWeight>=(weight-0.2))){
            isIdealBodyWeight=true;
        }
        return isIdealBodyWeight;
    }
}
