package org.example.servlets.model;

public class Transaction {
    public enum Type {INCOME, EXPENSE}

    private String name;
    private int amount;
    private Type type;

    public Transaction(String name, int amount, Type type) {
        this.name = name;
        this.amount = amount;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public int getAmount() {
        return amount;
    }

    public Type getType() {
        return type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "name='" + name + '\'' +
                ", amount=" + amount +
                ", type=" + type +
                '}';
    }
}