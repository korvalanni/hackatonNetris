import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.File;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collections;

public class VideoDownloader {
    private static Drive service;

    public static void main(String[] args) throws IOException {
        // Инициализируем Drive сервис
        // Подразумевается, что вы уже установили сервис с помощью Google Drive API и OAuth 2.0
        service = getDriveService();

        String fileId = "YOUR_FILE_ID";  // ID файла на Google Drive
        OutputStream outputStream = new FileOutputStream("destination_file_path");  // Путь и название файла, в который будет записано видео

        try {
            downloadFile(fileId, outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Drive getDriveService() throws IOException {
        InputStream in = VideoDownloader.class.getResourceAsStream("/credentials.json");
        GoogleCredential credential = GoogleCredential.fromStream(in)
                .createScoped(Collections.singleton(DriveScopes.DRIVE));
        return new Drive.Builder(new NetHttpTransport(), JacksonFactory.getDefaultInstance(), credential)
                .setApplicationName("Your Application Name")
                .build();
    }

    private static void downloadFile(String fileId, OutputStream outputStream) throws IOException {
        File file = service.files().get(fileId).execute();
        String downloadUrl = file.getWebContentLink();
        if (downloadUrl != null && downloadUrl.length() > 0) {
            HttpResponse resp = service.getRequestFactory().buildGetRequest(new GenericUrl(downloadUrl)).execute();
            resp.download(outputStream);
        }
    }
}
