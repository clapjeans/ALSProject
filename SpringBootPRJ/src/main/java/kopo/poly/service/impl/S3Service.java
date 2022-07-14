package kopo.poly.service.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import kopo.poly.service.IS3Service;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;

@Slf4j
@Service("S3Service")
public class S3Service implements IS3Service {

    @Value("${cloud.aws.s3.bucket}")
    public String bucketName;  // S3 원본 버킷 이름

    @Value("${cloud.aws.s3.sbucket}")
    public String thumbuketName;  // S3 썸네일 버킷 이름

    private final AmazonS3 amazonS3;

    //생성자
    S3Service(AmazonS3 amazonS3) {
        this.amazonS3 = amazonS3;
    }

    // 파일 삭제
    @Override
    public int fileDelete(String fileName,String sfileName)throws IOException  {

        int res = 0;

        amazonS3.deleteObject(bucketName, fileName);
        sfileDelete(sfileName);

        res =1;

        return res;
    }

    //파일업로드
    @Override
    public String upload(MultipartFile multipartFile, String dirName,String ext) throws IOException {
        File uploadFile = convert(multipartFile)
                .orElseThrow(() -> new IllegalArgumentException("MultipartFile -> File로 전환이 실패했습니다."));

        return upload(uploadFile, dirName,ext);
    }

    @Override
    public String thumnailurl(MultipartFile multipartFile, String dirName,String ext) throws IOException {
        File uploadFile = convert(multipartFile)
                .orElseThrow(() -> new IllegalArgumentException("MultipartFile -> File로 전환이 실패했습니다."));


        return thumupload(uploadFile, dirName,ext);
    }

    //썸네일 이미지 삭제
    @Override
    public void sfileDelete(String prev_sfile_name)throws IOException  {
        amazonS3.deleteObject(thumbuketName, prev_sfile_name);
    }


    //파일업로드 게시판에 오릴ㄹ사진
    private String upload(File uploadFile, String dirName,String ext) {
        String fileName = dirName + "/" + uploadFile.getName();

        try {

            BufferedImage originalImage = ImageIO.read(uploadFile);

            BufferedImage thumbnail = Thumbnails.of(originalImage)
                    .forceSize(500, 400)
                    .asBufferedImage();


            ImageIO.write(thumbnail, ext, uploadFile);

        } catch (IOException e) {
            e.printStackTrace();
        }

        String uploadImageUrl = putS3(uploadFile, fileName, bucketName);
        removeNewFile(uploadFile);
        return uploadImageUrl;
    }


    //s3에 파일 업로드
    private String putS3(File uploadFile, String fileName,String bucketName) {
        amazonS3.putObject(new PutObjectRequest(bucketName, fileName, uploadFile).withCannedAcl(CannedAccessControlList.PublicRead));
        return amazonS3.getUrl(bucketName, fileName).toString();
    }

    //로컬파일삭제
    private void removeNewFile(File targetFile) {
        if (targetFile.delete()) {
            log.info("파일이 삭제되었습니다.");
        } else {
            log.info("파일이 삭제되지 못했습니다.");
        }
    }

    //멀티파일 파일로 convert null확인하기위해 Optinal을 사용
    private Optional<File> convert(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        convFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return Optional.of(convFile);
    }


    //썸네일 파일업로드
    private String thumupload(File uploadFile, String dirName, String ext) {

        String fileName = dirName + "/" + "s_" + uploadFile.getName(); //저장할 파일이름


        try {

            BufferedImage originalImage = ImageIO.read(uploadFile); //원래 이미지를 읽음

            BufferedImage thumbnail = Thumbnails.of(originalImage) //썸네일이미지 크기로 변환
                    .forceSize(100, 80)
                    .asBufferedImage();


            ImageIO.write(thumbnail, ext, uploadFile);

        } catch (IOException e) {
            e.printStackTrace();
        }

        String uploadImageUrl = putS3(uploadFile, fileName, thumbuketName); //파일업로드
        removeNewFile(uploadFile); //로컬 파일 삭제
        return uploadImageUrl;


    }


}

