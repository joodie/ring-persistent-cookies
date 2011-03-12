(ns ring.persistent-cookies
  (:use    ring.middleware.cookies
           clj-time.format)
  (:import org.joda.time.DateTimeZone
           java.util.Locale))

(def #^{:private true}
  cookie-date-formatter (with-locale
                          (formatter "EEE, dd-MM-YYYY HH:mm:ss" DateTimeZone/UTC)
                          Locale/US))

(defn cookie-date [date]
  "convert a clj-time/joda-time instant to the correct date formatting
for HTTP cookies."
  (str (.print cookie-date-formatter date) " GMT"))


(defn persistent-cookie
  "create a persistent cookie. expires can be milliseconds epoch time
or joda-time (clj-time) compatible instant/partial."
  ([name value expires attrs]
     [name (assoc attrs :value value :expires (cookie-date expires))])
  ([name value expires]
     (persistent-cookie name value expires {})))
