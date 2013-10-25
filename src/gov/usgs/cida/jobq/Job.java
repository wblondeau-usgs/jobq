package gov.usgs.cida.jobq;

import java.net.URI;
import java.util.List;

/**
 *
 * @author Bill Blondeau
 */
public interface Job {

    List<JobEvent> getHistory();

    Integer getJobID();

    JobEvent logEvent(
            URI workerURI, 
            EventConsequence consequence, 
            String description, 
            Throwable cause);
    
}
