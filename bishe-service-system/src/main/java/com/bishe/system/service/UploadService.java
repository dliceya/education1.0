package com.bishe.system.service;

import com.bishe.framework.domain.system.response.Upload;
import com.bishe.system.dao.IUploadDao;
import com.bishe.system.service.impl.IUpload;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UploadService implements IUpload {

    private final IUploadDao uploadDao;

    public UploadService(IUploadDao uploadDao) {
        this.uploadDao = uploadDao;
    }

    @Override
    public Upload getData(String name) {

        List<String> date;
        List<String> time;

        //查询故障时间且仅截取年份
        date = uploadDao.getData(name);
        for (int i = 0; i < date.size(); i++) {
            date.set(i, date.get(i).substring(0, 4));
        }

        //查询故障时间列表
        time = uploadDao.getTime(name);

        Upload upload = new Upload();
        upload.setDate(date);
        upload.setTime(time);
        return upload;
    }

    @Override
    public List<String> getJudge() {
        return null;
    }
}
