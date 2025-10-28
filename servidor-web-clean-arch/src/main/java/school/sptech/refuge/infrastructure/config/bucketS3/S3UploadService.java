package school.sptech.refuge.infrastructure.config.bucketS3;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;

import java.time.Duration;

@Service
public class S3UploadService {

    private final S3Client s3Client;
    private final S3Presigner s3Presigner;

    // Comentado para evitar erro de bean ausente
    public S3UploadService(S3Client s3Client, S3Presigner s3Presigner) {
        this.s3Client = s3Client;
        this.s3Presigner = s3Presigner;
    }

    @Value("${aws.s3.bucket-refuge}")
    private String bucketName;

    public String uploadFile(String fileName, byte[] fileContent) {
        String s3Key = fileName;

        try {
            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(s3Key)
                    .build();

            s3Client.putObject(putObjectRequest, RequestBody.fromBytes(fileContent));

            return fileName;
        } catch (S3Exception exception) {
            throw new ResponseStatusException(500, "Error uploading file to S3: " + exception.awsErrorDetails().errorMessage(), exception);
        }
    }

    public String getFile(String key) {
        System.out.println("Gerando URL para key: " + key);

        /*try {
            GetObjectPresignRequest presignRequest = GetObjectPresignRequest.builder()
                    .getObjectRequest(r -> r.bucket(bucketName).key(key))
                    .signatureDuration(Duration.ofMinutes(10))
                    .build();

            return s3Presigner.presignGetObject(presignRequest).url().toString();
        } catch (S3Exception e) {
            throw new ResponseStatusException(500,
                    "Erro ao gerar URL pré-assinada do S3: " + e.awsErrorDetails().errorMessage(),
                    e);
        }
    }*/
        return "https://bucket-refuge-img-trusted.s3.amazonaws.com/" + key + ".jpg";
    }
}
