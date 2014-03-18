import com.domac.app.common.service.Profiles;
import com.domac.app.jetty.JettyFactory;
import org.eclipse.jetty.server.Server;

import java.awt.*;
import java.net.URI;

/**
 * @author : lihaoquan
 *
 * 运行内嵌Jetty服务器
 *
 */
public class JettyRunner {


    /**
     * 相关启动参数
     */
    private static final int PORT = 9394;//应用端口
    private static final String CONTEXT = "/tm";//上下文名称
    public static final String[] TLD_JAR_NAMES = new String[] { "sitemesh", "spring-webmvc", "shiro-web"};

    public static void main(String[] args) throws Exception {

        Profiles.setProfileAsSystemProperty(Profiles.DEVELOPMENT);

        Server server = JettyFactory.createServer(PORT, CONTEXT);//创建服务
        JettyFactory.setTldNames(server, TLD_JAR_NAMES);//设置JARS...

        try {
            //当控制台中输入回车的时候,应用重启     提交测试

            server.start();
            System.out.println("====application start success!url: http://localhost:" + PORT + CONTEXT);
            System.out.println("====press enter will restart this application!");

            /**
             * Linux 或 Unix 下打开操作系统设置的默认浏览器(建议使用Chrome或Firefox,Safari等)
             */
            Desktop.getDesktop().browse(new URI("http://localhost:" + PORT + CONTEXT));

            while(true) {
                char c = (char) System.in.read();
                if(c=='\n') {
                    JettyFactory.reloadContext(server);
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }
}
