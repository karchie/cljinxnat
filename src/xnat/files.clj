;;; Copyright (c) 2012 Washington University
(ns #^{:doc "XNAT file paths"
       :author "Kevin A. Archie <karchie@wustl.edu>"}
  xnat.files
  (:import (java.io File)
           (org.nrg.xnat.turbine.utils ArcSpecManager))
  (:require [clojure.string :as string]))

(defmacro build-path-functions
  "For each provided location, defines a get-{location} function that,
with no arguments, returns the site-wide directory (as a File); or,
given a project name, returns the project-specific directory."
  [arcspec & locs]
  `(let [~'arcspec ~arcspec]
     ~@(map
        (fn [loc]
          (let [lloc (string/lower-case loc)
                cloc (string/capitalize lloc)]
            `(defn ~(symbol (str "get-" lloc))
               ~(str "Get the " lloc " path. If a project name is "
                     "provided, returns the project-specific path."
                     " Otherwise, returns the global " lloc " path.")
               ([] (File. (~(symbol (str ".getGlobal" cloc "Path"))
                           ~'arcspec)))
               ([~'proj]
                  (File. (~(symbol (str ".get" cloc "PathForProject"))
                          ~'arcspec ~'proj))))))
        locs)))

(build-path-functions (ArcSpecManager/GetInstance)
                      archive prearchive cache build)

(def tomcat (.getCanonicalPath (File. ".")))

(def home (System/getProperty "user.home"))
