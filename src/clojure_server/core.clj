(ns clojure-server.core
  (:require [ring.adapter.jetty :refer [run-jetty]]))

(defn app [request]
  (let [{:keys [uri request-method]} request]
    {:status 200
     :headers {"Content-Type" "text/plain"}
     :body (format "You requested %s %s" (-> request-method name .toUpperCase) uri)}))

(app {:request-method :get :uri "/index.html"})
{:status 200
 :headers {"Content-Type" "text/plain"}
 :body "GET /index.html"}

(app {:request-method :post :uri "/user"})
{:status 200
 :headers {"Content-Type" "text/plain"}
 :body "POST /user"}

(defn -main
  [& args]
  (run-jetty app {:port 8888 :join? true}))
