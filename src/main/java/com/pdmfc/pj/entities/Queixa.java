package com.pdmfc.pj.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author pdmfc
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Queixa.findAll",
            query = "SELECT a FROM Queixa a")
})
public class Queixa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nome_queixoso;
    

        
    public Queixa() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome_queixoso() {
        return nome_queixoso;
    }

    public void setNome_queixoso(String nome_queixoso) {
        this.nome_queixoso = nome_queixoso;
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
        if (!(object instanceof Queixa)) {
            return false;
        }
        Queixa other = (Queixa) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.pdmfc.test.Queixa[ id=" + id + " ]";
    }

}
