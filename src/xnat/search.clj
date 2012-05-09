;;; Copyright (c) 2012 Washington University
(ns #^{:doc "XNAT search operations"
       :author "Kevin A. Archie <karchie@wustl.edu>"}
  xnat.search
  (:import (org.nrg.xdat.om XdatStoredSearch
                            XnatImagesessiondata)
           (org.nrg.xdat.search DisplaySearch)
           (org.nrg.xdat.security XDATUser)))


(defn get-stored-search [search-id user]
  "Retrieve the named stored search for the provided user."
  (XdatStoredSearch/getXdatStoredSearchsById search-id user true))

(defn make-display-search [& {:keys [user root fields]}]
  "Create a new DisplaySearch."
  (let [ds (DisplaySearch.)]
    (when user
      (.setUser ds (if (instance? XDATUser user)
                     user
                     (XDATUser. user))))
    (when root
      (.setRootElement ds root))
    (when fields
      (doseq [[elem field] fields]
        (.addDisplayField ds elem field)))
    ds))
