package controllers;

import java.util.List;
import models.Assessment;
import play.Logger;
import play.mvc.Controller;

/**
 * Admin class that renders and controls the admin page.
 *
 * @author Eamonn de Leastar & Ivan de Wergifosse 20091388
 */

public class Admin extends Controller{
    public static void index(){
        Logger.info("Rendering Admin");
        List<Assessment> assessments=Assessment.findAll();
        render("admin.html",assessments);
    }
}
