package com.mock.carrental.service.dto.query;

import com.mock.carrental.common.base.BasePage;
import com.mock.carrental.enums.RentedStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


/***
 * for rented record query
 * @author luw
 */
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PageRentedRecordQuery extends BasePage {

    private String carName;

    private RentedStatusEnum rentedStatus;
}
