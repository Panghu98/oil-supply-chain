package group.uchain.oilsupplychain.exception;

/**
 * @author panghu
 * @Title: OilException
 * @ProjectName oil-supply-chain
 * @Description: TODO
 * @date 19-3-13 下午10:00
 */
public class OilException extends RuntimeException {

    private static final long serialVersionUID = 6894154257254383622L;

    public OilException(String message) {
        super(message);
    }
}
