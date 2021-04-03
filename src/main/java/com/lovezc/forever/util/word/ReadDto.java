package com.lovezc.forever.util.word;

import lombok.Data;

@Data
public class ReadDto {
    private String prefix;
    private String text;
    private int titleLevel;
    private int sort;
    private String uuid;
    private String parentUuid;

}
