package com.example.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.util.Map;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Email {

    private String from;
    private String to;
    private String name;
    private String subject;
    private String content;
    private Map<String, String> model;
}
