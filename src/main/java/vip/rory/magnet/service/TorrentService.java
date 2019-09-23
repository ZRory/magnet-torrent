package vip.rory.magnet.service;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import be.adaxisoft.bencode.BDecoder;
import be.adaxisoft.bencode.BEncodedValue;
import be.adaxisoft.bencode.BEncoder;
import vip.rory.magnet.config.AppConfig;
import vip.rory.magnet.enums.BizErrorCodeEnum;
import vip.rory.magnet.exception.BizException;

/**
 * @author zhanghangtian
 * @date 2019年8月13日 上午9:24:53
 */
@Service
public class TorrentService {

    @Autowired
    private AppConfig appConfig;

    public String torrentToMagnet(MultipartFile torrentFile) throws IOException {
        if (!torrentFile.getOriginalFilename().toLowerCase().endsWith(".torrent")) {
            throw new BizException(BizErrorCodeEnum.TORRENT_FILE_ILLEGAL);
        }
        Map<String, BEncodedValue> infoMap = BDecoder.decode(torrentFile.getInputStream()).getMap().get("info")
                .getMap();
        String hash = DigestUtils.sha1Hex(BEncoder.encode(infoMap).array()).toUpperCase();
        File file = new File(appConfig.torrentDir + hash + ".torrent");
        if (!file.exists()) {
            FileUtils.copyInputStreamToFile(torrentFile.getInputStream(), file);
        }
        return appConfig.magnetHeader + hash;
    }

}
