package com.bjsxt.backend.item.service.Impl;

import com.bjsxt.backend.item.service.FileUploadService;
import com.bjsxt.utils.FtpUtil;
import com.bjsxt.utils.IDUtils;
import com.bjsxt.utils.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class FileUploadServiceImpl implements FileUploadService {

    @Value("${FTP_HOST}")
    private String FTP_HOST;

    @Value("${FTP_PORT}")
    private int FTP_PORT;

    @Value("${FTP_USERNAME}")
    private String FTP_USERNAME;

    @Value("${FTP_PASSWORD}")
    private String FTP_PASSWORD;

    @Value("${FTP_BASEPATH}")
    private String FTP_BASEPATH;

    @Override
    public Result fileUpdate(MultipartFile file) {
        try {
            //定义上传图片的目录结构
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("/yyyy/MM/dd");
            String path = simpleDateFormat.format(new Date());

            //设置新的文件名
            String newFileName = IDUtils.genImageName() + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
            FtpUtil.uploadFile(FTP_HOST, FTP_PORT, FTP_USERNAME,FTP_PASSWORD, FTP_BASEPATH, path, newFileName, file.getInputStream());
            String imageURL = "http://" + FTP_HOST + path + newFileName;
            return Result.ok(imageURL);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
