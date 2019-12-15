package data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import java.io.Serializable;

@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class WindowTopic implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @XmlAttribute
    int id_item;

    int revenue; //Receita
    int expenses; //Gasto
    int profit; //Lucro

    public WindowTopic() {}


    public WindowTopic(int id_item, int revenue, int expenses, int profit) {
        this.id_item = id_item;
        this.revenue = revenue;
        this.expenses = expenses;
        this.profit = profit;
    }

    public int getId_item() {
        return id_item;
    }

    public void setId_item(int id_item) {
        this.id_item = id_item;
    }

    public int getRevenue() {
        return revenue;
    }

    public void setRevenue(int revenue) {
        this.revenue = revenue;
    }

    public int getExpenses() {
        return expenses;
    }

    public void setExpenses(int expenses) {
        this.expenses = expenses;
    }

    public int getProfit() {
        return profit;
    }

    public void setProfit(int profit) {
        this.profit = profit;
    }
}
