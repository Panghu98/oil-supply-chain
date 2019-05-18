package group.uchain.oilsupplychain.mapper;

import group.uchain.oilsupplychain.vo.ViewUser;

import java.util.List;

/**
 * @author panghu
 * @title: InfoMapper
 * @projectName oil-supply-chain
 * @date 19-4-4 下午9:22
 */
public interface InfoMapper {

    /**
     * 获取所有的运输公司
     * @return
     */
    List<ViewUser> getAllCompany();

    /**
     * 获取所有的油库,加油站,炼油厂
     * @return
     */
    List<ViewUser> getAllUser();


}
