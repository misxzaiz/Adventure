package org.example.dto;

import lombok.Data;

import java.util.List;

@Data
public class SQLDto {
    private String sql;
    private String sqlTemplate;
    private List<String> parameters;
}
