(ns jaredbowie-2016.core
  (:require [hiccup.core :refer [html]]
            [me.raynes.fs :as fs]
            [clj-time.core :as t]
            [clj-time.coerce :as tc]
            [clojure.string :as string]
            ;;[clj-time.formatter :as tf]
            ))


(def blog-source "/Users/jared/clojureprojects/jaredbowie-2016-data/blog/")
(def index "target/site/index.html")
(def blogdir "target/site/blog/")

;;(def custom-formatter (tf/formatter "yyyyMMdd"))


(defn capitalize-words
  "Capitalize every word in a string assuming string is like-this-finish"
  [s]
  (->> (string/split (str s) #"-")
       (map string/capitalize)
       (string/join " ")))

(defn blog-info []
  (let [dir-list (fs/list-dir blog-source)]
    (map (fn [one-file]
           (let [full-name (fs/name one-file)
                 c-date (subs full-name 0 10)
                 title (capitalize-words (subs full-name 11 (.length full-name)))
                 content (slurp one-file)
                 m-date (tc/from-long (fs/mod-time (str one-file)))
                 href "nil.html"
                 tag "tag"]
             {:tag tag :href href :content content :c-date c-date :title title :m-date m-date}))
           dir-list)))

(defn categories []
  (html [:ul
         [:li [:a {:href "#"} "something generated"]]
         [:li [:a {:href "#"} "Web Development"]]
         [:li [:a {:href "#"} "SEO"]]]))

(defn recent [blog]
  (let [blog-date (reverse (sort-by :date blog))]
    (html
     [:ul
      [:li [:a {:href "#"}
            (:title (nth blog-date 0))]]
      [:li [:a {:href "#"} (:title (nth blog-date 1))]]
      [:li [:a {:href "#"} (:title (nth blog-date 2))]]])))

(defn music []
  (html
   [:ul
         [:li [:a {:href "#"} "November 2014"]]
         [:li [:a {:href "#"} "September 2014"]]
         [:li [:a {:href "#"} "January 2013"]]]))

(defn front-blog [blog]
  (let [blog-date (reverse (sort-by :date blog))]
    (html [:main.col-md-8
           [:article.post.post-1
            [:header.entry-header
             [:h1.entry-title
              [:a
               {:href (:href (nth blog-date 0))}
               (:title (nth blog-date 0))]]
             [:div.entry-meta
              [:span.post-category [:a {:href "#"} (:tag (nth blog-date 0))]]
              [:span.post-date
               [:a
                {:href "#"}
                [:time.entry-date
                 (:c-date (nth blog-date 0))]]]]]
            [:div.entry-content.clearfix
             [:p
              (:content (nth blog-date 0))]]]
           [:article.post.post-2
            [:header.entry-header
             [:h1.entry-title
              [:a
               {:href (:href (nth blog-date 1))}
               (:title (nth blog-date 1))]]
             [:div.entry-meta
              [:span.post-category [:a {:href "#"} (:tag (nth blog-date 1))]]
              [:span.post-date
               [:a
                {:href "#"}
                [:time.entry-date
                 (:c-date (nth blog-date 1))]]]]]
            [:div.entry-content.clearfix
             [:p
              (:content (nth blog-date 1))]]]
           [:article.post.post-3
            [:header.entry-header
             [:h1.entry-title
              [:a
               {:href (:href (nth blog-date 2))}
               (:title (nth blog-date 2))]]
             [:div.entry-meta
              [:span.post-category [:a {:href "#"} (:tag (nth blog-date 2))]]
              [:span.post-date
               [:a
                {:href "#"}
                [:time.entry-date
                 (:c-date (nth blog-date 2))]]]]]
            [:div.entry-content.clearfix
             [:p
              (:content (nth blog-date 2))]]]
#_           [:article.post.post-4
            [:header.entry-header
             [:h1.entry-title
              [:a
               {:href (:href (nth blog-date 3))}
               (:title (nth blog-date 3))]]
             [:div.entry-meta
              [:span.post-category [:a {:href "#"} (:tag (nth blog-date 3))]]
              [:span.post-date
               [:a
                {:href "#"}
                [:time.entry-date
                 (:c-date (nth blog-date 3))]]]]]
            [:div.entry-content.clearfix
             [:p
              (:content (nth blog-date 3))]]]])))



