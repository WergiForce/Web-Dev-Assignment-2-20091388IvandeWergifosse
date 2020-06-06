import java.util.List;

import play.*;
import play.jobs.*;
import play.test.*;

import models.*;

/**
 * Bootstrap class to load data from a yml, specifically data.yml
 *
 * @author Eamonn de Leastar
 */

@OnApplicationStart
public class Bootstrap extends Job {
    public void doJob() {
        if (Member.count() == 0) {
            Fixtures.loadModels("data.yml");
        }
    }
}
