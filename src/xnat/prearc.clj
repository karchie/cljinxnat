;;; Copyright (c) 2012 Washington University
(ns #^{:doc "XNAT prearchive"
       :author "Kevin A. Archie <karchie@wustl.edu>"}
  xnat.prearc
  (:import (java.io File)
           (org.nrg.xnat.helpers.prearchive PrearcDatabase)
           (org.nrg.xnat.turbine.utils ArcSpecManager
                                       XNATSessionPopulater)))

(defn get-sessions
  "Gets a sequence of maps, each representing a session in the
prearchive. If a project argument is provided, the sequence is all
sessions in that project; otherwise, all sessions for all projects."
  ([]
     (map bean (PrearcDatabase/getAllSessions)))
  ([project]
     (map bean (PrearcDatabase/getSessionsInProject project))))

  
(defn load-session [user project timestamp folder]
  {:pre [user project timestamp folder]}
  (let [prearc-dir (-> (ArcSpecManager/GetInstance)
                       (.getPrearchivePathForProject project)
                       (File.))
        ts-dir (File. prearc-dir timestamp)
        session-xml (File. ts-dir (str folder ".xml"))]
    (-> (XNATSessionPopulater. (xnat.security/user user)
                               session-xml project false)
        (.populate))))