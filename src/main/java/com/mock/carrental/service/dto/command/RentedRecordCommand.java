package com.mock.carrental.service.dto.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RentedRecordCommand {

    private Long carId;

    private String carName;

    private Integer maxTimeToRented;
}
