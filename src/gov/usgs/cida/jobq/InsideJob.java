package gov.usgs.cida.jobq;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * A utility for pending and completed Jobs.
 * 
 * @author Bill Blondeau
 */
public class InsideJob implements Job  {
    
    final List<JobEvent> history = new ArrayList<>();
    final int jobID;
    
    public InsideJob (int jobID) {
        
        this.jobID = jobID;
    }
    
    @Override
    public Integer getJobID() {
        return this.jobID;
    }

    @Override
    public List<JobEvent> getHistory() {
        // safe copy
        return new ArrayList<>(this.history);
    }
    
    @Override
    public JobEvent logEvent(URI workerURI, EventConsequence consequence, String description, Throwable cause) {
        
        JobEvent retval = new JobEvent(workerURI, this.jobID, consequence, description, cause);
        
        this.history.add(retval);
        return retval;
    }

}
