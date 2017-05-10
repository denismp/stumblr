package com.rnsolutions.stumblr.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * A text post.  Composed of a Title and the Text
 *
 * @author bsneade
 * @version $Id: $
 */
@Entity
public class TextPost extends Post {

    private String title;

    private String text;

    /**
     * <p>Getter for the field <code>text</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    @Column(length=4000)
    public String getText() {
        return text;
    }

    /**
     * <p>Setter for the field <code>text</code>.</p>
     *
     * @param text a {@link java.lang.String} object.
     */
    public void setText(String text) {
        this.text = text;
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
