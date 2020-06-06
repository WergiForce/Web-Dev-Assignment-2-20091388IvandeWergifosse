package controllers;

import models.Assessment;
import models.Member;

import java.text.SimpleDateFormat;
import java.util.Date;

import play.Logger;
import play.mvc.Controller;

/**
 * Member control class providing functionality for members to add and remove assessments, and provide a timestamp for
 * newly added assessments.
 *
 * @author Ivan de Wergifosse 20091388
 */

public class MemberCtrl extends Controller {
    public static void addAssessment(Long id,double weight,double chest,double thigh,double upperArm,double waist,double hips,String comment){
        Logger.info("Adding a new assessment");
        Member member=MemberAccounts.getLoggedInMember();
        String timestamp=timestamp();
        Assessment assessment=new Assessment(timestamp,weight,chest,thigh,upperArm,waist,hips,comment);
        member.assessments.add(assessment);
        member.save();
        redirect("/dashboard");
    }

    public static void deleteAssessment(Long id,Long assessmentid) {
        Logger.info("Deleting an assessment");
        Member member = Member.findById(id);
        Assessment assessment = Assessment.findById(assessmentid);
        member.assessments.remove(assessment);
        member.save();
        assessment.delete();
        redirect("/dashboard");
    }

    public static String timestamp(){
        String timestamp;
        Date date=new Date();
        SimpleDateFormat formatter=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        return timestamp=formatter.format(date);
    }
}
