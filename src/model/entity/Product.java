package model.entity;

import com.google.gson.Gson;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder

public class Product {

    private int id;
    private String name;
    private Brand brand;
    private int count;
    private double price;



    public String toString() {
        return new Gson().toJson(this);
    }


}
