package de.consol.ow.mypages;

import de.consol.ow.mypages.jaxb.Book;
import de.consol.ow.mypages.jpa.Page;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.impl.DefaultMessage;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named("Splitter")
@ApplicationScoped
@SuppressWarnings("unused")
public class Splitter {

    public List<Message> splitPages(Exchange e) {

        List<Message> out  = new ArrayList<>();

        Book book = e.getIn().getBody(Book.class);
        for (int idx=0; idx < book.getPages().size() ; idx++) {
            Page page = new Page();
            page.setIsbn(book.getIsbn());
            page.setPageNo(idx);
            page.setContent(book.getPages().get(idx));

            Message msg = new DefaultMessage();
            msg.setBody(page);

            out.add(msg);
        }

        return out;

    }

}
