<h1 align="center"><a href="https://github.com/easy-spring-staging/easy-spring-staging" target="_blank">easy-spring-staging</a></h1>
easy-spring-staging是一款简化致力于降低研发复杂度，同时大幅度提升研发效率的Spring Framework的脚手架开源项目。

------------------------------

## 介简
easy-spring-staging采用以代码模板(注：模板可以自定义)方式根据数据库定义通过代码生成插件自动生成工程代码的Spring Framework研发脚手架。
##  使用方式
![avatar](http://m.qpic.cn/psc?/V51ZBlr00lMhVT1pEITA4aBZaC1BLPNL/6tCTPh7N*X6CBkvkDvKlZQ6Oel6VsOPS1qmkUUDALslat8GPSIUfmyPIe4GiMcs6EipZys2ZVaxvhGfcJLVfWTlD4saIVePsTpheMBbjPyg!/b&bo=AQPIAgEDyAIDByI!&rf=viewer_4)

##  快速开始
以mvc-simple-demo模块为例
### 创建工程
#### 下载代码
![avatar](http://m.qpic.cn/psc?/V51ZBlr00lMhVT1pEITA4aBZaC1BLPNL/6tCTPh7N*X6CBkvkDvKlZaNo7*.f1s0XsX8MkLKxkCZPkHfahlU8nOmPtKbTKWqOkHMwxzoRVqPfxyHQ83q45HcpNrqge27pPE8YnyK541g!/b&bo=tgPWAAAAAAADB0E!&rf=viewer_4)

![avatar](http://m.qpic.cn/psc?/V51ZBlr00lMhVT1pEITA4aBZaC1BLPNL/6tCTPh7N*X6CBkvkDvKlZYrXzh3NBJ2V5TH*6lPYyMf18M*a07R1nXc5QArrhfy0t7iY16MyFBm5zWBEx*tbG35vb*8HXzqxS6sUqLt0YHQ!/b&bo=gQI0AQAAAAADB5Q!&rf=viewer_4)

#### Idea打开工程
打开工程，创建mvc-simple-demo目录

![avatar](http://m.qpic.cn/psc?/V51ZBlr00lMhVT1pEITA4aBZaC1BLPNL/6tCTPh7N*X6CBkvkDvKlZbMnCTw*yQ.WafgbNzTKShJpANHeHChsG9YpYWS1D6*KNYLgSqrf08LSUTpc.lAR*Yp8sK1xAfmqujSJxa2xQos!/b&bo=owP5AaMD.QEDByI!&rf=viewer_4)
### 安装easy code插件

![avatar](http://m.qpic.cn/psc?/V51ZBlr00lMhVT1pEITA4aBZaC1BLPNL/6tCTPh7N*X6CBkvkDvKlZbMnCTw*yQ.WafgbNzTKShJpANHeHChsG9YpYWS1D6*KNYLgSqrf08LSUTpc.lAR*Yp8sK1xAfmqujSJxa2xQos!/b&bo=owP5AaMD.QEDFzI!&rf=viewer_4)
### 导入easy code模板
![avatar](http://m.qpic.cn/psc?/V51ZBlr00lMhVT1pEITA4aBZaC1BLPNL/6tCTPh7N*X6CBkvkDvKlZbsOTxeQ3NKlFCNvuug7Hhb5L0LUr85IUvg0vNOVVjzXeNgiXzVxJDaMAJEtqsv6FejfwnKiBWZ4ezvK6iNAUAY!/b&bo=4QPKAgAAAAADBwg!&rf=viewer_4)

导入easy-spring-staging\easy-code-template\EasyCodeConfig.json:
![avatar](http://m.qpic.cn/psc?/V51ZBlr00lMhVT1pEITA4aBZaC1BLPNL/6tCTPh7N*X6CBkvkDvKlZXK3DNO5ZS7JH40gMZ1JJds382N05cOv*tK4htn77xPPyphBMtLgTFSOO7Bi2UWr*RU*p18IKYKpy1d*z1QT6Ro!/b&bo=3APDAtwDwwIDByI!&rf=viewer_4)
导入成功后会新增两个模板：ess-mvc-simple-create-project-template(生成工程代码模板), ess-mvc-simple-create-code-template(生成REST接口的代码模板)
![avatar](http://m.qpic.cn/psc?/V51ZBlr00lMhVT1pEITA4aBZaC1BLPNL/6tCTPh7N*X6CBkvkDvKlZbabvMCakl4W3HLkXL14fIWF2nD1Hs94u2RonXHBeSjHZ6Z0uSELmoky1THbf*Piu3tw3VXoYIbtjp*3tVIKLmA!/b&bo=5wPHAucDxwIDByI!&rf=viewer_4)

### 创建数据库表
使用easy-spring-staging\resource\easy-spring-staging-demo-db.sql创建mysql数据库
注意：每个表都需要有且仅有一个主键字段，表和字段都需要注释说明
![avatar](http://m.qpic.cn/psc?/V51ZBlr00lMhVT1pEITA4aBZaC1BLPNL/6tCTPh7N*X6CBkvkDvKlZYWskPjV36AFsNsrDyODtl3uZzqNchI*ti.f2LfFX.w*F81o2ZmDiXXhSTbcuk0Ky*6aD*y.oaBhK*.VzVObaus!/b&bo=EASgARAEoAEDByI!&rf=viewer_4)
### 创建easy code数据库库连接
![avatar](http://m.qpic.cn/psc?/V51ZBlr00lMhVT1pEITA4aBZaC1BLPNL/6tCTPh7N*X6CBkvkDvKlZdHbaUS*Iprsr9JLk9JuvkPYUXflqLvFy2ELIU.gMPiFcRocD*JEKU*d.3RK9iDszbkG1klW.kCHXTpuCIB9Nbo!/b&bo=nQVTA50FUwMDByI!&rf=viewer_4)
![avatar](http://m.qpic.cn/psc?/V51ZBlr00lMhVT1pEITA4aBZaC1BLPNL/6tCTPh7N*X6CBkvkDvKlZYpyb7AI3Aj0iEfOiKdg*fLKJUVt6HGgGUlUY8J2fnKJsNCnbuGjd3oICcGwyf672cxANkpv0.KG0KZkD6mLzr0!/b&bo=tgORAbYDkQEDByI!&rf=viewer_4)

### 创建工程代码
配置工程代码Global Config配置项，Group Name选择ess-mvc-simple-create-project-template,根据实际情况配置sitting.vm,点击“OK”
![avatar](http://m.qpic.cn/psc?/V51ZBlr00lMhVT1pEITA4aBZaC1BLPNL/6tCTPh7N*X6CBkvkDvKlZY3QyqCZzXbQ46qDNEH2y.J*Hlc3L4KtOK6b9Gcs9dW8I8Gnu7UQJSNCXgDnh5i3OZ6m7KraHqPdZ70heiN4cLg!/b&bo=cwcGBHMHBgQDByI!&rf=viewer_4)
点击任意表执行代码生成
![avatar](http://m.qpic.cn/psc?/V51ZBlr00lMhVT1pEITA4aBZaC1BLPNL/6tCTPh7N*X6CBkvkDvKlZdtJXUodHrjkcTDmlmJeDbF3zqgYKfudgTu.5LwlXld3iFtyDfJF5SiIh4oaxP2OAu7CMR8TPWSrLwjj6AZ2Dlc!/b&bo=igJlAYoCZQEDFzI!&rf=viewer_4)
设置生成工程代码设置，请注意模板请选择选择ess-mvc-simple-create-project-template
![avatar](http://m.qpic.cn/psc?/V51ZBlr00lMhVT1pEITA4aBZaC1BLPNL/6tCTPh7N*X6CBkvkDvKlZf5rzGZu58FKhStpi8kkiK9GUpbbj3N7CeY1Ai5jWp37Mva1r0ltRwXRWI9iXFGh57tV62EFCAxOjNWFlp08xgU!/b&bo=ZgeuA2YHrgMDJwI!&rf=viewer_4)
生成代码的目录结构如下：
![avatar](http://m.qpic.cn/psc?/V51ZBlr00lMhVT1pEITA4aBZaC1BLPNL/6tCTPh7N*X6CBkvkDvKlZT01gnqCvGxh*Te75zfGwU0f5GOX8mjkoj9Uxnrq9UknhGKQEr4vh6J7NtwtLDcPhJ7DTqoLWn2byYqH0JxGu7s!/b&bo=QgJ.A0ICfgMDFzI!&rf=viewer_4)
新生成工程加入主工程
![avatar](http://m.qpic.cn/psc?/V51ZBlr00lMhVT1pEITA4aBZaC1BLPNL/6tCTPh7N*X6CBkvkDvKlZQZ0Ki4PM21epzyLw24D0QdC3rxzS7An9eaRACV5H8OUQjcmn55waN2ZRZz1xfKtgqCHOni2Kc3cz69G0wpF9qk!/b&bo=UQaPA1EGjwMDJwI!&rf=viewer_4)

### 创建REST接口代码
配置REST接口代码Global Config配置项，Group Name选择ess-mvc-simple-create-code-template,根据实际情况配置sitting.vm,点击“OK”
![avatar](http://m.qpic.cn/psc?/V51ZBlr00lMhVT1pEITA4aBZaC1BLPNL/6tCTPh7N*X6CBkvkDvKlZW44rz29p3WlPCmt1kVfeoU45WH0gQj5EnIrih.wl1YG0a*YPX9lFnMLkvdwAi6wZNDAhj5TjfNU*K2sLG4gaeI!/b&bo=LwcyAy8HMgMDByI!&rf=viewer_4)
配置REST接口代码模板项，Group Name选择ess-mvc-simple-create-code-template,根据实际情况配置sitting.vm,点击“OK”
![avatar](http://m.qpic.cn/psc?/V51ZBlr00lMhVT1pEITA4aBZaC1BLPNL/6tCTPh7N*X6CBkvkDvKlZfMHLmI6zKHdAZDszdMVB0W.8Dcq1OM9vxcYn4ARg8eK48W0BKfjwFThNfN1rSRe4M**FS2wRMwxM9pYifqku34!/b&bo=ZwcNBGcHDQQDFzI!&rf=viewer_4)
配置REST接口代码Type Mapper项，Group Name选择ess-mvc-simple-create-code-template,根据实际情况配置sitting.vm,点击“OK”
![avatar](http://m.qpic.cn/psc?/V51ZBlr00lMhVT1pEITA4aBZaC1BLPNL/6tCTPh7N*X6CBkvkDvKlZSV9y5*GbfOM2piPaXGkvYTrPJhxHFyWZ1ax3ueKXdA4jALiIKVupezp*65g1YRNM52s1aEXdPm36l9PeAwGfoM!/b&bo=aQcQBGkHEAQDFzI!&rf=viewer_4)
创建模块employeeapi模块
![avatar](http://m.qpic.cn/psc?/V51ZBlr00lMhVT1pEITA4aBZaC1BLPNL/6tCTPh7N*X6CBkvkDvKlZerGXi5i*mKCOZUwBs9LanoBMpaia5De9Km1yZPJCSudSVt12CP*CHse7VKb52w4vWyvYuc*bbhrSuXgzNQiYWY!/b&bo=iwLVA4sC1QMDByI!&rf=viewer_4)
创建模块REST接口代码,可以选择一个表或多个表
![avatar](http://m.qpic.cn/psc?/V51ZBlr00lMhVT1pEITA4aBZaC1BLPNL/6tCTPh7N*X6CBkvkDvKlZYPX8FzJ.oWX9.ry7u6YSmZiVBV8YGudBTcJOzHovOdv1HCVqpmU3xT9NABWeD7sRIOx5aC8vUtVfL*dg0si76I!/b&bo=igJlAYoCZQEDFzI!&rf=viewer_4)
![avatar](http://m.qpic.cn/psc?/V51ZBlr00lMhVT1pEITA4aBZaC1BLPNL/6tCTPh7N*X6CBkvkDvKlZY1QubLpJmvkGV2.PMXrtKrpX7E8VWT728DIVxpaS9YzSvDPR8v0mUPOs15d74cy4Ggo.0YugwG4LRvm2ieoib0!/b&bo=dgfyA3YH8gMDJwI!&rf=viewer_4)
![avatar](http://m.qpic.cn/psc?/V51ZBlr00lMhVT1pEITA4aBZaC1BLPNL/6tCTPh7N*X6CBkvkDvKlZVl4BmBEhqrQyY2kGX3oo20PdgEE7.v8415J33AJD.yTfrcM5uRYKYQQ8FOnK9KxPpQRroGCLu656sx23pLB4o0!/b&bo=BwL9AwcC*QMDFzI!&rf=viewer_4)

### 运行REST接口工程
![avatar](http://m.qpic.cn/psc?/V51ZBlr00lMhVT1pEITA4aBZaC1BLPNL/6tCTPh7N*X6CBkvkDvKlZb.MSAxnLgL0QgSECLYGAHb*inrba*89Ih49M5badKd*QtE1ek1zoq5fSh8OaEM6C0fP1zdb4j.b4GR3h3yaEig!/b&bo=gAcaBIAHGgQDJwI!&rf=viewer_4)
![avatar](http://m.qpic.cn/psc?/V51ZBlr00lMhVT1pEITA4aBZaC1BLPNL/6tCTPh7N*X6CBkvkDvKlZTYrtx30CHqYHS5Sa3uK5nMyHP58*GXqShXvQvQXZ9w97P7JIrza9DjZHqExlZqSRoYpXPMtF8XR0n*xsYGCKaM!/b&bo=gAezA4AHswMDFzI!&rf=viewer_4)


