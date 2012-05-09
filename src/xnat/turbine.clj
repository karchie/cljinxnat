;;; Copyright (c) 2012 Washington University
(ns #^{:doc "Support for Apache Turbine/Velocity context"
       :author "Kevin A. Archie <karchie@wustl.edu>"}
  xnat.turbine
  (:import (org.apache.turbine.services.rundata DefaultTurbineRunData)
           (org.apache.velocity VelocityContext)))

(defn make-rundata-context []
  (vector (DefaultTurbineRunData.) (VelocityContext.)))
