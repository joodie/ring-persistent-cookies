(ns test-persistent-cookies
  (:use ring.persistent-cookies
        clojure.test
        [clj-time.core :only [date-time]]))

(deftest cookie-formatting
  (= (persistent-cookie "test" "value" (clj-time.core/date-time 2020 01 01) {:path "/"})
     ["test" {:expires "Wed, 01-01-2020 00:00:00 GMT", :value "value", :path "/"}]))
