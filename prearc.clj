;;; Copyright (c) 2012 Washington University
(ns #^{:doc "XNAT prearchive"
       :author "Kevin A. Archie <karchie@wustl.edu>"}
  xnat.prearc
  (:import (org.nrg.xnat.helpers.prearchive PrearcDatabase)))

(defn get-sessions
  "Gets a sequence of maps, each representing a session in the
prearchive. If a project argument is provided, the sequence is all
sessions in that project; otherwise, all sessions for all projects."
  ([]
     (map bean (PrearcDatabase/getAllSessions)))
  ([project]
     (map bean (PrearcDatabase/getSessionsInProject project))))