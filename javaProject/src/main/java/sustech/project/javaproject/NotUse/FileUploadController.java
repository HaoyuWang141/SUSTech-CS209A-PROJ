package sustech.project.javaproject.NotUse;

// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FileUploadController {
  @PostMapping("/upload")
  public String up(String nickname, MultipartFile photo, HttpServletRequest request) throws IOException{
    System.out.println(nickname);
    System.out.println(photo.getOriginalFilename());
    System.out.println(photo.getContentType());
    System.out.println(System.getProperty("user.dir"));

    String path = request.getServletContext().getRealPath("/upload/");
    System.out.println(path);
    saveFile(photo, path);
    return "upload successfully";
  }

  private void saveFile(MultipartFile f, String path) throws IOException{
    File dir = new File(path);
    if(!dir.exists())
      dir.mkdir();
      File file = new File(path+f.getOriginalFilename());
      f.transferTo(file);
  }

}
