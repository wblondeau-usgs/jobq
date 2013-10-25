package gov.usgs.cida.jobq;

import java.net.URI;
import java.util.List;

/**
 *
 * @author Bill Blondeau
 */
public class PendingInsideJob implements PendingJob {
    
    private InsideJob insideJob;
    private URI designatedResource;
    private Object input;
    
    public PendingInsideJob (int jobID, URI designatedResource, Object input) {
        
        if (designatedResource == null) {
            throw new IllegalArgumentException(
                "Parameter 'designatedResource' not permitted to be null.");
        }
        
        this.insideJob = new InsideJob(jobID);
        this.designatedResource = designatedResource;
        this.input = input;
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
    public URI getDesignatedResource() {
        return this.designatedResource;
    }
    
    @Override
    public Object getInput() {
        return this.input;
    }
}