package com.samsung.demo;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;


/**
 * Util for processing properties
 *
 * Created by ji.zhang on 8/28/18.
 */
public class PropertiesUtil {

    public static Properties retrieveProps(String propsName) {
        URL resource = PropertiesUtil.class.getClassLoader().getResource(propsName);
        if (resource != null) {
            Properties props = new Properties();
            try (InputStream is = resource.openStream()) {
                props.load(is);
                return props;
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("Load properties failure! " + propsName);
            }
        } else {
            throw new RuntimeException("Can't find configFile" + propsName);
        }
    }

    public static Properties getPropertiesForPerfix(String perfix, Properties props) {
        Properties filterProps = new Properties();
        props.forEach((o, o2) -> {
            String k = (String) o;
            String v = (String) o2;
            if (k.startsWith(perfix)) {
                filterProps.put(k, v);
            }
        });
        return filterProps;
    }
}
