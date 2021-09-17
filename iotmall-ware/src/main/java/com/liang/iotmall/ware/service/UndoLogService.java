package com.liang.iotmall.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liang.common.utils.PageUtils;
import com.liang.iotmall.ware.entity.UndoLogEntity;

import java.util.Map;

/**
 * @author liangpengfei
 * @email pengf.liang@foxmail.com
 * @date 2021-03-15 12:42:07
 */
public interface UndoLogService extends IService<UndoLogEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

