package org.unicat.oss.service.impl;

import cn.hutool.core.date.DateTime;
import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.unicat.commonutils.R;
import org.unicat.oss.service.OssService;
import org.unicat.oss.util.ConstantPropertiesUtil;

import java.io.*;
import java.util.Date;
import java.util.UUID;

@Service
public class OssServiceImpl implements OssService {

    @Override
    public R uploadHeadImg(MultipartFile file) {

        String endpoint = ConstantPropertiesUtil.END_POINT;
        String accessKeyId = ConstantPropertiesUtil.KEY_ID;
        String accessKeySecret = ConstantPropertiesUtil.KEY_SECRET;
        String bucketName = ConstantPropertiesUtil.BUCKET_NAME;
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        String url = null;

        try {
            // 得到传入的文件输入流
            InputStream inputStream = file.getInputStream();

            // 得到文件输入名称
            String filename = file.getOriginalFilename();
            // 多用户可能会传入同一张图片，但文件名不能重复
            // 我们需要对名称进行一些处理
            // 1.添加随机数
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            filename = uuid + filename;
            // 2.根据日期分类
            // jo
            // String dateTime = new DateTime().toString("yyyy/MM/dd");

            // hutool
            String dateTime = new DateTime(new Date()).toString("yyyy/MM/dd");
            filename = "headImg/" + dateTime + "/" + filename;


            // 创建PutObject请求。
            ossClient.putObject(bucketName, filename, inputStream);

            // 把上传之后的路径返回
            // 需要把路径手动拼接后返回
            // 示例：https://unicat.oss-cn-beijing.aliyuncs.com/562c11dfa9ec8a131a5ac56efd03918fa1ecc0f8.jfif
            url = "https://" + bucketName + "." + endpoint + "/" + filename;

        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message:" + oe.getErrorMessage());
            System.out.println("Error Code:" + oe.getErrorCode());
            System.out.println("Request ID:" + oe.getRequestId());
            System.out.println("Host ID:" + oe.getHostId());
            return R.error();
        } catch (ClientException ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message:" + ce.getMessage());
            return R.error();
        } catch (FileNotFoundException fe) {
            System.out.println("Caught an FileNotFoundException, which means the file not found. ");
            System.out.println("Error Message:" + fe.getMessage());
            return R.error();
        } catch (IOException ie) {
            System.out.println("Caught an IOException. ");
            System.out.println("Error Message:" + ie.getMessage());
            return R.error();
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }

        return R.ok()
                .data("url", url);
    }

    @Override
    public R uploadProjectCover(MultipartFile file) {
        String endpoint = ConstantPropertiesUtil.END_POINT;
        String accessKeyId = ConstantPropertiesUtil.KEY_ID;
        String accessKeySecret = ConstantPropertiesUtil.KEY_SECRET;
        String bucketName = ConstantPropertiesUtil.BUCKET_NAME;
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        String url = null;

        try {
            // 得到传入的文件输入流
            InputStream inputStream = file.getInputStream();

            // 得到文件输入名称
            String filename = file.getOriginalFilename();
            // 多用户可能会传入同一张图片，但文件名不能重复
            // 我们需要对名称进行一些处理
            // 1.添加随机数
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            filename = uuid + filename;

            filename = "project/cover/" + filename;


            // 创建PutObject请求。
            ossClient.putObject(bucketName, filename, inputStream);

            // 把上传之后的路径返回
            // 需要把路径手动拼接后返回
            // 示例：https://unicat.oss-cn-beijing.aliyuncs.com/562c11dfa9ec8a131a5ac56efd03918fa1ecc0f8.jfif
            url = "https://" + bucketName + "." + endpoint + "/" + filename;

        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message:" + oe.getErrorMessage());
            System.out.println("Error Code:" + oe.getErrorCode());
            System.out.println("Request ID:" + oe.getRequestId());
            System.out.println("Host ID:" + oe.getHostId());
            return R.error();
        } catch (ClientException ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message:" + ce.getMessage());
            return R.error();
        } catch (FileNotFoundException fe) {
            System.out.println("Caught an FileNotFoundException, which means the file not found. ");
            System.out.println("Error Message:" + fe.getMessage());
            return R.error();
        } catch (IOException ie) {
            System.out.println("Caught an IOException. ");
            System.out.println("Error Message:" + ie.getMessage());
            return R.error();
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }

        return R.ok()
                .data("url", url);
    }

}
