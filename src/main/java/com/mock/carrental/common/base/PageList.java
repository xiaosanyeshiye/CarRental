package com.mock.carrental.common.base;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public final class PageList<T> extends ArrayList<T> {

    private List<T> record;

    /** 分页信息 **/
    private BasePage page;

    public PageList(List<T> record, BasePage page) {
        this.record = record;
        this.page = page;
    }

    public PageList() {
        this.record = new ArrayList<>();
        this.page = new BasePage();
    }
}
