package gov.usgs.cida.jobq;

import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

/**
 * Instances of this class are immutable.
 * @author Bill Blondeau
 */
public class JobEvent {
    
    // current time, default locale and timezone.
    GregorianCalendar timestamp = new GregorianCalendar();
    SimpleDateFormat stringFormat 
            = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
    String timestampString = stringFormat.format(timestamp.getTime());
    
    private URI workerURI;
    private Integer jobID;
    private EventConsequence consequence;
    private String description;
    private Throwable cause;
    
    /**
     * This constructor has only package visibility because it's not intended
     * for general use. Rather than calling a constructor, the calling
     * routine will invoke 
     * <code>Job. logEvent(URI, EventConsequence, String, Throwable)</code>, which 
     * logs the information inside the Job itself and returns the JobEvent
     * instance.
     * 
     * 
     * @param workerURI
     * @param jobID
     * @param consequence
     * @param description
     * @param cause 
     */
    JobEvent(
            URI workerURI,
            Integer jobID, 
            EventConsequence consequence, 
            String description,
            Throwable cause) {
        if (workerURI == null) {
            throw new IllegalArgumentException (
                    "Parameter 'workerURI' not permitted to be null.");
        }
        if (jobID == null) {
            throw new IllegalArgumentException (
                    "Parameter 'jobID' not permitted to be null.");
        }
        if (consequence == null) {
            throw new IllegalArgumentException(
                    "Parameter 'consequence' not permitted to be null.");
        }
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException (
                    "Parameter 'description' not permitted to be null, "
                            + "empty, or blank.");
        }
        
        this.workerURI = workerURI;
        this.jobID = jobID;
        this.consequence = consequence;
        this.description = description;
        this.cause = cause;
    }
    
    public long getMillisecondTimestamp() {
        return this.timestamp.getTimeInMillis();
    }
    
    public String getTimestampContent() {
        return this.timestampString;
    }
    
    public URI getWorkerURI() {
        return this.workerURI;
    }
    
    public Integer getJobID() {
        return this.jobID;
    }
    
    public EventConsequence getConsequence() {
        return this.consequence;
    }
    
    public String getDescription() {
        return this.description;
    }
    
    public Throwable getCause() {
        return this.cause;
    }
    
    @Override
    public String toString() {
        StringBuilder retval = new StringBuilder();
        retval.append(this.jobID);
        retval.append("\nJOB: ");
        retval.append(this.consequence.toString());
        retval.append("\nAT: ");
        retval.append(this.timestampString);
        retval.append("\nAT WORKER: ");
        retval.append(this.workerURI);
        retval.append("\n");
        retval.append(this.description);
        if (this.cause != null) {
            retval.append("\nTHROWN CAUSE: ");
            retval.append(cause.getClass().getName());
            retval.append("\n");
            retval.append(cause.getMessage());
        }
        return retval.toString();
    }
}
