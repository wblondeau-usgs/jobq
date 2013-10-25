package gov.usgs.cida.jobq;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Bill Blondeau
 */
public class CompletedInsideJob implements CompletedJob {
    
    private InsideJob insideJob;
    private List<URI> contributingSources;
    private Object output;
    
    public CompletedInsideJob (int jobID, List<URI> contributingSources, Object output) {
        
        if (contributingSources == null) {
            throw new IllegalArgumentException(
                "Parameter 'contributingSources' not permitted to be null. An empty"
                        + "List is OK, but null is not.");
        }
        this.insideJob = new InsideJob(jobID);
        this.output = output;
    }

    @Override
    public Integer getJobID() {
        return this.insideJob.getJobID();
    }

    @Override
    public List<JobEvent> getHistory() {
        return this.insideJob.getHistory();
    }

    @Override
    public JobEvent logEvent(URI workerURI, EventConsequence consequence, 
            String description, Throwable cause) {
        return this.insideJob.logEvent(workerURI, consequence, description, cause);
    }
    
    @Override
    public List<URI> getContributingResources() {
        return new ArrayList<>(this.contributingSources);
    }
    
    @Override
    public Object getOutput() {
        return this.output;
    }

}
