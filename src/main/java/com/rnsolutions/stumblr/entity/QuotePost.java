package com.rnsolutions.stumblr.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * A quote post.  Composed of a quote and source
 *
 * @author bsneade
 * @version $Id: $
 */
@Entity
public class QuotePost extends Post {

    private String quote;

    private String source;

    @Column(length=4000)
    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    @Column(length=4000)
    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }


   
}
