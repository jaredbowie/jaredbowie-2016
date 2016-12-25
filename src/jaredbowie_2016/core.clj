(ns jaredbowie-2016.core
  (:require [hiccup.core :refer [html]]))

(def index "target/site/index.html")
(def blogdir "target/site/blog/")


(defn categories []
  (html [:ul
         [:li [:a {:href "#"} "something generated"]]
         [:li [:a {:href "#"} "Web Development"]]
         [:li [:a {:href "#"} "SEO"]]]))

(defn recent []
  (html
   [:ul
    [:li
     [:a
      {:href "#"}
      "Adaptive Vs. Responsive Layouts And Optimal Text Readability"]]
    [:li [:a {:href "#"} "Web Design is 95% Typography"]]
    [:li [:a {:href "#"} "Paper by FiftyThree"]]]))

(defn music []
  (html
   [:ul
         [:li [:a {:href "#"} "November 2014"]]
         [:li [:a {:href "#"} "September 2014"]]
         [:li [:a {:href "#"} "January 2013"]]]))


(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))


(defn test-it []
  (spit index
        (html
[:html
  [:head
   [:title "Black & White"]
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
           [:a {:data-hover "Home", :href "index.html"} "Blog"]]
          [:li.cl-effect-11
           [:a {:data-hover "Blog", :href "full-width.html"} "Music"]]
         #_ [:li.cl-effect-11
           [:a {:data-hover "About", :href "about.html"} "About"]]
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
      [:main.col-md-8
       [:article.post.post-1
        [:header.entry-header
         [:h1.entry-title
          [:a
           {:href "single.html"}
           "Adaptive Vs. Responsive Layouts And Optimal Text Readability"]]
         [:div.entry-meta
          [:span.post-category [:a {:href "#"} "Web Design"]]
          [:span.post-date
           [:a
            {:href "#"}
            [:time.entry-date
             {:datetime "2012-11-09T23:15:57+00:00"}
             "February 2, 2013"]]]
          [:span.post-author [:a {:href "#"} "Albert Einstein"]]
          [:span.comments-link [:a {:href "#"} "4 Comments"]]]]
        [:div.entry-content.clearfix
         [:p
          "Responsive web design offers us a way forward, finally allowing us to design for the ebb and flow of things. There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don’t look even slightly."]
         [:div.read-more.cl-effect-14
          [:a.more-link
           {:href "single.html"}
           "Continue reading "
           [:span.meta-nav "→"]]]]]
       [:article.post.post-2
        [:header.entry-header
         [:h1.entry-title
          [:a
           {:href "single.html"}
           "Adaptive Vs. Responsive Layouts And Optimal Text Readability"]]
         [:div.entry-meta
          [:span.post-category [:a {:href "#"} "Web Design"]]
          [:span.post-date
           [:a
            {:href "#"}
            [:time.entry-date
             {:datetime "2012-11-09T23:15:57+00:00"}
             "February 2, 2013"]]]
          [:span.post-author [:a {:href "#"} "Albert Einstein"]]
          [:span.comments-link [:a {:href "#"} "4 Comments"]]]]
        [:div.entry-content.clearfix
         [:p
          "Responsive web design offers us a way forward, finally allowing us to design for the ebb and flow of things. There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don’t look even slightly."]
         [:div.read-more.cl-effect-14
          [:a.more-link
           {:href "single.html"}
           "Continue reading "
           [:span.meta-nav "→"]]]]]
       [:article.post.post-3
        [:header.entry-header
         [:h1.entry-title
          [:a
           {:href "single.html"}
           "Adaptive Vs. Responsive Layouts And Optimal Text Readability"]]
         [:div.entry-meta
          [:span.post-category [:a {:href "#"} "Web Design"]]
          [:span.post-date
           [:a
            {:href "#"}
            [:time.entry-date
             {:datetime "2012-11-09T23:15:57+00:00"}
             "February 2, 2013"]]]
          [:span.post-author [:a {:href "#"} "Albert Einstein"]]
          [:span.comments-link [:a {:href "#"} "4 Comments"]]]]
        [:div.entry-content.clearfix
         [:p
          "Responsive web design offers us a way forward, finally allowing us to design for the ebb and flow of things. There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don’t look even slightly."]
         [:div.read-more.cl-effect-14
          [:a.more-link
           {:href "single.html"}
           "Continue reading "
           [:span.meta-nav "→"]]]]]
       [:article.post.post-4
        [:header.entry-header
         [:h1.entry-title
          [:a
           {:href "single.html"}
           "Adaptive Vs. Responsive Layouts And Optimal Text Readability"]]
         [:div.entry-meta
          [:span.post-category [:a {:href "#"} "Web Design"]]
          [:span.post-date
           [:a
            {:href "#"}
            [:time.entry-date
             {:datetime "2012-11-09T23:15:57+00:00"}
             "February 2, 2013"]]]
          [:span.post-author [:a {:href "#"} "Albert Einstein"]]
          [:span.comments-link [:a {:href "#"} "4 Comments"]]]]
        [:div.entry-content.clearfix
         [:p
          "Responsive web design offers us a way forward, finally allowing us to design for the ebb and flow of things. There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don’t look even slightly."]
         [:div.read-more.cl-effect-14
          [:a.more-link
           {:href "single.html"}
           "Continue reading "
           [:span.meta-nav "→"]]]]]]
      [:aside.col-md-4
       [:div.widget.widget-recent-posts
        "\t\t\n\t\t\t\t\t\t\t"
        [:h3.widget-title "Recent Posts"]
        "\t\t\n\t\t\t\t\t\t\t"
         (recent)]
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
