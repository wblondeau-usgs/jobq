package gov.usgs.cida.jobq;

import java.net.URI;
import java.util.Deque;
import java.util.LinkedList;

/**
 *
 * @author Bill Blondeau
 */
public class JobQueue implements JobWorker {
    
    private Deque<PendingJob> insideQueue = new LinkedList<>();
    private URI workerURI;
    
    public JobQueue (URI workerURI) {
        if (workerURI == null) {
            throw new IllegalArgumentException (
                "Parameter 'workerURI' not permitted to be null.");
        }
        this.workerURI = workerURI;
    }
    
    @Override
    public URI getWorkerURI() {
        return this.workerURI;
    }
    
    public Integer submitJob(URI designatedResource, Object input) {
        if (designatedResource == null) {
            throw new IllegalArgumentException(
                "Parameter 'designatedResource' not permitted to be null.");
        }
        if (input == null) {
            throw new IllegalArgumentException(
                "Parameter 'input' not permitted to be null.");
        }
        
        PendingJob newJobToEnqueue = new PendingInsideJob (
                JobIDSource.THE_ONLY_JOB_ID_SOURCE.getNewJobID(),
                designatedResource,
                input
            );
        insideQueue.addLast(newJobToEnqueue);
        
        newJobToEnqueue.logEvent(
                this.workerURI,
                EventConsequence.ROUTINE, 
                "Job queued for processing.", 
                null);
        
        return newJobToEnqueue.getJobID();
    }
    
    public boolean hasJob() {
        return ! this.insideQueue.isEmpty();
    }
    
    public PendingJob peekAtNextJob() {
        return this.insideQueue.peekFirst();
    }
    
    public PendingJob acceptNextJob(Integer jobID) {
        return this.insideQueue.removeFirst();
    }
    
}
