package com.lovezc.forever.entity;

import lombok.AllArgsConstructor;
import lombok.Data;  
import lombok.NoArgsConstructor;  
  
  
@Data  
@AllArgsConstructor  
@NoArgsConstructor  
public class MessageResponse {  
  
    private String responseMessage;  
  
    private String status;  
}  