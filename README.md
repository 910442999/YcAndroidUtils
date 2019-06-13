# YcAndroidUtils
安卓的工具库介绍

此库主要是收集一些开发中用到的相关工具类等

目前常用的有：复制粘贴，设备信息，编码，加密解密，文件，图片，日志，数字相关，照片相关，SP存储，系统状态栏，字符串相关，自定义toast，等

增加glide 圆角 圆形 模糊等图片的加载  数据转换  自定义圆角imageView

    
    使用方法  com.yc:YcAndroidUtils:1.3.5
    1.3.6以上版本 新的下载依赖的方式更改为
     implementation 'com.github.910442999:YcAndroidUtils:1.0.3'
    

    1.3.1 版本

      增加YcGlideCircleTransform  glide转变 圆形带边框
      增加 YcCrashHandler  异常捕获
      优化 YcGlideUtils  , 新增带边框原型图片
      增加 YcImageUtils 工具类中   1, imageZoomInputStream 通过异步 将bitmap压缩后获取流数据 2,imageZoomBitmap     异步压缩图片
      增加 YcJsonReadUtils 读取本地json文件    asset文件 转 json字符串  Raw 文件转 json字符串
      优化 YcLogUtils
      优化 YcToastUtils


   1.3.0版本

        1.优化软件盘工具类

   1.2.8版本:

        1、增加  YcImageUtils 工具类中  clearLruCacheBitmap  方法  清除LruCache缓存

   1.2.7版本:

        1、优化YcResizableImageView 工具类

   1.2.6版本:

        1、增加YcResizableImageView 工具类 根据视图的宽  计算视图的高


   1.2.5版本:

      1.  YcImageUtils 增加以下方法
         * saveLruCacheBitmap   保存LruCache中的Bitmap图片
         * getLruCacheBitmap    获取LruCache中的Bitmap图片
         * removeLruCacheBitmap  移除LruCache中的Bitmap图片

      2. YcUtils 增加以下方法
         * stringFilter     只允许数字和汉字
         * setEditNumberAuto  输入框控件
         * getWebIconUrl  获取连接中的ico图片

   1.2.2版本:

         1.将模糊位图转移到YcImageUtils工具类中
         2.优化YcActivityUtils工具类
         3.增加YcEditTextInputLenLimit工具类  EditText输入限制仅支持中文
         4.修改OnSimpleClickListener监听回调函数


工具类说明:

  #-------------------------------------------------

   Yc工具类 YcUtils

     init   初始化工具类
     getContext 获取 Context
     dip2px   dip转px
     dp2px    dp转px
     px2dip   px转dip
     px2dp    px转dp
     sp2px     sp转px
     px2sp     px转sp
     getRsString      获取string 资源文件
     getRsDrawable    获取 Drawable 资源文件
     getRsColor       获取 Color 资源文件
     delayToDo        延迟任务
     countDown        倒计时
     setEdTwoDecimal  设置Edittext 首位小数点自动加零，最多两位小数
     setEdType        设置光标输入位置
     getSystemLanguage 获取系统语言
     changeAppLanguage 更改App语言
     stringFilter     只允许数字和汉字
     setEditNumberAuto  输入框控件
     getWebIconUrl  获取连接中的ico图片

  #-------------------------------------------------

YcActivityUtils.java

    addActivity                 : 添加Activity 到栈
    currentActivity             : 获取当前的Activity（堆栈中最后一个压入的)
    finishActivity              : 结束当前Activity（堆栈中最后一个压入的）
    finishAllActivity           : 结束所有的Activity
    AppExit                     : 退出当前APP
    getActivityStack            : 获取Activity栈

    单个Activity操作
    isExistActivity             : 判断是否存在指定Activity
    launchActivity              : 打开指定的Activity
    skipActivity                : 跳转到指定Activity
    skipActivityAndFinish       : 跳转到指定Activity并关闭当前Activity
    skipActivityAndFinishAll    : 跳转后Finish之前所有的Activity
    skipActivityForResult       : activityForResult封装
    getLauncherActivity         : 获取launcher activity

#------------------------------------------------

YcBlurBitmapUtils

       blurBitmap  模糊位图

#------------------------------------------------
YcCircularBeadUtils

    roundCrop 图片圆角

