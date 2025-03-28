package br.edu.ifsp.arq.tsi.arqweb2.assistencia.model;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class PaymentMethod implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        PaymentMethod other = (PaymentMethod) obj;
        return Objects.equals(id, other.id);
    }
}
