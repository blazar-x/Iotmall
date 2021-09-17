package com.liang.iotmall.search.service;

import com.liang.iotmall.search.vo.SearchParam;
import com.liang.iotmall.search.vo.SearchResult;
import org.springframework.stereotype.Service;

/**
 * @author: Mr.Liang
 * @date: 2021/5/26 17:38
 * @Version: 1.0
 */
@Service
public interface MallSearchService {
    /**
     * 检索的所有参数
     * param is null
     * @return: 所有的结果
     * @param param
     */
    SearchResult search(SearchParam param);
}
