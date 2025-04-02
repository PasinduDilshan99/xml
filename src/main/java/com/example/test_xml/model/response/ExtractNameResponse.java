package com.example.test_xml.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExtractNameResponse {
    private String firstName;
    private String lastName;
}
