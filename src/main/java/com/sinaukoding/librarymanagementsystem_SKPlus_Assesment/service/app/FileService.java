package com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.service.app;

import com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.model.enums.TipeUpload;
import com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.model.response.BaseResponse;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    BaseResponse<?> upload(MultipartFile files, TipeUpload tipeUpload);

    Resource loadFileAsResource(String pathFile);

}
