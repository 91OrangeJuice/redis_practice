package com.tkp.learn.admin.util;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tkp.learn.admin.model.common.PageParamVo;
import com.tkp.learn.admin.model.common.PageVo;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @description:
 * @author: itw_wangsc01
 * @createDate: 2020/6/8
 * @version: 1.0
 */
public class PageUtils {

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

    public static <T> PageVo<T>  buildPageVo(Page<T> poPage){
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


