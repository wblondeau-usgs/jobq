package gov.usgs.cida.jobq;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Bill Blondeau
 */
public class ReturnedJob implements Job
{

    private InsideJob insideJob;
    private List<ReturnedJob> contributingJobs;
    private Object workResult;

    public ReturnedJob (int jobID, URI resourceDefinitionURI,
            List<ReturnedJob> contributingJobs, Object workResult)
    {

        if (contributingJobs == null)
        {
            throw new IllegalArgumentException (
                    "Parameter 'contributingJobs' not permitted to be null. An empty"
                    + "List is OK, but null is not.");
        }
        this.insideJob = new InsideJob (jobID, resourceDefinitionURI);
        this.workResult = workResult;
        this.contributingJobs.addAll (contributingJobs);
    }

    @Override
    public Integer getJobID ()
    {
        return this.insideJob.getJobID ();
    }

    @Override
    public URI getResourceDefinitionURI ()
    {
        return this.insideJob.getResourceDefinitionURI ();
    }

    @Override
    public List<ReturnedJob> getContributingReturns ()
    {
        return new ArrayList<> (this.contributingJobs);
    }

    public Object getWorkResult ()
    {
        return this.workResult;
    }
}
