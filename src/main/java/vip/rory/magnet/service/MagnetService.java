package vip.rory.magnet.service;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vip.rory.magnet.config.AppConfig;
import vip.rory.magnet.enums.BizErrorCodeEnum;
import vip.rory.magnet.exception.BizException;

/**
 * @author zhanghangtian
 * @date 2019年8月12日 下午2:22:43
 */
@Service
public class MagnetService {

    @Autowired
    private AppConfig appConfig;

    public File magnet2Torrent(String magnet) throws IOException, InterruptedException {
        if (!magnet.matches(appConfig.magnetRegular)) {
            throw new BizException(BizErrorCodeEnum.MAGNET_ILLEGAL);
        }
        magnet = magnet.replace(appConfig.magnetHeader, "").toUpperCase();
        String magnetFileName = magnet + ".torrent";
        //判断文件是否存在
        File file = new File(appConfig.torrentDir + magnetFileName);
        if (file.exists()) {
            return file;
        }
        //调用迅雷进行下载
        Runtime.getRuntime().exec(appConfig.thunderProgramPath);
        Thread.sleep(2000);
        Runtime.getRuntime().exec(appConfig.thunderProgramPath + " " + appConfig.magnetHeader + magnet);
        File downFile = null;
        for (int i = 0;; i++) {
            Thread.sleep(1 * 1000);
            downFile = new File(appConfig.thunderTorrentPath + magnetFileName);
            if (downFile.exists()) {
                break;
            } else if (i < appConfig.downCheckTimes) {
                continue;
            }
            throw new BizException(BizErrorCodeEnum.MAGNET_DOWNLOAD_TIMEOUT);
        }
        //移动文件到文件库
        FileUtils.copyFile(downFile, file);
        downFile.delete();
        return file;
    }

}
