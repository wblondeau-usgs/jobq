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
    //private final List<ReturnedJob> contributingJobs = new ArrayList<>();
    private Object workResult;

    public ReturnedJob (
            int jobID, 
            URI resourceDefinitionURI,
            Object workResult)
    {

        this.insideJob = new InsideJob (jobID, resourceDefinitionURI);
        this.workResult = workResult;
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

    public Object getWorkResult ()
    {
        return this.workResult;
    }
}
