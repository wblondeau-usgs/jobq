package gov.usgs.cida.jobq;

import java.net.URI;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * A utility for pending and completed Jobs.
 *
 * @author Bill Blondeau
 */
public class InsideJob implements Job
{
    private final int jobID;
    private final URI resourceDefinitionURI;

    public InsideJob (int jobID, URI resourceDefinitionURI)
    {
        if (resourceDefinitionURI == null)
        {
            throw new IllegalArgumentException(
                    "Parameter 'resourceDefinitionURI' not "
                            + "permitted to be null.");
        }
        this.jobID = jobID;
        this.resourceDefinitionURI = resourceDefinitionURI;
    }

    @Override
    public Integer getJobID ()
    {
        return this.jobID;
    }

    @Override
    public URI getResourceDefinitionURI ()
    {
        return this.resourceDefinitionURI;
    }
}
