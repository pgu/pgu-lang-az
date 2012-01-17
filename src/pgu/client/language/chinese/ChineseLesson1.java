package pgu.client.language.chinese;

import pgu.client.utils.guava.HashTriMap;

public enum ChineseLesson1 {
    INSTANCE;

    private final HashTriMap<String, String, String> latin2chinese = HashTriMap.create();

    {
        latin2chinese.put("I", "w&#335;", "&#25105;");
        latin2chinese.put("you", "n&#301;", "&#20320;");
        latin2chinese.put("he", "t&#227;", "&#20182;");
        latin2chinese.put("she", "t&#227;", "&#22905;");
        latin2chinese.put("nous", "w&#335;men", "&#25105;&#20204;");
        latin2chinese.put("vous", "n&#301;men", "&#20320;&#20204;");
        latin2chinese.put("ils", "t&#227;men", "&#20182;&#20204;");
        latin2chinese.put("elles", "t&#227;men", "&#22905;&#20204;");
    }

}
