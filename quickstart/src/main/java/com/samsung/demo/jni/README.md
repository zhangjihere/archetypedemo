#JNI的相关说明
[参考资料](http://www3.ntu.edu.sg/home/ehchua/programming/java/JavaNativeInterface.html)
##External工具的配置
在IntelliJ的External Tool工具中增加javah的配置  
  ```
  Program:$JDKPath$/bin/javah
  Parameters:-classpath $OutputPath$ -jni -d $FileRelativeDir$/$Prompt$ $FileClass$
  Working Directory:$ProjectFileDir$
  ```
注意，在跳出的对话框中，输入生成的JNI的.h文件的路径，这里默认是不输入，直接回车，即指定相对目录jni/为默认目录  

##命令行编译动态库
命令行进入jni目录，Linux下输入命令
  ```
  gcc -Wall -fPIC -I"$JAVA_HOME"/include -I"$JAVA_HOME"/include/linux -shared -o libhello.so HelloJNI.cpp
  ```
注意，Windows和Linux环境的下的动态库的命名方式不同，例如，Windows下hello.so，则Linux下是libhello.so  
##命令行执行
编译调用JNI的.java文件为.class，然后执行命令的工作目录在包外(src/main/java目录下),并配置JVM参数，命令如下：
    ```
    java -Djava.library.path=com/samsung/demo/jni com.samsung.demo.jni.HelloJNI
    ```