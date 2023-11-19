package com.commercial.gestion.controller;

import com.commercial.gestion.model.Article;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class ArticleController {
    @GetMapping("/articles")
    public ArrayList<Article> allArticles() {
        return Article.allArticle();
    }
}
