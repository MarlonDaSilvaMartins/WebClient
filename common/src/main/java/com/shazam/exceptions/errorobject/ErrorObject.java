package com.shazam.exceptions.errorobject;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ErrorObject {
    private String message;
    private String field;
    private String parameter;
}
