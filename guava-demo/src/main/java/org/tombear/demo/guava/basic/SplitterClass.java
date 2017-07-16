package org.tombear.demo.guava.basic;

import com.google.common.base.Splitter;
import com.google.common.collect.Maps;
import org.junit.Test;

import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by ji.zhang on 7/14/17.
 */
public class SplitterClass {

    //Splitter.on("") is immutable
    //基本的字符分割器定义
    Splitter splitter1 = Splitter.on('|');
    Splitter splitter2 = Splitter.on("\\d+");//split on one or more consecutive digits embedded in a string
    Splitter splitter3 = Splitter.on('|').trimResults();

    @Test
    public void SmTest() {
        String startString = "Washington D.C=Redskins#New York City=Giants#Philadelphia=Eagles#Dallas=Cowboys";
        Map<String, String> testMap = Maps.newLinkedHashMap();
        testMap.put("Washington D.C", "Redskins");
        testMap.put("New York City", "Giants");
        testMap.put("Philadelphia", "Eagles");
        testMap.put("Dallas", "Cowboys");
        //字符串分割器，把字符串分割生成Map数据结构
        Splitter.MapSplitter mapSplitter = Splitter.on("#").withKeyValueSeparator("=");
        Map<String, String> splitMap = mapSplitter.split(startString);
        assertThat(testMap, is(splitMap));
    }

}
