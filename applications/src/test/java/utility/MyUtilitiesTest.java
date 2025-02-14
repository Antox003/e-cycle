package carzone.com.utility;

import static org.junit.jupiter.api.Assertions.*;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.OutputStream;
import java.util.ArrayList;
import carzone.com.utility.MyUtilities;

public class MyUtilitiesTest {
    private MyUtilities myUtilities;
    private String destinationPath;
    private ArrayList<String> allowedExtensions;

    @BeforeEach
    void setUp() {
        myUtilities = new MyUtilities();
        destinationPath = "./uploads/";

        // 🔥 Assicurati che la directory "uploads" esista
        File uploadDir = new File(destinationPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs(); // Creazione della cartella se non esiste
        }

        allowedExtensions = new ArrayList<>();
        allowedExtensions.add(".jpg");
        allowedExtensions.add(".png");
    }

    @Test
    void testUploadFile_Success() throws Exception {
        FileItem fileItem = new DiskFileItem("file", "image/jpeg", false, "test.jpg", 1024, new File(destinationPath));
        try (OutputStream os = fileItem.getOutputStream()) {
            os.write(new byte[1024]); // Simulazione file di 1 KB
        }

        String uploadedFileName = myUtilities.UploadFile(fileItem, destinationPath, allowedExtensions);
        assertEquals("test.jpg", uploadedFileName);

        // 🔥 Verifica che il file sia stato effettivamente creato
        File uploadedFile = new File(destinationPath + "test.jpg");
        assertTrue(uploadedFile.exists());

        // Pulizia dopo il test
        uploadedFile.delete();
    }
}
