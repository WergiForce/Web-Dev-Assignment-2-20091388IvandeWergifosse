package models;

import javax.persistence.Entity;

import play.db.jpa.Model;

/**
 * Assessment model class, creating the model for assessments to be used throughout the application
 *
 * @author Ivan de Wergifosse 20091388
 */

@Entity
public class Assessment extends Model{
    public String timestamp;
    public double weight;
    public double chest;
    public double thigh;
    public double upperArm;
    public double waist;
    public double hips;
    public String comment;
    public String trend;

    public Assessment(String timestamp,double weight,double chest,double thigh,double upperArm,double waist,double hips,String comment){
        setTimestamp(timestamp);
        setWeight(weight);
        setChest(chest);
        setThigh(thigh);
        setUpperArm(upperArm);
        setWaist(waist);
        setHips(hips);
        setComment(comment);
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setChest(double chest) {
        this.chest = chest;
    }

    public void setThigh(double thigh) {
        this.thigh = thigh;
    }

    public void setUpperArm(double upperArm) {
        this.upperArm = upperArm;
    }

    public void setWaist(double waist) {
        this.waist = waist;
    }

    public void setHips(double hips) {
        this.hips = hips;
    }

    public void setComment(String comment){
        this.comment=comment;
    }

    public void setTimestamp(String timestamp){
        this.timestamp=timestamp;
    }

    public void setTrend(String trend){
        this.trend=trend;
    }

    public double getWeight() {
        return weight;
    }

    public double getChest() {
        return chest;
    }

    public double getThigh() {
        return thigh;
    }

    public double getUpperArm() {
        return upperArm;
    }

    public double getWaist() {
        return waist;
    }

    public double getHips() {
        return hips;
    }

    public String getComment(){
        return comment;
    }

    public String getTimestamp(){
        return timestamp;
    }


}
