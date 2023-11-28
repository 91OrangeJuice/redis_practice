package com.tkp.learn.web.utils;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tkp.learn.web.model.vo.OrderVo;
import com.tkp.learn.web.model.vo.PageParamVo;
import com.tkp.learn.web.model.vo.RankingListPage;
import com.tkp.learn.web.model.vo.common.PageVo;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @description:
 * @author: itw_wangsc01
 * @createDate: 2020/6/8
 * @version: 1.0
 */
public class PageUtils {

    public static String getOrderCondition(List<OrderVo> orders) {
        StringBuilder stringBuilder = new StringBuilder();
        if (orders != null && !orders.isEmpty()) {
            for (int i = 0; i < orders.size(); i++) {
                stringBuilder.append(orders.get(i).getColumnName()).append(" ").append(orders.get(i).getSort());
                if (orders.size() > 1 && i < orders.size() - 1) {
                    stringBuilder.append(",");
                }
            }
        }
        return stringBuilder.toString();
    }

    public static <T> Page getPage(PageParamVo<T> pagePageParamVo) {
        Page page = new Page();
        page.setCurrent(pagePageParamVo.getPageNum());
        page.setSize(pagePageParamVo.getPageSize());
        return page;
    }

    /**
     * 生成PageVo
     *
     * @param poPage
     * @param datas
     * @param <T>
     * @param <F>
     * @return
     */
    public static <T, F> PageVo<T> buildPageVo(Page<F> poPage, List<T> datas) {
        PageVo<T> pageVo = new PageVo<>();
        pageVo.setCurrentPage(poPage.getCurrent());
        pageVo.setPageSize(poPage.getSize());
        pageVo.setTotalElements(poPage.getTotal());
        pageVo.setTotalPages(poPage.getPages());
        if (!CollectionUtils.isEmpty(datas)) {
            pageVo.setDatas(datas);
        }
        return pageVo;
    }

    public static <T> PageVo<T>  buildPageVo(IPage<T> poPage){
        PageVo<T> pageVo=new PageVo();
        pageVo.setCurrentPage(poPage.getCurrent());
        pageVo.setPageSize(poPage.getSize());
        pageVo.setTotalElements(poPage.getTotal());
        pageVo.setTotalPages(poPage.getPages());
        if(!CollectionUtils.isEmpty(poPage.getRecords())){
            pageVo.setDatas(poPage.getRecords());
        }
        return pageVo;
    }

}


