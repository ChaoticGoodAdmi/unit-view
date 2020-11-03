package com.example.unitview.util;

public class UnitUtils {
    public static int convertArticleToId(String article) {
        return article != null && article.trim().length() > 0 ?
                Integer.parseInt(article.substring(0, article.indexOf("/"))) : 0;
    }

}
