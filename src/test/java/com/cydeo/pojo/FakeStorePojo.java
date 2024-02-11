
package com.cydeo.pojo;

import lombok.Data;

import java.util.List;

@Data
public class FakeStorePojo {

    public Integer id;
    public String title;
    public Integer price;
    public String description;
    public List<String> images;
    public String creationAt;
    public String updatedAt;
    public Category category;

}
