package group.uchain.oilsupplychain.mapper;

/**
 * @author panghu
 * @Title: UtilMapper.xml
 * @ProjectName oil-supply-chain
 * @date 19-3-27 下午1:17
 */
public interface UtilMapper {


    /**
     *检查邮箱状态
     * @param email 邮箱账号
     * @return  邮箱状态
     */
    String checkEmailStatus(String email);

    /**
     * 更新验证码
     * @param checkCode 验证码
     * @param email  邮箱账号
     */
    void updateCheckCode(String checkCode, String email);

    /**
     * 获取验证码
     * @param email  邮箱账号
     * @return 验证码
     */
    String getCheckCode(String email);

    /**
     * 在数据库中加入邮箱信息
     * 验证码和状态码都拥有默认值
     * 状态码的默认值就为被占用
     * @param email 邮箱账号
     */
    void addCount(String email);

    /**
     * 更新邮箱状态 设置为已经启用
     * @param email 邮箱账号
     */
    void updateEmailStatus(String email);



}
