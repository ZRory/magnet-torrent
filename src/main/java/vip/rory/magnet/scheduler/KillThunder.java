package vip.rory.magnet.scheduler;

import java.io.IOException;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author zhanghangtian
 * @since 2019年9月19日 下午5:10:16
 */
@Component
@EnableScheduling
public class KillThunder {

    @Scheduled(fixedDelay = 60000) //上一次执行完毕时间点之后60秒再执行
    public void killThunder() throws IOException {
        System.out.println("Kill Thunder进程");
        Runtime.getRuntime().exec("TASKKILL /IM DownloadSDKServer.exe /F");
        Runtime.getRuntime().exec("TASKKILL /IM Thunder.exe /F");
    }

}
