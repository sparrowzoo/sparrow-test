package com.sparrow.es;

import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.env.Environment;
import org.springframework.util.StringUtils;
import org.wltea.analyzer.cfg.Configuration;
import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class IKParser {
    static {
        System.setProperty("web.root","D:\\workspace\\sparrow\\sparrow-shell\\sparrow-test\\target");
    }
    public static void main(String[] args) {
        IKParser parser=new IKParser();
        List<Lexeme> lexemes= parser.parseLexeme("中华人民共和国");
        for (Lexeme lexeme:lexemes){
            com.sparrow.support.lucence.LexemeWithBoost lexeme1=new com.sparrow.support.lucence.LexemeWithBoost(lexeme.getOffset(),lexeme.getBegin(),lexeme.getLength(),lexeme.getLexemeType());
            lexeme1.setLexemeText(lexeme.getLexemeText());
            System.out.println(lexeme1);
        }
    }
    private static Settings defaultSetting = defaultSetting();

    private static Environment defaultEnv = new Environment(defaultSetting(),null);
    private static Configuration defaultConfig = new Configuration(defaultEnv, defaultSetting);
    //默认加载路径
    private static final String PATH_HOME = "path.home";
    private static Settings defaultSetting() {
        String pathHome = System.getProperty("web.root");
        if (null == pathHome || pathHome.trim().isEmpty())
            throw new IllegalArgumentException("load defaultSetting error!" + PATH_HOME + " can't be null!");
        return Settings.builder().put(PATH_HOME, pathHome + "/classes").build();
    }

    private static IKSegmenter ikSegmenter = new IKSegmenter(null,defaultConfig);

    public synchronized List<Lexeme> parseLexeme(String text) {
        if (!StringUtils.hasText(text))
            return null;

        Lexeme textLexeme = new Lexeme(0, 0, text.length(), 0);
        textLexeme.setLexemeText(text);
        StringReader reader= new StringReader(text);
        //重置切词器,该方法内部已经线程同步
        ikSegmenter.reset(reader);
        Lexeme token;
        List<String> words =new ArrayList<>();
        String word = "";
        List<Lexeme> orginWord = new ArrayList<>();
        List<Lexeme> lexemeWord =new ArrayList<>();
        try {
            while ((token = ikSegmenter.next()) != null) {
                word = token.getLexemeText();
                if (StringUtils.hasText(word)){
                    orginWord.add(token);
                    words.add(word);
                }
            }
            for(Lexeme lexeme : orginWord){
                lexemeWord.add(lexeme);
            }
            if (lexemeWord.size()==0)
                lexemeWord.add(textLexeme);

        } catch (IOException e) {
            lexemeWord.add(textLexeme);
        }
        return lexemeWord;
    }
}
