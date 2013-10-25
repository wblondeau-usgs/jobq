package gov.usgs.cida.jobq;

/**
 * A generic Exception for situations involving explicitly identified
 * discrete jobs.
 * 
 * @author Bill Blondeau
 */
public class JobException extends RuntimeException {
    
    private final String jobID;
    
    public JobException (String jobID, String message, Throwable cause) {
        super(message, cause);
        this.jobID = jobID;
    }
    
    public String getJobID() {
        return this.jobID;
    }
}
