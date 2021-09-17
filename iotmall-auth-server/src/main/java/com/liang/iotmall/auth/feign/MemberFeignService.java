package com.liang.iotmall.auth.feign;

import com.liang.common.utils.R;
import com.liang.iotmall.auth.vo.UserLoginVo;
import com.liang.iotmall.auth.vo.UserRegistVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author: Mr.Liang
 * @date: 2021/5/28 15:56
 * @Version: 1.0
 */
@FeignClient("iotmall-member")
public interface MemberFeignService {
    @PostMapping("/member/member/regist")
    R regist(@RequestBody UserRegistVo vo);

    @PostMapping("/member/member/login")
    R login(@RequestBody UserLoginVo vo);
}
