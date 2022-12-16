package com.mock.carrental.service;

import com.mock.carrental.common.base.PageList;
import com.mock.carrental.service.dto.RentedRecordDto;
import com.mock.carrental.service.dto.command.RentedRecordCommand;
import com.mock.carrental.service.dto.command.RentedRecordUpdateCommand;
import com.mock.carrental.service.dto.query.PageRentedRecordQuery;

public interface IRentedRecordService {

    PageList<RentedRecordDto> listRentedRecords(PageRentedRecordQuery rentedRecordQuery);

    RentedRecordDto genRecord(RentedRecordCommand command);

    /***
     * modify record after return car
     * @param command necessary param for update record
     */
    void updateReturnRecord(RentedRecordUpdateCommand command);
}
