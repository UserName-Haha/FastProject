- 一个帮你快速实现MVVM+组件化的项目
  - [x] MVVM
  - [x] 组件化

- [代码规范](https://github.com/getActivity/AndroidCodeStandard#%E4%BB%A3%E7%A0%81%E8%A7%84%E8%8C%83%E5%8E%9F%E5%88%99)

- 架构为**基础组件、功能组件和业务组件**

- 项目整体依赖关系

  - `App`（空壳，打包生成apk）

    > 业务组件

    - `module-main` (主业务实现模块)

    - `module-platform-login`（三方登录业务模块）

    - `module-crashlytics` （异常上报模块）

    - `Module.....`

      > 功能组件

      - `feature-nmessa`（message功能）

      - `feature-push` （推送功能）

      - `feature-platform-login` （三方登录功能）

        > 基础组件

        - `base-res`  （放置项目通用资源的library）
        - `base-mvvmhabit` （帮助更好的实现MVVM的library）
        - `base-net` （网络基础模块）

- 项目中以`Module-`开头的module一般都是可以独立运行的，通过修改 `gradle.properties` 文件中的 `isBuildModule` 属性即可设置是否独立运行

- `module` 的 `build.gradle` 文件统一应用 `apply from:"../module.build.gradle"` 以达到配置统一

- 新建 `Library` 后需在 `Library` 的 `build.gradle` 中指定混淆文件位置

  ```kotlin
   defaultConfig {
          minSdkVersion rootProject.ext.android.minSdkVersion
          targetSdkVersion rootProject.ext.android.targetSdkVersion
          versionCode rootProject.ext.android.versionCode
          versionName rootProject.ext.android.versionName
          //指定子模块的混淆文件
          consumerProguardFiles 'proguard-rules.pro'
      }

  buildTypes {
          release {
              minifyEnabled true   //开启混淆
              zipAlignEnabled true  //压缩优化
              shrinkResources true  //移出无用资源
              proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro' //默认的混淆文件以及我们指定的混淆文件
          }
      }
  ```



- 新引入三方依赖库时记得在对应的`Library`下的 `proguard-rules.pro` 中添加混淆信息

- 组件化时每个组件单独运行时包名是不一样的，而`Google`登录的所需要配置的`google-services.json`文件是与包名一一对应的，所以为了保证组件单独运行时可以正常使用`Google`登录，每个组件下都有一个`google-services.json`文件，对应的是在`firebase`控制台中有多个项目

- 每个 `Library `中的 `IModuleInit` 实现类记得添加混淆信息保持不被混淆，并在基础 `Library`的 `ModuleLifecycleReflexs` 类中添加路径信息

