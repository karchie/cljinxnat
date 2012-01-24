;;; Copyright (c) 2012 Washington University
(ns #^{:doc "XNAT DICOM services"
       :author "Kevin A. Archie <karchie@wustl.edu>"}
  xnat.dicom
  (:import [org.nrg.xdat XDAT]))


(defn get-dicom-scp
  "Get the DICOM C-STORE SCP from the Spring context."
  []
  (-> (XDAT/getContextService)
      (.getBean "dicomSCP" org.nrg.dcm.DicomSCP)))