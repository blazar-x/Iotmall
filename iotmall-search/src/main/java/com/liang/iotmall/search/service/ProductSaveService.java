package com.liang.iotmall.search.service;

import com.liang.common.to.es.SkuEsModel;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * @author: Mr.Liang
 * @date: 2021/5/24 12:54
 * @Version: 1.0
 */
@Service
public interface ProductSaveService {
    boolean productStatusUp(List<SkuEsModel> skuEsModels) throws IOException;
}
