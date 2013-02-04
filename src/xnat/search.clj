;;; Copyright (c) 2012,2013 Washington University School of Medicine
(ns #^{:doc "XNAT search operations"
       :author "Kevin A. Archie <karchie@wustl.edu>"}
  xnat.search
  (:import (org.nrg.xdat.om XdatStoredSearch
                            XnatImagesessiondata)
           (org.nrg.xdat.search DisplaySearch)
           (org.nrg.xdat.security XDATUser)))


(defn get-stored-search
  "Retrieve the named stored search for the provided user."
  [search-id user]
  (XdatStoredSearch/getXdatStoredSearchsById search-id user true))

(defn get-stored-searches
  "Retrieve all stored searches for the provided user."
  [user]
  (XdatStoredSearch/getAllXdatStoredSearchs user true))

(defn get-field-ids
  "Get the field IDs for the provided search."
  [search]
  (map (memfn getFieldId) (.getSearchField search)))

(defn make-display-search
  "Create a new DisplaySearch."
  [& {:keys [user root fields]}]
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

