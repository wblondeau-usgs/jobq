The JobQ README
=========================

The JobQ project provides a very simple framework for 

# receiving units of work ("Jobs") whose execution is requested by calling code
# enqueueing those jobs in order received
# assigning a persistent identifier ("jobID") to each job
# dispatching each job to one or more "JobWorker" components
# assembling the Returned Jobs for final processing and return to the requestor.

The JobQ framework also provides an in-memory logging system tracking the 
process of each Job.

JOBS
----
Each Job is a unit of work, represented as a java.lang.Object, wrapped in
a Job instance. The Job holds the following information:

* A jobID, which is an object of type Integer, sequentially assigned on enqueueing;
* A Resource definition URI, which identifies the kind of operation to be performed on the unit of work
* The Unit of Work (in a Pending Job) or the Work Result (in a Returned Job)
* 