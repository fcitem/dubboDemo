此文件夹下面用于配置绕过注册中心点对点直连方式的配置
此模式主要用于测试调试
需要在提供方启动的时候加上vm启动参数-Dcom.dubbo.provider.service.RemoteService=dubbo://localhost:20889 (jdk1.5以上支持)
同时消费方必须指定dubbo:reference 的id，名称与测试获取时候的名称一样