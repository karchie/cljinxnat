;;; Copyright (c) 2012 Washington University
(ns #^{:doc "XNAT security operations"
       :author "Kevin A. Archie <karchie@wustl.edu>"}
  xnat.security
  (:import (org.nrg.xdat.security XDATUser)))

(defn user [u]
  (if (instance? XDATUser u) u (XDATUser. u)))


