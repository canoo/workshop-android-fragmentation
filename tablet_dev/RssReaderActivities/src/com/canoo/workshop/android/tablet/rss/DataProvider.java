package com.canoo.workshop.android.tablet.rss;

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
        
        RssItem post1 = new RssItem();
        post1.title = "Java 7 Small Language Changes Screencast";
        post1.publishDate = new Date(111, 6, 14, 19, 53);
        post1.author = "Hamlet";
        post1.categories = Arrays.asList("Java", "Screencast", "Hamlet");
        post1.description = "<script type=\"text/javascript\">dzone_url = \"http://www.canoo.com/blog/2011/07/14/java-7-small-language-changes-screencast/\";</script>This screencast demonstrates the small language changes that are part of Open JDK 7, which is available from the Open JDK website. It demonstrates multi-catch, try with resources, strings in switch statements, underscores in literals, and the diamond operator. If you have any issues watching the video below, then you may have better luck viewing [...]";
        post1.content = "<script type=\"text/javascript\">dzone_url = \"http://www.canoo.com/blog/2011/07/14/java-7-small-language-changes-screencast/\";</script><p>This screencast demonstrates the <a href=\"http://openjdk.java.net/projects/coin/\">small language changes</a> that are part of <a href=\"http://openjdk.java.net/\">Open JDK 7</a>, which is available from the Open JDK website. It demonstrates multi-catch, try with resources, strings in switch statements, underscores in literals, and the diamond operator.</p>\n" +
                "<p>If you have any issues watching the video below, then you may have better luck viewing it on the <a href=\"http://tv.jetbrains.net/videocontent/java-7-small-language-changes\">JetBrains.tv</a> site. </p>\n" +
                "<p><object width=\"400\" height=\"300\" id=\"_ipad\" data=\"http://tv.jetbrains.net/flowplayer/flowplayer-3.2.7.swf\" type=\"application/x-shockwave-flash\"><param name=\"movie\" value=\"http://tv.jetbrains.net/flowplayer/flowplayer-3.2.7.swf\" /><param name=\"allowfullscreen\" value=\"true\" /><param name=\"allowscriptaccess\" value=\"always\" /><param name=\"flashvars\" value='config={\"clip\":{\"scaling\":\"orig\",\"autoPlay\":false,\"autoBuffering\":true,\"url\":\"/sites/default/files/videos/converted/projectcoin.mp4\"},\"plugins\":{\"controls\":{\"stop\":true}},\"playlist\":[{\"scaling\":\"orig\",\"autoPlay\":false,\"autoBuffering\":true,\"url\":\"http://tv.jetbrains.net/sites/default/files/videos/converted/projectcoin.mp4\"}]}' /></object>\n" +
                "<p>I&#8217;ve made a lot of screencasts and blog posts over the years. If you like this, then there are many ways to see the other stuff I&#8217;ve done:&nbsp;</p>\n" +
                "<ul>\n" +
                "<li>My main blog:&nbsp;<a href=\"http://hamletdarcy.blogspot.com\">http://hamletdarcy.blogspot.com</a></li>\n" +
                "<li>My other JetBrains.tv posts:&nbsp;<a href=\"http://tv.jetbrains.net/tags/hamlet\">http://tv.jetbrains.net/tags/hamlet</a></li>\n" +
                "<li>IDEA&nbsp;related posts on my blog:&nbsp;<a href=\"http://hamletdarcy.blogspot.com/search/label/IDEA\">http://hamletdarcy.blogspot.com/search/label/IDEA</a></li>\n" +
                "<li>My screencasts on YouTube:&nbsp;<a href=\"http://www.youtube.com/user/HamletDRC\">http://www.youtube.com/user/HamletDRC</a></li>\n" +
                "<li>IDEA related Posts on my work blog:&nbsp;<a href=\"http://www.canoo.com/blog/tag/idea/\">http://www.canoo.com/blog/tag/idea/</a></li>\n" +
                "<li>Or follow me on Twitter:&nbsp;<a href=\"http://twitter.com/hamletdrc\">@HamletDRC</a></li>\n" +
                "</ul>\n" +
                "<p>The screencast was created with Ubuntu 10.04, PiTiVi, Audicity, gtk-RecordMyDesktop, IntelliJ IDEA, and LibreOffice. OS from top to bottom.</p>\n" +
                "<p>Thanks for watching, and leave a comment! </p>";
        rssFeed.items.add(post1);
        
        RssItem post2 = new RssItem();
        post2.title = "Mock Objects with Spock Screencast";
        post2.publishDate = new Date(111, 6, 12, 23, 57);
        post2.author = "Hamlet";
        post2.categories = Arrays.asList("Groovy", "Screencast", "Hamlet", "Spock");
        post2.description = "This screencast demonstrates how to use Spock testing specifications and Groovy for mocking and stubbing behavior in unit tests. It covers creating the mock object syntax, setting expectations, verifying and spying on results, and argument matchers. If you have any issues with video playback, then trying viewing it from the JBrains.tv website Here are some [...]";
        post2.content = "<script type=\"text/javascript\">dzone_url = \"http://www.canoo.com/blog/2011/07/13/mock-objects-with-spock-screencast/\";</script><p>This screencast demonstrates how to use <a href=\"http://code.google.com/p/spock/\">Spock testing</a> specifications and Groovy for mocking and stubbing behavior in unit tests. It covers creating the mock object syntax, setting expectations, verifying and spying on results, and argument matchers.</p>\n" +
                "<p>If you have any issues with video playback, then trying viewing it from the <a href=\"http://tv.jetbrains.net/videocontent/spock-and-mock-object-basics\">JBrains.tv website</a></p>\n" +
                "<p><object width=\"400\" height=\"300\" id=\"_ipad\" data=\"http://tv.jetbrains.net/flowplayer/flowplayer-3.2.7.swf\" type=\"application/x-shockwave-flash\"><param name=\"movie\" value=\"http://tv.jetbrains.net/flowplayer/flowplayer-3.2.7.swf\" /><param name=\"allowfullscreen\" value=\"true\" /><param name=\"allowscriptaccess\" value=\"always\" /><param name=\"flashvars\" value='config={\"clip\":{\"scaling\":\"orig\",\"autoPlay\":false,\"autoBuffering\":true,\"url\":\"/sites/default/files/videos/converted/spockmocking.mp4\"},\"plugins\":{\"controls\":{\"stop\":true}},\"playlist\":[{\"scaling\":\"orig\",\"autoPlay\":false,\"autoBuffering\":true,\"url\":\"http://tv.jetbrains.net/sites/default/files/videos/converted/spockmocking.mp4\"}]}' /></object>\n" +
                "<p>Here are some useful links to read for this webcast:</p>\n" +
                "<ul>\n" +
                "<li>Spock Framework &#8211; <a href=\"http://code.google.com/p/spock/\">http://code.google.com/p/spock/ </a></li>\n" +
                "<li>Spock Basics Screencast: <a href=\"http://tv.jetbrains.net/videocontent/ffff\">http://tv.jetbrains.net/videocontent/ffff </a></li>\n" +
                "<li>Mocks and Stubs aren&#8217;t Spies: <a href=\"http://hamletdarcy.blogspot.com/2007/10/mocks-and-stubs-arent-spies.html\">http://hamletdarcy.blogspot.com/2007/10/mocks-and-stubs-arent-spies.html </a></li>\n" +
                "<li>Mocking with Spocks: <a href=\"http://www.canoo.com/blog/2010/04/20/spock-and-test-spies-a-logical-choice/\">http://www.canoo.com/blog/2010/04/20/spock-and-test-spies-a-logical-choice/</a></li>\n" +
                "</ul>\n" +
                "<p>I&#8217;ve made a lot of screencasts and blog posts over the years. If you like this, then there are many ways to see the other stuff I&#8217;ve done: </p>\n" +
                "<ul>\n" +
                "<li>My main blog: <a href=\"http://hamletdarcy.blogspot.com/\">http://hamletdarcy.blogspot.com</a></li>\n" +
                "<li>My other JetBrains.tv posts: <a href=\"http://tv.jetbrains.net/tags/hamlet\">http://tv.jetbrains.net/tags/hamlet</a></li>\n" +
                "<li>IDEA related posts on my blog: <a href=\"http://hamletdarcy.blogspot.com/search/label/IDEA\">http://hamletdarcy.blogspot.com/search/label/IDEA</a></li>\n" +
                "<li>My screencasts on YouTube: <a href=\"http://www.youtube.com/user/HamletDRC\">http://www.youtube.com/user/HamletDRC</a></li>\n" +
                "<li>IDEA related Posts on my work blog: <a href=\"http://www.canoo.com/blog/tag/idea/\">http://www.canoo.com/blog/tag/idea/</a></li>\n" +
                "<li>Or follow me on Twitter: <a href=\"http://twitter.com/hamletdrc\">@HamletDRC</a></li>\n" +
                "</ul>\n" +
                "<p>Phew, that&#8217;s a lot of self-promotion <img src='http://www.canoo.com/blog/wp-includes/images/smilies/icon_smile.gif' alt=':)' class='wp-smiley' /> </p>\n" +
                "<p>The screencast was created with Ubuntu 10.04, PiTiVi, Audicity, gtk-RecordMyDesktop, IntelliJ IDEA, and LibreOffice. OS from top to bottom.</p>\n" +
                "<p>Thanks for watching, and leave a comment! </p>";
        rssFeed.items.add(post2);
        
        RssItem post3 = new RssItem();
        post3.title = "What to Expect at Hackergarten";
        post3.publishDate = new Date(111, 4, 12, 10, 57);
        post3.author = "Hamlet";
        post3.categories = Arrays.asList("Events", "Hackergarten");
        post3.description = "<script type=\"text/javascript\">dzone_url = \"http://www.canoo.com/blog/2011/05/12/what-to-expect-at-hackergarten/\";</script>Hackergarten is on tour again, and in the next few days we have an all day coding event at GeeCON in Krakow (Saturday 14th May) and all night event at GR8 in Copenhagen (Tuesday 17th May). So what is Hackergarten anyway? Hackergarten is a group of people that come together to write open source code. [...]";
        post3.content = "<script type=\"text/javascript\">dzone_url = \"http://www.canoo.com/blog/2011/05/12/what-to-expect-at-hackergarten/\";</script><p><a href=\"http://hackergarten.net/\">Hackergarten</a> is on tour again, and in the next few days we have an all day coding event at <a href=\"http://2011.geecon.org/\">GeeCON in Krakow</a> (Saturday 14th May) and all night event at <a href=\"http://www.eu2011.gr8conf.org/\">GR8 in Copenhagen</a> (Tuesday 17th May). So what is Hackergarten anyway?</p>\n" +
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
        rssFeed.items.add(post3);
        
        RssItem post4 = new RssItem();
        post4.title = "Git training at Canoo with Matthew McCullough";
        post4.publishDate = new Date(111, 4, 6, 14, 55);
        post4.author = "Andreas";
        post4.categories = Arrays.asList("General", "Git", "Training");
        post4.description = "<script type=\"text/javascript\">dzone_url = \"http://www.canoo.com/blog/2011/05/06/git-training-at-canoo-with-matthew-mccullough/\";</script>Yesterday I was lucky to attend an excellent git training at Canoo headquarters in Basel. Matthew McCullough of Ambient Ideas, Denver USA was invited by Canoo to share his expert Git knowledge with the people of Basel and nearby. Matthew is a first class trainer, speaking about Git at many conferences and also providing the [...]";
        post4.content = "<script type=\"text/javascript\">dzone_url = \"http://www.canoo.com/blog/2011/05/06/git-training-at-canoo-with-matthew-mccullough/\";</script><p>Yesterday I was lucky to attend an excellent <a href=\"http://git-scm.com/\">git</a> training at Canoo headquarters in Basel. Matthew McCullough of <a href=\"http://ambientideas.com/\">Ambient Ideas</a>, Denver USA was invited by Canoo to share his expert Git knowledge with the people of Basel and nearby. Matthew is a first class trainer, speaking about Git at many conferences and also providing the online git training (<a href=\"https://github.com/training/online\">https://github.com/training/online</a>).</p>\n" +
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
        rssFeed.items.add(post4);
        
        return rssFeed;
    }
}
