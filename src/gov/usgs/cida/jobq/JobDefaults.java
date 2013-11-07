/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.usgs.cida.jobq;

import gov.usgs.cida.miscutils.MiscUtil;
import java.net.URI;

/**
 *
 * @author whb
 */
public class JobDefaults
{
    private JobDefaults ()
    {
        // private constructor enforces noninstantiability
    }
    
    /**
     * Conventional HTTP Header name when referencing a Job ID in an HTTP
     * message
     */
    public static final String JOB_CORRELATION_HEADER_NAME = "JobID";
    
    public static final URI SINGLETON_JOBQ_WORKER_URI = MiscUtil.makeValidURI (
            "http://cida.usgs.gov/jobq/defaultqueue");
}
