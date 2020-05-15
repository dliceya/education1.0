package com.bishe.framework.domain.ucenter.response;

import com.bishe.framework.domain.ucenter.ext.BsUserExt;
import lombok.Data;

import java.util.List;

@Data
public class QueryPageResponse {
    int total;
    int currentPage;
    List<BsUserExt> userlist;
}
