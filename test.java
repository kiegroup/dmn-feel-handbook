///usr/bin/env jbang "$0" "$@" ; exit $?
//JAVA 11+
//DEPS org.kie:kie-dmn-feel:8.39.0.Final
//DEPS org.slf4j:slf4j-simple:1.7.36
//DEPS info.picocli:picocli:4.6.3
//DEPS com.vladsch.flexmark:flexmark-all:0.64.0
//DEPS com.fasterxml.jackson.jr:jackson-jr-objects:2.13.3
//DEPS com.fasterxml.jackson.jr:jackson-jr-stree:2.13.3

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.jr.ob.JSON;
import com.fasterxml.jackson.jr.ob.JSONObjectException;
import com.fasterxml.jackson.jr.stree.JacksonJrsTreeCodec;
import com.fasterxml.jackson.jr.stree.JrsString;
import com.vladsch.flexmark.ast.FencedCodeBlock;
import com.vladsch.flexmark.ast.Text;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.ast.NodeVisitor;
import com.vladsch.flexmark.util.ast.VisitHandler;
import com.vladsch.flexmark.util.collection.iteration.ReversiblePeekingIterator;
import com.vladsch.flexmark.util.sequence.BasedSequence;

import org.drools.core.util.Drools;
import org.kie.dmn.api.feel.runtime.events.FEELEvent;
import org.kie.dmn.api.feel.runtime.events.FEELEventListener;
import org.kie.dmn.api.feel.runtime.events.FEELEvent.Severity;
import org.kie.dmn.feel.FEEL;
import org.kie.dmn.feel.lang.FEELProfile;
import org.kie.dmn.feel.parser.feel11.profiles.KieExtendedFEELProfile;

import picocli.CommandLine;

public class test {
    private static final List<FEELProfile> profiles = new ArrayList<>();
    static {
        profiles.add(new KieExtendedFEELProfile());
    }
    private static final FEEL feel = FEEL.newInstance(profiles);
    private static final List<String> failedExpressions = new ArrayList<>();

    public static void main(String... args) throws Exception {
        String md = Files.readString(Paths.get("source/index.html.md"));
        Parser parser = Parser.builder().build();
        Node document = parser.parse(md);
        NodeVisitor visitor = new NodeVisitor(
            new VisitHandler<>(FencedCodeBlock.class, test::visit)
        );
        visitor.visit(document);
        if (!failedExpressions.isEmpty()) {
            System.out.println(CommandLine.Help.Ansi.AUTO.string("@|bold,red The following expressions did not pass testing:|@"));
            failedExpressions.forEach(System.out::println);
            System.exit(-1);
        }
        final String usedVersion = Drools.getFullVersion();
        System.out.println("Used Drools DMN engine version: "+usedVersion);
        final String latestVersion = checkLatestVersionFromMavenCentral();
        System.out.print("Checking the used version: "+usedVersion + " is the latest available: "+latestVersion+" ...");
        if (usedVersion.equals(latestVersion)) {
            System.out.println(CommandLine.Help.Ansi.AUTO.string("@|bold,green OK|@"));
        } else {
            System.out.println(CommandLine.Help.Ansi.AUTO.string("@|bold,red FAIL|@"));
            System.exit(-1);
        }
    }

    private static String checkLatestVersionFromMavenCentral() throws URISyntaxException, IOException, InterruptedException, JSONObjectException {
        System.out.println("Checking on Maven Central for latest published Drools DMN engine versions...");
        List<String> versionsFromMavenCentral = new ArrayList<>();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("https://search.maven.org/solrsearch/select?q=g:org.kie+AND+a:kie-dmn-feel&core=gav&rows=20&wt=json"))
                .GET()
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, BodyHandlers.ofString());
        TreeNode mavenCentralRoot = JSON.builder().treeCodec(new JacksonJrsTreeCodec()).build().treeFrom(response.body());
        TreeNode docs = mavenCentralRoot.get("response").get("docs");
        for (int i=0; i<docs.size(); i++) {
            String v = ((JrsString) docs.get(i).get("v")).getValue();
            versionsFromMavenCentral.add(v);
        }
        String latestFromMavenCentral = versionsFromMavenCentral.stream().filter(x -> !x.toLowerCase().contains("beta")).findFirst().orElse("unknown");
        return latestFromMavenCentral;
    }

    public static void visit(FencedCodeBlock text) {
        String infoString = text.getInfo().toString();
        if (infoString.toUpperCase().contains("FEEL")) {
            ReversiblePeekingIterator<Node> itr = text.getChildIterator();
            while (itr.hasNext()) {
                Node next = itr.next();
                if (next instanceof Text) {
                    Text textNode = (Text) next;
                    BasedSequence[] lines = textNode.getChars().split(System.lineSeparator());
                    for (BasedSequence bs : lines) {
                        if (infoString.equalsIgnoreCase("FEEL")) {
                            checkFEEL(bs.toString(), null);
                        } else if (infoString.equalsIgnoreCase("FEEL,commented")) {
                            String[] parts = bs.toString().split("//➔");
                            if (parts.length != 2) {
                                throw new UnsupportedOperationException("FEEL,commented line not split by //➔ in 2 parts: "+bs.toString());
                            }
                            checkFEEL(parts[0].toString(), parts[1].toString());
                        } else if (infoString.equalsIgnoreCase("FEEL,justValid")) {
                            checkJustValid(bs.toString());
                        } else {
                            throw new UnsupportedOperationException("Unhandled FEEL fenced code block: "+infoString);
                        }
                    }
                }
            }
        }
    }

    private static void checkJustValid(String expression) {
        System.out.print(CommandLine.Help.Ansi.AUTO.string("@|blue "+expression+"|@"));
        System.out.print(" to be valid FEEL...");
        FEEL feelWithListener = FEEL.newInstance(profiles);
        ErrorListener errorListener = new ErrorListener();
        feelWithListener.addListener(errorListener);
        final Object feelResult = feelWithListener.evaluate(expression);
        if (feelResult != null && errorListener.getErrors().isEmpty()) {
            System.out.println(CommandLine.Help.Ansi.AUTO.string("@|bold,green OK|@"));
        } else {
            failedExpressions.add(expression + " seems not valid FEEL and/or generated some kind of errors.");
            System.out.println(CommandLine.Help.Ansi.AUTO.string("@|bold,red FAIL|@"));
            System.out.println(feelResult);
        }
    }

    private static void checkFEEL(String expression, String equalTo) {
        final String evaluate = equalTo == null ? expression : "( " + expression + ") = " + equalTo;
        System.out.print(CommandLine.Help.Ansi.AUTO.string("@|blue "+evaluate+"|@"));
        System.out.print(" ...");
        final Object feelResult = feel.evaluate(evaluate);
        if (Boolean.TRUE.equals(feelResult)) {
            System.out.println(CommandLine.Help.Ansi.AUTO.string("@|bold,green OK|@"));
        } else {
            failedExpressions.add(evaluate);
            System.out.println(CommandLine.Help.Ansi.AUTO.string("@|bold,red FAIL|@"));
            System.out.println(feelResult);
        }
    }

    private static class ErrorListener implements FEELEventListener {

        private final List<FEELEvent> errors = new ArrayList<>();

        @Override
        public void onEvent(FEELEvent event) {
            if (event.getSeverity() == Severity.ERROR) {
                System.err.println(event.getMessage());
                errors.add(event);
            }
        }

        public List<FEELEvent> getErrors() {
            return errors;
        }
    }
}
