package vip.rory.magnet.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vip.rory.magnet.exception.BizException;
import vip.rory.magnet.exception.domain.BizBaseResponse;
import vip.rory.magnet.service.MagnetService;
import vip.rory.magnet.util.WebUtils;

/**
 * @author zhanghangtian
 * @date 2019年8月12日 下午3:12:32
 */
@RequestMapping(path = "/magnet")
@RestController
public class MagnetController {

    @Autowired
    private MagnetService magnetService;

    @PostMapping(path = "/torrent")
    public void magnet2Torrent(String magnet, HttpServletRequest request, HttpServletResponse response)
            throws IOException, InterruptedException {
        File torrentFile = magnetService.magnet2Torrent(magnet);
        WebUtils.writeFileToResponse(torrentFile, request, response);
    }

    @PostMapping(path = "/torrentPre")
    public BizBaseResponse magnet2TorrentPre(String magnet) throws IOException, InterruptedException {
        try {
            File torrentFile = magnetService.magnet2Torrent(magnet);
            if (torrentFile.exists()) {
                return BizBaseResponse.success();
            }
        } catch (BizException e) {
            return BizBaseResponse.operationFailed(e.getErrorMessage());
        }
        return BizBaseResponse.systemError();
    }

}