(defn index-html [blog]
  (spit index
        (html
         [:html
          [:head
   [:title "Jared Bowie's Site"]
   "<!-- meta -->"
   [:meta {:charset "UTF-8"}]
   [:meta
    {:content "width=device-width, initial-scale=1", :name "viewport"}]
   "<!-- css -->"
   [:link {:href "css/bootstrap.min.css", :rel "stylesheet"}]
   [:link {:href "css/ionicons.min.css", :rel "stylesheet"}]
   [:link {:href "css/pace.css", :rel "stylesheet"}]
   [:link {:href "css/custom.css", :rel "stylesheet"}]
   "<!-- js -->"
  ;; [:script {:src "js/jquery-2.1.3.min.js"}]
  ;; [:script {:src "js/bootstrap.min.js"}]
  ;; [:script {:src "js/pace.min.js"}]
  ;; [:script {:src "js/modernizr.custom.js"}]
   ]
  [:body
   [:div.container
    "\t\n\t\t\t"
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
           [:a {:data-hover "Home", :href "index.html"} "Home"]]
          [:li.cl-effect-11
           [:a {:data-hover "Music", :href "music.html"} "Music"]]
         [:li.cl-effect-11
           [:a {:data-hover "Links", :href "links.html"} "Link"]]
         #_ [:li.cl-effect-11
           [:a
            {:data-hover "Contact", :href "contact.html"}
            "Contact"]]]]
        "<!-- /.navbar-collapse -->"]
       #_[:div#header-search-box
        [:a#search-menu
         {:href "#"}
         [:span#search-icon.ion-ios-search-strong]]
        [:div#search-form.search-form
         [:form#searchform
          {:action "#", :method "get", :role "search"}
          [:input
           {:required "required",
            :placeholder "Search",
            :type "search"}]
          [:button {:type "submit"} [:span.ion-ios-search-strong]]]
         "\t\t\t\t\n\t\t\t\t\t\t\t"]]]
      "<!-- col-md-8 -->"]]]
   [:div.content-body
    [:div.container
     [:div.row
      (front-blog blog)
      [:aside.col-md-4
       [:div.widget.widget-recent-posts
        "\t\t\n\t\t\t\t\t\t\t"
        [:h3.widget-title "Recent Posts"]
        "\t\t\n\t\t\t\t\t\t\t"
         (recent blog)]
       [:div.widget.widget-archives
        "\t\t\n\t\t\t\t\t\t\t"
        [:h3.widget-title "Music"]
        "\t\t\n\t\t\t\t\t\t\t"
        (music)]
       [:div.widget.widget-category
        "\t\t\n\t\t\t\t\t\t\t"
        [:h3.widget-title "Category"]
        "\t\t\n\t\t\t\t\t\t\t"
        (categories)]]]]]
   [:footer#site-footer
    [:div.container
     [:div.row
      ;;[:div.col-md-12 [:p.copyright "© 2014 ThemeWagon.com"]]
      ]]]
   "<!-- Mobile Menu -->"
   [:div.overlay.overlay-hugeinc
    [:button.overlay-close
     {:type "button"}
     [:span.ion-ios-close-empty]]
    [:nav
     [:ul
      [:li [:a {:href "index.html"} "Blog"]]
      [:li [:a {:href "full-width.html"} "Music"]]
#_      [:li [:a {:href "about.html"} "About"]]
  #_    [:li [:a {:href "contact.html"} "Contact"]]]]]]])))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (let [blog (blog-info)]
    (println blog)
       (index-html blog
     )))
