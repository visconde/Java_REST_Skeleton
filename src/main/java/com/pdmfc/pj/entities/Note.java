package com.pdmfc.pj.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author pdmfc
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Note.findAll",
            query = "SELECT a FROM Note a")
})
public class Note implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String text;
    
       @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private  Date creationDate;
     @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private  Date lastUpdateDate;

 
   

        
    public Note() {
     
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
     public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

      public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
 public Date getCreationDate() {
        return creationDate;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Note)) {
            return false;
        }
        Note other = (Note) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.pdmfc.test.Note[ id=" + id + " ]";
    }

}
