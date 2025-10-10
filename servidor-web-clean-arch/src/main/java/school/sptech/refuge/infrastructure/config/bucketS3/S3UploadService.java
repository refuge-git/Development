package school.sptech.refuge.infrastructure.config.bucketS3;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;

@Service
public class S3UploadService {

    private final S3Client s3Client;

    public S3UploadService(S3Client s3Client) {
        this.s3Client = s3Client;
    }

    @Value("${aws.s3.bucket-refuge}")
    private String bucketName;

    public String uploadFile(String fileName, byte[] fileContent) {
     //   if (fileContent == null || fileContent.length == 0) {
      //      throw new ResponseStatusException(400, "O arquivo de imagem não pode ser nulo ou vazio", null);
       // }
        String s3Key = fileName;

        try {
            //Cria a requisição para enviar o objeto
            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(s3Key)
                    .build();

            //Envia o array de bytes diretamente usando o RequestBody.fromBytes
            s3Client.putObject(putObjectRequest, RequestBody.fromBytes(fileContent));

            //Retorna a URL do objeto recem criado (opcional)
            String fileUrl = s3Client.utilities().getUrl(builder -> builder.bucket(bucketName).key(s3Key)).toString();
            return fileUrl;
        } catch (S3Exception exception) {
            throw new ResponseStatusException(500, "Error uploading file to S3: " + exception.awsErrorDetails().errorMessage(), exception);
        }

    }

}
