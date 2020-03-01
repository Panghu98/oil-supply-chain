# 石油供应链  oil-supply-chain
* 基于区块链3.0的fabric的石油信息管理系统
* 区块链的特点
  * 去中心化
  * 去信任化
  * 数据共享
  * 不可篡改
     *  不同于不可修改,指发起一笔交易后不能单方面撤销交易,如果需要撤销之前的交易必须再花几笔交易告诉全网我需要撤销之前的交易.
## 使用的技术栈
* Mybatis+fabric-sdk+springboot+redis

## 主要存在的问题
1.区块链和后端的对接问题
>后端和区块链进度不一致，区块链网络节点对接不上

**解决方案**：使用Docker容器进行本地网络节点搭建，并部署测试链码。保证对接的可用性。

## 页面展示
![登录界面](https://github.com/Panghu98/oil-supply-chain/blob/master/src/main/resources/picture/%E5%9B%BE%E7%89%871.png)

![操作界面](https://github.com/Panghu98/oil-supply-chain/blob/master/src/main/resources/picture/%E5%9B%BE%E7%89%873.png)


