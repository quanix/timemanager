2、可插性设计

   2.1、web模块化
        可以将一个项目分成N个模块，然后通过扫描模块下的META-INF/web-fragment.xml进行装配
   2.2、容器启动时可插拔
        使用ServletContainerInitializer实现，可以在容器启动时自动回调其onStartup方法，插入一些功能
   2.3、零XML化SpringMVC
        使用ServletContainerInitializer即SpringMVC注解配置实现无XML化的SpringMVC配置