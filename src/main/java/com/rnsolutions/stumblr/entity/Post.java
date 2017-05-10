package com.rnsolutions.stumblr.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

/**
 * This is the base for a Post.  Perhaps this should be abstract?
 *
 * @author bsneade
 * @version $Id: $
 */
@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class Post implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 7382341400243763281L;

	private Long id;

    private String author;

    private Date createDate;

    /**
     * The ID for the class.  Since we use an Oracle Database, normally we have
     * a sequence here.  Also, because Oracle can be annoying, there is some
     * additional stuff we have to do here to register the Oracle Sequnce.
     *
     * @return a {@link java.lang.Long} object.
     */
    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    /**
     * <p>Setter for the field <code>id</code>.</p>
     *
     * @param id a {@link java.lang.Long} object.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Maintain a field for optimistic locking.  Yes, I know Date is not JPA supported, but
     * Hibernate seems to be happy with it.
     *
     * @return a {@link java.util.Date} object.
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Version
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * <p>Setter for the field <code>createDate</code>.</p>
     *
     * @param createDate a {@link java.util.Date} object.
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Column(length=60)
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

}
