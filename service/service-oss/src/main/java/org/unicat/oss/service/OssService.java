package org.unicat.oss.service;

import org.springframework.web.multipart.MultipartFile;
import org.unicat.commonutils.R;

public interface OssService {
    R uploadHeadImg(MultipartFile file);

    R uploadProjectCover(MultipartFile file);
}