#-------------------------------------------------
YcConstUtils

 ****************** 存储相关常量 ********************
      Byte与Byte的倍数
      KB与Byte的倍数
      MB与Byte的倍数
      GB与Byte的倍数
      毫秒与毫秒的倍数

    ****************** 时间相关常量 ********************

      秒与毫秒的倍数
      分与毫秒的倍数
      时与毫秒的倍数
      天与毫秒的倍数
      正则：手机号（简单）
      正则：手机号（精确）
    ****************** 正则相关常量 ********************
      正则：电话号码
      正则：身份证号码15位
      正则：身份证号码18位
      正则：身份证号码15或18位 包含以x结尾
      正则：邮箱
      正则：URL
      正则：汉字
      正则：用户名，取值范围为a-z,A-Z,0-9,"_",汉字，不能以"_"结尾,用户名必须是6-20位
      正则：yyyy-MM-dd格式的日期校验，已考虑平闰年
      正则：IP地址
    ****************** 提取相关常量 ********************
      提取信息中的网络链接:(h|H)(r|R)(e|E)(f|F) *= *('|")?(\w|\\|\/|\.)+('|"| *|>)?
      提取信息中的邮件地址:\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*
      提取信息中的图片链接:(s|S)(r|R)(c|C) *= *('|")?(\w|\\|\/|\.)+('|"| *|>)?
      提取信息中的IP地址:(\d+)\.(\d+)\.(\d+)\.(\d+)
      提取信息中的中国电话号码（包括移动和固定电话）:(\(\d{3,4}\)|\d{3,4}-|\s)?\d{7,14}
      提取信息中的中国邮政编码:[1-9]{1}(\d+){5}
      提取信息中的中国身份证号码:\d{18}|\d{15}
      提取信息中的整数：\d+
      提取信息中的浮点数（即小数）：(-?\d*)\.?\d+
      提取信息中的任何数字 ：(-?\d*)(\.\d+)?
      提取信息中的中文字符串：[\u4e00-\u9fa5]*
      提取信息中的双字节字符串 (汉字)：[^\x00-\xff]*

#-------------------------------------------------
 YcCalculationRelatedUtils

      * addition             加法运算
      * subtraction          减法运算
      * multiplication       乘法运算
      * DivideOperation      除法运算
        DecimalProcessing    提供精确的小数位处理(格式化数字,自定义摄入模式)
#-------------------------------------------------
   数据处理相关  YcDataConversionUtils

        数据的判断
        isNullString                : 判断字符串是否为空 为空即true
        isEmpty                     : 判断对象是否为空 为空即true
        isInteger                   : 判断字符串是否是整数
        isDouble                    : 判断字符串是否是浮点数
        isNumber                    : 判断字符串是否是数字
        getAstro                    : 根据日期判断星座
        <p>
        数据的转换
        stringToInt                 : 字符串转换成整数 ,转换失败将会 return 0;
        stringToLong                : 字符串转换成long ,转换失败将会 return 0;
        stringToDouble              : 字符串转换成double ,转换失败将会 return 0;
        StringToInputStream         : 字符串转InputStream
        stringTwoLines                将字符串从中间对折显示两行
        hideCenterString              隐藏字符串中间的缺省
        upperFirstLetter            : 首字母大写
        lowerFirstLetter            : 首字母小写
        reverse                     : 反转字符串
        toDBC                       : 转化为半角字符
        toSBC                       : 转化为全角字符
        oneCn2ASCII                 : 单个汉字转成ASCII码
        oneCn2PY                    : 单个汉字转成拼音
        getPYFirstLetter            : 获得第一个汉字首字母
        cn2PY                       : 中文转拼音
        bytes2HexString             : byteArr转hexString
        hexString2Bytes             : hexString转byteArr
        hex2Dec                     : hexChar转int
        chars2Bytes                 : charArr转byteArr
        bytes2Chars                 : byteArr转charArr
        byte2Size                   : 字节数转以unit为单位的size
        size2Byte                   : 以unit为单位的size转字节数
        byte2FitSize                : 字节数转合适大小
        input2OutputStream          : inputStream转outputStream
        output2InputStream          : outputStream转inputStream
        inputStream2Bytes           : inputStream转byteArr
        bytes2InputStream           : byteArr转inputStream
        outputStream2Bytes          : outputStream转byteArr
        bytes2OutputStream          : outputStream转byteArr
        inputStream2String          : inputStream转string按编码
        string2InputStream          : string转inputStream按编码
        outputStream2String         : outputStream转string按编码
        string2OutputStream         : string转outputStream按编码
        format2Decimals               将字符串格式化为 (自定义位数 ) 小数的字符串
        stringTwoLines                将字符串从中间对折显示两行
        hideCenterString              隐藏字符串中间的缺省
        format2Decimals                将字符串格式化为带两位小数的字符串 四舍五入
        formatDecimalsRounding       保留字符串自定义的小数位数 , 如果不够自定义的小数位数 则显示原来的数值 四舍五入
        formatDecimals                将字符串格式化为 (自定义位数 ) 小数的字符串  不四舍五入
        formatDecimals                将字符串格式化为 (自定义位数 ) 小数的字符串 自定义是否四舍五入
        formatDecimalsNoRounding       保留字符串自定义的小数位数 , 如果不够自定义的小数位数 则显示原来的数值  不四舍五入
        formatNoRoundingDecimals     将字符串格式化为 (自定义位数 ) 小数的字符串 不四舍五入 不够位数原值返回  够则取舍
        <p>
        formatDecimals (三参)         将字符串格式化为 (自定义位数 ) 小数的字符串  自定义舍入模式
        如果数值为 0.0000000000001   自定义的舍入小数为#0.0000  四位小数  则 直接返回0.0000
        如果数值为0.0001   自定义舍入小数为#0.0000000  七位小数位   则返回 0.0001000
        <p>
        getAmountValue                金额格式化
        getRoundUp                    格式化数值 四舍五入
        getRoundDown                  格式化数值 不四舍五入
        getRoundUpDown                自定义四舍五入
        getPercentValue               获取百分比（乘100）
        baseToSubunit                 将字符串 乘 10 的几次方 (如 10的18次方)
        subunitToBase                 将大整形 除以 10 的 几次方
   #-------------------------------------------------
   设备工具  YcDeviceUtils

        判断设备类型及系统

   #-------------------------------------------------

   编码解码相关工具类  YcEncodeUtils

      urlEncode               : URL编码
      urlDecode                   : URL解码
      base64Encode                : Base64编码
      base64Encode2String         : Base64编码
      base64Decode                : Base64解码
      base64UrlSafeEncode         : Base64URL安全编码
      htmlEncode                  : Html编码
      htmlDecode                  : Html解码
   #-------------------------------------------------

   加密解密相关的工具类  YcEncryptUtils

       <p>
       哈希加密相关
       encryptMD2ToString          : MD2加密
       encryptMD2                  : MD2加密
       encryptMD5ToString          : MD5加密
       encryptMD5                  : MD5加密
       encryptMD5File2String       : MD5加密文件
       encryptMD5File              : MD5加密文件
       encryptSHA1ToString         : SHA1加密
       encryptSHA1                 : SHA1加密
       encryptSHA224ToString       : SHA224加密
       encryptSHA224               : SHA224加密
       encryptSHA256ToString       : SHA256加密
       encryptSHA256               : SHA256加密
       encryptSHA384ToString       : SHA384加密
       encryptSHA384               : SHA384加密
       encryptSHA512ToString       : SHA512加密
       encryptSHA512               : SHA512加密
       encryptAlgorithm            : 对data进行algorithm算法加密
       <p>
       DES加密相关
       DESTemplet                  : DES加密
       encryptDES                  : DES加密
       encryptDES2Base64           : DES加密后转为Base64编码
       encryptDES2HexString        : DES加密后转为16进制
       decryptBase64DES            : DES解密Base64编码密文
       decryptHexStringDES         : DES解密16进制密文
       decryptDES                  : DES解密
       <p>
       3DES加密相关
       encrypt3DES2Base64          : 3DES加密后转为Base64编码
       encrypt3DES2HexString       : 3DES加密后转为16进制
       encrypt3DES                 : 3DES加密
       decryptBase64_3DES          : 3DES解密Base64编码密文
       decryptHexString3DES        : 3DES解密16进制密文
       decrypt3DES                 : 3DES解密
       <p>
       AES加密相关
       encryptAES2Base64           : AES加密后转为Base64编码
       encryptAES2HexString        : AES加密后转为16进制
       encryptAES                  : AES加密
       decryptBase64AES            : AES解密Base64编码密文
       decryptHexStringAES         : AES解密16进制密文
       decryptAES                  : AES解密

   #-------------------------------------------------

   文件相关    YcFileUtils

       SD卡操作
       getRootPath                 : 得到SD卡根目录
       getCecheFolder              : 获取本应用图片缓存目录
       isSDCardEnable              : 判断SD卡是否打开
       getSDCardPath               : 获取SD卡路径
       getDataPath                 : 获取SD卡Data路径
       getFreeSpace                : 获取SD卡剩余空间
       sdCardIsAvailable           : SD卡是否可用

       fileExists                  : 文件或者文件夹是否存在
       delAllFile                  : 删除指定文件夹下所有文件, 不保留文件夹.
       copy                        : 文件复制(文件路径)
       copyFile                    : 复制文件(文件/InputStream流)
       copyFolder                  : 复制整个文件夹内
       renameFile                  : 重命名文件
       getSDCardAvailaleSize       : 获取磁盘可用空间
       getDirSize                  : 获取某个目录可用大小
       getFileAllSize              : 获取文件或者文件夹大小
       initFile                    : 创建一个文件
       initDirectory               : 创建一个文件夹
       saveFile                    : 保存InputStream流到文件
       saveFileUTF8                : 用UTF8保存一个文件
       getFileUTF8                 : 用UTF8读取一个文件
       getFileIntent               : 得到一个文件Intent
       getDiskCacheDir             : 获取缓存目录
       getDiskFileDir              : 获取缓存视频文件目录
       mergeFiles                  : 多个文件拼接合并
       getNativeM3u                : 将在线的m3u8替换成本地的m3u8
       write                       : 将字符串 保存成 文件
       TextToFile                  : 传入文件名以及字符串, 将字符串信息保存到文件中
       GetAllFileName              : 获取 搜索的路径 下的 所有 后缀 的文件
       readFileByLines             : 以行为单位读取文件，常用于读面向行的格式化文件
       getFileByPath               : 根据文件路径获取文件
       isFileExists                : 判断文件是否存在
       isDir                       : 判断是否是目录
       isFile                      : 判断是否是文件
       createOrExistsDir           : 判断目录是否存在，不存在则判断是否创建成功
       createOrExistsFile          : 判断文件是否存在，不存在则判断是否创建成功
       createFileByDeleteOldFile   : 判断文件是否存在，存在则在创建之前删除
       copyOrMoveDir               : 复制或移动目录
       copyOrMoveFile              : 复制或移动文件
       copyDir                     : 复制目录
       copyFile                    : 复制文件
       moveDir                     : 移动目录
       moveFile                    : 移动文件
       deleteDir                   : 删除目录
       deleteFile                  : 删除文件
       listFilesInDir              : 获取目录下所有文件
       listFilesInDirWithFilter    : 获取目录下所有后缀名为suffix的文件
       searchFileInDir             : 获取目录下指定文件名的文件包括子目录
       writeFileFromIS             : 将输入流写入文件
       writeFileFromString         : 将字符串写入文件
       readFile2List               : 指定编码按行读取文件到List
       readFile2String             : 指定编码按行读取文件到字符串中
       readFile2Bytes              : 指定编码按行读取文件到字符数组中
       getFileCharsetSimple        : 简单获取文件编码格式
       getFileLines                : 获取文件行数
       getFileSize                 : 获取文件大小
       getFileMD5                  : 获取文件的MD5校验码
       closeIO                     : 关闭IO
       getDirName                  : 获取全路径中的最长目录
       getFileName                 : 获取全路径中的文件名
       getFileNameNoExtension      : 获取全路径中的不带拓展名的文件名
       getFileExtension            : 获取全路径中的文件拓展名

       清除数据
       cleanInternalCache          : 清除内部缓存
       cleanInternalFiles          : 清除内部文件
       cleanInternalDbs            : 清除内部数据库
       cleanInternalDbByName       : 根据名称清除数据库
       cleanInternalSP             : 清除内部SP
       cleanExternalCache          : 清除外部缓存
       cleanCustomCache            : 清除自定义目录下的文件

   #-------------------------------------------------

   Fragment管理相关    YcFragmentV4Utils 和 YcFragmentV7Utils

      add                   : 新增 fragment
      show                  : 显示 fragment
      hide                  : 隐藏 fragment
      showHide              : 先显示后隐藏 fragment
      replace               : 替换 fragment
      pop                   : 出栈 fragment
      popTo                 : 出栈到指定 fragment
      popAll                : 出栈所有 fragment
      remove                : 移除 fragment
      removeTo              : 移除到指定 fragment
      removeAll             : 移除所有 fragment
      getTop                : 获取顶部 fragment
      getTopInStack         : 获取栈中顶部 fragment
      getTopShow            : 获取顶部可见 fragment
      getTopShowInStack     : 获取栈中顶部可见 fragment
      getFragments          : 获取同级别的 fragment
      getFragmentsInStack   : 获取同级别栈中的 fragment
      getAllFragments       : 获取所有 fragment
      getAllFragmentsInStack: 获取栈中所有 fragment
      findFragment          : 查找 fragment
      dispatchBackPress     : 处理 fragment 回退键
      setBackgroundColor    : 设置背景色
      setBackgroundResource : 设置背景资源
      setBackground         : 设置背景

   #-------------------------------------------------
   加载网络图片相关 YcGlideUtils

      loadImageView       加载网络图片
      LoadingThumbnails   缩略图支持
      loadingGif          加载gif图片
      loadFilletImage     加载网络圆角图片
      loadRoundCornerImage     自定义圆角角度
      loadCircleImage     加载网络圆型图片
      loadLocal           加载本地图片
      loadCrossFade       加载图片带淡入淡出的动画效果
      loadingBlurformation    加载一个图片为高斯模糊效果
      loadingClean            清理磁盘的缓存
      commonRequestOptions 公共的 gilde请求选项
      getImagePath Glide 获得图片缓存路径
      loadCircleBorderTransform 圆形带边框
   #-------------------------------------------------

   图像工具类 YcImageUtils

    <p>
    bitmap2Bytes, bytes2Bitmap      : bitmap 与 byteArr 互转
    drawable2Bitmap, bitmap2Drawable: drawable 与 bitmap 互转
    drawable2Bytes, bytes2Drawable  : drawable 与 byteArr 互转
    view2Bitmap                     : view 转 bitmap
    getBitmap                       : 获取 bitmap
    scale                           : 缩放图片
    clip                            : 裁剪图片
    skew                            : 倾斜图片
    rotate                          : 旋转图片
    getRotateDegree                 : 获取图片旋转角度
    toRound                         : 转为圆形图片
    toRoundCorner                   : 转为圆角图片
    addCornerBorder                 : 添加圆角边框
    addCircleBorder                 : 添加圆形边框
    addReflection                   : 添加倒影
    addTextWatermark                : 添加文字水印
    addImageWatermark               : 添加图片水印
    toAlpha                         : 转为 alpha 位图
    toGray                          : 转为灰度图片
    fastBlur                        : 快速模糊
    renderScriptBlur                : renderScript 模糊图片
    stackBlur                       : stack 模糊图片
    save                            : 保存图片
    isImage                         : 根据文件名判断文件是否为图片
    getImageType                    : 获取图片类型
    compressByScale                 : 按缩放压缩
    compressByQuality               : 按质量压缩
    compressBySampleSize            : 按采样大小压缩
    saveLruCacheBitmap   保存LruCache中的Bitmap图片
    getLruCacheBitmap    获取LruCache中的Bitmap图片
    removeLruCacheBitmap  移除LruCache中的Bitmap图片
    clearLruCacheBitmap   清除LruCache缓存
    imageZoomInputStream 通过异步 将bitmap压缩后获取流数据
    imageZoomBitmap     异步压缩图片
   #-------------------------------------------------

   log工具类  YcLogUtils

   #-------------------------------------------------

   图片工具类    YcPhotoUtils

    openCameraImage             : 调用系统相机
    openLocalImage              : 调用系统相册
    cropImage                   : 裁剪图片
    createImagePathUri          : 创建一条图片地址uri,用于保存拍照后的照片
    getRealFilePath             : 获取图片uri的真实文件地址

   #-------------------------------------------------

   sp缓存 YcSPUtils

     getInstance: 获取 SP 实例
     put        : SP 中写入数据
     getString  : SP 中读取 String
     getInt     : SP 中读取 int
     getLong    : SP 中读取 long
     getFloat   : SP 中读取 float
     getBoolean : SP 中读取 boolean
     getAll     : SP 中获取所有键值对
     getStringSet : SP 中读取set
     getObject : SP 中读取对象
     contains   : SP 中是否存在该 key
     remove     : SP 中移除该 key
     clear      : SP 中清除所有数据

   #-------------------------------------------------

   状态栏: YcStatusBarUtils

      快速实现沉浸式状态栏（支持 4.4 以上版本的 MIUI 和 Flyme，以及 5.0 以上版本的其他 Android）。
      快速设置状态栏为黑色或白色字体图标（支持 4.4 以上版本 MIUI 和 Flyme，以及 6.0 以上版本的其他 Android）。
      提供多个常用的工具方法，如获取状态栏高度、判断当前是否全屏等等。

   #-------------------------------------------------

   字符串相关 YcStringUtils

     isEmpty                判断字符串是否为 null 或长度为 0
     isTrimEmpty            判断字符串是否为 null 或全为空格
     equals                 判断两字符串是否相等
     equalsIgnoreCase       判断两字符串忽略大小写是否相等
     null2Length0           null 转为长度为 0 的字符串
     length                 返回字符串长度
     byte2FitSize           字节数转合适大小
     bytes2HexString        byteArr转hexString
     hexString2Bytes        hexString转byteArr
     hex2Dec                hexChar转int
     ExistOtherChar         判断字符串中是否出现非数字字符，如果出现非数字字符，返回true，否则返回false
     decode                 将十六进制字符串解码成字符串
     hexStr2Str             将16进制字符串转化为10进制字符串
     toStringHex            将16进制字符串转化为10进制字符串
   #-------------------------------------------------

   时间相关 YcTimeUtils

      millis2String           : 将时间戳转为时间字符串
      string2Millis           : 将时间字符串转为时间戳
      string2Date             : 将时间字符串转为 Date 类型
      date2String             : 将 Date 类型转为时间字符串
      date2Millis             : 将 Date 类型转为时间戳
      millis2Date             : 将时间戳转为 Date 类型
      getTimeSpan             : 获取两个时间差（单位：unit）
      getFitTimeSpan          : 获取合适型两个时间差
      getNowMills             : 获取当前毫秒时间戳
      getNowString            : 获取当前时间字符串
      getNowDate              : 获取当前 Date
      getTimeSpanByNow        : 获取与当前时间的差（单位：unit）
      getFitTimeSpanByNow     : 获取合适型与当前时间的差
      getFriendlyTimeSpanByNow: 获取友好型与当前时间的差
      getMillis               : 获取与给定时间等于时间差的时间戳
      getString               : 获取与给定时间等于时间差的时间字符串
      getDate                 : 获取与给定时间等于时间差的 Date
      getMillisByNow          : 获取与当前时间等于时间差的时间戳
      getStringByNow          : 获取与当前时间等于时间差的时间字符串
      getDateByNow            : 获取与当前时间等于时间差的 Date
      isToday                 : 判断是否今天
      isLeapYear              : 判断是否闰年
      getChineseWeek          : 获取中式星期
      getUSWeek               : 获取美式式星期
      getWeekIndex            : 获取星期索引
      getWeekOfMonth          : 获取月份中的第几周
      getWeekOfYear           : 获取年份中的第几周
      getChineseZodiac        : 获取生肖
      getZodiac               : 获取星座

   #-------------------------------------------------
    Intent相关 YcIntentUtils

       <p>
       getInstallAppIntent         : 获取安装App(支持7.0)的意图
       getUninstallAppIntent       : 获取卸载App的意图
       getLaunchAppIntent           : 获取打开App的意图
       getAppInfoIntent            : 获取App信息的意图
       getShareInfoIntent          : 获取App信息分享的意图
       getIntentByPackageName      : 根据包名获取意图
       getComponentNameIntent      : 获取其他应用的Intent
       getAppDetailsSettingsIntent: 获取 App 具体设置的意图
       getShareTextIntent         : 获取分享文本的意图
       getShareImageIntent        : 获取分享图片的意图
       getComponentIntent         : 获取其他应用组件的意图
       getShutdownIntent          : 获取关机的意图
       getCaptureIntent           : 获取拍照的意图
   #-------------------------------------------------

   Shell相关工具类(是否root) YcShellUtils

     <p>
     isRoot                      : 判断设备是否root
     execCmd                     : 是否是在root下执行命令

   #-------------------------------------------------

   进程相关 YcProcessUtils

    getForegroundProcessName    : 获取前台线程包名
    getAllBackgroundProcesses   : 获取后台服务进程
    killAllBackgroundProcesses  : 杀死所有后台服务进程
    killBackgroundProcesses     : 杀死后台服务进程

   #-------------------------------------------------

  应用相关 ->YcAppUtils

    * InstallAPK                  : 安装APK
    * installApp                  : 安装 App（支持 8.0）
    * installAppSilent            : 静默安装App
    * uninstallApp                : 卸载App
    * uninstallAppSilent          : 静默卸载App
    * isAppRoot                   : 判断App是否有root权限
    * launchApp                   : 打开App
    * getAppPackageName           : 获取App包名
    * getAppDetailsSettings       : 获取App具体设置
    * getAppName                  : 获取App名称
    * getAppIcon                  : 获取App图标
    * getAppPath                  : 获取App路径
    * getAppVersionName           : 获取App版本号
    * getAppVersionCode           : 获取App版本码
    * isSystemApp                 : 判断App是否是系统应用
    * isAppDebug                  : 判断App是否是Debug版本
    * getAppSignature             : 获取App签名
    * getAppSignatureSHA1         : 获取应用签名的的SHA1值
    * isInstallApp                : 判断App是否安装
    * getAppInfo                  : 获取当前App信息
    * getBean                     : 得到AppInfo的Bean
    * getAllAppsInfo              : 获取所有已安装App信息
    * isAppBackground             : 判断当前App处于前台还是后台

   #-------------------------------------------------
      事件传递相关   YcRxBus (类似于eventBus)

   #-------------------------------------------------

  Byte相关工具类

    * <p>
    * hexStringToByte      将16进制字符串转化为bytes数组
    * bytesToHexString     将bytes数组转化为16进制字符串
    * toByte               将Short Int类型转化为16进制再转化为byte数组
    * int2Bytes            将INT类型转化为10进制byte数组（占4字节）
    * intToHexToBytes      将int类型转化为16进制数，转化为byte数组类型
    * intToHexToByte       将int类型转化为16进制数，转化为byte类型
    * int2OneByte          将int类型转化为byte类型
    * twoBytes2Int         将2个字节的byte数组转化为int类型
    * oneByte2Int          将byte类型数转化为int类型
    * byteToInt16          将16进制的byte类型转化为10进制的int类型
    * bytes2Int            将byte类型数组（4字节）转化为int类型
    * long2Bytes           将长整形转化为byte数组
    * bytes2Long           将byte数组（长度为8）转化为长整形
    * byte162float         将16进制的byte数组转化为float类型
    * float2ByteArray      将float转化为byte数组，占用4个字节
    * bytes2float          将10进制byte数组转化为Float
    * byteCompare          两个byte数组是否值相等的比较
    * byteToBit            将byte（字节）类型转化为位
    * byteHexString        将指定byte数组以16进制的形式转成字符串

   #-------------------------------------------------
   自定义控件相关

    #YcRoundRectImageView  自定义圆角imageview


   #-------------------------------------------------

   键盘相关 -> YcKeyboardUtils

     /**
      * isSoftInputVisible                : 判断软键盘是否可见
      * toggleSoftInput                   : 切换键盘显示与否状态
      * showSoftInput                     : 显示软键盘
      * hideSoftInput                     : 隐藏软键盘
      */

   #-------------------------------------------------

    EditText 相关 -> YcEditTextInputLenLimit

    isChinese 判断是否是中文
    checkNameChese 检测String是否全是中文
    lengthFilter  仅支持EditText输入中文

   #-------------------------------------------------

        自定义view相关

       1、 YcResizableImageView 根据视图的宽  计算视图的高

       2、YcGlideCircleTransform  glide转变 圆形带边框

   #-------------------------------------------------

        YcCrashHandler  异常捕获

  #-------------------------------------------------

        YcJsonReadUtils 读取本地json文件

        1,asset文件 转 json字符串
        2.Raw 文件转 json字符串
