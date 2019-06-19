
#协议处理框架
##添加注解
注解：`ProtocolPath` ,用于增加自定义协议处理器：
```java
@ProtocolPath(path = {"fragment_activity", "path2/mainactivity"})
public class CustomProcessor extends AbsProtocolProcessor {
    ...
}

```

##配置
###设置协议解析器
```java
Protocol.setParser(new DefaultProtocolParser("in", DefaultProtocolParser.ComponentIdentifier.HOST));

```

###设置默认协议处理器
```java
Protocol.setDefaultProcessor(new DefaultProcessor());
```

```java
public class DefaultProcessor extends AbsProtocolProcessor {
    @Override
    public void process(Context context) {
        Toast.makeText(context,"默认协议处理器处理中...", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void parseParameters() {

    }
}
```


###增加自定义协议处理器,注解标注
```java
@ProtocolPath(path = {"fragment_activity", "path2/mainactivity"})
public class CustomProcessor extends AbsProtocolProcessor {
    @Override
    public void process(Context context) {
        //do something
        Toast.makeText(context,"自定义协议处理器处理中...", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void parseParameters() {

    }
}
```

##使用
```java
Protocol.gotoProtocol(this,"crgt://ccrgt.com/trip/bigscreen?provider={xxx/location,xxx/trip/data/tripId}");
Protocol.gotoProtocol(this,"action://ccrgt.com/openTab?tab=video");

```
