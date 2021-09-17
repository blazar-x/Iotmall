package com.liang.iotmall.product.feign;

import com.liang.common.utils.R;
import com.liang.iotmall.product.vo.SkuHasStockVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author: Mr.Liang
 * @date: 2021/5/24 12:25
 * @Version: 1.0
 */
@FeignClient("iotmall-ware")
public interface WareFeignService {
    //查询sku是否有库存
    /* *
     * 1.R设计的时候可以加上泛型
     * 2. 直接返回我们想要的结果
     * 3. z自己封装解析结果
     */
    @PostMapping("/ware/waresku/hasstock")
    R getSkusHasStock(@RequestBody List<Long> skuIds);
}
