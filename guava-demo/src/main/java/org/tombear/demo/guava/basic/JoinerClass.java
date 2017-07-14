package org.tombear.demo.guava.basic;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by ji.zhang on 7/14/17.
 */
public class JoinerClass {

    //Joiner.on("") is immutable

    @Test
    public void SbTest() {
        //追加字符串
        StringBuilder stringBuilder = new StringBuilder();
        Joiner joiner = Joiner.on("|").skipNulls();
        //returns the StringBuilder instance with the values foo,bar,baz appeneded with "|" delimiters
        stringBuilder = joiner.appendTo(stringBuilder, "foo", "bar", "baz");
        System.out.println(stringBuilder);
    }

    @Test
    public void FwTest() throws IOException {
        //追加文件
        File file = new File("fw.txt");//这个是在工程目录下创建，即guava-demo文件夹下创建
//        File file = new File(JoinerClass.class.getClassLoader().getResource("fw.txt").getPath());
        FileWriter fileWriter = new FileWriter(file);
        Date date = new Date();
        Date[] dates = {date, date};
        List<Date> dateList = Lists.asList(date, dates);
        Joiner joiner2 = Joiner.on("#").useForNull(" ");
        //returns the FileWriter instance with the values appended into it
        fileWriter = joiner2.appendTo(fileWriter, dateList);

    }

    @Test
    public void MjTest() {
        //Using LinkedHashMap so that the original order is preserved
        String expectedString = "Washington D.C=Redskins#New York City=Giants#Philadelphia=Eagles#Dallas=Cowboys";
        Map<String, String> testMap = Maps.newLinkedHashMap();
        testMap.put("Washington D.C", "Redskins");
        testMap.put("New York City", "Giants");
        testMap.put("Philadelphia", "Eagles");
        testMap.put("Dallas", "Cowboys");
        String returnedString = Joiner.on("#").
                withKeyValueSeparator("=").join(testMap);
        Assert.assertThat(returnedString, Is.is(expectedString));
    }
}
