package com.liang.iotmall.product.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author: Mr.Liang
 * @date: 2021/5/24 19:28
 * @Version: 1.0
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Catelog2Vo {
    private String catalog1Id;  //1级父分类id
    private List<Catelog3Vo> catalog3List; //三级子分类
    private String id;
    private String name; //这里犯了个大错！！！！！


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Catelog3Vo{
        private String catalog2Id; //父分类，二级父分类id
        private String id;
        private String name;
    }
}
