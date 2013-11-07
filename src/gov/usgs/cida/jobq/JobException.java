package gov.usgs.cida.jobq;

/**
 * A generic Exception for situations involving explicitly identified
 * discrete jobs.
 * 
 * @author Bill Blondeau
 */
public class JobException extends RuntimeException {
    
    private final int jobID;
    
    public JobException (int jobID, String message, Throwable cause) {
        super(message, cause);
        this.jobID = jobID;
    }
    
    public int getJobID() {
        return this.jobID;
    }
}
