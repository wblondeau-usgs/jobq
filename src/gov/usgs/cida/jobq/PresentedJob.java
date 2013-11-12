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
public class PresentedJob implements Job 
{
    private InsideJob insideJob;
    private Object unitOfWork;
    private boolean isComplete = false;
    private final List<JobEvent> history = new ArrayList<> ();
    private final Deque backflowQueue = new LinkedList<>();
    private final List<ReturnedJob> contributingReturns = new ArrayList<>();

    
    public PresentedJob (int jobID, URI resourceDefinitionURI, Object unitOfWork) {
        
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
        return new ArrayList<>(this.history);
    }

    /**
     * Log the event, as described by the parameters, in this 
     * <code>PresentedJob</code>'s History.
     * 
     * @param workerURI The non-null workerURI of the component responsible 
     *      for the event.
     * @param consequence The non-null enumerated value describing the category
     *      of the event and its impact on job control.
     * @param description A non-null brief human-readable text describing the event.
     * @param cause any Throwable that caused the event. <code>null</code>
     *      in the event of a non-Exceptional condition.
     * @return the JobEvent created and registered with this 
     *      instance's History.
     */
    public JobEvent logEvent (URI workerURI, 
            EventConsequence consequence, 
            String description, 
            Throwable cause)
    {
        if (workerURI == null)
        {
            throw new IllegalArgumentException (
                    "Parameter 'workerURI' not permitted to be null.");
        }
        if (consequence == null)
        {
            throw new IllegalArgumentException (
                    "Parameter 'consequence' not permitted to be null.");
        }
        if (description == null)
        {
            throw new IllegalArgumentException (
                    "Parameter 'description' not permitted to be null.");
        }
        
        JobEvent retval = new JobEvent (
                workerURI, 
                this.getJobID(), 
                consequence, 
                description, 
                cause);

        this.history.add(retval);
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

    public List<ReturnedJob> getContributingReturns ()
    {
        return new ArrayList(this.contributingReturns);
    }
    
    public void addContributingReturn (ReturnedJob contributingReturn)
    {
        if (contributingReturn == null)
        {
            throw new IllegalArgumentException (
                    "Parameter 'contributingReturn' not "
                            + "permitted to be null.");
        }
        this.contributingReturns.add(contributingReturn);
    }
    
    public synchronized int getPendingBackflowCount()
    {
        return this.backflowQueue.size ();
    }
    
    /**
     * Convenience method, logically equivalent to 
     * <code>this.getPendingBackflowCount() == 0</code>.
     * 
     * @return 
     */
    public synchronized boolean isBackflowEmpty()
    {
        return this.backflowQueue.isEmpty ();
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