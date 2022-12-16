package com.mock.carrental.service.dto.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RentedRecordUpdateCommand {

    private Long rentedRecordId;
}
