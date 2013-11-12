/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.usgs.cida.jobq;

/**
 * This interface serves two purposes.
 * <ul>
 *  <li>It's a marker as the point of intersection between the JobQ 
 *      framework and whatever external classes perform work within 
 *      the scope of a single Job.</li>
 *  <li>It supplies a single invocation method to that purpose.</li>
 * </ul>
 * 
 * Classes that do not implement this interface will need to be wrapped in
 * (or invoked from within) a class that does implement this interface, in
 * order to participate in JobQ work.
 * 
 * @author Bill Blondeau
 */
public interface JobTarget
{
    public void executeJob(PresentedJob presentedJob);
}
