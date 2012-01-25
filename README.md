cljinxnat : Clojure utilities for liverepl in a running XNAT
============================================================

Author: Kevin A. Archie, <karchie@wustl.edu>

Copyright (c) 2012 Washington University

This code is designed be loaded into an XNAT liverepl.

***
***

## xnat.files

Variables and functions for finding files and directories in an XNAT.


***

__tomcat__
_variable_

Tomcat root directory

***

__home__
_variable_

Tomcat user (XNAT owner) home directory

***

__get-archive__
_function_

    Usage: (xnat.files/get-archive)
       	   (xnat.files/get-archive "project")

With no project argument, returns the global archive location as a File.
With a project argument, returns the archive location for that project.

***

__get-prearchive__
_function_

    Usage: (xnat.files/get-prearchive)
       	   (xnat.files/get-prearchive "project")

With no project argument, returns the global prearchive location as a File.
With a project argument, returns the prearchive location for that project.

***

__get-cache__
_function_

    Usage: (xnat.files/get-cache)
       	   (xnat.files/get-cache "project")

With no project argument, returns the global cache location as a File.
With a project argument, returns the cache location for that project.

***

__get-build__
_function_

    Usage: (xnat.files/get-build)
       	   (xnat.files/get-build "project")

With no project argument, returns the global build location as a File.
With a project argument, returns the build location for that project.

***
***

## xnat.dicom

XNAT DICOM support

***

__get-spring-cstore-scp__
_function_

    Usage: (xnat.dicom/get-dicom-scp)

Returns the DICOM C-STORE SCP object (org.nrg.dcm.DicomSCP) specified
in the Spring configuration.

***

__make-cstore-scp__
_function_

    Usage: (make-cstore-scp xdat-user :ae-title "XNAT" :port 8104)

Creates a new DICOM C-STORE SCP object. There are many optional
arguments allowing considerable flexibility:

^String ae-title:
application entity title, must be provided unless application-entity
is set.

^org.dcm4che2.net.NetworkApplicationEntity application-entity:
If none is provided, a new one will be created using the network
connection and provided AE title.

^org.dcm4che2.net.Device device:
If none is provided, a new one will be created using the provided (or
default) network connection and the application entity.

^java.util.concurrent.ExecutorService executor-service:
If none is provided, a new cached thread pool will be created.

^org.dcm4che2.net.NetworkConnection network-connection:
If none is provided, a new one will be created on the provided port.

^int port:
TCP port for service, must be provided unless either
network-connection is set, or both application-entity and device are
set.

^String service-name:
Name used for thread identification.

^org.nrg.xnat.DicomObjectIdentifier<org.nrg.xdat.om.XnatProjectdata>
 xnat-identifier:
If none is provided, a new org.nrg.dcm.id.ClassicDicomObjectIdentifier
is created.

***

## xnat.prearc

Prearchive operations

***

__get-sessions__
_function_

    Usage: (xnat.prearc/get-sessions)
           (xnat.prearc/get-sessions "project")

Gets a sequence of maps, each representing a session in the
prearchive. If a project argument is provided, the sequence is all
sessions in that project; otherwise, all sessions for all projects.
