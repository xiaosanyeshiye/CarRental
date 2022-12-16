package com.mock.carrental.common.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BasePage {

    private Long pageIndex = 1l;

    private Long pageSize = 10l;

    private Long totalPage;

    private Long totalCount;
}
