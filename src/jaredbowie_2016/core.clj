(ns jaredbowie-2016.core
  (:require [hiccup.core :refer [html]]
            [me.raynes.fs :as fs]
            [clj-time.core :as t]
            [clj-time.coerce :as tc]
            [clojure.string :as string]
            [jaredbowie-2016.blog :refer :all]
            [jaredbowie-2016.pageparts :refer :all]))


;;(def root-relative "/Users/jared/clojureprojects/jaredbowie-2016/target/site")
(def blog-source "/Users/jared/clojureprojects/jaredbowie-2016-data/blog/")
(def music-source "/Users/jared/clojureprojects/jaredbowie-2016-data/music/")
(def links-file "/Users/jared/clojureprojects/jaredbowie-2016-data/links-file")
(def index "/index.html")

;;(def blogdir "target/site/blog/")
;;(def catdir "target/site/blog/cat/")
;;(def musicdir "target/site/music/")


;;(def custom-formatter (tf/formatter "yyyyMMdd"))




(defn capitalize-words
  "Capitalize every word in a string assuming string is like-this-finish"
  [s]
  (->> (string/split (str s) #"_")
       (map string/capitalize)
       (string/join " ")))

(defn blog-info []
  (let [dir-list (fs/list-dir blog-source)]
    (map (fn [one-file]
           (let [full-name (fs/name one-file)
                 c-date (subs full-name 0 10)
                 name-no-date (subs full-name 11 (.length full-name))
                 title (capitalize-words name-no-date)
                 content (slurp one-file)
                 content-split (string/split-lines content)
                 tag (if (= (subs (first content-split) 0 3) "tag")
                       (subs (first content-split) 4 (count (first content-split)) )
                       nil)
                 content-final (if tag
                                 (drop 1 content-split)
                                 content)
                 m-date (tc/from-long (fs/mod-time (str one-file)))
                 href (str name-no-date ".html")]
             {:tag tag
              :filename (str name-no-date ".html")
              :href (str root-relative "/blog/posts/"  name-no-date ".html")
              :content content-final
              :c-date c-date
              :title title
              :m-date m-date}))
         dir-list)))

(defn music-info []
  (let [dir-list (fs/list-dir music-source)]
    (map (fn [one-file]
           (let [full-name (fs/name one-file)
                 c-date (subs full-name 0 10)
                 name-no-date (subs full-name 11 (.length full-name))
                 title (capitalize-words name-no-date)
                 content-first (slurp one-file)
                 content-split (string/split-lines content-first)
                 tag (if (= (subs (first content-split) 0 3) "tag")
                       (subs (first content-split) 4 (count (first content-split)) )
                       nil)
                 youtube-url (if tag
                               (apply str (drop 1 content-split))
                               content-first)
                 content [:iframe {:width "560" :height "315" :src youtube-url :allowfullscreen "allowfullscreen"}]
                 m-date (tc/from-long (fs/mod-time (str one-file)))
                 href "nil.html"]
  ;;           (println youtube-url)
             {:tag tag :href (str root-relative "/music/" name-no-date ".html") :content content :c-date c-date :title title :m-date m-date}))

         dir-list)))

(defn create-links-page [blog]
  (spit (str root-relative "/links.html")
   (let [links (slurp links-file)]
     (html
      [:html
       (page-head)
       [:body
        [:div.container
         "\t\n\t\t\t"
         (site-header)]
        [:div.content-body
         [:div.container
          [:div.row
           [:main.col-md-8
            [:article.post.post-1
             [:header.entry-header
              [:h1.entry-title
               "Links"]]
             [:div.entry-content.clearfix
              links]]]
           (side-widget blog)]]]
        [:footer#site-footer
         [:div.container
          [:div.row
           ;;[:div.col-md-12 [:p.copyright "© 2014 ThemeWagon.com"]]
           ]]]
        "<!-- Mobile Menu -->"
        (mobile-menu)]]))))

(defn create-categories [blog]
  (let [category-set (set (remove #(nil? %) (map #(:tag %) blog)))]
;;    (println (str "cat-set " category-set))
    (doseq [one-cat category-set]
;;      (println one-cat)
      (let [all-tag (sort-by :c-date (filter #(= (:tag %) one-cat) blog))
            cat-title (capitalize-words one-cat)]
;;        (println (str "spitting " (str catdir one-cat ".html")))
        (spit (str root-relative "/blog/cat/" one-cat ".html")
              (html
               [:html
                (page-head)
                [:body
                 [:div.container
                  "\t\n\t\t\t"
                  (site-header)]
                 [:div.content-body
                  [:div.container
                   [:div.row
                    [:main.col-md-8
                     [:article.post.post-1
                      [:header.entry-header
                       [:h1.entry-title
                        cat-title]]
                      [:div.entry-content.clearfix
                       [:ul
                        (for [one-page all-tag]
                          [:li [:a {:href (:href one-page)} (:title one-page)]])]]]]
                    (side-widget blog)]]]
                 [:footer#site-footer
                  [:div.container
                   [:div.row
                    ;;[:div.col-md-12 [:p.copyright "© 2014 ThemeWagon.com"]]
                    ]]]
                 "<!-- Mobile Menu -->"
                 (mobile-menu)]]))))))

(defn create-links [blog]
  (let [category-set (set (remove #(nil? %) (map #(:tag %) blog)))]
;;    (println (str "cat-set " category-set))
    (doseq [one-cat category-set]
;;      (println one-cat)
      (let [all-tag (sort-by :c-date (filter #(= (:tag %) one-cat) blog))
            cat-title (capitalize-words one-cat)]
;;        (println (str "spitting " (str catdir one-cat ".html")))
        (spit (str root-relative "/blog/cat/" one-cat ".html")
              (html
               [:html
                (page-header)
                [:body
                 [:div.container
                  "\t\n\t\t\t"
                  (site-header)]
                 [:div.content-body
                  [:div.container
                   [:div.row
                    [:main.col-md-8
                     [:article.post.post-1
                      [:header.entry-header
                       [:h1.entry-title
                        cat-title]]
                      [:div.entry-content.clearfix
                       [:ul
                        (for [one-page all-tag]
                          [:li [:a {:href (:href one-page)} (:title one-page)]])]]]]
                    (site-side blog)]]]
                 [:footer#site-footer
                  [:div.container
                   [:div.row
                    ;;[:div.col-md-12 [:p.copyright "© 2014 ThemeWagon.com"]]
                    ]]]
                 "<!-- Mobile Menu -->"
                 (mobile-menu)]]))))))

(defn make-one-blog-post [blog article-filename article-title article-c-date article-tag article-content article-href]
  ;;(println "spitting " (str blogdir article-filename))
  (spit (str root-relative "/blog/posts/" article-filename)
        (html
         [:html
          (page-head)
  [:body
   [:div.container
    "\t\n\t\t\t"
    (site-header)]
   [:div.content-body
    [:div.container
     [:div.row
      [:main.col-md-8
       [:article.post.post-1
        [:header.entry-header
         [:h1.entry-title
          [:a
           {:href article-href}
           article-title]]
         [:div.entry-meta
          [:span.post-category [:a {:href article-href} article-tag]]
          [:span.post-date
           [:time.entry-date
            article-c-date]]]]
        [:div.entry-content.clearfix
         [:p
          article-content]]]]
      (side-widget blog)]]]
   [:footer#site-footer
    [:div.container
     [:div.row
      ;;[:div.col-md-12 [:p.copyright "© 2014 ThemeWagon.com"]]
      ]]]
   "<!-- Mobile Menu -->"
   (mobile-menu)]])))




(defn front-music [blog page-info previous-page next-page numbers]
  (let [page-date (reverse (sort-by :date page-info))]
    (html [:main.col-md-8
           [:article.post.post-1
            [:header.entry-header
             [:h1.entry-title
              (:title (nth page-date (nth numbers 0)))]
             [:div.entry-meta
              [:span.post-category
               (:tag (nth page-date (nth numbers 0)))]
              [:span.post-date
               [:time.entry-date
                (:c-date (nth page-date (nth numbers 0)))]]]]
            [:div.entry-content.clearfix
             [:p
              (:content (nth page-date (nth numbers 0)))]]]
           (if (> (count numbers) 1)
             [:article.post.post-2
              [:header.entry-header
               [:h1.entry-title
                (:title (nth page-date (nth numbers 1)))]
               [:div.entry-meta
                [:span.post-category
                 (:tag (nth page-date (nth numbers 1)))]
                [:span.post-date
                 [:time.entry-date
                  (:c-date (nth page-date (nth numbers 1)))]]]]
              [:div.entry-content.clearfix
               [:p
                (:content (nth page-date (nth numbers 1)))]]])
           (if (> (count numbers) 2)
             [:article.post.post-3
              [:header.entry-header
               [:h1.entry-title
                (:title (nth page-date (nth numbers 2)))]
               [:div.entry-meta
                [:span.post-category
                 (:tag (nth page-date (nth numbers 2)))]
                [:span.post-date
                 [:time.entry-date
                  (:c-date (nth page-date (nth numbers 2)))]]]]
              [:div.entry-content.clearfix
               [:p
                (:content (nth page-date (nth numbers 2)))]]])
           (if (> (count numbers) 3) (do
;;                                       (println "here")
 ;;                                      (println (first (nth blog-date 3)))
                                       [:article.post.post-4
                                        [:header.entry-header
                                         [:h1.entry-title
                                          (:title (nth page-date (nth numbers 3)))]
                                         [:div.entry-meta
                                          [:span.post-category
                                           (:tag (nth page-date (nth numbers 3)))]
                                          [:span.post-date
                                           [:time.entry-date
                                            (:c-date (nth page-date (nth numbers 3)))]]]]
                                        [:div.entry-content.clearfix
                                         [:p
                                          (:content (nth page-date (nth numbers 3)))]]
                                        ]))

           (if (not= nil previous-page)
             [:a {:href previous-page} [:div "Backward (in time)"]])
           (if (not= nil next-page)
             [:a {:href next-page} [:div "Forward (in time)"]])])))

(defn front-blog [blog previous-page next-page numbers]
  (let [blog-date (reverse (sort-by :date blog))]
    (html [:main.col-md-8
           (let [article-href (:href (nth blog-date (nth numbers 0)))
                 article-title (:title (nth blog-date (nth numbers 0)))
                 article-c-date (:c-date (nth blog-date (nth numbers 0)))
                 article-tag (:tag (nth blog-date (nth numbers 0)))
                 article-content (:content (nth blog-date (nth numbers 0)))
                 article-filename (:filename (nth blog-date (nth numbers 0)))]
             (make-one-blog-post blog article-filename article-title article-c-date article-tag article-content article-href)
             [:article.post.post-1
              [:header.entry-header
               [:h1.entry-title
                [:a
                 {:href article-href}
                 article-title]]
               [:div.entry-meta
                [:span.post-category [:a {:href article-href} article-tag]]
                [:span.post-date
                 [:time.entry-date
                  article-c-date]]]]
              [:div.entry-content.clearfix
               [:p
                article-content]]])
           (if (> (count numbers) 1)
             (let [article-href (:href (nth blog-date (nth numbers 1)))
                   article-title (:title (nth blog-date (nth numbers 1)))
                   article-c-date (:c-date (nth blog-date (nth numbers 1)))
                   article-tag (:tag (nth blog-date (nth numbers 1)))
                   article-content (:content (nth blog-date (nth numbers 1)))
                   article-filename (:filename (nth blog-date (nth numbers 1)))]
               (make-one-blog-post blog article-filename article-title article-c-date article-tag article-content article-href)
               [:article.post.post-2
                [:header.entry-header
                 [:h1.entry-title
                  [:a
                   {:href article-href}
                   article-title]]
                 [:div.entry-meta
                  [:span.post-category [:a {:href "#"} article-tag]]
                  [:span.post-date
                   [:time.entry-date
                    article-c-date ]]]]
                [:div.entry-content.clearfix
                 [:p
                  article-content]]]))
           (if (> (count numbers) 2)
             (let [article-href (:href (nth blog-date (nth numbers 2)))
                   article-title (:title (nth blog-date (nth numbers 2)))
                   article-c-date (:c-date (nth blog-date (nth numbers 2)))
                   article-tag (:tag (nth blog-date (nth numbers 2)))
                   article-content (:content (nth blog-date (nth numbers 2)))
                   article-filename (:filename (nth blog-date (nth numbers 2)))]
               (make-one-blog-post blog article-filename article-title article-c-date article-tag article-content article-href)
               [:article.post.post-3
                [:header.entry-header
                 [:h1.entry-title
                  [:a
                   {:href article-href}
                   article-title]]
                 [:div.entry-meta
                  [:span.post-category [:a {:href "#"} article-tag]]
                  [:span.post-date
                   [:time.entry-date
                    article-c-date]]]]
                [:div.entry-content.clearfix
                 [:p
                  article-content]]]))
           (if (> (count numbers) 3)
             (let [article-href (:href (nth blog-date (nth numbers 3)))
                   article-title (:title (nth blog-date (nth numbers 3)))
                   article-c-date (:c-date (nth blog-date (nth numbers 3)))
                   article-tag (:tag (nth blog-date (nth numbers 3)))
                   article-content (:content (nth blog-date (nth numbers 3)))
                   article-filename (:filename (nth blog-date (nth numbers 3)))]
               (make-one-blog-post blog article-filename article-title article-c-date article-tag article-content article-href)
               [:article.post.post-4
                [:header.entry-header
                 [:h1.entry-title
                  [:a
                   {:href article-href}
                   article-title]]
                 [:div.entry-meta
                  [:span.post-category [:a {:href "#"} article-tag]]
                  [:span.post-date
                   [:time.entry-date
                    article-c-date]]]]
                [:div.entry-content.clearfix
                 [:p
                  article-content]]]))
           (if (not= nil previous-page)
             [:a {:href previous-page} [:div "Backward (in time)"]])
           (if (not= nil next-page)
             [:a {:href next-page} [:div "Forward (in time)"]])])))

(defn create-page [blog
                   page-info
                   to-file
                   page-name
                   & [previous-page
                      next-page
                      numbers]]
  "page-name blog, music, links will determine what to display in bottom left section"
  (spit to-file
        (html
         [:html
          (page-head)
          [:body
           [:div.container
            "\t\n\t\t\t"
            (site-header)]
           [:div.content-body
            [:div.container
             [:div.row
              (cond (= page-name "blog") (front-blog blog previous-page next-page numbers)
                    (= page-name "music") (front-music blog page-info previous-page next-page numbers))
              (side-widget blog)]]]
           [:footer#site-footer
            [:div.container
             [:div.row]]]
           (mobile-menu)]])))


(defn -main
  "main"
  [& args]
  (let [blog (sort-by :c-date (blog-info))
        blog-entry-count (count blog)
        blog-entry-count-fours (/ blog-entry-count 4)
        blog-entry-range (range 1 (+ 1 blog-entry-count-fours))]
;;;;create links page

    (create-links-page blog)
;;;;create categories page

    (create-categories blog)

;;;create music page


    (let [music-list (sort-by :c-date (music-info))
          music-entry-count (count music-list)
          music-entry-count-tens (/ music-entry-count 10)
          music-entry-range (range 1 (+ 1 music-entry-count-tens))]
      (doseq [x music-entry-range]
        (let [
              page-name (if (= 1 x)
                          (str root-relative "/music" index)
                          (str root-relative "/music/" "page" x ".html"))
              previous-page (if (< x music-entry-count-tens)
                              (str root-relative "/music/" "page" (+ 1 x) ".html")
                              nil)
              next-page (cond
                          (= 1 x) nil
                          (= 2 x) index
                          (> 2 x) (str root-relative "/music/" "page" (- x 1) ".html"))]
          ;;(println previous-page)
          ;;(println x)
          ;;(println music-entry-count-tens)
          ;;(println (str "music-list " (first music-list)))
          (let [numbers (range (* (- x 1) 10) (+ 10 (* (- x 1) 10)))]
            ;;(0 1 2 3)
            (if (> (last numbers) music-entry-count)
              (create-page blog music-list page-name "music" previous-page next-page (range  (* (- x 1) 10) music-entry-count) )
              (create-page blog music-list  page-name "music" previous-page next-page numbers))))))

      ;;;create blog page
    (doseq [x blog-entry-range]
      (let [page-name (if (= 1 x)
                        (str root-relative index)
                        (str root-relative "/blog/pages/" "page" x ".html"))
            previous-page (if (< x blog-entry-count-fours)
                        (str root-relative "/blog/pages/" "page" (+ 1 x) ".html")
                        nil)
            next-page (cond
                        (= 1 x) nil
                        (= 2 x) (str root-relative index)
                        (> 2 x) (str root-relative "/blog/pages/" "page" (- x 1) ".html"))]
        ;;(println previous-page)
        ;;(println x)
        ;;(println blog-entry-count-fours)
        (let [numbers (range (* (- x 1) 4) (+ 4 (* (- x 1) 4)))]
          ;;(0 1 2 3)
          (if (> (last numbers) blog-entry-count)
            (create-page blog nil page-name "blog" previous-page next-page (range  (* (- x 1) 4) blog-entry-count) )
            (create-page blog nil page-name "blog" previous-page next-page numbers)))))))
