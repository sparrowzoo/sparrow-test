package com.sparrow.es;

import com.sparrow.support.lucence.KeyAnalyzer;
import com.sparrow.support.lucence.LexemeWithBoost;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.env.Environment;
import org.wltea.analyzer.cfg.Configuration;
import org.wltea.analyzer.lucene.IKAnalyzer;


public class KeyAnalyzerTest {
    static {
        System.setProperty("web.root","D:\\workspace\\sparrow\\sparrow-shell\\sparrow-test\\target");
    }
    //默认加载路径
    private static final String PATH_HOME = "path.home";

    public static void main(String[] args) {
        KeyAnalyzer keyAnalyzer = new KeyAnalyzer();
        // IndexMetaData indexMetaData=new IndexMetaData();
        // IndexSettings indexSettings=new IndexSettings(null,defaultSetting());

        IKAnalyzer ikAnalyzer = new IKAnalyzer(defaultConfig);
        keyAnalyzer.setAnalyzer(ikAnalyzer);
        for (LexemeWithBoost lexeme : keyAnalyzer.getKeyList("我早上早餐喜欢吃康师傅方便面")) {
            System.out.println(lexeme.toString());
        }
    }


    /**
     *  configFile = homeFile.resolve("config");
     *  默认
     *
     *  if (PATH_HOME_SETTING.exists(settings)) {
     *             homeFile = PathUtils.get(PATH_HOME_SETTING.get(settings)).normalize();
     *         } else {
     *             throw new IllegalStateException(PATH_HOME_SETTING.getKey() + " is not configured");
     *         }
     *         path.home 不填会报错
     */
    private static Environment defaultEnv = new Environment(defaultSetting(),null);
    private static Configuration defaultConfig = new Configuration(defaultEnv, defaultSetting());

    private static Settings defaultSetting() {
        String pathHome = System.getProperty("web.root");
        if (null == pathHome || pathHome.trim().isEmpty())
            throw new IllegalArgumentException("load defaultSetting error!" + PATH_HOME + " can't be null!");
        //this.useSmart = settings.get("use_smart", "false").equals("true");
//.put("use_smart",true)
        return Settings.builder().put(PATH_HOME, pathHome + "/classes")
                .build();
    }
}
