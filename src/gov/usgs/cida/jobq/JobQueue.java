package gov.usgs.cida.jobq;

import java.net.URI;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Bill Blondeau
 */
public class JobQueue implements JobWorker
{
    /**
     * The singleton instance of this class.
     */
    public static final JobQueue JOB_QUEUE = new JobQueue(
            JobDefaults.SINGLETON_JOBQ_WORKER_URI);

    private final Deque<PresentedJob> insideQueue = new LinkedList<> ();
    private URI workerURI;
    
    private final List<Runnable> jobRunners = new ArrayList<>();

    private final Map<Integer, Job> acceptedJobs = new HashMap<> ();
    
    

    private JobQueue (URI workerURI)
    {
        if (workerURI == null)
        {
            throw new IllegalArgumentException (
                    "Parameter 'workerURI' not permitted to be null.");
        }
        this.workerURI = workerURI;
    }

    @Override
    public URI getWorkerURI ()
    {
        return this.workerURI;
    }

    public synchronized Integer submitJob (URI designatedResource, Object unitOfWork)
    {
        if (designatedResource == null)
        {
            throw new IllegalArgumentException (
                    "Parameter 'designatedResource' not permitted to be null.");
        }
        if (unitOfWork == null)
        {
            throw new IllegalArgumentException (
                    "Parameter 'unitOfWork' not permitted to be null.");
        }

        PresentedJob newJobToEnqueue = new PresentedJob (
                JobIDSource.THE_ONLY_JOB_ID_SOURCE.getNewJobID (),
                designatedResource,
                unitOfWork
        );
        insideQueue.addLast (newJobToEnqueue);

        newJobToEnqueue.logEvent (
                this.workerURI,
                EventConsequence.ROUTINE,
                "Job created and queued for processing.",
                null);

        return newJobToEnqueue.getJobID ();
    }

    public synchronized boolean hasJob ()
    {
        return  ! this.insideQueue.isEmpty ();
    }

    /**
     * Retrieves, but does not remove, the PresentedJob at the head of this queue.
     * If there are no waiting jobs, returns <code>null</code>.
     * @return 
     */
    public synchronized PresentedJob peekAtNextJob ()
    {
        return this.insideQueue.peekFirst ();
    }

    /**
     * Obtains the PresentedJob at the head of the queue, deletes it
     * from the queue, and places a reference to it in the Accepted 
     * Jobs register. It will remain there until removed (hopefully to
     * persistent storage in accordance with archive policy.)
     * 
     * @param jobID
     * @return 
     */
    public synchronized PresentedJob acceptNextJob (Integer jobID)
    {
        PresentedJob newJob = this.insideQueue.pollFirst ();
        this.acceptedJobs.put (newJob.getJobID (), newJob);
        newJob.logEvent (this.workerURI, 
                EventConsequence.ROUTINE, "Accepted.", null);
        
        return newJob;
    }

    public synchronized Job getAcceptedJob (int jobID)
    {
        return this.acceptedJobs.get (jobID);
    }
}
