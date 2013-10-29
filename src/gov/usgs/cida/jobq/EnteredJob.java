package gov.usgs.cida.jobq;

import java.net.URI;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Bill Blondeau
 */
public class EnteredJob implements Job {
    
    private InsideJob insideJob;
    private Object unitOfWork;
    private boolean isComplete = false;
    private final List<JobEvent> history = new ArrayList<> ();
    
    private final Deque backflowQueue = new LinkedList<>();
    private final List<ReturnedJob> contributingReturns = new ArrayList<>();
    
    public EnteredJob (int jobID, URI resourceDefinitionURI, Object unitOfWork) {
        
        if (resourceDefinitionURI == null) {
            throw new IllegalArgumentException(
                "Parameter 'resourceDefinitionURI' not permitted to be null.");
        }
        
        this.insideJob = new InsideJob(jobID, resourceDefinitionURI);
        this.unitOfWork = unitOfWork;
    }
    
    @Override
    public Integer getJobID() {
        return this.insideJob.getJobID();
    }

    public List<JobEvent> getHistory ()
    {
        return new ArrayList<> (this.history);
    }

    public JobEvent logEvent (URI workerURI, EventConsequence consequence, String description, Throwable cause)
    {
        JobEvent retval = new JobEvent (workerURI, this.getJobID(), consequence, description, cause);

        this.history.add (retval);
        if (consequence == EventConsequence.COMPLETE) 
        {
            this.isComplete = true;
        }
        return retval;
    }

    /**
     * 
     * @return <code>true</code> if this instance has logged an event with
     *      <code>EventConsequence.COMPLETE</code>, else <code>false</code>
     */
    public boolean isComplete()
    {
        return this.isComplete;
    }
        
    public Object getUnitOfWork() {
        return this.unitOfWork;
    }

    @Override
    public URI getResourceDefinitionURI ()
    {
        return this.insideJob.getResourceDefinitionURI ();
    }

    @Override
    public List<ReturnedJob> getContributingReturns ()
    {
        return this.insideJob.getContributingReturns ();
    }

    public synchronized void backflow (ReturnedJob returnedJob)
    {
        this.backflowQueue.addLast (returnedJob);
    }

    public synchronized ReturnedJob getNextBackflowJob ()
    {
        return (ReturnedJob) this.backflowQueue.removeFirst ();
    }

}