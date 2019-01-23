## 内容和类型转换

### 自定以邮件类型
除了Spring Cloud Stream提供的类型转换之外，我们还可以自定义类型转换。自定义类型转换允许你以各种自定义格式发送和接收数据，并且可以将自定义格式于特定的contentTypes进行关联。Spring Cloud Stream将org.springframework.messaging.converter.MessageConverter所有bean注册成开箱即用的自定义消息转换器。<br/>
为了让你的消息转换器能够满足特定的content-type和目标类（输入和输出），需要继承org.springframework.messaging.converter.AbstractMessageConverter。对于使用@StreamListener转换的情况，使用继承org.springframework.messaging.converter.MessageConverter的消息转换器就可以了。<br/>
下面是在Spring Cloud Stream应用程序种创建消息转换器Bean（内容类型位application/bar）的示例。<br/>

```
@EnableBinding(Sink.class)
@SpringBootApplication
public static class SinkApplication {
  ...
    @Bean
    public MessageConverter customMessageConverter() {
        return new MyCustomMessageConverter();
    }

    public class MyCustomMessageConverter extends AbstractMessageConverter {

        public MyCustomMessageConverter() {
            super(new MimeType("application", "bar"));
        }

        @Override
        protected boolean supports(Class<?> clazz) {
            return (Bar.class == clazz);
        }

        @Override
        protected Object convertFromInternal(Message<?> message, Class<?> targetClass, Object conversionHint) {
            Object payload = message.getPayload();
            return (payload instanceof Bar ? payload : new Bar((byte[]) payload));
        }
    }
```

### @StreamListener和讯息转换
@StreamListener注释提供了一种不需要指定转换类型的方便的转换传入消息的方式。在调用@StreamListener注释的方法的时候，入股偶传入了要求类型转换的参数，将自动进行类型转换。例如，让我们看一个内容为{"greeting":"Hello,world"}的String消息，输入通道上的请求头信息为application/json。让我们来看一看消费消息的应用程序如下：

```
public class GreetingMessage {

    String greeting;

    public String getGreeting() {
        return greeting;
    }

    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }
}

@EnableBinding(Sink.class)
@EnableAutoConfiguration
public static class GreetingSink {

    @StreamListener(Sink.INPUT)
    public void receive(Greeting greeting) {
        // handle Greeting
    }
}
```
这个方法的参数将根据json的属性字段进行自动的填充。

### 7 Schema进化支持
 Spring Cloud Stream通过他的spring-cloud-stream-schema模块为基于模式的消息转换器提供支持。当前，支持基于模式的开箱即用的消息转换器的序列化格式仅仅有Apache Avro,将来的版本将会有更多的格式被添加。

#### 7.1 Apache Avro消息转换器
 spring-cloud-stream-schema模块包含了两种可用于Apache Avro序列化的消息转换器。
 

 - 使用序列化/反序列化对象的类信息的转换器, 或具有启动时已知位置的架构的转换器；
 - 转换器使用在运行时确定的模式注册表，并且随着域对象的发展动态地注册新模式；

#### 7.2 具有Schema支持的转换器
AvroSchemaMessageConverter支持使用预定义的Schema信息或者在类中可用的Schema信息来进行序列化和反序列化。如果转换的目标类是GenericRecord，则必须设置Schema。<br/>
针对他的使用，你可以简单地将其添加到应用上下文中，可以任意指定一个或者多个MimeType和他进行关联。默认的MimeType为application/avro。<br/>
下面是在Sink应用程序中注册Apache Avro MessageConverterd的示例，这个示例没有用到预定义的Schema。<br/>

```
@EnableBinding(Sink.class)
@SpringBootApplication
public static class SinkApplication {

    ...

    @Bean
    public MessageConverter userMessageConverter() {
        return new AvroSchemaMessageConverter(MimeType.valueOf("avro/bytes"));
    }
}
```
相应地，这儿有一个例子展示使用预定义Schema注册的转换器，可以在类路径中找到：<br/>

```
@EnableBinding(Sink.class)
@SpringBootApplication
public static class SinkApplication {

    ...

    @Bean
    public MessageConverter userMessageConverter() {
        AvroSchemaMessageConverter converter = new AvroSchemaMessageConverter(MimeType.valueOf("avro/bytes"));
        converter.setSchemaLocation(new ClassPathResource("schemas/User.avro"));
        return converter;
    }
}
```
为了理解Schema注册表客户端转换器，我们将首先描述Schema注册表支持。<br/>
#### 7.3 Schema注册表支持
大多数序列化模型，特定是针对跨平台和语言的，依赖于二进制数据的序列化。为了序列化数据然后解释他，发送方和接收方都必须能够使用处理二进制数据的Schema。在某些情况下，可以从序列化的有效载荷类型或者放序列化的目标类型中推断出Schema,但是在大多数情况下，应用程序可以从描述二进制数据格式的显示Schema中受益。Schema注册表允许使用文本格式存储Schema信息，并且需要以二进制形式发送和接收数据的应用程序使用这个信息。一个Schema可以作为原组引用，他由以下的部分组成：

 - 作为Schema逻辑名称的主题；
 - Schema版本；
 - 描述数据二进制格式的Schema格式；

