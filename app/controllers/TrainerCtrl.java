package controllers;

import models.Assessment;
import models.Member;
import play.Logger;
import play.mvc.Controller;

/**
 * Trainer control class providing functionality for trainers to add and remove member assessments, and provide a timestamp for
 * newly added assessments, comment on assessments, and delete members entirely.
 *
 * @author Ivan de Wergifosse 20091388
 */

public class TrainerCtrl extends Controller{

    public static void deleteMember(Long id){
        Logger.info("Deleting a member");
        Member member=Member.findById(id);
        member.delete();
        redirect("/trainerdashboard");
    }

    public static void addAssessment(Long id,double weight,double chest,double thigh,double upperArm,double waist,double hips,String comment){
        Logger.info("Adding a new assessment");
        String timestamp=MemberCtrl.timestamp();
        Assessment assessment=new Assessment(timestamp,weight,chest,thigh,upperArm,waist,hips,comment);
        Member member=Member.findById(id);
        member.assessments.add(assessment);
        member.save();
        redirect("/members/"+id);
    }

    public static void deleteAssessment(Long id,Long assessmentid){
        Logger.info("Deleting an assessment");
        Member member=Member.findById(id);
        Assessment assessment=Assessment.findById(assessmentid);
        member.assessments.remove(assessment);
        member.save();
        assessment.delete();
        redirect("/members/"+id);
    }

    public static void addComment(Long id,Long assessmentid,String comment){
        Logger.info("Adding comment");
        Member member=Member.findById(id);
        Assessment assessment=Assessment.findById(assessmentid);
        assessment.setComment(comment);
        assessment.save();
        redirect("/members/"+id);
    }
}
