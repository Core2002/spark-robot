<h1 align="center">🎆 火花机器人</h1>
<h5 align="center">使用Java 8、Mirai、Gson、Gradle开发的具有简单功能的QQ机器人</h5>

------

## 来 源

本项目自 `2020年9月6日` 启动，最初用来实现自动续火花，故名为 `「火花」`   
后来用作群里做聊机器人，活跃群内气氛  
再后来，加入了截屏功能，帮助班上坐在后面的同学看黑板、保存老师的板书  
高考结束后，本项目退役，决定开源

------

## 功 能

------

### 截图

#### 简述

本功能主要应用场景为教室多媒体平板  
为了应对班上后排同学看不到黑板而开发的功能  
机器人可把电脑屏幕截图发送至群里

#### 例子

```shell
A: 截图
C: [电脑屏幕截图]
A: 截屏
C: [90度旋转的电脑屏幕截图]
```

------

### 问答

#### 简述

词库文件默认为 `Qa.yml`  
键为问，值为答

#### 例子

```shell
A: 问小白帅吗答小白最帅qwq
C: 添加成功
A: 小白帅吗
C: 小白最帅qwq
```

> 若结尾是 `答` 字（即答句为空），则清除该问答

------

### 火花

#### 简述

每天自动续火花（默认关闭） 具体请参阅 `config.yml` 配置文件  
在 `Login` 项中配置要续火花的QQ，然后开启 `AutoMorning` 即可  
然后每天早晨6-10点会给每个人发早安，词库在 `GoodMorning` 中随机挑选一条

------

## 部 署

### 编译

请先准备好 `Java` 环境，并保持良好网络环境

```shell
git clone https://github.com/Core2002/spark-robot
cd spark-robot
chmod +x gradlew
gradlew shadowJar
```

至此，编译完毕，产物为 `spark-robot\build\libs\FiFuSparkBot-all.jar`

### 配置

请填写 `Config.json` 文件

```json
{
  "ownerId": 3513353936,
  "botId": 0,
  "botPassword": "请输入密码"
}
```

参数含义依次为 `机器人所有者的QQ号` 、 `机器人的QQ号` 、 `机器人的QQ密码`

请创建并填写 `config.yml` 文件   
将机器人拉入QQ群  
在 `OpenGroup` 中填写要启用该机器人的群号  
此文件为火花功能文件的配置文件，若不开启此功能，可以照抄例子  
例子：

```yaml
OpenGroup:
  - 1111111
  - 2222222
  - 3333333
  - 0000000
Login:
  - qq: 8888888888
    passwd: 1123
    ing:
      - 111
      - 122
      - 133
  - qq: 2123
    passwd: 6666666666
    ing:
      - 211
      - 222
      - 233
AutoMorning: false
GoodMorning:
  - 早
  - 早鸭
```

请创建 `Qa.yml` 文件  
此文件为机器人问答词库文件  
例子：

```yaml
小白: 最帅

```

此时，目录应有以下文件：

```shell
Mode                 LastWriteTime         Length Name
----                 -------------         ------ ----
-a----         2022/5/21     16:52             77 Config.json
-a----         2022/5/20     17:40            299 config.yml
-a----         2022/5/21     17:13           1275 deviceInfo.json
-a----         2022/5/21     16:52       35128301 FiFuSparkBot-all.jar
-a----         2022/5/20     17:33             16 Qa.yml
```

### 应用

> 注意：  
> 请确保目标设备已安装 `Java`  
> 请确保目标设备java文件打开方式配置正确  
> 请确保程序可在测试环境正常运行  
> 请确保配置文件携带完整  
> 请确保请确保设备文件 `deviceInfo.json` 携带完整

在合适的路径释放 `编译产物`、`配置文件`、`设备文件`  
然后右键 `FiFuSparkBot-all.jar` ，在当前位置创建快捷方式  
`Win + R` 打开运行界面，输入 `shell:startup`  
将快捷方式复制进去  
重启电脑，若机器人上线，既部署完毕  
