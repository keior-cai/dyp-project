package com.sise.ccj.service;

import com.sise.ccj.request.admin.AdminRequest;
import com.sise.ccj.vo.BaseVO;

/**
 * @ClassName AdminService
 * @Description
 * @Author CCJ
 * @Date 2020/1/14 0:26
 **/
public interface AdminService {

    BaseVO queryAdmin(AdminRequest param);

    void updateAdmin(AdminRequest param);

    void deleteAdmin(Integer id);

    void addAdmin(AdminRequest param);
}
