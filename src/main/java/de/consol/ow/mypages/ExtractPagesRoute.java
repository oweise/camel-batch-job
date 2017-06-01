package de.consol.ow.mypages;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.cdi.ContextName;
import org.apache.camel.converter.jaxb.JaxbDataFormat;

@ContextName("MyPages")
public class ExtractPagesRoute extends RouteBuilder {

    public void configure() throws java.lang.Exception {

        JaxbDataFormat jaxbDataFormat = new JaxbDataFormat("de.consol.ow.mypages.jaxb");

        from("file:/home/oliver/IdeaProjects/camel-batch-job/books?pollStrategy=#RunOncePollingConsumer")
                .unmarshal(jaxbDataFormat)
                .split().method("Splitter", "splitPages")
                .to("log:mypages")
                .to("jpa:?persistenceUnit=mypages");

    }
}
