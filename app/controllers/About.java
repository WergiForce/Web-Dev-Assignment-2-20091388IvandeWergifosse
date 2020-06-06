package controllers;

import play.*;
import play.mvc.*;

/**
 * About class that renders and controls the about page.
 *
 * @author Eamonn de Leastar & Ivan de Wergifosse 20091388
 */

public class About extends Controller
{
  public static void index() {
    Logger.info("Rendering about");
    render ("about.html");
  }
}
