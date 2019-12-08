package com.wan.system;

import com.wan.library.base.BaseResponse;
import com.wan.system.bean.SystemArticleResult;
import com.wan.system.bean.SystemResult;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface SystemApiService {

    /**
     * 获取体系列表
     *
     * @return
     */
    @GET("tree/json")
    Observable<BaseResponse<List<SystemResult>>> getSystemList();

    /**
     * 获取体系下分级下文章
     *
     * @param cid
     * @return
     */
    @GET("/article/list/{page}/json")
    Observable<BaseResponse<SystemArticleResult>> getSystemArticles(@Path("page") int page,
                                                                    @Query("cid") int cid);

}
