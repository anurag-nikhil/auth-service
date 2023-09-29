package com.reservation.authservice.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusResponse {
    private  RequestStatus status = RequestStatus.SUCCESS;
    private String message;
}
