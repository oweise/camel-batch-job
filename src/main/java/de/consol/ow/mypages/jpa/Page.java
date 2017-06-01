package de.consol.ow.mypages.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "page")
@SuppressWarnings("unused")
public class Page {

    @Id
    @Column(name = "isbn")
    private String isbn;

    @Id
    @Column(name = "pageNo")
    private int pageNo;

    @Column(name = "content")
    private String content;


    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }
}
