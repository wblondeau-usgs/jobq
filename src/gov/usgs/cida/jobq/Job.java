package gov.usgs.cida.jobq;

import java.net.URI;
import java.util.List;
import java.util.Queue;

/**
 *
 * @author Bill Blondeau
 */
public interface Job {

    public Integer getJobID();

    public URI getResourceDefinitionURI ();
    
    /**
     * This List contains all ReturnedJobs that contributed to the current
     * instance's return value. Its purpose is historical, not operational:
     * this is the formal record of which JobWorkers provided a meaningful
     * return for this Job.
     * 
     * Note that this structure supports recursive arrangements of ReturnedJob
     * instances. Such a recursive structure, in the context of a JobQueue, will
     * be an acyclic tree with a single EnqueuedJob at its root.
     * 
     * @return 
     */
    public List<ReturnedJob> getContributingReturns();
  
}
