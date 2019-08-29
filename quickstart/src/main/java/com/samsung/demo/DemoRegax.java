package com.samsung.demo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ji.zhang on 9/19/17.
 */
public class DemoRegax {
    public static void main(String[] args) {

        // Mod (%) computation
        System.out.println(Math.floorMod(-59, 60));
        System.out.println(Math.floorMod(59, -60));
        System.out.println(Math.floorMod(-59, -60));
        System.out.println(Math.floorMod(59, 60));
        System.out.println("====");
        System.out.println(Math.floorMod(-60, 59));
        System.out.println(Math.floorMod(60, -59));
        System.out.println(Math.floorMod(-60, -59));
        System.out.println(Math.floorMod(60, 59));

        Pattern p = Pattern.compile("(\\d{1,2})");
        Matcher m = p.matcher("12,54,1,65");
        StringBuffer s = new StringBuffer();
        while (m.find())
            m.appendReplacement(s, String.valueOf(3 * Integer.parseInt(m.group(1))));
        System.out.println(s.toString());


        String json = "{\"faceSearchResult\":{\"face_num\":1,\"face_list\":[{\"face_token\":\"cb4d07cc5ec398d6888b8a0c1ed0d3c0\",\"location\":{\"left\":338.92,\"top\":99.17,\"width\":111,\"height\":116,\"rotation\":0},\"user_list\":[{\"group_id\":\"demo529\",\"user_id\":\"demo001\",\"user_info\":\"zhangji\",\"score\":94.959136962891}]}]},\"faceDetectResult\":{\"face_num\":1,\"face_list\":[{\"face_token\":\"cb4d07cc5ec398d6888b8a0c1ed0d3c0\",\"location\":{\"left\":338.92,\"top\":99.17,\"width\":111,\"height\":116,\"rotation\":0},\"face_probability\":1,\"angle\":{\"yaw\":4.77,\"pitch\":-3.14,\"roll\":-4.53},\"age\":25,\"beauty\":42.92,\"expression\":{\"type\":\"none\",\"probability\":1},\"face_shape\":{\"type\":\"square\",\"probability\":0.53},\"gender\":{\"type\":\"male\",\"probability\":1},\"emotion\":{\"type\":\"neutral\",\"probability\":0.5}}]},\"bodyDetectResult\":{\"person_num\":2,\"person_info\":[{\"attributes\":{\"upper_wear_fg\":{\"score\":0.9384064674377441,\"name\":\"衬衫\"},\"cellphone\":{\"score\":0.839216947555542,\"name\":\"未使用手机\"},\"lower_cut\":{\"score\":0.9833630919456482,\"name\":\"有下方截断\"},\"umbrella\":{\"score\":0.9997916221618652,\"name\":\"未打伞\"},\"orientation\":{\"score\":0.9885568618774414,\"name\":\"正面\"},\"is_human\":{\"score\":0.7700353860855103,\"name\":\"正常人体\"},\"headwear\":{\"score\":0.9795424938201904,\"name\":\"无帽\"},\"gender\":{\"score\":0.9884909391403198,\"name\":\"男性\"},\"age\":{\"score\":0.8863770961761475,\"name\":\"青年\"},\"upper_cut\":{\"score\":0.9867532849311829,\"name\":\"无上方截断\"},\"glasses\":{\"score\":0.8125892877578735,\"name\":\"戴眼镜\"},\"lower_color\":{\"score\":0.9052664637565613,\"name\":\"不确定\"},\"bag\":{\"score\":0.9908758401870728,\"name\":\"无背包\"},\"upper_wear_texture\":{\"score\":0.9035210013389587,\"name\":\"纯色\"},\"smoke\":{\"score\":0.9064040780067444,\"name\":\"未吸烟\"},\"vehicle\":{\"score\":0.988170862197876,\"name\":\"无交通工具\"},\"lower_wear\":{\"score\":0.9917014241218567,\"name\":\"不确定\"},\"carrying_item\":{\"score\":0.766636073589325,\"name\":\"无手提物\"},\"upper_wear\":{\"score\":0.9583455920219421,\"name\":\"短袖\"},\"occlusion\":{\"score\":0.8830191493034363,\"name\":\"无遮挡\"},\"upper_color\":{\"score\":0.8887960314750671,\"name\":\"蓝\"}},\"location\":{\"height\":353,\"width\":500,\"top\":40,\"score\":0.9996213912963867,\"left\":31}},{\"attributes\":{\"upper_wear_fg\":{\"score\":0.9631311297416687,\"name\":\"T恤\"},\"cellphone\":{\"score\":0.70948725938797,\"name\":\"未使用手机\"},\"lower_cut\":{\"score\":0.8850736618041992,\"name\":\"有下方截断\"},\"umbrella\":{\"score\":0.9996039867401123,\"name\":\"未打伞\"},\"orientation\":{\"score\":0.5851974487304688,\"name\":\"左侧面\"},\"is_human\":{\"score\":0.5989487767219543,\"name\":\"正常人体\"},\"headwear\":{\"score\":0.9298388957977295,\"name\":\"无帽\"},\"gender\":{\"score\":0.9339067339897156,\"name\":\"男性\"},\"age\":{\"score\":0.8520548343658447,\"name\":\"青年\"},\"upper_cut\":{\"score\":0.7410176992416382,\"name\":\"有上方截断\"},\"glasses\":{\"score\":0.4676331281661987,\"name\":\"无眼镜\"},\"lower_color\":{\"score\":0.5003644227981567,\"name\":\"蓝\"},\"bag\":{\"score\":0.9898664355278015,\"name\":\"无背包\"},\"upper_wear_texture\":{\"score\":0.5754246711730957,\"name\":\"纯色\"},\"smoke\":{\"score\":0.5811298489570618,\"name\":\"未吸烟\"},\"vehicle\":{\"score\":0.9992938041687012,\"name\":\"无交通工具\"},\"lower_wear\":{\"score\":0.742812991142273,\"name\":\"不确定\"},\"carrying_item\":{\"score\":0.79532390832901,\"name\":\"无手提物\"},\"upper_wear\":{\"score\":0.9613993167877197,\"name\":\"短袖\"},\"occlusion\":{\"score\":0.7483961582183838,\"name\":\"无遮挡\"},\"upper_color\":{\"score\":0.9613183736801147,\"name\":\"蓝\"}},\"location\":{\"height\":397,\"width\":145,\"top\":0,\"score\":0.7581886053085327,\"left\":667}}],\"log_id\":3748640680430183204}}";
//        String json = "{\"faceSearchResult\":{},\"faceDetectResult\":{},\"bodyDetectResult\":\"\"}";

        Pattern p2 = Pattern.compile("\"faceDetectResult\":(.*),\"bodyDetectResult\":(.*)\\}");
        Matcher m2 = p2.matcher(json);
        while (m2.find()) {
            System.out.println(m2.group(1));
            System.out.println(m2.group(2));

        }

    }

    private static int gcd(int a, int b) {
        while (b != 0) {
            int c = b;
            b = a % b;
            a = c;
        }
        return a;
    }
}
