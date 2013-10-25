
package gov.usgs.cida.jobq;

/**
 *
 * @author Bill Blondeau
 */
public enum EventConsequence {
    ROUTINE ("normal operation"),
    RETRY ("causes at least one repetition of a failed attempt"),
    DELAY ("blocked, waiting, or otherwise taking a longer time than usual"),
    FATAL ("job has failed and cannot be completed"),
    UNKNOWN ("status of job cannot be determined");
    
    private final String descr;
    
    private EventConsequence(String description) {
        this.descr = description;
    }
    
    public String getDescription() {
        return this.descr;
    }
}
