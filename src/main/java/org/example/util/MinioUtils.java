package org.example.util;

import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.errors.MinioException;
import java.io.IOException;
import java.security.GeneralSecurityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

@Component
public class MinioUtils {

	@Autowired
	MinioClient minioClient;

	public void putFile(MultipartFile file, String uuid, String bucket) {
		try {
			minioClient.putObject(PutObjectArgs.builder()
					.bucket(bucket)
					.object(uuid)
					.contentType(file.getContentType())
					.stream(file.getInputStream(), file.getSize(), -1)
					.build());
		} catch (MinioException | GeneralSecurityException | IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void getFile(String uuid, String bucket) {
		try {
			minioClient.getObject(GetObjectArgs.builder()
					.bucket(bucket)
					.object(uuid)
					.build());
		} catch (MinioException | GeneralSecurityException | IOException e) {
			throw new RuntimeException(e);
		}
	}

}
