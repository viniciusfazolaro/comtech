package br.edu.ifsp.arq.tsi.arqweb2.assistencia.model;

public enum Status {

    EM_APROVACAO("Em aprovação"),
    APROVADA("Aprovada"),
    EM_ANDAMENTO("Em andamento"),
    FINALIZADA("Finalizada");

    private final String description;

    Status(String description) { this.description = description; }

    private String getDescription() { return description; }
}
