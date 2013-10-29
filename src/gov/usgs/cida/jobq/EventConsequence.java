
package gov.usgs.cida.jobq;

/**
 *
 * @author Bill Blondeau
 */
public enum EventConsequence {
    ROUTINE ("normal operation"),
    RETRY ("causes at least one repetition of a failed attempt"),
    DELAY ("blocked, waiting, or otherwise taking a longer time than usual"),
    COMPLETE ("finishes the Job"),
    FATAL ("Job has failed and cannot be completed"),
    DIAGNOSTIC ("special procedure to investigate status of Job"),
    UNKNOWN ("status of Job cannot be determined");
    
    private final String descr;
    
    private EventConsequence(String description) {
        this.descr = description;
    }
    
    public String getDescription() {
        return this.descr;
    }
}
