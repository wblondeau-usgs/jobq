/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.usgs.cida.jobq;

import java.net.URI;

/**
 *
 * @author whb
 */
public interface PendingJob extends Job {

    URI getDesignatedResource();

    Object getInput();
    
}
