package vip.rory.magnet.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import vip.rory.magnet.exception.BizException;
import vip.rory.magnet.exception.domain.BizBaseResponse;
import vip.rory.magnet.service.TorrentService;

/**
 * @author zhanghangtian
 * @date 2019年8月13日 上午9:46:37
 */
@RequestMapping(path = "/torrent")
@RestController
public class TorrentController {

    @Autowired
    private TorrentService torrentService;

    @PostMapping(path = "/magnet")
    public BizBaseResponse torrentToMagnet(MultipartFile torrentFile) throws IOException {
        try {
            String magnet = torrentService.torrentToMagnet(torrentFile);
            return BizBaseResponse.success("获取成功", magnet);
        } catch (BizException e) {
            return BizBaseResponse.operationFailed(e.getErrorMessage());
        }
    }

}
