/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.usgs.cida.jobq;

/**
 * This class is intended to be instantiated when a Job is available 
 * on the JobQueue. It executes once, then is garbage collected. It is
 * the implementation of concurrent execution for the JobQ framework.
 * 
 * This class will not run unless it has been assigned a
 * <code>PresentedJob</code>. It will draw all necessary information about the
 * actions to be taken in Runnable from the <code>PresentedJob</code>. It is 
 * then responsible simply for invoking the execution method of the Job.
 * 
 * @author Bill Blondeau
 */
public class JobRunner implements Runnable
{
    private PresentedJob job = null;
    

    @Override
    public void run ()
    {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    public PresentedJob getJob ()
    {
        return job;
    }

    public void setJob (PresentedJob job)
    {
        if (this.job != null)
        {
            throw new IllegalStateException (
                    "This instance already has a Job, which cannot be modified.");
        }
        this.job = job;
    }
    
}
