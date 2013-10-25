/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.usgs.cida.jobq;

import java.net.URI;
import java.util.List;

/**
 *
 * @author whb
 */
public interface CompletedJob extends Job {

    List<URI> getContributingResources();

    Object getOutput();
    
}
