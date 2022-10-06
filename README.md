<h1 align="center"><a href="https://github.com/easy-spring-staging/easy-spring-staging" target="_blank">easy-spring-staging</a></h1>
    easy-spring-staging是一款简化致力于降低研发复杂度，同时大幅度提升研发效率的Spring Framework的脚手架开源项目。

------------------------------

## 介简
    easy-spring-staging采用以代码模板(注：模板可以自定义)方式根据数据库定义通过代码生成插件自动生成工程代码的Spring Framework研发脚手架。
##  使用模式
![avatar](http://m.qpic.cn/psc?/V51ZBlr00lMhVT1pEITA4aBZaC1BLPNL/6tCTPh7N*X6CBkvkDvKlZR5IBwpVZY1uGj5Pa8t7wI8KvT0qiHckm0WtS.XqFc4kGLb5FFBGeX1xJrQNq6A4fYEuncm2iOOqiSBJMjzgnAg!/b&bo=mQNLAQAAAAADB*I!&rf=viewer_4)

##  快速开始
    以mvc-simple-demo模块为例
### 创建工程
#### 下载代码
    git clone https://github.com/easy-spring-staging/easy-spring-staging.git

目录结构  

![avatar](http://a1.qpic.cn/psc?/V51ZBlr00lMhVT1pEITA4aBZaC1BLPNL/6tCTPh7N*X6CBkvkDvKlZcXN.XPFSGOit.rV1XW0x7KYlrzZrzfRG0jaPqPFboICyJrA9ZWY.U.xirnct6gY*EyTKULStj*DFnake*GGEbI!/c&ek=1&kp=1&pt=0&bo=gASIAgAAAAADFzw!&tl=1&vuin=17440478&tm=1664809200&dis_t=1664812660&dis_k=ac7a74b30bbb7b4ecaccea57ce2bb802&sce=60-2-2&rf=viewer_4)

#### Idea打开工程
打开工程

![avatar](http://m.qpic.cn/psc?/V51ZBlr00lMhVT1pEITA4aBZaC1BLPNL/6tCTPh7N*X6CBkvkDvKlZdrAPBTZ8erFewr1mcO9mRVFZh1os.b0mJwVQgxkGJaZFGLz3JB5InqJWo.DCapZ*byhmR6bI*IE2tBe4Ugofas!/b&bo=QQc4BAAAAAADB1g!&rf=viewer_4)
### 安装easy code插件
<a href="https://github.com/makejavas/EasyCode" target="_blank">EasyCode</a>

![avatar](http://m.qpic.cn/psc?/V51ZBlr00lMhVT1pEITA4aBZaC1BLPNL/6tCTPh7N*X6CBkvkDvKlZZW7cJofVS.tCnuK*8Hm03pohla4EscjjWogzhSuSXOUMuZZIZJLfjWg.3Sv9byFYNFQTdpq8uUePBhtuHP9t*0!/b&bo=fAcTBHwHEwQDByI!&rf=viewer_4)
### 导入easy code模板
![avatar](http://m.qpic.cn/psc?/V51ZBlr00lMhVT1pEITA4aBZaC1BLPNL/6tCTPh7N*X6CBkvkDvKlZTx*YdXVhm3CT98lsmgx8vMzCG7NvTCu0O2GZl3NS380zh953tVRTOPJtfSb7Ze4fXoZlsSNQ8LRZUIQJUQyn3s!/b&bo=gAcaBIAHGgQDFzI!&rf=viewer_4)

导入easy-spring-staging\easy-spring-staging-resource\EasyCodeConfig.json:
导入成功后会新增三个个模板：  

    ESS-MVC-PRO(生成工程代码模板)
    ESS-MVC-SI-API(生成简化REST接口的代码模板)
    ESS-MVC-ST-API(生成标准REST接口的代码模板)

### 创建数据库表
使用easy-spring-staging\easy-spring-staging-resource\easy-spring-staging-demo-db.sql  
创建mysql数据库,注意：每个表都需要有且仅有一个主键字段，表和字段都需要注释说明  

### 创建easy code数据库库连接
![avatar](http://m.qpic.cn/psc?/V51ZBlr00lMhVT1pEITA4aBZaC1BLPNL/6tCTPh7N*X6CBkvkDvKlZX1wPG5twXQL8bKNcIAqWShyyCKBajYkJXmtl*XbHagq05RN6UfYx6t8LNTOB5MwO*.D1EQkooAIV1vi3dh.yz8!/b&bo=gAciBAAAAAADB4M!&rf=viewer_4)  
  
  
![avatar](http://m.qpic.cn/psc?/V51ZBlr00lMhVT1pEITA4aBZaC1BLPNL/6tCTPh7N*X6CBkvkDvKlZSz1WWa1FSjj4m1BrhPRSnCwPmUbfN.nRpLawgMM4OnhoYsRyf.C0fZb7KmbumpcNTcEuZkJ1dV5BZN3I45inSI!/b&bo=gAciBAAAAAADF5M!&rf=viewer_4)  
  
  
![avatar](http://m.qpic.cn/psc?/V51ZBlr00lMhVT1pEITA4aBZaC1BLPNL/6tCTPh7N*X6CBkvkDvKlZXVLp04nV8wz3kJyNEsphBVHbZKmnamc65RLDa2*QC3WvNeXeRogOapreWFIMze.yZwJkexK7CjfxGd4UKvmd10!/b&bo=gAciBAAAAAADF5M!&rf=viewer_4)  


### 创建工程代码
1、主工程下创建目录:mvc-simple-demo
![avatar](http://m.qpic.cn/psc?/V51ZBlr00lMhVT1pEITA4aBZaC1BLPNL/6tCTPh7N*X6CBkvkDvKlZT.uvI.fDG76r3SFRcmeYBpi7P*rDn4eEeN3B0T7SLidK1VGq1tgfLMTJyWjuenEanraI5v*U7ILBfTiZvV4WFk!/b&bo=gAciBIAHIgQDByI!&rf=viewer_4)
2、生成工程主体代码
设置EasyCode插件的Global Config：选择ESS-MVC-PRO，修改sitting点击“OK”
![avatar](http://m.qpic.cn/psc?/V51ZBlr00lMhVT1pEITA4aBZaC1BLPNL/6tCTPh7N*X6CBkvkDvKlZW8en4BKHcBZeZRngt8WT2.JlUV5Pny.1muimMsfEM9PzFrT95.KadCtmriCzfq..rd660b73AyQBmBIjJAWDbY!/b&bo=gAcaBIAHGgQDByI!&rf=viewer_4)
鼠标右键点击任意表，选择EasyCode > Generate Code,设置相应配置项，点击“OK”
![avatar](http://m.qpic.cn/psc?/V51ZBlr00lMhVT1pEITA4aBZaC1BLPNL/6tCTPh7N*X6CBkvkDvKlZRxlZxVwtyQEOwvF2REu1hpwtPm2xPHcnkIG0JftSql7K.Q716DiqZ2LpPMksckaP0LQOr9W80YRj8y.g4.lbh8!/b&bo=gAciBIAHIgQDByI!&rf=viewer_4)
![avatar](http://m.qpic.cn/psc?/V51ZBlr00lMhVT1pEITA4aBZaC1BLPNL/6tCTPh7N*X6CBkvkDvKlZSukXgc.vlFVD5N10pfApSOgAj4KhO5t8*82QeAjgVlOR3reN8Tcrc9R83g1u2VC4cqDC4aaJ3*7cFSaRuhDM7o!/b&bo=gAciBIAHIgQDJwI!&rf=viewer_4)
目录结构如下：
![avatar](http://m.qpic.cn/psc?/V51ZBlr00lMhVT1pEITA4aBZaC1BLPNL/6tCTPh7N*X6CBkvkDvKlZcqKzmn3eo2Z2MlP*.SXWBBXSoHuTLJ5L1Tngpkk6Rs7m25aiy3kgt0iv2lYOwycV*DS7b*49Oi0GziVLtc8XJY!/b&bo=gAciBIAHIgQDByI!&rf=viewer_4)
导入新生成模块：
![avatar](http://m.qpic.cn/psc?/V51ZBlr00lMhVT1pEITA4aBZaC1BLPNL/6tCTPh7N*X6CBkvkDvKlZZZQKZKFnXfKpq6wEPBskzbxC98VmRJtb48GgWbzGY.YKZCtfoD3eP9JT7rO.9QHUE0p4elogBA1Y7Qdaqr4J2Q!/b&bo=gAciBIAHIgQDByI!&rf=viewer_4)
3、生成工程REST API代码
创建模块包
![avatar](http://m.qpic.cn/psc?/V51ZBlr00lMhVT1pEITA4aBZaC1BLPNL/6tCTPh7N*X6CBkvkDvKlZbpJvqDRtArwll8lzpw58QQ3DVLTzun7SuQk6l5VQSrjzBZTnZwUXL7CUpF1*G5xrSKA1ciZGmSRqAFNwJBZCwU!/b&bo=gAciBIAHIgQDByI!&rf=viewer_4)
设置EasyCode插件的Global Config：选择ESS-MVC-SI-API，修改sitting点击“OK”
![avatar](http://m.qpic.cn/psc?/V51ZBlr00lMhVT1pEITA4aBZaC1BLPNL/6tCTPh7N*X6CBkvkDvKlZQms4cV7gvsfHG2PGxSHh8UXkCVLCZEGQ4CEB1*tJUKBIaMHMjBZ4wWdbZ2LI5UV7v2*PNE3s3y6tff8ocRZqkg!/b&bo=gAcaBIAHGgQDByI!&rf=viewer_4)
选择要生成代码的所有表点击鼠标右键，选择EasyCode > Generate Code,设置相应配置项，点击“OK”
![avatar](http://m.qpic.cn/psc?/V51ZBlr00lMhVT1pEITA4aBZaC1BLPNL/6tCTPh7N*X6CBkvkDvKlZfKuLr*bCW0NCJ8srjiCK61MetREKzzY*s8poi1mR*fcOqhVXivROQV1vV7pBDyeQplHhgF3oSp5iTsKXOUj.6g!/b&bo=gAciBIAHIgQDByI!&rf=viewer_4)
![avatar](http://m.qpic.cn/psc?/V51ZBlr00lMhVT1pEITA4aBZaC1BLPNL/6tCTPh7N*X6CBkvkDvKlZRDQLEeleEC49ElSkMnGhEcVMr8aG3x.d97e7ti9zJ5PfIHRCca2M.hlQAQRul0fRhdQdymJ.V3Fl33TlMlv9vk!/b&bo=gAciBIAHIgQDFzI!&rf=viewer_4)
目录结构如下：
![avatar](http://m.qpic.cn/psc?/V51ZBlr00lMhVT1pEITA4aBZaC1BLPNL/6tCTPh7N*X6CBkvkDvKlZd0Pmh6u3.uE*Vd5MPdLFeQZRWvcRGjEVWA06akLaMX*BkKWZAgOVkApLbB6gLkhdErzvRpQlASgvSWJefDqdm0!/b&bo=gAciBIAHIgQDFzI!&rf=viewer_4)

4、运行工程
运行com.easyspring.demo.mvc.simple.AppLaunch
![avatar](http://m.qpic.cn/psc?/V51ZBlr00lMhVT1pEITA4aBZaC1BLPNL/6tCTPh7N*X6CBkvkDvKlZaO7ezxGUpHo42HtirMY5Uh.L3m05TZ3FjaAYNoSqPhzxlyBd6pPr3Xj4DCfimBxsg44vEcp4n0f.ox3zoubis4!/b&bo=gAciBIAHIgQDByI!&rf=viewer_4)
swagger文档效果
![avatar](http://m.qpic.cn/psc?/V51ZBlr00lMhVT1pEITA4aBZaC1BLPNL/6tCTPh7N*X6CBkvkDvKlZcoxHpS4OQhdJaB*b9xRhqXOFaKJElc0Oiq0lSbzAAQnKtLsGVrqi*7u9wM5OnYk5CtfbTEZUrW7zrF4sZ7LpKI!/b&bo=cAcQBHAHEAQDFzI!&rf=viewer_4)


