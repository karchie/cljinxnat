;;; Copyright (c) 2012 Washington University
(ns #^{:doc "XNAT DICOM services"
       :author "Kevin A. Archie <karchie@wustl.edu>"}
  xnat.dicom
  (:import (java.util.concurrent Executors)
           (org.nrg.xdat XDAT)
           (org.nrg.dcm DicomSCP)
           (org.nrg.dcm.id ClassicDicomObjectIdentifier)
           (org.dcm4che2.net Device
                             NetworkApplicationEntity
                             NetworkConnection)
           (org.dcm4che2.net.service VerificationService)))

(defn get-spring-cstore-scp
  "Get the DICOM C-STORE SCP from the Spring context."
  []
  (-> (XDAT/getContextService)
      (.getBean "dicomSCP" org.nrg.dcm.DicomSCP)))

(defn make-cstore-scp
  "Create a new DICOM C-STORE SCP.
^org.nrg.xdat.user.XDATUser user

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
"
  [user &
   {:keys [ae-title
           application-entity
           device
           executor-service
           network-connection
           port
           service-name
           xnat-identifier]
    :or {executor-service (Executors/newCachedThreadPool)
         xnat-identifier (ClassicDicomObjectIdentifier.)
         service-name "XNAT_DICOM"}}]
  
  {:pre [(or port
             network-connection
             (and application-entity device))
         (or ae-title application-entity)]}
  
  (let [network-connection
        (if network-connection
          network-connection
          (doto (NetworkConnection.)
            (.setPort port)))
        
        application-entity
        (if application-entity
          application-entity
          (doto (NetworkApplicationEntity.)
            (.setNetworkConnection network-connection)
            (.setAssociationAcceptor true)
            (.register (VerificationService.))
            (.setAETitle ae-title)))
        
        device
        (if device
          device
          (doto (Device. service-name)
            (.setNetworkConnection network-connection)
            (.setNetworkApplicationEntity application-entity)))]
    
    (DicomSCP. user executor-service
               device application-entity
               xnat-identifier)))

