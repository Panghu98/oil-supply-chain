package group.uchain.oilsupplychain.utils;

import group.uchain.oilsupplychain.fabric.ChaincodeManager;
import group.uchain.oilsupplychain.fabric.FabricConfig;
import group.uchain.oilsupplychain.fabric.bean.Chaincode;
import group.uchain.oilsupplychain.fabric.bean.Orderers;
import group.uchain.oilsupplychain.fabric.bean.Peers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.Resource;
import java.io.File;
import java.util.Objects;

/**
 * @author panghu
 * @Title: FabricManagerMethod
 * @ProjectName oil-supply-chain
 * @date 19-3-20 下午10:32
 */
@Slf4j
public class FabricManagerMethod {


    @Resource()


    /**
     *     智能合约管理,不止一个对象
     */
    private ChaincodeManager chaincodeManager;

    private static FabricManagerMethod instance = null;

    /**指定channel的名称,从而调用不同的服务
     *
     */
    private final static String channelName = "mychannel";

    private final static String version = "1.0";

    /**
     *
     * 获取一个FabricManager的实例
     */
    public static FabricManagerMethod obtain() throws Exception {
        if (null == instance) {
            synchronized (FabricManagerMethod.class) {
                if (null == instance) {
                    instance = new FabricManagerMethod();
                }
            }
        }
        return instance;
    }

    private FabricManagerMethod()  throws Exception{
        chaincodeManager = new ChaincodeManager(getConfig(1));
    }

    /**
     * 获取节点服务器管理器
     *
     * @return 节点服务器管理器
     */
    public ChaincodeManager getManager(Integer type) {
        if (type==1) {
            return chaincodeManager;
        }
        return null;
    }

    /**
     * 根据节点作用类型获取节点服务器配置
     * 该方法主要进行加载配置文件,(如果区块链相关的配置信息写在配置文件中);
     * 初始化数据库连接信息;
     * 再加载证书和通道相关文件的路径。
     * @param //type 服务器作用类型（1、执行；2、查询）
     * @return 节点服务器配置
     */
    private FabricConfig getConfig(Integer type) {
        FabricConfig config = new FabricConfig();
        config.setOrderers(getOrderers());
        config.setPeers(getPeers());
        if (type==1){
            config.setChaincode(getChainCode(channelName, "mycc2007"
                    , "github.com/hyperledger/fabric/multipeer/chaincode/go", version));
        }
        //加载通道配置文件
        config.setChannelArtifactsPath(getChannelArtifactsPath());
        //加载证书的相关配置文件
        config.setCryptoConfigPath(getCryptoConfigPath());
        return config;
    }

    /**
     *加载排序节点相关配置信息
     * @return
     */
    private Orderers getOrderers() {
        Orderers orderer = new Orderers();
        //设置根域名和排序服务器对象
        orderer.setOrdererDomainName("example.com");
        //设置orderer节点名称和所在地址
        //112.74.190.137
        //39.108.86.64
        orderer.addOrderer("orderer.example.com", "grpc://112.74.190.137:7050");
        return orderer;
    }

    /**
     * 获取节点服务器集
     * 加载节点服务相关配置信息
     * @return 节点服务器集
     */
    private Peers getPeers() {
        Peers peers = new Peers();
        //设置组织名
        peers.setOrgName("Org1");
        //设置组织的MSPID
        peers.setOrgMSPID("Org1MSP");
        //设置根域名
        peers.setOrgDomainName("org1.example.com");
        //节点服务器对象（包含节点名称，节点事件监听名称，节点访问地址，节点事件监听访问地址，CA访问地址）
        peers.addPeer("peer0.org1.example.com", "peer1.org1.example.com",
                "grpc://112.74.190.137:7051", "grpc://112.74.190.137:7051", "http://112.74.190.137:7054");
        return peers;
    }

    /**
     * 获取智能合约
     *  通过getChaincode()方法加载chaincode相关信息,
     *  需要定义Chaincode对象,
     *  （包含归属的channel名称，chaincode名称，安装路径，版本号）,
     *  设置执行智能合约操作等待时间，执行智能合约实例等待时间
     * @param channelName      频道名称
     * @param chaincodeName    智能合约名称
     * @param chaincodePath    智能合约路径
     * @param chaincodeVersion 智能合约版本
     * @return 智能合约
     */
    private Chaincode getChainCode(String channelName, String chaincodeName, String chaincodePath, String chaincodeVersion) {
        Chaincode chaincode = new Chaincode();
        chaincode.setChannelName(channelName);
        chaincode.setChaincodeName(chaincodeName);
        chaincode.setChaincodePath(chaincodePath);
        chaincode.setChaincodeVersion(chaincodeVersion);
        //
        chaincode.setInvokeWaitTime(100000);
        chaincode.setDeployWaitTime(120000);
        return chaincode;
    }

    /**
     * 获取channel-artifacts配置路径
     *
     * @return /WEB-INF/classes/fabric/channel-artifacts/
     */
    private String getChannelArtifactsPath() {
        File directory= this.getFile();
        return directory.getPath() + "/channel-artifacts/";
    }

    /**
     * 获取crypto-config配置路径
     *
     * @return /WEB-INF/classes/fabric/crypto-config/
     */
    private String getCryptoConfigPath() {
        File directory= this.getFile();
        return directory.getPath() + "/crypto-config/";
    }

    private File getFile(){
        String directories = Objects.requireNonNull(FabricManagerMethod.class.getClassLoader().getResource("fabric")).getFile();
        log.debug("directories = " + directories);
        File directory = new File(directories);
        log.debug("directory = " + directory.getPath());
        return directory;
    }

}
