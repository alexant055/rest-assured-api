package com.api.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PassengerResponse {
    private String _id;
    private String name;
    private int trips;
    private int __v;
    private List<Airline> airline;
}
