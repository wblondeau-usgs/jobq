/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.usgs.cida.jobq;

/**
 * A singleton class 
 * @author whb
 */
public class JobIDSource {
    
    public static final JobIDSource THE_ONLY_JOB_ID_SOURCE = new JobIDSource();
    
    private int lastID = -1;
    
    private JobIDSource () {
        // private constructor to guarantee singleton instance 
    }
    
    public synchronized Integer getNewJobID() {
        if (lastID == Integer.MAX_VALUE) {
            // reset
            this.lastID = -1;
        }
        int newID = this.lastID++;
        
        return newID;
    }
    
}
