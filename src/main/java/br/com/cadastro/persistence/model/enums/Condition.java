package br.com.cadastro.persistence.model.enums;

public enum Condition {
    LIKE("like"), EQUAl("=");
    private String condition;

    Condition(String condition) {
        this.condition = condition;
    }

    public String getCondition() {
        return condition;
    }
}
