package com.canoo.workshop.android.tablet.rss.fragments;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

/**
 * @author Andrei Socaciu
 */
class DataProvider {
    RssFeed getRssFeedContent() {
        RssFeed rssFeed = new RssFeed();
        rssFeed.title = "Rich Internet Applications (RIA)";
        rssFeed.link = "http://www.canoo.com/blog";
        rssFeed.items = new ArrayList<RssItem>();
        
        /*aa*/
        RssItem post1 = new RssItem();
        post1.title = "Code generation in GWT with Deferred Binding (CDI-like events)";
        post1.publishDate = new Date(111, 6, 4, 11, 22);
        post1.author = "Alberto";
        post1.categories = Arrays.asList("Java", "Code Generation", "EventBus", "GIN");
        post1.description = "<script type=\"text/javascript\">dzone_url = \"http://www.canoo.com/blog/2011/07/04/code-generation-in-gwt-with-deferred-binding-cdi-like-events/\";</script>code { font-size: 12px; overflow: auto; } If you read my series on GWT dependency injection (parts: first, second and third), maybe you remember that, in part 3, I mentioned how convenient would be to reduce the boilerplate required to define events in GWT. I also mentioned how elegant I find the event definition in [...]";
        post1.content = "<p>If you read my series on GWT dependency injection (parts: <a href='http://www.canoo.com/blog/2011/04/05/gwt-dependency-injection-recipes-using-gin'>first</a>, <a href='http://www.canoo.com/blog/2011/06/14/gwt-dependency-injection-recipes-using-gin-ii'>second</a> and <a href='http://www.canoo.com/blog/2011/06/20/gwt-dependency-injection-recipes-using-gin-iii'>third</a>), maybe you remember that, in part 3, I mentioned how convenient would be to reduce the boilerplate required to define events in GWT. I also mentioned how elegant I find the event definition in CDI. Wouldn&#8217;t it be nice to have a lightweight event model like that in GWT?</p>\n" +
                "<p>Let&#8217;s see first how an event is defined and handled in CDI:<br />\n" +
                "<pre><code>\n" +
                "public class CDISample {\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;@Inject\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;private Event&lt;String&gt; event;\n" +
                "\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;public void handler(@Observes String payload) {\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;System.out.println(payload);\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;}\n" +
                "\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;public static void main(String[] args) {\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;event.fire(&quot;Hello event world!&quot;);\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;}\n" +
                "}\n" +
                "</code></pre><br />\n" +
                "As you can grasp from the code, to define a new type of event in CDI it is enough to define an injected field of the parametrized class Event and the CDI container will do all the wiring for us. The parametrized type of the event defines its payload type by mean of the type parameter. To listen to the event, we just need to define a method that receives a parameter of the event payload type  and annotate it with &#8220;@Observes&#8221;.</p>\n" +
                "<p>While exactly this is maybe not doable in GWT (at least not yet), what about something like this?:<br />\n" +
                "<pre><code>\n" +
                "public class GWTSample {\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;@Inject\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;private SampleEvent event;\n" +
                "\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;public void execute() {\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;event.dispatchTo(new Handler&lt;String&gt;() {\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;public void onEvent(String payload) {\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Window.alert(payload);\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;}\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;});\n" +
                "\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;event.fire(&quot;Hello event world!&quot;);\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;}\n" +
                "\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;public interface SampleEvent extends Event&lt;String&gt; {\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;}\n" +
                "}\n" +
                "</code></pre><br />\n" +
                "While I can agree with you that it is not as elegant as the events approach in CDI, it is (IMO) a big improvement compared with, for instance, the ping / pong event definition in the demo application of my GIN series (source code <a href='http://www.canoo.com/blog/wp-content/uploads/2011/06/Part0.zip'>here</a>):<br />\n" +
                "<pre><code>\n" +
                "public class PingEvent extends GwtEvent&lt;Handler&gt; {\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;public static Type&lt;Handler&gt; TYPE = new Type&lt;Handler&gt;();\n" +
                "\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;@Override\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;public Type&lt;Handler&gt; getAssociatedType() {\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;return TYPE;\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;}\n" +
                "\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;@Override\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;protected void dispatch(Handler handler) {\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;handler.onEvent(this);\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;}\n" +
                "\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;public interface Handler extends EventHandler {\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;void onEvent(PingEvent event);\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;}\n" +
                "}\n" +
                "</code></pre><br />\n" +
                "With the new approach, the resulting &#8220;PingEvent&#8221; would look like this:<br />\n" +
                "<pre><code>\n" +
                "public interface PingEvent extends Event&lt;Void&gt; {}\n" +
                "</code></pre><br />\n" +
                "If you are wondering why not to avoid defining the &#8220;PingEvent&#8221; class itself, two are the reasons:</p>\n" +
                "<ul>\n" +
                "<li>First, the events have the same payload type (Void in this case), and therefore we need explicit types to distinguish the ping event from the pong event. This also happens in CDI where we would define explicit payload types instead.</li>\n" +
                "<li>Second, in this implementation we will be using GWT&#8217;s deferred binding and, during the code generation phase, we need to know the parametrized type. Because of Java generics limitations, for this last it is necessary to define a new parametrized type which captures the payload type in its type parameter (wrapper).</li>\n" +
                "</ul>\n" +
                "<p>Did I convince you? If I did, let&#8217;s go on and see how to use deferred binding and dependency injection to achieve it.</p>\n" +
                "<h3>Letting GWT generate the boilerplate</h3>\n" +
                "<p>Deferred binding in GWT is a really powerful feature implemented in the GWT compiler to substitute class implementations depending in environment properties like the user agent. This way, while the developer has the illusion of using the same classes independently of the browser, the deferred binding mechanism allows to select the adequate implementation for each browser.</p>\n" +
                "<p>Far from being static, deferred binding allows to generate code on the fly by mean of a generator class. This happens when the developer invokes the &#8220;GWT.create()&#8221; method on an interface. For the operation to succeed, a generator for the interface must exist. This will be invoked during GWT compilation time and its result (one or more Java classes) will be compiled and linked into the resulting JS application.</p>\n" +
                "<p>To configure which generator should be used for which interface, it is necessary to add a new &#8220;generate-with&#8221; entry in the GWT module descriptor. In the case of the demo application, the following code has been added to the &#8220;PingPong.gwt.xml&#8221; file:<br />\n" +
                "<pre><code>\n" +
                "...\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;&lt;generate-with class=&quot;com.canoo.gwt.events.server.eventsfwk.EventGenerator&quot;&gt;\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;when-type-assignable class=&quot;com.canoo.gwt.events.client.eventsfwk.Event&quot;/&gt;\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;&lt;/generate-with&gt;\n" +
                "...\n" +
                "</code></pre></p>\n" +
                "<p>The contents of the &#8220;Event&#8221; and &#8220;EventGenerator&#8221; classes are the following:<br />\n" +
                "<pre><code>\n" +
                "public interface Event&lt;T&gt; {\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;public void fire(T payload);\n" +
                "\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;public void dispatchTo(Handler&lt;T&gt; callback);\n" +
                "\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;public interface Handler&lt;T&gt; {\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;public void onEvent(T payload);\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;}\n" +
                "}\n" +
                "\n" +
                "public class EventGenerator extends Generator {\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;private static final String EVENT_IMPL = &quot;EventImpl&quot;;\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;private static final CodeTemplate GWT_SIMPLE_EVENT_TEMPLATE = new GwtSimpleEventTemplate();\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;private static final CodeTemplate EVENT_IMPL_TEMPLATE = new EventImplTemplate();\n" +
                "\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;@Override\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;public String generate(TreeLogger treeLogger, GeneratorContext generatorContext, String typeName) throws UnableToCompleteException {\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;TypeOracle typeOracle = generatorContext.getTypeOracle();\n" +
                "\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;JClassType parentEventType = typeOracle.findType(Event.class.getName());\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;JClassType eventType = typeOracle.findType(typeName);\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;JClassType payloadType = findPayloadType(eventType, parentEventType);\n" +
                "\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;String gwtSimpleEventImplName = generateGwtSimpleEvent(treeLogger, generatorContext, eventType, payloadType);\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;String eventImplTypeName = generateEventImpl(treeLogger, generatorContext, eventType, payloadType, gwtSimpleEventImplName);\n" +
                "\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;return qualifyInGwtEvents(eventImplTypeName);\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;}\n" +
                "\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;private static JClassType findPayloadType(JClassType eventType, JClassType parentEventType) {\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;JClassType interfaces[] = eventType.getImplementedInterfaces();\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;for (JClassType anInterface : interfaces) {\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;if ((anInterface instanceof JParameterizedType) &amp;&amp; (anInterface.getErasedType() == parentEventType.getErasedType())) {\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;JParameterizedType parametrizedType = (JParameterizedType) anInterface;\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;return parametrizedType.getTypeArgs()[0];\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;}\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;}\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;throw new IllegalStateException(&quot;Payload type not found for: &#039;&quot; + eventType + &quot;&#039;.&quot;);\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;}\n" +
                "\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;private String generateGwtSimpleEvent(TreeLogger treeLogger, GeneratorContext generatorContext, JClassType eventType, JClassType payloadType) {\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;String gwtSimpleEventImplName = getGwtSimpleEventImplName(eventType);\n" +
                "\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;PrintWriter gwtSimpleEventPrintWriter = generatorContext.tryCreate(treeLogger, getEventsPackageName(), gwtSimpleEventImplName);\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;GWT_SIMPLE_EVENT_TEMPLATE.generate(gwtSimpleEventPrintWriter, getEventsPackageName(), gwtSimpleEventImplName, payloadType.getName());\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;generatorContext.commit(treeLogger, gwtSimpleEventPrintWriter);\n" +
                "\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;return gwtSimpleEventImplName;\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;}\n" +
                "\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;private String generateEventImpl(TreeLogger treeLogger, GeneratorContext generatorContext, JClassType eventType, JClassType payloadType, String gwtSimpleEventImplName) {\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;String eventImplTypeName = getEventImplTypeName(eventType);\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;String eventTypeName = getEventTypeName(eventType);\n" +
                "\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;PrintWriter eventPrintWriter = generatorContext.tryCreate(treeLogger, getEventsPackageName(), eventImplTypeName);\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;EVENT_IMPL_TEMPLATE.generate(eventPrintWriter, getEventsPackageName(), eventType.getQualifiedSourceName(), eventImplTypeName, eventTypeName, gwtSimpleEventImplName, payloadType.getName());\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;generatorContext.commit(treeLogger, eventPrintWriter);\n" +
                "\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;return eventImplTypeName;\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;}\n" +
                "\n" +
                "... (some methods omitted)\n" +
                "</code></pre></p>\n" +
                "<p>While the &#8220;Event&#8221; class contains the interface definition for our events and lives in the &#8220;client&#8221; package of our GWT application, the &#8220;EventGenerator&#8221; is a GWT specific class that resides in the &#8220;server&#8221; package of our GWT application. In order to compile the code, we will need to add a new dependency to our project: &#8220;gwt-dev-&lt;version&gt;.jar&#8221;.</p>\n" +
                "<p>This generator class, by mean of some templates included in the source code, generates the boilerplate classes and saves us the tedious work of writing these classes. If you want to see this in detail, please refer to the &#8220;eventsfwk&#8221; packages in the client and server part of the application (source code <a href='http://www.canoo.com/blog/wp-content/uploads/2011/06/Part1.zip'>here</a>).</p>\n" +
                "<h3>Using GIN to bring everything together</h3>\n" +
                "<p>Following what I mentioned until now, it seems that if we define an interface extending the &#8220;Event&#8221; interface (ex: &#8220;SampleEvent&#8221;) and we use the generator to generate and wire the code by mean of &#8220;GWT.create(SampleEvent.class)&#8221;, we should be able to use the result as shown in the &#8220;GWTSample&#8221; execute method. Not really.</p>\n" +
                "<p>One piece that is missing in the puzzle is which event bus should the underlying GWT events use. We could create an own one without loosing functionality but a better thing that we can do is to provide an injection point and provide a mechanism to make our events participate in the application&#8217;s dependency injection context and use the application&#8217;s event bus instead of an own instance.</p>\n" +
                "<p>To achieve this, we have created a GIN module in the &#8220;eventsfwk&#8221; package:<br />\n" +
                "<pre><code>\n" +
                "public class EventsModule extends AbstractGinModule {\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;@Override\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;protected void configure() {\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;requestStaticInjection(GwtSimpleEvent.class);\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;}\n" +
                "}\n" +
                "</code></pre></p>\n" +
                "<p>This module should be installed in the application&#8217;s dependency context and it will inject an static field in the &#8220;GwtSimpleEvent&#8221; class which is the event bus used for our improved events. To install it in our application, the injection module has been modified like this (note the line &#8220;install(EventsModule.class);&#8221;):<br />\n" +
                "<pre><code>\n" +
                "@GinModules(Module.class)\n" +
                "public interface Injector extends Ginjector {\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;public static class Module extends AbstractGinModule {\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;@Override\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;protected void configure() {\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;bind(EventBus.class).to(SimpleEventBus.class).in(Singleton.class);\n" +
                "\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;install(new EventsModule());\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;install(new GinFactoryModuleBuilder().build(Factory.class));\n" +
                "\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;bindConstant().annotatedWith(named(&quot;PingText&quot;)).to(&quot;Ping&quot;);\n" +
                "\n" +
                "... (code omitted)\n" +
                "}\n" +
                "</code></pre></p>\n" +
                "<p>This means that, when the dependency injection module is initialized at the beginning of the application execution, the previously configured event bus will be statically injected in the &#8220;GwtSimpleEvent&#8221; class used by all the generated event classes.</p>\n" +
                "<p>I can not read minds, but maybe you are also wondering why the event fields annotated with &#8220;@Inject&#8221; get generated and injected. All in all, we don&#8217;t call anywhere &#8220;GWT.create()&#8221; to use the generator class.</p>\n" +
                "<p>Well, we are not doing it but GIN does. Whenever GIN finds a dependency (something annotated with &#8220;@Inject&#8221;) and that dependency has not explicitly been configured (bound in the GIN modules), GIN invokes &#8220;GWT.create()&#8221; as fallback strategy. This simple mechanism allows us to generate and inject our event in only one step.</p>\n" +
                "<p>Wow! This post has been &#8220;hardcore&#8221; GWT, don&#8217;t you think? Maybe even too &#8220;hardcore&#8221;. Please, just let me know if you like it and what do you think about the approach.</p>\n" +
                "<p>See you in a future post!</p>\n";
        rssFeed.items.add(post1);
        
        
        /*aa*/
        RssItem post2 = new RssItem();
        post2.title = "GWT Dependency Injection recipes using GIN (II)";
        post2.publishDate = new Date(111, 5, 14, 10, 43);
        post2.author = "Alberto";
        post2.categories = Arrays.asList("Java", "Dependency Injection", "GIN");
        post2.description = "<script type=\"text/javascript\">dzone_url = \"http://www.canoo.com/blog/2011/06/14/gwt-dependency-injection-recipes-using-gin-ii/\";</script>code { font-size: 12px; overflow: auto; } This is the second part of a series about Dependency Injection in Google Web Toolkit using GIN. If you have not yet read the first part, there we explained how to integrate GIN in an existing GWT sample application. In this second part, we will continue enhancing the [...]";
        post2.content = "<p>This is the second part of a series about Dependency Injection in Google Web Toolkit using GIN. If you have not yet read the <a href='http://www.canoo.com/blog/2011/04/05/gwt-dependency-injection-recipes-using-gin/'>first part</a>, there we explained how to integrate GIN in an existing GWT sample application. In this second part, we will continue enhancing the sample application while explaining other types of injection supported by GIN.</p>\n" +
                "<h3>Encapsulating the event bus</h3>\n" +
                "<p>In the first part of this series, we configured the GWT event bus used in the original application to be injected in some of the application elements. Using an event bus is a &#8220;best practice&#8221; that helps communicating the different components in the application while keeping a low coupling between them. The point here was to show how to declare and configure a first dependency using the &#8220;injector&#8221; and the &#8220;module&#8221;. Because the class &#8220;SimpleEventBus&#8221; is provided by GWT, it was not possible to annotate the code of the class with &#8220;@Singleton&#8221; to set the &#8220;injection scope&#8221;. Instead, we use the Java DSL provided by GIN.<br />\n" +
                "In order to see how to do the same with annotations, let&#8217;s use now an application class (instead of a framework class) where we can use the annotations.<br />\n" +
                "As you probably noticed, we are using the event bus to emulate the &#8220;ping-pong&#8221; strokes. Instead of such a technical artifact, let&#8217;s create a &#8220;racket&#8221;:<br />\n" +
                "<pre><code>\n" +
                "@Singleton\n" +
                "public class Racket {\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;private final EventBus fEventBus;\n" +
                "\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;private GwtEvent&lt;?&gt; fLastEvent;\n" +
                "\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;@Inject\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;public Racket(EventBus eventBus) {\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;fEventBus = eventBus;\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;}\n" +
                "\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;public void serve() {\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;fLastEvent = null;\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;hit();\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;}\n" +
                "\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;public void hit() {\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;fEventBus.fireEvent(getNextEvent());\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;}\n" +
                "\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;private GwtEvent&lt;?&gt; getNextEvent() {\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;if (fLastEvent == null || fLastEvent instanceof PongEvent) {\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;fLastEvent = new PingEvent();\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;} else {\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;fLastEvent = new PongEvent();\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;}\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;return fLastEvent;\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;}\n" +
                "\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;public void onStroke(Class&lt;?&gt; pingPongEvent, final Command command) {\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;if (pingPongEvent == PingEvent.class) {\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;fEventBus.addHandler(PingEvent.TYPE, new PingEventHandler() {\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;public void onEvent(PingEvent event) {\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;command.execute();\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;}\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;});\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;} else {\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;fEventBus.addHandler(PongEvent.TYPE, new PongEventHandler() {\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;public void onEvent(PongEvent event) {\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;command.execute();\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;}\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;});\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;}\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;}\n" +
                "}\n" +
                "</code></pre><br />\n" +
                "As you can see at the code, our class is annotated with &#8220;@Singleton&#8221; to indicate the injection scope and &#8220;@Inject&#8221; to get the event bus injected in the constructor. The class is a more adequate wrapper for the event handling and offers three methods: &#8220;serve&#8221;, &#8220;hit()&#8221; and &#8220;onStroke()&#8221;.<br />\n" +
                "To configure the injection please adjust the &#8220;injector&#8221; class like this:<br />\n" +
                "<pre><code>\n" +
                "@GinModules(InjectorModule.class)\n" +
                "public interface Injector extends Ginjector {\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;public static final Injector INSTANCE = GWT.create(Injector.class);\n" +
                "\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;public Racket getRacket();\n" +
                "\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;public PingView getPingView();\n" +
                "\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;public PongView getPongView();\n" +
                "}\n" +
                "</code></pre><br />\n" +
                "This time, we only need to declare the dependency in the &#8220;Injector&#8221; interface (the method will be invoked from the &#8220;Simulator&#8221; class) but no additional configuration is required and therefore no changes should be made in the &#8220;InjectorModule&#8221; class.<br />\n" +
                "Because now the event bus will not be directly accessed from the &#8220;Simulator&#8221; class, we have removed it from the &#8220;Injector&#8221; interface. But, let&#8217;s see the rest of the code changes:<br />\n" +
                "<pre><code>\n" +
                "public class PingView extends Label {\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;@Inject\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;public PingView(final Racket racket) {\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;racket.onStroke(PongEvent.class, new Command() {\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;public void execute() {\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;setText(&quot;Pong&quot;);\n" +
                "\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;new Timer() {\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;public void run() {\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;setText(&quot;&quot;);\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;racket.hit();\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;}\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;}.schedule(1000);\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;}\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;});\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;}\n" +
                "}\n" +
                "</code></pre><br />\n" +
                "The &#8220;PongView&#8221; class is almost identical and will not be shown here.<br />\n" +
                "<pre><code>\n" +
                "public class Simulator implements EntryPoint {\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;public void onModuleLoad() {\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;final Injector injector = Injector.INSTANCE;\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;RootPanel.get(&quot;pingSlot&quot;).add(injector.getPingView());\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;RootPanel.get(&quot;pongSlot&quot;).add(injector.getPongView());\n" +
                "\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;final Button button = new Button(&quot;Serve!&quot;);\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;button.addClickHandler(new ClickHandler() {\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;public void onClick(ClickEvent event) {\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;button.setVisible(false);\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;injector.getRacket().serve();\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;}\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;});\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;RootPanel.get(&quot;buttonSlot&quot;).add(button);\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;}\n" +
                "}\n" +
                "</code></pre><br />\n" +
                "Recapitulating what we have seen so far:</p>\n" +
                "<ul>\n" +
                "<li>GIN allows to define &#8220;injectors&#8221; by mean of interfaces (&#8220;Injector&#8221; interface) that can be configured using one or more &#8220;modules&#8221; each (&#8220;InjectorModule&#8221; class).</li>\n" +
                "<li>If we need to configure an injection aspect we can use annotations in the class code or the Java DSL in the module.</li>\n" +
                "<li>In at least one point of the application we will need to access the &#8220;injector&#8221; to retrieve a top-level injected object (ex: &#8220;Racket&#8221;, &#8220;PingView&#8221;, &#8220;PongView&#8221; ). For these objects to be accessible through the injector, we need to declare them in the &#8220;injector&#8221; interface.</li>\n" +
                "<li>Any other dependency that is declared to be injected (annotated with @Inject) will be resolved by GIN using the algorithm described in the first part of this series and does not need to be declared in the &#8220;injector&#8221; interface (ex: &#8220;EventBus&#8221;).</li>\n" +
                "</ul>\n" +
                "<p>If you are familiar with GUICE, you probably already know other ways of configuring dependencies and injection. Let&#8217;s see in the next point how to avoid the repetition in the views by using &#8220;assisted injection&#8221;.</p>\n" +
                "<h3>Using assisted injection to avoid code repetition</h3>\n" +
                "<p>One of the main applications of dependency injection is to avoid coding factory classes and singletons and also centralizing the application&#8217;s configuration values. This is a very powerful tool to configure application-wide objects like services, which typically are either &#8220;singletons&#8221; or &#8220;prototypes&#8221; and scarce.</p>\n" +
                "<p>When working with data, persistency tools like JPA assist us with the task of generating Java objects for our numerous domain model entities. These objects have normally no behavior or just a little which is typically coded in the &#8220;entity&#8221; classes. But, what happens if we want to inject our services in some of these entities to have a richer domain model? Normally, we have to tackle this task ourselves and inject the services by hand. This happens because such objects do not by default participate in the injection context (unless we are using JEE or weaving).</p>\n" +
                "<p>While this not a difficult task, it is a purely programmatic one and breaks one of the principles of dependency injection: declarative dependencies definition.<br />\n" +
                "In GUICE (and therefore in GIN) there is a concept called &#8220;assisted injection&#8221; that helps us to create objects which require dependencies from the injection context but also instance specific property values. Basically, it consists of using a factory interface with methods to instantiate the objects as managed beans (injected objects) receiving each method only the object&#8217;s specific properties as parameters. The object constructors receive of course not only the object properties but also the dependencies who should be retrieved from the dependency injection context.</p>\n" +
                "<p>Because an image is worth a thousand words, let&#8217;s see this with an example. What we would like to achieve here is to have only one view class (&#8220;PingPongView&#8221;) instead of two specific ones that have almost the same code. If we inspect both view classes, the differences are: to which event each reacts and which text each shows. If we refactor these values as constructor parameters, we have the following resulting class:<br />\n" +
                "<pre><code>\n" +
                "public class PingPongView extends Label {\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;@Inject\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;public PingPongView(final Racket racket, @Assisted final String text, @Assisted Class&lt;? extends GwtEvent&gt; eventClass) {\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;racket.onStroke(eventClass, new Command() {\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;public void execute() {\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;setText(text);\n" +
                "\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;new Timer() {\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;public void run() {\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;setText(&quot;&quot;);\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;racket.hit();\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;}\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;}.schedule(1000);\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;}\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;});\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;}\n" +
                "}\n" +
                "</code></pre><br />\n" +
                "Probably you already noticed the @Assisted annotation in the new extracted parameters. It just indicates GIN that the non-annotated parameters should be resolved as dependencies and that the annotated ones should be provided though parameters in a factory method of the assisted injection factory:<br />\n" +
                "<pre><code>\n" +
                "public interface AssistedInjectionFactory {\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;public PingPongView createPingPongView(String text, Class&lt;? extends GwtEvent&gt; eventClass);\n" +
                "}\n" +
                "</code></pre><br />\n" +
                "To install this factory in our dependency injection context we need to modify our GIN module class like this:<br />\n" +
                "<pre><code>\n" +
                "public class InjectorModule extends AbstractGinModule {\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;@Override\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;protected void configure() {\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;bind(EventBus.class).to(SimpleEventBus.class).in(Singleton.class);\n" +
                "\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;install(new GinFactoryModuleBuilder().build(AssistedInjectionFactory.class));\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;}\n" +
                "}\n" +
                "</code></pre><br />\n" +
                "And in the injector class, we have to expose the factory as any other dependency:<br />\n" +
                "<pre><code>\n" +
                "@GinModules(InjectorModule.class)\n" +
                "public interface Injector extends Ginjector {\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;public static final Injector INSTANCE = GWT.create(Injector.class);\n" +
                "\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;public Racket getRacket();\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;public AssistedInjectionFactory getFactory();\n" +
                "}\n" +
                "</code></pre><br />\n" +
                "And last but not least, let&#8217;s use the assisted injection in our application:<br />\n" +
                "<pre><code>\n" +
                "...\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;public void onModuleLoad() {\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;final Injector injector = Injector.INSTANCE;\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;final AssistedInjectionFactory factory = injector.getFactory();\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;RootPanel.get(&quot;pingSlot&quot;).add(factory.createPingPongView(&quot;Ping&quot;, PingEvent.class));\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;RootPanel.get(&quot;pongSlot&quot;).add(factory.createPingPongView(&quot;Pong&quot;, PongEvent.class));\n" +
                "...\n" +
                "</code></pre></p>\n" +
                "<p>In this article, we have applied new dependency injection recipes to our GWT demo application. I hope that they can help you give a better structure to your GWT applications and also learn dependency injection features and its &#8220;best practices&#8221;.</p>\n" +
                "<p>The source code of the application can be downloaded <a href='http://www.canoo.com/blog/wp-content/uploads/2011/06/Part2.zip'>here</a>. To see the application working, unzip the file, change to the folder where the Maven pom file is stored and type the command: &#8220;mvn clean gwt:run&#8221;. After the GWT &#8220;Development mode&#8221; application starts, click on the &#8220;Launch Default Browser&#8221; button.</p>\n" +
                "<p>The other parts of this series can be found <a href='http://www.canoo.com/blog/2011/04/05/gwt-dependency-injection-recipes-using-gin'>here</a> and <a href='http://www.canoo.com/blog/2011/06/20/gwt-dependency-injection-recipes-using-gin-iii'>here</a>.</p>\n" +
                "<p>I hope that you enjoyed reading this article as much as I did writing it.<br />\n" +
                "See you soon!</p>";
        rssFeed.items.add(post2);
        
        RssItem post3 = new RssItem();
        post3.title = "Hackergarten at Canoo 27.05.2011";
        post3.publishDate = new Date(111, 4, 26, 13, 23);
        post3.author = "Hamlet";
        post3.categories = Arrays.asList("Events", "Hackergarten");
        post3.description = "<script type=\"text/javascript\">dzone_url = \"http://www.canoo.com/blog/2011/05/26/hackergarten-at-canoo-27-05-2011/\";</script>Don&#8217;t forget! Tomorrow night is Hackergarten. On the last Friday of the month Canoo opens our doors for an open source programming group called Hackergarten. Our goal is to contribute in some way to open source software, to meet new friends, and to generally have a good time. In the past we have made contributions [...]";
        post3.content = "<script type=\"text/javascript\">dzone_url = \"http://www.canoo.com/blog/2011/05/26/hackergarten-at-canoo-27-05-2011/\";</script><p>Don&#8217;t forget! Tomorrow night is <a href=\"http://www.hackergarten.net\">Hackergarten</a>.</p>\n" +
                "<p>On the last Friday of the month Canoo opens our doors for an open source programming group called Hackergarten. Our goal is to contribute in some way to open source software, to meet new friends, and to generally have a good time. In the past we have made contributions to Groovy, Gradle, NetBeans, Griffon, GPars&#8230; and the list goes on and on. Canoo provides food, drinks, and wifi. Please note, there is a Hackergarten tomorrow! If you&#8217;re coming tomorrow then please let me know because I am cooking American hamburgers for everyone and need to know how much food to buy. To learn more about Hackergarten you can see our main site at <a href=\"http://hackergarten.net/\">http://hackergarten.net/</a> or join the low-volume mailing list at <a href=\"http://groups.google.com/group/hackergarten\">http://groups.google.com/group/hackergarten</a>. </p>\n" +
                "<p>This month we have an open topic. There is always interest in hacking on Gradle and other Groovy technologies, and contributing to IntelliJ IDEA was also proposed. The topic can be anything you&#8217;d like&#8230; so please just show up with ideas. As always, Canoo provides food and drink, and the party starts at 5 PM. We are grilling hamburgers tomorrow, so if you&#8217;d like one please respond to the active <a href=\"http://groups.google.com/group/hackergarten/browse_frm/thread/dcacf4c88ed21409\">mailing list thread</a>. </p>\n" +
                "<p>I hope to see you tomorrow night! </p>";
        rssFeed.items.add(post3);
        
        RssItem post4 = new RssItem();
        post4.title = "IntelliJ IDEA 10.5 for the Groovy and Grails Developer";
        post4.publishDate = new Date(111, 4, 23, 13, 54);
        post4.author = "Hamlet";
        post4.categories = Arrays.asList("Grails", "Groovy", "Idea");
        post4.description = "<script type=\"text/javascript\">dzone_url = \"http://www.canoo.com/blog/2011/05/23/intellij-idea-10-5-for-the-groovy-and-grails-developer/\";</script>The formal release of IntelliJ IDEA 10.5 came out this month, and the new Groovy features are all part of the free and open source Community Edition, and the Grails features are part of the Ultimate Edition. IDEA X (or 10 to you non-Romans) was a larger release of the product, and I already blogged [...]";
        post4.content = "<script type=\"text/javascript\">dzone_url = \"http://www.canoo.com/blog/2011/05/23/intellij-idea-10-5-for-the-groovy-and-grails-developer/\";</script><p>The formal release of <a href=\"http://www.jetbrains.com/idea/whatsnew/\">IntelliJ IDEA 10.5</a> came out this month, and the new Groovy features are all part of the free and open source Community Edition, and the Grails features are part of the Ultimate Edition. IDEA X (or 10 to you non-Romans) was a larger release of the product, and I already blogged about <a href=\"http://www.canoo.com/blog/2010/12/20/intellij-idea-x-for-groovy-developers/\">IDEA X for Groovy</a> and <a href=\"http://www.canoo.com/blog/2010/12/23/idea-x-for-the-grails-developer/\">IDEA X for Grails</a>. There&#8217;s still plenty of nice features in 10.5 though. The prices for IDEA recently dropped between $100 and $50, and anyone purchasing IDEA since last November gets 10.5 as a free upgrade.</p>\n" +
                "<p>Here&#8217;s what 10.5 is all about (or skip straight to the <a href=\"http://confluence.jetbrains.net/display/IDEADEV/IDEA+10.5+RC+Release+Notes\">release notes</a>).</p>\n" +
                "<p><strong>Groovy 1.8 Support</strong><br />\n" +
                "A big push in IDEA 10.5 was Groovy 1.8 support. Groovy 1.8 contains many compile time AST transformations that do things like write out new methods and fields into the Groovy .class files. Normal IDEs will show in-IDE compile errors when using these annotations yet still allow you to compile and execute the script. This has been fixed in IDEA, so the IDE should give you proper code completion and support when you use <a href=\"http://groovy.codehaus.org/gapi/groovy/transform/Field.html\">@Field</a>, <a href=\"http://groovy.codehaus.org/gapi/groovy/transform/TupleConstructor.html\">@TupleConstructor</a>, and <a href=\"http://www.canoo.com/blog/2010/09/20/log-groovys-new-and-extensible-logging-conveniences/\">@Log</a>. This is especially helpful when invoking these synthetic members from Java code.</p>\n" +
                "<p><strong>Introduce Parameter and Introduce Field Refactorings</strong><br />\n" +
                "Introduce Parameter is one of my favorite refactorings. Select a local variable within a method, press Ctrl+Alt+P, and the local variable is extracted into a method parameter. It doesn&#8217;t <em>yet</em> work for closures, but you can <a href=\"http://youtrack.jetbrains.net/issue/IDEA-70064\">vote for that feature</a>. Introduce Field is also handy: select a local variable in either a method <em>or a closure</em>, press Ctrl+Alt+F, and the local variable is extracted into a field on the enclosing class. IDEA is not capable of extracting a field in a script yet, which would logically create an <a href=\"http://groovy.codehaus.org/gapi/groovy/transform/Field.html\">@Field</a> script field, but feel free to <a href=\"http://youtrack.jetbrains.net/issue/IDEA-70065\">upvote the issue</a>.</p>\n" +
                "<p><strong>Go To Test (and vice versa) Support</strong><br />\n" +
                "Tests and production source follows a naming convention, for example MyClass and MyClassTest. You can now press Ctrl+Shift+T to jump to the test (if you&#8217;re in production code) or jump to the production code (if you&#8217;re in the test). And if there is no test, then it will prompt you to create a new one. This works great in most cases. Of course, with Groovy it is not so rare to have several top-level classes in a single source file. In these cases the feature can sometimes get confused. <a href=\"http://youtrack.jetbrains.net/issue/IDEA-70036\">Upvote the fix here</a> if you want it to be just that much smarter in the future.</p>\n" +
                "<p><strong>More Code Completion and Intentions</strong><br />\n" +
                "The &#8220;add static import&#8221; intention is nice for those who use a lot of static imports. Just set your cursor in a constant referenced from your code, press Alt+Enter, and viola&#8230; the constant is statically imported. Also, code completion is now available when creating an object using named parameters, which makes them a little easier to use. A whole bunch of other code completion issues were fixed as well, but these were technically marked as bugs not features. You can always peruse the release notes to see the whole story.</p>\n" +
                "<p><strong>Performance</strong><br />\n" +
                "JetBrains claims that file indexing (typically at IDE startup) is now faster and that working with large Groovy files is more performant. It is hard for me to see a difference since I use the EAP versions and don&#8217;t currently work on any massive projects currently.</p>\n" +
                "<p><strong>Grails Code Generation &amp; Completion</strong> (Ultimate Edition)<br />\n" +
                "Some small but nice things here. If you reference a controller action from a GSP, and that action does not exist, then pressing Alt+Enter creates an empty action for you. Also, the type inference for values on the GrailsPlugins has been improved, such as the closure parameters for doWithApplicationContext, doWithDynamicMethods, etc, and code completion for controllers and action in custom plugins are now discovered automatically. Finally, the code inside &lt;r:script&gt; tags from the <a href=\"http://grails.org/plugin/resources\">Grails Resource plugin</a> is now parsed as JavaScript, so full IDE JavaScript is available within them. This is supposed to become a standard for Grails 1.4, so it should continue to work with that release.</p>\n" +
                "<p><strong>Improved Grails Resource Bundle and i18n Support</strong><br />\n" +
                "With 10.5, if you reference a property using the &lt;g:message&gt; tag in a GSP, and that property does not exist, then the property will be underlined in red and you&#8217;ll be given an Alt+Enter Intention to create it for you. Nifty. Also, the existing i18n intentions should now work better when you have GString syntax in your text. For instance, the string &#8220;Hello, ${user}&#8221; should now be properly handled when extracting to a resource bundle.</p>\n" +
                "<p><strong>Various Usability Improvements</strong><br />\n" +
                "Last on the list are a few odds and ends around usability. Closures can now have the separator line between them in the IDE, the way methods show a line between them. GSP stacktraces have correct (and clickable) hyperlinks. The scripts folder is visible in the Grails view. And code navigation and formatting has been improved for several Grails Artefacts.</p>\n" +
                "<p>That&#8217;s it. Enjoy the upgrade, may your solid state disk never fail, and may your caches always be valid. Caio!</p>\n" +
                "<p>If you like this sort of thing, then there is also a whole bunch of other IDEA related content on <a href=\"http://hamletdarcy.blogspot.com/search/label/IDEA\">my own blog</a> and on <a href=\"http://www.canoo.com/blog/tag/idea/\">the Canoo blog</a>. Enjoy.</p>";
        rssFeed.items.add(post4);
        
        RssItem post5 = new RssItem();
        post5.title = "What to Expect at Hackergarten";
        post5.publishDate = new Date(111, 4, 12, 10, 57);
        post5.author = "Hamlet";
        post5.categories = Arrays.asList("Events", "Hackergarten");
        post5.description = "<script type=\"text/javascript\">dzone_url = \"http://www.canoo.com/blog/2011/05/12/what-to-expect-at-hackergarten/\";</script>Hackergarten is on tour again, and in the next few days we have an all day coding event at GeeCON in Krakow (Saturday 14th May) and all night event at GR8 in Copenhagen (Tuesday 17th May). So what is Hackergarten anyway? Hackergarten is a group of people that come together to write open source code. [...]";
        post5.content = "<script type=\"text/javascript\">dzone_url = \"http://www.canoo.com/blog/2011/05/12/what-to-expect-at-hackergarten/\";</script><p><a href=\"http://hackergarten.net/\">Hackergarten</a> is on tour again, and in the next few days we have an all day coding event at <a href=\"http://2011.geecon.org/\">GeeCON in Krakow</a> (Saturday 14th May) and all night event at <a href=\"http://www.eu2011.gr8conf.org/\">GR8 in Copenhagen</a> (Tuesday 17th May). So what is Hackergarten anyway?</p>\n" +
                "<p>Hackergarten is a group of people that come together to write open source code. If you come to Hackergarten, then expect to do some pair programming, learn better how to write code, and make a contribution to the open source world. The idea of the event is to create a hands-on user group, where you don&#8217;t sit an listen to a presentation, but instead you learn through doing and creating. Conferences give people tons of energy and excitement, and here&#8217;s a way to continue your conference experience and make a positive impact on the world while you&#8217;re still amped up from the conference.</p>\n" +
                "<p>Here&#8217;s a run-down of some important aspects of hackergarten:</p>\n" +
                "<ul>\n" +
                "<li><em>there will be coding</em> &#8211; most of your the time is spent pair programming on a small task for an existing project</li>\n" +
                "<li><em>you will submit a patch</em> &#8211; your goal is to write a feature or fix for a project and then submit the patch (or make a commit)</li>\n" +
                "<li><em>there is no agenda</em> &#8211; the session starts with chaos as people suggest coding ideas and naturally from into small teams and groups</li>\n" +
                "<li><em>you don&#8217;t need a laptop</em> &#8211; If you have a computer then please bring it! If you don&#8217;t then come anyway and don&#8217;t worry about it</li>\n" +
                "<li><em>you don&#8217;t need specific skills</em> &#8211; All skills and backgrounds are welcome: beginner to expert, assembler to Scala, and everything in between</li>\n" +
                "<li><em>you can recruit for your open source project</em> &#8211; got an OS project of your own? Come to Hackergarten and convince other people to work on it with you</li>\n" +
                "</ul>\n" +
                "<p>There will be some Hackergarten veterans to help out with the event. We have our own project ideas and can lead some teams if you want.</p>\n" +
                "<p><a href=\"http://twitter.com/aalmiray\">Andres Almiray</a> &#8211; Andres is the lead on the <a href=\"http://griffon.codehaus.org/\">Griffon Framework</a> (among other things) and he&#8217;s always ready to lead people through contributing<br />\n" +
                "<a href=\"http://twitter.com/breskeby\">Rene Groeschke</a> &#8211; Rene is a frequent plugin contributor to the <a href=\"http://gradle.org/\">Gradle build system</a> and will to help people with working on Gradle<br />\n" +
                "<a href=\"http://twitter.com/aalmiray\">Hamlet D&#8217;Arcy</a> &#8211; I am a committer on <a href=\"http://codenarc.sourceforge.net/\">CodeNarc</a> (static analysis for Groovy) and the <a href=\"http://groovy.codehaus.org/\">Groovy language</a>. I have a ton of static analysis rules that are ready to be implemented for Groovy, and just need some help from you.<br />\n" +
                "You &#8211; Got your own project? Please show up and help people contribute!</p>\n" +
                "<p>There is one last important thing: drinks and food are provided. <a href=\"http://www.canoo.com/\">Canoo</a> sponsors Hackergarten (thanks <a href=\"http://www.canoo.com/\">Canoo</a>!), so you&#8217;ll at least be fed and watered.</p>\n" +
                "<p>See you soon!</p>";
        rssFeed.items.add(post5);
        
        RssItem post6 = new RssItem();
        post6.title = "Git training at Canoo with Matthew McCullough";
        post6.publishDate = new Date(111, 4, 6, 14, 55);
        post6.author = "Andreas";
        post6.categories = Arrays.asList("General", "Git", "Training");
        post6.description = "<script type=\"text/javascript\">dzone_url = \"http://www.canoo.com/blog/2011/05/06/git-training-at-canoo-with-matthew-mccullough/\";</script>Yesterday I was lucky to attend an excellent git training at Canoo headquarters in Basel. Matthew McCullough of Ambient Ideas, Denver USA was invited by Canoo to share his expert Git knowledge with the people of Basel and nearby. Matthew is a first class trainer, speaking about Git at many conferences and also providing the [...]";
        post6.content = "<script type=\"text/javascript\">dzone_url = \"http://www.canoo.com/blog/2011/05/06/git-training-at-canoo-with-matthew-mccullough/\";</script><p>Yesterday I was lucky to attend an excellent <a href=\"http://git-scm.com/\">git</a> training at Canoo headquarters in Basel. Matthew McCullough of <a href=\"http://ambientideas.com/\">Ambient Ideas</a>, Denver USA was invited by Canoo to share his expert Git knowledge with the people of Basel and nearby. Matthew is a first class trainer, speaking about Git at many conferences and also providing the online git training (<a href=\"https://github.com/training/online\">https://github.com/training/online</a>).</p>\n" +
                "<p/>\n" +
                "The training was open to everyone and very well attended, probably because it was very affordable. Over the one-day workshop &#8220;fast-forward&#8221; became a new meaning to me. Starting at the very beginning, such as where to get, install and host git repositories, Matthew quickly passed over all the base concepts and commands including comprehensive exercises. The workshop was rounded off by more advanced concepts like how to handle your revision graph in every imaginable way, including highlights like &#8220;fast-forward&#8221; and &#8220;octopus&#8221; <a href=\"http://www.kernel.org/pub/software/scm/git/docs/git-merge.html\">merges</a>, &#8220;<a href=\"http://www.kernel.org/pub/software/scm/git/docs/git-cherry-pick.html\">cherry-picking</a>&#8220;, &#8220;<a href=\"http://www.kernel.org/pub/software/scm/git/docs/git-rebase.html\">rebasing</a>&#8221; and &#8220;<a href=\"http://www.kernel.org/pub/software/scm/git/docs/git-bisect.html\">bisecting</a>&#8220;. The hours flew by and over the full day Matthew never lowered his fast pace not in the slightest.</p>\n" +
                "<p/>\n" +
                "<a href=\"http://www.canoo.com/blog/wp-content/uploads/2011/05/IMG_0031.jpg\"><img src=\"http://www.canoo.com/blog/wp-content/uploads/2011/05/IMG_0031.jpg\" alt=\"\" title=\"git training @Canoo with Matthew McCullough\" width=\"600\" height=\"450\" class=\"alignnone size-full wp-image-2124\" /></a></p>\n" +
                "<p/>\n" +
                "Before attending the training my perception of git was merely of &#8220;yet another version control system, but distributed&#8221;, but this changed significantly afterwards. Now I believe the distributed nature of Git is not the most important aspect. Git&#8217;s value derives from its most fundamental concept: make the handling of branches easy and treat all branches equally. This opens up the door for a much more fine grained revision management, having branches literally for single features or experiments. Treating branches equally opens up the door for distribution on a larger scale &#8230; </p>\n" +
                "<p/>\n" +
                "Liberating branching wouldn&#8217;t work if you didn&#8217;t have tools to bring back together you and your colleagues&#8217; work. This is another aspect where git excels and impresses. Similarity detection, multi-branch merges and rebasing techniques, together with sophisticated revision graph inspection tools, help you with this normally non-trivial task. As an interesting side aspect Matthew also talked about the design principals behind git, which are very cunning. Git actually tracks content, not files and is designed to be very reliable and fast.</p>\n" +
                "<p/>\n" +
                "If possible I&#8217;ll switch to git for my next software projects. Thank you Matthew for the excellent training and multiple insights.</p>\n" +
                "<p/>\n" +
                "Matthew can be reached at twitter: <a href=\"http://twitter.com/#!/matthewmccull\">@matthewmccull</a> and maintains a blog under: <a href=\"http://ambientideas.com/blog/\">http://ambientideas.com/blog/</a>. He is the author of a huge amount of git tutorials, documentation and related links: <a href=\"http://bit.ly/gitlinks\">http://bit.ly/gitlinks</a>. </p>\n" +
                "<p/>\n" +
                "As a suggestion to programmers: if you want to have a famous speaker at your office then just send them an email and ask. At a minimum they will be thankful; at best they will come and speak at your office! That&#8217;s what we did, and the result was great. </p>\n" +
                "<p/>\n" +
                "Did you not know that Canoo was offering a Git training? Canoo is offering a lot of free (or very affordable) public events. Basel does not have a permanent Java User Group, but we hold &#8220;Reading Groups&#8221; every few months. The best way to be kept informed is to follow <a href=\"http://twitter.com/#!/canoo\">@Canoo</a> on Twitter or send an email to info@canoo.com. Also, we host Hackergarten (<a href=\"http://www.hackergarten.net\">http://www.hackergarten.net</a>) every month, which is your chance to come and hack on open source software with some great friends. We have pizza and drinks on a Friday and then try to make a patch or contribution to an open source project. Our goal is to make Basel a great technology community. Care to help us make it so?</pre>";
        rssFeed.items.add(post6);
        
        return rssFeed;
    }
}
