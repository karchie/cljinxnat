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

__get-dicom-scp__
_function_

    Usage: (xnat.dicom/get-dicom-scp)

Returns the DICOM C-STORE SCP object (org.nrg.dcm.DicomSCP) specified
in the Spring configuration.