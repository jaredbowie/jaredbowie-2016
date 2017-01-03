(ns jaredbowie-2016.pageparts
    (:require [hiccup.core :refer [html]]
            [me.raynes.fs :as fs]
            [clj-time.core :as t]
            [clj-time.coerce :as tc]
            [clojure.string :as string]))

(def root-relative "/Users/jared/clojureprojects/jaredbowie-2016/target/site")
(def spotify-url "https://embed.spotify.com/?uri=spotify%3Auser%3Aonetwothreedog%3Aplaylist%3A7FKiE3NTpVTT32l3vPRwLv")

(def bootstrapmin (str root-relative "/css/bootstrap.min.css"))
(def ionicons (str root-relative "/css/ionicons.min.css"))
(def pace (str root-relative "/css/pace.css"))
(def custom (str root-relative "/css/custom.css"))

(defn music []
  (html
   [:iframe {:src spotify-url :width "300" :height "380" :frameborder "0" :allowtransparency "true"}]))

(defn categories [blog]
  (let [all-tags (set (map #(:tag %) blog))]
;;    (println all-tags)
    (html [:ul
           (for [one-tag all-tags]
             [:li [:a {:href (str root-relative "/blog/cat/" one-tag ".html")} one-tag]])])))

(defn recent [blog]
  (let [blog-date (reverse (sort-by :date blog))]
    (html
     [:ul
      [:li [:a {:href (:href (nth blog-date 0))} (:title (nth blog-date 0))]]
      [:li [:a {:href (:href (nth blog-date 1))} (:title (nth blog-date 1))]]
      [:li [:a {:href (:href (nth blog-date 2))} (:title (nth blog-date 2))]]])))

(defn side-widget [blog]
  [:aside.col-md-4
       [:div.widget.widget-recent-posts
        "\t\t\n\t\t\t\t\t\t\t"
        [:h3.widget-title "Recent Posts"]
        "\t\t\n\t\t\t\t\t\t\t"
        (recent blog)]
       [:div.widget.widget-category
        "\t\t\n\t\t\t\t\t\t\t"
        [:h3.widget-title "Category"]
        "\t\t\n\t\t\t\t\t\t\t"
        (categories blog)]
       [:div.widget.widget-archives
        "\t\t\n\t\t\t\t\t\t\t"
        [:h3.widget-title "Music"]
        "\t\t\n\t\t\t\t\t\t\t"
        (music)]])

(defn mobile-menu []
  (html
   [:div.overlay.overlay-hugeinc
    [:button.overlay-close
     {:type "button"}
     [:span.ion-ios-close-empty]]
    [:nav
     [:ul
      [:li [:a {:href "index.html"} "Blog"]]
      [:li [:a {:href "full-width.html"} "Music"]]
#_      [:li [:a {:href "about.html"} "About"]]
  #_    [:li [:a {:href "contact.html"} "Contact"]]]]]))

(defn page-head []
  (html
   [:head
    [:title "Jared Bowie's Site"]
    "<!-- meta -->"
    [:meta {:charset "UTF-8"}]
    [:meta
     {:content "width=device-width, initial-scale=1", :name "viewport"}]
    "<!-- css -->"
    [:link {:href (str bootstrapmin) :rel "stylesheet"}]
    [:link {:href (str ionicons) :rel "stylesheet"}]
    [:link {:href (str pace) :rel "stylesheet"}]
    [:link {:href (str custom) :rel "stylesheet"}]]))


(defn site-header []
  (html
   [:header#site-header
    [:div.row
     [:div.col-md-4.col-sm-5.col-xs-8
      [:div.logo
       [:h1 [:a {:href "index.html"} [:b "Jared"] " Bowie"]]]]
     "<!-- col-md-4 -->"
     [:div.col-md-8.col-sm-7.col-xs-4
      [:nav.main-nav
       {:role "navigation"}
       [:div.navbar-header
        [:button#trigger-overlay.navbar-toggle
         {:type "button"}
         [:span.ion-navicon]]]
       [:div#bs-example-navbar-collapse-1.collapse.navbar-collapse
        [:ul.nav.navbar-nav.navbar-right
         [:li.cl-effect-11
          [:a {:data-hover "Home", :href (str root-relative "/index.html")} "Home"]]
         [:li.cl-effect-11
          [:a {:data-hover "Music", :href (str root-relative "/music/index.html")} "Music"]]
         [:li.cl-effect-11
          [:a {:data-hover "Links", :href (str root-relative "/links.html")} "Link"]]]]
       "<!-- /.navbar-collapse -->"]]
     "<!-- col-md-8 -->"]]))
