;;; Copyright (c) 2012,2013 Washington University School of Medicine
(ns #^{:doc "XNAT security operations"
       :author "Kevin A. Archie <karchie@wustl.edu>"}
  xnat.security
  (:import (org.nrg.xdat.security XDATUser)))

(defn user [u]
  (if (instance? XDATUser u) u (XDATUser. u)))

(def context-service (org.nrg.xdat.XDAT/getContextService))

(def session-registry
  (.getBean context-service "sessionRegistry"
            org.springframework.security.core.session.SessionRegistryImpl))

(defn get-sessions
  "Gets the Spring SessionInformation object for each active session.
These are a little opaque but contain both the jsessionid and the
XDATUser."
  []
  (mapcat #(.getAllSessions session-registry % false)
          (.getAllPrincipals session-registry)))

(defn get-session-ids
  "Get the JSESSIONID strings for every active session."
  []
  (map (memfn getSessionId) (get-sessions)))

(defn get-active-users
  "Get XDATUser objects for all currently logged in users."
  []
  (map (memfn getPrincipal) (get-sessions)))

(defn get-active-logins []
  "Get usernames for all currently logged in users."
  []
  (map (memfn getLogin) (get-active-users)))
