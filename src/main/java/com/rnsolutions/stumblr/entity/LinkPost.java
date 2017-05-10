package com.rnsolutions.stumblr.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * A link post.  Composed of a Title and the link
 *
 * @author bsneade
 * @version $Id: $
 */
@Entity
public class LinkPost extends Post {

    private String title;

    private String link;

    @Column(length=100)
    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    /**
     * <p>Getter for the field <code>title</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    @Column(length=100)
    public String getTitle() {
        return title;
    }

    /**
     * <p>Setter for the field <code>title</code>.</p>
     *
     * @param title a {@link java.lang.String} object.
     */
    public void setTitle(String title) {
        this.title = title;
    }

}
