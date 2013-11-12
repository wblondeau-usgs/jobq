
package gov.usgs.cida.jobq;

/**
 *
 * @author Bill Blondeau
 */
public enum EventConsequence {
    /**
     * Normal operation.
     */
    ROUTINE ("Normal operation."),
    /**
     * Non-fatal, non-blocking failure of part of the job, 
     * causing no retry/recovery attempt.
     */
    ERROR ("Non-fatal, non-blocking failure of part of the "
            + "job, causing no retry/recovery attempt."),
    /**
     * Causes at least one repetition of a failed attempt, or
     * special recovery measures.
     */
    RETRY ("Causes at least one repetition of a failed attempt, or"
            + "special recovery measures."),
    /**
     * Blocked, waiting, or otherwise taking a longer time than usual.
     */
    DELAY ("Blocked, waiting, or otherwise taking a longer time than usual."),
    /**
     * Finishes the Job.
     */
    COMPLETE ("Finishes the Job."),
    /**
     * Job has failed and cannot be completed.
     */
    FATAL ("Job has failed and cannot be completed."),
    /**
     * Special procedure to investigate status of Job:
     * normally a non-production operation.
     */
    DIAGNOSTIC ("Special procedure to investigate status of Job: "
            + "normally a non-production operation."),
    /**
     * Status of Job cannot be determined.
     */
    UNKNOWN ("Status of Job cannot be determined.");
    
    private final String descr;
    
    private EventConsequence(String description) {
        this.descr = description;
    }
    
    public String getDescription() {
        return this.descr;
    }
}
