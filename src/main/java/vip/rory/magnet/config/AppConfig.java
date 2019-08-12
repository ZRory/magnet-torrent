package vip.rory.magnet.config;

import org.springframework.context.annotation.Configuration;

/**
 * @author zhanghangtian
 * @date 2019年8月12日 下午2:47:29
 */
@Configuration
public class AppConfig {

    public String  magnetRegular      = "^(magnet:\\?xt=urn:btih:|)[0-9a-fA-F]{40}$";

    public String  torrentDir         = "/torrentRepository/";

    public Integer downCheckTimes     = 10;

    public String  thunderProgramPath = "./MagnetEx/MagnetEx.exe";

    public String  thunderTorrentPath = System.getProperty("user.home") + "/AppData/Local/Temp/magnetex/";

}
