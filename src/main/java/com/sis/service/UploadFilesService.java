package com.sis.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sis.dto.FileUploadDownLoadModel;
import com.sis.repository.UserFileRepository;
import com.sis.repository.UserRepository;
import com.sis.util.Constants;
import com.sis.util.MessageResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UploadFilesService {

    private final ObjectMapper objectMapper;
    private final RestTemplate restTemplate;

    private final UserFileRepository userFileRepository;

    private final UserRepository userRepository;
    Logger logger = LoggerFactory.getLogger(UploadFilesService.class);
    @Value("${drive.upload.url}")
    private String uploadFileUrl;

    public MessageResponse uploadProfilePicture(String file, String fileName, String email) {
        FileUploadDownLoadModel fileUploadDownLoadModel = constructUploadModel(email, fileName, Constants.PROFILE_PICTURE_FOLDER_NAME, Boolean.TRUE);
        try {
            Long userID = userRepository.findUserByUsername(email).getId();
            MessageResponse messageResponse = uploadToDrive(file, fileUploadDownLoadModel);
            userFileRepository.deletePreviousPictures(Constants.FILE_TYPE_PROFILE_PICTURE, userID);
            fileUploadDownLoadModel.setFileName(messageResponse.getMessage());
            String directories = saveFile(fileUploadDownLoadModel, userID, Constants.FILE_TYPE_PROFILE_PICTURE);
            messageResponse.setMessage(directories);
            return messageResponse;
        } catch (Exception e) {
            logger.error("error uploading file", e);
            return new MessageResponse(500, "error Uploading file", null);
        }
    }

    public void uploadStudents(String file, String fileName, String adminUserName) {
        FileUploadDownLoadModel fileUploadDownLoadModel = constructUploadModel(adminUserName, fileName, Constants.STUDENT_UPLOAD_FOLDER_NAME, Boolean.FALSE);
        try {
            MessageResponse messageResponse = uploadToDrive(file, fileUploadDownLoadModel);
            fileUploadDownLoadModel.setFileName(messageResponse.getMessage());
            saveFile(fileUploadDownLoadModel, userRepository.findUserByUsername(adminUserName).getId(), Constants.FILE_TYPE_STUDENT_UPLOAD);
        } catch (Exception e) {
            logger.error("error uploading file", e);
        }
    }

    public MessageResponse uploadToDrive(String file, FileUploadDownLoadModel fileUploadDownLoadModel) throws IOException {
        String fileUploadModel = objectMapper.writeValueAsString(fileUploadDownLoadModel);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", file);
        body.add("fileUploadModel", fileUploadModel);
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
        String content = restTemplate.exchange(uploadFileUrl, HttpMethod.POST, requestEntity, String.class).getBody();
        return objectMapper.readValue(content, MessageResponse.class);
    }

    public FileUploadDownLoadModel constructUploadModel(String email, String originalFilename, String parentFolderName, Boolean removeOthers) {
        List<String> directories = Arrays.asList(email, parentFolderName);
        return FileUploadDownLoadModel.builder().directories(directories).removeOthers(removeOthers).fileName(originalFilename).build();
    }

    public String saveFile(FileUploadDownLoadModel fileUploadDownLoadModel, Long userID, String fileType) {
        String directories = String.join(Constants.DASH_DELIMITER, fileUploadDownLoadModel.getDirectories()) + Constants.DASH_DELIMITER + fileUploadDownLoadModel.getFileName();
        userFileRepository.saveUserFile(directories, fileUploadDownLoadModel.getFileName(), userID, fileType);
        return directories;
    }

}
