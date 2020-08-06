package com.bishe.system.service.impl;

import com.bishe.framework.domain.system.response.Upload;

import java.util.List;

public interface IUpload {

    //获取故障视图数据
    Upload getData(String name);

    //获取
    List<String> getJudge();
}
