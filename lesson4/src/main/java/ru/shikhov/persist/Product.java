package ru.shikhov.persist;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Product {

    private final SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-YYYY");
    public static final Pattern PATTERN = Pattern.compile("(\\d{2}).(\\d{2}).(\\d{4})");

    private Long id;

    @NotBlank
    private String title;

    @Positive
    private Long cost;

    private Date deliveryDate;

    private Date orderDate;

    public Product(String title, Long cost, String deliveryDate, String orderDate) {
        this.title = title;
        this.cost = cost;
        Matcher matcher1 = PATTERN.matcher(deliveryDate);
        Matcher matcher2 = PATTERN.matcher(orderDate);
        if(matcher1.matches()&&matcher2.matches()) {
            this.deliveryDate = new Date(Integer.parseInt(matcher1.group(3))-1900,
                    Integer.parseInt(matcher1.group(1))-1,
                    Integer.parseInt(matcher1.group(2)));
            this.orderDate = new Date(Integer.parseInt(matcher2.group(3))-1900,
                    Integer.parseInt(matcher2.group(1))-1,
                    Integer.parseInt(matcher2.group(2)));

        }
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCost(Long cost) {
        this.cost = cost;
    }

    public void setDeliveryDate(String deliveryDate) {
        Matcher matcher = PATTERN.matcher(deliveryDate);
        if(matcher.matches()) {
            this.deliveryDate = new Date(Integer.parseInt(matcher.group(3))-1900,
                    Integer.parseInt(matcher.group(1))-1,
                    Integer.parseInt(matcher.group(2)));
        }
    }

    public void setOrderDate(String orderDate) {
        Matcher matcher = PATTERN.matcher(orderDate);
        if(matcher.matches()) {
            this.orderDate = new Date(Integer.parseInt(matcher.group(3))-1900,
                    Integer.parseInt(matcher.group(1))-1,
                    Integer.parseInt(matcher.group(2)));
        }
    }

    public String getDeliveryDate() {
        String date = formatter.format(deliveryDate);
        if(date == null) {
            return "";
        }
        return date;
    }
    public String getOrderDate() {
        String date = formatter.format(orderDate);
        if(date == null) {
            return "";
        }
        return date;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Long getCost() {
        return cost;
    }
}
