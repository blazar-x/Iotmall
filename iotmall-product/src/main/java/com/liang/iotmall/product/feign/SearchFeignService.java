package com.liang.iotmall.product.feign;

import com.liang.common.to.es.SkuEsModel;
import com.liang.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author: Mr.Liang
 * @date: 2021/5/24 13:20
 * @Version: 1.0
 */
@FeignClient("iotmall-search")
public interface SearchFeignService {
    //上架商品
    @PostMapping("/search/save/product")
    public R productStatusUp(@RequestBody List<SkuEsModel> skuEsModels);
}