#### 7.4 Schema注册服务器
Spring Cloud Stream提供了一个Schema注册表的服务端实现。为了使用他，我们可以在我们的应用程序中添加spring-cloud-stream-schema-server依赖，并在Application类上面添加@EnableSchemaRegistryServer注释，添加Schema注册表服务端的REST控制器到应用中。@EnableSchemaRegistryServer注释和SpringBoot Web程序一起使用，并且服务的监听端口由server.port设置。<br/>
spring.cloud.stream.schema.server.path设置能够用来控制Schema服务端的根路径（特别是潜入到其他应用的时候）。<br/>
spring.cloud.stream.schema.server.allowSchemaDeletion的布尔设置能够删除Schema。默认情况下，这个属性的值是false。<br/>
Schema注册表服务端使用关系数据库来存储Schema。默认情况下，这里使用的是一个嵌入式的数据库。你可以使用Spring Boot SQL数据库和JDBC配置项来自定义Schema存储。<br/>
启用Schema注册表的SpringBoot应用程序如下：<br/>

```
@SpringBootApplication
@EnableSchemaRegistryServer
public class SchemaRegistryServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(SchemaRegistryServerApplication.class, args);
    }
}
```
##### 7.4.1 Schema注册器服务端API
Schema注册器服务端API注册项如下:
**POST/**
注册一个新的Schema。
接受包含以下属性的JSON数据：

 - subject Schema的主题。
 - format Schema的格式。
 - definition Schema的定义。

响应是以JSON格式的schema对象，这个对象包含以下属性：

 - id schema的id；
 - subject schema的主题；
 - version schema的版本；
 - definition schema的定义；

**GET/{subject}/{format}/{version}**
通过subject、format和version来检索一个已经存在的schema。
响应是一个JSON格式的schema对象，这个对象包含以下属性：

 - id schema的id；
 - subject schema的主题；
 - format schema的格式；
 - version schema的版本；
 - definition schema的定义；
 
 **GET/{subject}/{format}**
 
#### 7.5 Schema 注册表客户端
于Schema服务端相对应的客户端的接口是SchemaRegistryClient接口，具体的结构如下：
```
public interface SchemaRegistryClient {

	SchemaRegistrationResponse register(String subject, String format, String schema);

	String fetch(SchemaReference schemaReference);

	String fetch(Integer id);

}
```
Spring Cloud Stream提供了开箱即用的实现，用于和自己的schema服务端与Confluent Schema注册表交互。<br/>
我们可以在SpringBoot应用中添加@EnableSchemaRegistryClient注释来让这个应用是schema注册表的客户端，示例如下：<br/>
```
@EnableBinding(Sink.class)
@SpringBootApplication
@EnableSchemaRegistryClient
public static class AvroSinkApplication {
    ...
}
```

*注意：默认转换器被优化不仅仅用来还缓存来自远程服务端的schemas也包括比较费事的parse()和toString()函数。因此，它使用不缓
存响应的DefaultSchemaRegistryClient。如果你想要在你得代码中直接使用这个客户端，你可以请求一个缓存响应的bean被创建。
为此，只需将属性spring.cloud.stream.schemaRegistryClient.cached=true添加到应用程序属性中即可。*

##### 7.5.1 Schema注册表客户端属性
Schema注册表客户端支持以下属性：<br/>

- spring.cloud.stream.schemaRegistryClient.endpoint schema-server的位置，这个属性用一个完整的URL来进行设置，包括协议类型、
端口和路径信息。这个属性的默认值是：http://localhost:8990/；
- spring.cloud.stream.schemaRegistryClient.cached 客户端是否应该缓存服务端的响应。通常被设置成false,因为缓存发生在消息转换器当中。
使用schema注册表客户端的客户应该将此项设置成true。默认值为true。

#### 7.6 Avro Schema注册表客户端消息转换器
对于在应用上下文中注册了SchemaRegistryClient bean的Spring Boot应用，Spring Cloud Stream将自动配置一个使用schema注册表客户端来进行
schema管理的Apache Avro消息转换器。这简化了schema演进，因为接收消息的应用程序能够很容易到达和自己的读模式的schema对应的写模式的schema。<br/>
针对出站的消息，如果这个channel的内容的类型被设置成application/*+avro，MessageConverter将被激活。例如：
```
spring.cloud.stream.bindings.output.contentType=application/*+avro
```
在出战转换期间，消息转换器将尝试基于出战消息的类型推断出schema,并基于载荷类型，使用SchemaRegistryClient将其注册到主题。如果已经找到一个完全相同的schema，那么
他的引用将被检索出来。如果没有，这个schema将被注册并且提供一个新的版本号。该消息将使用application/[prefix].[subject].v[version]+avro的
contentType头发送，其中prefix是可配置的，并且从有效载荷类型推导出subject。<br/>
例如，

