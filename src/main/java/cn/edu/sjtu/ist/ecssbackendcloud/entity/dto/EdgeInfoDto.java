package cn.edu.sjtu.ist.ecssbackendcloud.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EdgeInfoDto {

    private String name;

    private String url;

    private String api;

    private String description;

    private String timeUnit;

    private int interval;

}
