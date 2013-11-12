package gov.usgs.cida.jobq;

import java.net.URI;

/**
 *
 * @author Bill Blondeau
 */
public interface Job {

    public Integer getJobID();

    public URI getResourceDefinitionURI ();  
}
