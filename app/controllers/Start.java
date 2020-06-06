package controllers;

import play.Logger;
import play.mvc.Controller;

/**
 * Simple controller to display start page on initial navigation to the site.
 *
 * @author Eamonn de Leastar & Ivan de Wergifosse 20091388
 */

public class Start extends Controller
{
  public static void index() {
    Logger.info("Rendering Start");
    render ("start.html");
  }
}
