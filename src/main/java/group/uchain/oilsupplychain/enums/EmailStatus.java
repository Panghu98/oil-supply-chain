package group.uchain.oilsupplychain.enums;

import lombok.Getter;

/**
 * @author panghu
 * @Title: Status
 * @ProjectName oil-supply-chain
 * @Description: TODO
 * @date 19-3-27 下午8:51
 */
@Getter
public enum EmailStatus {

    /***/
    REGISTER_YET("被占用","0"),
    OK("成功","1"),
    NOT_ACTIVE("未激活","2");



    private String tips;

    private String msg;

    EmailStatus(String tips, String msg) {
        this.tips = tips;
        this.msg = msg;
    }}
