package com.blackat.pocs.keycloak.jaxrs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product
{
    private int id;

    private String productName;

    private String producer;
}
